<?
require($_SERVER["DOCUMENT_ROOT"]."/bitrix/header.php");


//проверка есть ли LIVEZAKAZ у этого пользователя
$status="";
//$status_data="";
 
global $USER; 
// ПАССАЖИР
$USRpole = CUser::GetList(($by="ID"), ($order="desc"),array("ID"=>$USER->GetID()) ,array("SELECT"=>array("UF_*")));
$data = $USRpole->GetNext(); 
$livez=  $data[UF_LIVEZAKAZ];
$gpspx=$data[UF_LIVEX];
$gpspy=$data[UF_LIVEY];
$p_last_time_update=$data[TIMESTAMP_X]; 
	
if ($livez>0)
{//у клиента есть заказ

$notv=true;
//если этот заказ деактивирован взят диспетчером, то сообщаем
	//получить по номер заказ номер последнего кто его изменил
	
												CModule::IncludeModule("iblock");
												$arFilter = array( 	"ID"=>$livez, "ACTIVE"=>"N",  "IBLOCK_ID"=>5 );
												$arSelect = Array("ID","MODIFIED_BY","PROPERTY_vkorzine_ukogo_id"
												);
												$res = CIBlockElement::GetList(Array("ACTIVE_TO"=>"asc"), $arFilter, false, false, $arSelect);
												while($ob = $res->GetNextElement())
												{
												  $arFields = $ob->GetFields();
												  $voditel_id=$arFields["PROPERTY_VKORZINE_UKOGO_ID_VALUE"];
											if ($arFields[MODIFIED_BY]==798 || $arFields[MODIFIED_BY]==728) 
													{
														$status= "Ваш заказ принят диспетчером, пожалуйста ожидайте."	;
														$notv=false;
														//обнуляем UF_LIVEZAKAZ
																 		$Vuser = new CUser;
																		$fields = Array( 		  "UF_LIVEZAKAZ"          => 0 		  );
																		$Vuser->Update($USER->GetID(), $fields);
														//обнуляем UF_LIVEZAKAZ
														
													}
												}
//если этот заказ деактивирован взят диспетчером, то сообщаем


if ($notv)//// проверяем что водитель делает с заказом
{
		$filter = Array("ACTIVE"=>"Y","GROUPS_ID"=>Array(7,8,9,10),  "UF_LIVEZAKAZ" => $livez); //поиск из всех водителей которые могли взять заказ с номер $livez
		$Voditel = CUser::GetList(($by="ID"), ($order="desc"), 		$filter , 		array("SELECT"=>array("UF_*")));
		$data = $Voditel->GetNext();

		$v_last_time_update=$data[TIMESTAMP_X]; 
		
		$vID=  $data[ID];
		$gpsvx=$data[UF_LIVEX];
		$gpsvy=$data[UF_LIVEY];

		if ($vID==0) { 		
			$status="Ищем водителя, пожалуйста ожидайте ..."."</br></br>"; 
			
			

  			    ///////////////////////////////////////////////////////////////////////////////////////////////если заказ находится в корзине то написать что 		на заказ назначен водитель
												$arFilter = array( 	"ID"=>$livez, "ACTIVE"=>"Y",  "IBLOCK_ID"=>5 );
												$arSelect = Array("ID","PROPERTY_vkorzine_ukogo_id" 												);
												$res = CIBlockElement::GetList(Array("ACTIVE_TO"=>"asc"), $arFilter, false, false, $arSelect);
												while($ob = $res->GetNextElement())
												{
												  $arFields = $ob->GetFields();
												  $voditel_id=$arFields["PROPERTY_VKORZINE_UKOGO_ID_VALUE"];
												}

				if ($voditel_id>0)
				{
					$Select = Array("ID","ACTIVE","PROPERTY_VKORZINE_UKOGO_ID");
					$Filter= Array("ID"=>$livez, "ACTIVE"=>"Y", "IBLOCK_ID"=>5 , "PROPERTY_vkorzine_ukogo_id">= $voditel_id   );
					$r = CIBlockElement::GetList(false, $Filter, false, false, $Select);
					
					$count=0;
					while($o = $r->GetNextElement())
					{
						$f = $o->GetFields();
					
						if ($f[PROPERTY_VKORZINE_UKOGO_ID_VALUE]==$voditel_id)
						{ 
							$count=$count+1;
						}
					
					}
				 
					if ($count>0)
					{
						$status="На заказ назначен водитель</br>Ждите ответа системы"."</br></br>"; 
					}
				}
  			    ///////////////////////////////////////////////////////////////////////////////////////////////если заказ находится в корзине то написать что 		на заказ назначен водитель
				
				
				
			//$status_data="<a href=\"flow_otmena.php\" rel=\"external\">отменить заказ</a>"; 
				

					
			}
		else 		
			{
				$fio=$data[NAME];
				$vUF_AVTOFOTO=  $data[UF_AVTOFOTO];
				$vUF_MYFOTO=  $data[UF_MYFOTO];
				$an=$data[UF_AVTONOMER];
				$an=	preg_replace("/[^0-9]+/ui"," ",$an);

				
				 if ($data[UF_ZAKAZSTATUS]>0)  
				 { 	
							$status="Вас ожидает машина ";
							$status_data=$status_data."</br></br>марки ".$data[UF_AVTOMARKA]."</br>";		
							$status_data=$status_data."цвет ".$data[UF_AVTOCVET]."</br>";		
							$status_data=$status_data."номер ".$an."</br>";		
							$status_data=$status_data."водитель"."</br>";		
							$status_data=$status_data.$fio;		
				 }
				 else
				 {
				
				
				
				
				$status="к Вам выехала машина";		
				
				
				
				
				
							 
 			    $status_data=$status_data."</br></br>".$data[UF_AVTOMARKA];		
				$status_data=$status_data.", водитель ".$fio."</br>";		
				
				 













//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// расчёт времени прибытия

$arFilter = array( 	"ID"=>$livez, "ACTIVE"=>"Y",  "IBLOCK_ID"=>5 );
												$arSelect = Array("ID","MODIFIED_BY","PROPERTY_gpspx","PROPERTY_gpspy",	"PROPERTY_gpsvx","PROPERTY_gpsvy",	"PROPERTY_dist","PROPERTY_dist_time");
												$res = CIBlockElement::GetList(Array("ACTIVE_TO"=>"asc"), $arFilter, false, false, $arSelect);
												while($ob = $res->GetNextElement())
												{
													 
												  $arFields = $ob->GetFields();
												  
												$z_gpspx=$arFields[PROPERTY_GPSPX_VALUE];
												$z_gpspy=$arFields[PROPERTY_GPSPY_VALUE];

												$z_gpsvx=$arFields[PROPERTY_GPSVX_VALUE];
												$z_gpsvy=$arFields[PROPERTY_GPSVY_VALUE];
											  
												$z_dist=$arFields[PROPERTY_DIST_VALUE];
												$z_time=$arFields[PROPERTY_DIST_TIME_VALUE];
												}
												
// формируется когда водитель берет заказ = google_time_distance, start_gps_delta, beru_time,
												 // google_time_distance+beru_time = end_time

//1 live_gps_delta 

	$a=abs($gpspx-$gpsvx);
	$b=abs($gpspy-$gpsvy);

	$live_gps_delta =sqrt( $a*$a+$b*$b);
	$live_gps_delta=$live_gps_delta*10000;
	
//2 start_gps_delta = из инфоблока
	//$a=abs($z_gpspx-$z_gpsvx);
	//$b=abs($z_gpspy-$z_gpsvy);
	
//$start_gps_delta =sqrt( $a*$a+$b*$b);

//3 C = A / B
	//$c=$live_gps_delta/$start_gps_delta;

//4 D = google_time_distance*C = текущее время до окончания проходждения маршрута

///	$z_dist
	//$time_itog=ceil($z_time*$c*60);//5 округляем в большую сторону 
	//$time_itog=$time_itog+5;//прибавляем 5 минут

	$km=$live_gps_delta*1.618;//делаем среднию дистанцию



	$car_speed=50;//60км в час среднее движение авто в городе ПО СЕТКЕ ЧАСОВ
	
	
	
	$time_itog=ceil($km/$car_speed);
	$time_itog=$time_itog+5;
	$ti=$time_itog." мин.";

//6 если нет 2х или текущей GPS то не выводим

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// расчёт времени прибытия
//			if ( strlen( $z_time>0 && $live_gps_delta>0 && $start_gps_delta)  )

//1 время получения gps от водителя и пассажира 1 мин

//$v_last_time_update
//$p_last_time_update


		$v_delta_lt=round((strtotime("now")-strtotime($v_last_time_update))/60,0);
		$p_delta_lt=round((strtotime("now")-strtotime($p_last_time_update))/60,0);
	
			

			if (   $time_itog>0 && $time_itog<120 && $v_delta_lt<=5 && $p_delta_lt<=5)
			{
				$status_data=$status_data."прибытие через"."</br>".$ti; //брать google time при старте		//."vt=".$v_delta_lt."pt=".$p_delta_lt
			}
			else
			{
				$status_data=$status_data."ожидайте сигнал о</br>прибытии";
			}



















				
				 }

			 }
}
//// проверяем что водитель делает с заказом

}//у клиента есть заказ
else
{// у клиента нет заказа
if ($d==1)
	{
	$status_data="Заказ завершен.";//Заказ выполнен, благодарим за поездку.
	}
	else
	{
		//проверяем кто последний (диспетчер или водитель) изменил последний MAX заказ этого клиента
								CModule::IncludeModule("iblock");
								
								//
								$filter = Array("ACTIVE"=>"N","GROUPS_ID"=>Array(6),  "ID"=>$USER->GetID());
									$USRpole = CUser::GetList(
														($by="ID"),
														($order="desc"),
														$filter,
														array("SELECT"=>array("UF_*"))
									);
									
									$data = $USRpole->GetNext(); 
									$HYSMAXZ=  $data[UF_HYSMAXZ];
										 
								//
								
												$arFilter = array( 	"ID"=>$HYSMAXZ, "ACTIVE"=>"N",  "IBLOCK_ID"=>5 );
												$arSelect = Array("MODIFIED_BY");
												$res = CIBlockElement::GetList(Array("ID"=>"desc"), $arFilter, false, false, $arSelect);
												while($ob = $res->GetNextElement())
												{
												  $arFields = $ob->GetFields();
												if ($arFields[MODIFIED_BY]==798 || $arFields[MODIFIED_BY]==728) 
													{
														//$status= "Ваш заказ принят диспетчером, пожалуйста ожидайте."	;
														$status="Заказ завершен.";//Заказ выполнен, благодарим за поездку.
														
													}
													else
													{
														$status="Заказ завершен.";//Заказ выполнен, благодарим за поездку.
													}
												}
								
		//проверяем кто последний (диспетчер или водитель) изменил последний MAX заказ этого клиента
	//$status="Заказ завершен.";//Заказ выполнен, благодарим за поездку.
		//$status_data="Ваш заказ принят диспетчером, пожалуйста ожидайте.";//Заказ выполнен, благодарим за поездку.
	
	}
	

}


 


$st=$status.$status_data; 
$st=str_replace("</br></br>","</br>",$st);
if (strrpos($st,"завер")>0 || strrpos($st,"ожидает")>0)
{} else {
$st=$st."</br></br><a href=\"flow_otmena.php\" rel=\"external\">отменить заказ</a>"; }

if (strrpos($st,"выехала")>0 )
{} else {
$st=str_replace("</br></br>","</br>",$st); }

echo $st;

require($_SERVER["DOCUMENT_ROOT"]."/bitrix/footer.php");
?>


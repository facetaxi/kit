<?
require($_SERVER["DOCUMENT_ROOT"]."/bitrix/header.php");
if ( CSite::InGroup(array(7)) || CSite::InGroup(array(8)) || CSite::InGroup(array(9)) || CSite::InGroup(array(10)) ) {

//проверка активного заказа для тек-ого водителя
							
if ( CSite::InGroup(array(7)) || CSite::InGroup(array(8)) || CSite::InGroup(array(9)) || CSite::InGroup(array(10)) ) //для водителей
	{
		
					global $USER; 
					
if(CModule::IncludeModule("iblock")) // вывод заказов для водителей = каждом/подъезду своё
					{
					
					//определяем какой группе Водителей принадлежит текущий user
					$userTarif=0;
					$arGroups = CUser::GetUserGroup(($USER->GetID()));
						if ($arGroups[0] == 7) $userTarif=7;
						if ($arGroups[0] == 8) $userTarif=8;
						if ($arGroups[0] == 9) $userTarif=9;
						if ($arGroups[0] == 10) $userTarif=10;
						$arSelect = Array("ID","ACTIVE_TO","CREATED_DATE","CREATED","PREVIEW_TEXT", "PROPERTY_tarif_v", "PROPERTY_tarif_h","ID","PROPERTY_uslugi","PROPERTY_userrem","PROPERTY_kondic","PROPERTY_kurevo","PROPERTY_baby","PROPERTY_vkorzine_ukogo_id","PROPERTY_voditel_live");
					
					if ($userTarif==7) // ЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭ
					{
					$arFilter = array(
					    "ID">$m,
						"ACTIVE"=>"Y",
						"IBLOCK_ID"=>5,
						   array(
							"LOGIC" => "OR",
							array("PROPERTY_tarif_h" => "Э"),
										array("PROPERTY_tarif_h" => "x")
						)
					);
					}
					//// ЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭ
					
					
					if ($userTarif==8) // H
					{
					//	echo $userTarif."<br>";
				
					$arFilter = array(
					    "ID">$m,
						"ACTIVE"=>"Y",
						"IBLOCK_ID"=>5,
						   array(
							"LOGIC" => "OR",
							array("PROPERTY_tarif_h" => "Э"),
							array("PROPERTY_tarif_h" => "Н"),
									array("PROPERTY_tarif_h" => "x")
						)
					);
				 
					}
					
					if ($userTarif==9) // K
					{
				 
					$arFilter = array(
					    "ID">$m,
						"ACTIVE"=>"Y",
						"IBLOCK_ID"=>5,
						   array(
							"LOGIC" => "OR",
							array("PROPERTY_tarif_h" => "Э"),
							array("PROPERTY_tarif_h" => "Н"),
							array("PROPERTY_tarif_h" => "К"),
									array("PROPERTY_tarif_h" => "x")
						)
					);
					//
					
					}
					
					if ($userTarif==10) // V
					{
			
					//
					$arFilter = array(
					    "ID">$m,
						"ACTIVE"=>"Y",
						"IBLOCK_ID"=>5,
						   array(
							"LOGIC" => "OR",
							array("PROPERTY_tarif_h" => "Э"),
							array("PROPERTY_tarif_h" => "Н"),
							array("PROPERTY_tarif_h" => "К"),
							array("PROPERTY_tarif_h" => "V"),
									array("PROPERTY_tarif_h" => "x")
						)
						 

					);
					//
					
					}
				  
			 
					$res = CIBlockElement::GetList(Array("ID"=>"desc"), $arFilter, false, false, $arSelect);
$totalhint="";
$ai=-1;


					while($ob = $res->GetNextElement())
					{
					$arFields = $ob->GetFields();
					 
					$busy=true;
 
					
					$vf=strlen($arFields[PROPERTY_VKORZINE_UKOGO_ID_VALUE])+strlen($arFields[PROPERTY_VODITEL_LIVE_VALUE]);
					if ($vf>0) { $busy=false;}
												
					if ($busy) {

	 					if ($arFields[ID]>$m) {
							  if ($arFields[PROPERTY_TARIF_H_VALUE]=="Э") { $a="эконом"; }
							  if ($arFields[PROPERTY_TARIF_H_VALUE]=="Н") { $a="норма"; }
							  if ($arFields[PROPERTY_TARIF_H_VALUE]=="К") { $a="комфорт"; }
							  if ($arFields[PROPERTY_TARIF_H_VALUE]=="V") { $a="VIP"; }
									
							  if ($arFields[PROPERTY_TARIF_V_VALUE]=="S") { $b="км"; }
							  if ($arFields[PROPERTY_TARIF_V_VALUE]=="T") { $b="часы"; }
									
							  if ($arFields[PROPERTY_TARIF_V_VALUE]=="x") { $a="x"; }
							  if ($arFields[PROPERTY_TARIF_V_VALUE]=="x") { $b="x"; }
							
								$ttariff=$a."/".$b;
							 
								$aaddrreess=str_replace($gorod, "",  $arFields[PREVIEW_TEXT]);
							
								//*******************************************************************************************************************************************
								$seconds = strtotime($arFields[ACTIVE_TO])-strtotime("now");
								$asasa=$seconds/60;
								$asasa=round($asasa,0);
								//$aaddrreess="(".$asasa.") мин ".$aaddrreess;
								$aaddrreess="(".$asasa.")".$aaddrreess;
								
								//*******************************************************************************************************************************************
								$livez=$arFields[ID];
								$hint=$livez.";".$ttariff.$aaddrreess;
								//доп услуги сразу
								$dopU=$arFields[PROPERTY_USLUGI_VALUE];
								$hint=$hint."&nbsp;".$dopU;
								$hint=$hint."&nbsp;".$arFields[PROPERTY_USERREM_VALUE];
								//доп услуги сразу
								//опции
								$hint=$hint."&nbsp;".$arFields[PROPERTY_KONDIC_VALUE];
								$hint=$hint."&nbsp;".$arFields[PROPERTY_KUREVO_VALUE];
								$hint=$hint."&nbsp;".$arFields[PROPERTY_BABY_VALUE];
								//опции
								 
								$hint=str_replace("без дополнительных услуг","без доп.у.",$hint);
								$hint=str_replace("Краснодар","(К)",$hint);
								$hint=str_replace("&nbsp;"," ",$hint);
								  
								$hint=strtoupper($hint);
								 $hint=rtrim(ltrim($hint));

			 
			 //////////////////////////////////////////// ПОЛУЧИТЬ ПО ЗАКАЗУ РАССТОЯНИЕ МЕЖДУ ВОДИТЕЛЕМ И ПАССАЖИРОМ
		
		$gps_voditel_time_ok=false;// если от водителя не получен сигнал gps за последние 5 минут то не сортировать
		
		// GPS ВОДИТЕЛЯ
		$filter = Array("ACTIVE"=>"Y",  "ID"=>$USER->GetID()      );  
		$Voditel = CUser::GetList(($by="ID"), ($order="desc"), 		$filter , 		array("SELECT"=>array("UF_*")));
		$data = $Voditel->GetNext();
		$v_last_time_update=$data[TIMESTAMP_X]; 
		$gpsvx=$data[UF_LIVEX];
		$gpsvy=$data[UF_LIVEY];			 
			 
		$v_delta_lt=round((strtotime("now")-strtotime($v_last_time_update))/60,0);	 
		//echo "[".$v_delta_lt."]";
		if ($v_delta_lt<=5)
		{//сигнал получен готовим массив для сортировки
			$gps_voditel_time_ok=true;
			
			//GPS ПАССАЖИРА КОТОРЫЙ СДЕЛАЛ ЗАКАЗ
			$filter = Array("ACTIVE"=>"Y","GROUPS_ID"=>Array(6),  "UF_LIVEZAKAZ" => $livez); 
			$PasagirGps = CUser::GetList(($by="ID"), ($order="desc"), 		$filter , 		array("SELECT"=>array("UF_*")));
			$data = $PasagirGps ->GetNext(); 
			$p_last_time_update=$data[TIMESTAMP_X];
			$gpspx=$data[UF_LIVEX];
			$gpspy=$data[UF_LIVEY];
						
			$p_delta_lt=round((strtotime("now")-strtotime($v_last_time_update))/60,0);	 
											
			$a=abs($gpspx-$gpsvx);
			$b=abs($gpspy-$gpsvy);
		
			$live_gps_delta =sqrt( $a*$a+$b*$b);
			$live_gps_delta=$live_gps_delta*100*1.618;			

			//добавляем в массив
			$ai=$ai+1;
			 
			$fa[$ai][0]=$live_gps_delta;
			if ($live_gps_delta<1000)
			{
				$fa[$ai][1]=$hint."[".round($live_gps_delta,1)."км]</br>";
			}
			else
			{
//				$fa[$ai][1]=$hint."[gps отключен]</br>";
				$fa[$ai][1]=$hint."</br>";
			}

		}//сигнал получен готовим массив для сортировки
			 

 			 //////////////////////////////////////////// ПОЛУЧИТЬ ПО ЗАКАЗУ РАССТОЯНИЕ МЕЖДУ ВОДИТЕЛЕМ И ПАССАЖИРОМ
		 
			 			
					  $totalhint=$totalhint.$hint."</br>";								
					//			 echo $hint."</br>";

	    				}//		if ($arFields[ID]>$m) {

							
					}//$busy
					}//while


		//////////////////////////сортировать массив по расстояниям, времени
if ($gps_voditel_time_ok)
{//сортировать и выводить результат
	sort($fa);
	for ($q=0; $q<=$ai; $q++) 
	{
//		echo $fa[$q][0]."<".$fa[$q][1]."><br>";
		echo $fa[$q][1];
	}
}//сортировать и выводить результат
else
{//не сортировать выводить
	echo $totalhint; // если от водителя не получен сигнал gps за последние 5 минут то не сортировать
}//не сортировать выводить
		//////////////////////////сортировать массив по расстояниям, времени
							
					}//if CMODULE
					
}
 			
//if ($fnoNew==1)
//{
	//echo "1</br>";
//}
					
	}
require($_SERVER["DOCUMENT_ROOT"]."/bitrix/footer.php");
?>
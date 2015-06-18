<?
require($_SERVER["DOCUMENT_ROOT"]."/bitrix/header.php");


//проверка активного заказа для тек-ого водителя
							
if ( CSite::InGroup(array(7)) || CSite::InGroup(array(8)) || CSite::InGroup(array(9)) || CSite::InGroup(array(10)) ) //для водителей
	{
		
					global $USER; 
					
		   //откуда фильтр    
					
if(CModule::IncludeModule("iblock")) // вывод заказов для водителей = каждом/подъезду своё
					{
					
					
					//определяем какой группе Водителей принадлежит текущий user
					$userTarif=0;
					$arGroups = CUser::GetUserGroup(($USER->GetID()));
						if ($arGroups[0] == 7) $userTarif=7;
						if ($arGroups[0] == 8) $userTarif=8;
						if ($arGroups[0] == 9) $userTarif=9;
						if ($arGroups[0] == 10) $userTarif=10;
					
						
						$arSelect = Array("ACTIVE_TO","CREATED_DATE","CREATED","PREVIEW_TEXT", "PROPERTY_tarif_v", "PROPERTY_tarif_h","ID","PROPERTY_uslugi","PROPERTY_userrem");
					
					
					
					if ($userTarif==7) // ЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭ
					{
					//$arFilter = Array("ACTIVE"=>"Y","IBLOCK_ID"=>5,"PROPERTY_tarif_h"=>"Э"   );
					$arFilter = array(
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
				 
					
					///************************************************************ ДЕАКТИВТАТОР
					$DarFilter = array(   "ACTIVE"=>"Y",   "IBLOCK_ID"=>5    );
					
					 
					$Dres = CIBlockElement::GetList(Array("ACTIVE_TO"=>"asc"), $DarFilter, false, Array(), $arSelect);
					
					while($Dob = $Dres->GetNextElement())
					{
					  $DarFields = $Dob->GetFields();
					  $Dseconds = strtotime($DarFields[ACTIVE_TO])-strtotime("now");
					  if ($Dseconds<(-15*60) ) 
							{
									//deactivate		//деактивируем заказ в системе
										CModule::IncludeModule("iblock");
										$Delel = new CIBlockElement; 
										$ddd = Array(
										  "MODIFIED_BY"    => 690, // элемент изменен deactivator пользователем
										  "ACTIVE"         => "N"            // активен деактивируем заказ в системе
										  );
										$Delel = $Delel->Update($DarFields["ID"], $ddd);
						//deactivate		//деактивируем заказ в системе
							}
					}
					
					///************************************************************ ДЕАКТИВТАТОР
					
			 
					$res = CIBlockElement::GetList(Array("ACTIVE_TO"=>"asc"), $arFilter, false, Array("nPageSize"=>3), $arSelect);
				 	
					$nv= $res->NavPrint(GetMessage("PAGES")); // печатаем постраничную навигацию
					echo $nv;
				 
					while($ob = $res->GetNextElement())
					{
					  $arFields = $ob->GetFields();
					
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
					$aaddrreess="(".$asasa.") мин ".$aaddrreess;
					//*******************************************************************************************************************************************
					

					$$korzinaON=15;
					$hint="";
					$rsUser = CUser::GetByID(51);
		
		 			 	$arUser = $rsUser->Fetch();
					 				
			

						 
										$hint=$hint."<tr>";
										$hint=$hint."<td align=\"left\">".$ttariff."&nbsp;".$aaddrreess."</td>";
										$hint=$hint."</tr>";
										$hint=$hint."<tr>";

									
									//доп услуги сразу
									$dopU=$arFields[PROPERTY_USLUGI_VALUE];
																			$hint=$hint."<tr>";
										$hint=$hint."<td align=\"left\">".$dopU."</td>";
										$hint=$hint."</tr>";
									//доп услуги сразу
									
									//доп услуги сразу
										$hint=$hint."<tr>";
										$hint=$hint."<td align=\"left\">".$arFields[PROPERTY_USERREM_VALUE]."</td>";
										$hint=$hint."</tr>";
									//доп услуги сразу
									
									
										$hint=$hint."<td align=\"left\">";
									
							 
						$hint=$hint."</td>";
						
						$hint=$hint."</tr>";
					 
					 $hint=str_replace("без дополнительных услуг","без доп.у.",$hint);
					  $hint=str_replace("Краснодар","(К)",$hint);
					  $hint=str_replace("&nbsp;"," ",$hint);
					  
					 $hint=strtoupper($hint);
					   $hint=str_replace("BERU.PHP?Z","beru.php?z",$hint);

					if ($seconds>=(-15*60) ) //60
					{
						echo $hint."</br>";
					}
						
					}//while
					
					}//if CMODULE
					

					
}
 			

					

require($_SERVER["DOCUMENT_ROOT"]."/bitrix/footer.php");
?>

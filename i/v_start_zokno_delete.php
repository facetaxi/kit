<?
require($_SERVER["DOCUMENT_ROOT"]."/bitrix/header.php");
if ( CSite::InGroup(array(7)) || CSite::InGroup(array(8)) || CSite::InGroup(array(9)) || CSite::InGroup(array(10)) ) {

//выбираем НЕАКТИВНЫЕ от MinIdZakaz ПЛЮС по тарифу МИНУС VODITEL_ZOKNO

//отдаем водителю и делаем запись в VODITEL_ZOKNO , ЧТО Заказы Удалены из Окна Водителя
//запись создана = номер водителя
//название = номер заказа

//выбираем от MinIdZakaz + по тарифу 
$deltatime=-1500*60;


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
						$arSelect = Array("ID","ACTIVE_TO","CREATED_DATE","CREATED","PREVIEW_TEXT", "PROPERTY_tarif_v", "PROPERTY_tarif_h","ID","PROPERTY_uslugi","PROPERTY_userrem");
					
					if ($userTarif==7) // ЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭЭ
					{
					$arFilter = array(
					    "ID">$m,
						"ACTIVE"=>"N",
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
						"ACTIVE"=>"N",
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
						"ACTIVE"=>"N",
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
						"ACTIVE"=>"N",
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
					  if ($Dseconds<($deltatime) ) 
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
					
			 
					$res = CIBlockElement::GetList(Array("ID"=>"desc"), $arFilter, false, false, $arSelect);

					while($ob = $res->GetNextElement())
					{
					  $arFields = $ob->GetFields();
	 					if ($arFields[ID]>$m) {
		
								$hint=$arFields[ID].";";
			 
								if ($seconds>=($deltatime) ) { echo $hint; }
	    				}//		if ($arFields[ID]>$m) {
					}//while
					
					}//if CMODULE
					
}
 			
					
}
require($_SERVER["DOCUMENT_ROOT"]."/bitrix/footer.php");
?>
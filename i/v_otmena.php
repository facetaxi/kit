<?
require($_SERVER["DOCUMENT_ROOT"]."/bitrix/header.php");
if ( CSite::InGroup(array(7)) || CSite::InGroup(array(8)) || CSite::InGroup(array(9)) || CSite::InGroup(array(10)) ) {

 CModule::IncludeModule("iblock");
  $err=0;
try {
 
 
CIBlockElement::SetPropertyValues($z, 5, Array("VALUE"=>"" ) ,"voditel_live"        ); 
CIBlockElement::SetPropertyValues($z, 5, Array("VALUE"=>"" ) ,"vkorzine_ukogo_id"        ); 
	
///////////////////////////////////////////////////////////////////////////////////////////////////  
$user = new CUser;
		$fields = Array(  "UF_ZAKAZSTATUS"          =>""  );
$user->Update($USER->GetID(), $fields);
$strError .= $user->LAST_ERROR;
///////////////////////////////////////////////////////////////////////////////////////////////////  

// обнуляем заказ
$user = new CUser;
$fields = Array(
  "UF_LIVEZAKAZ"          => ""
  );
$user->Update($USER->GetID(), $fields);
$strError = $user->LAST_ERROR;

// обнуляем заказ

//определяем тариф заказа
$arSelect = Array("PROPERTY_tarif_h");
$arFilter = Array("ID"=>$z, "IBLOCK_ID"=>5);
$res = CIBlockElement::GetList(Array(), $arFilter, false, false , $arSelect);

$ob = $res->GetNextElement();
$arFields = $ob->GetFields();

if ($arFields[PROPERTY_TARIF_H_VALUE]=="Э") { $tth = 7; }
if ($arFields[PROPERTY_TARIF_H_VALUE]=="Н") { $tth = 8; }
if ($arFields[PROPERTY_TARIF_H_VALUE]=="К") { $tth = 9; }
if ($arFields[PROPERTY_TARIF_H_VALUE]=="V") { $tth = 10; }
//определяем тариф заказа

if ($tth==7) { $snimaemPoTarifu=-15;} 
if ($tth==8) { $snimaemPoTarifu=-20;} 
if ($tth==9) { $snimaemPoTarifu=-25;} 
if ($tth==10) { $snimaemPoTarifu=-30;} 
 
if ($vodrem==1)//ошибка снимаем со счёта 25 руб
{
	if (CModule::IncludeModule("sale"))
	   {
		   
		if (!CSaleUserAccount::UpdateAccount(
				$USER->GetID(),
				$snimaemPoTarifu,
				"RUB",
				"ошибка",
				$z
			)
			);

	} 
	
	 
	
	}
//////////

if ($vodrem==2)  //дтп (нужен документ) снимаем со счёта 0 руб
		{
		if (CModule::IncludeModule("sale"))
		   {
			if (!CSaleUserAccount::UpdateAccount(
					$USER->GetID(),
					0,
					"RUB",
					"дтп (нужен документ)",
					$z
				)
				);
		   }
		   
		 
		   
		   }

if ($vodrem==3)  //неисправность (нужен документ) снимаем со счёта 0 руб
		{
		if (CModule::IncludeModule("sale"))
		   {
		   
			if (!CSaleUserAccount::UpdateAccount(
					$USER->GetID(),
					0,
					"RUB",
					"неисправность (нужен документ)",
					$z
				)
				);
		}
		
		 
		
		}
 

if ($vodrem==4)// пассажир не вышел
{

	if (CModule::IncludeModule("sale"))
	{
		   
		if (!CSaleUserAccount::UpdateAccount(
				$USER->GetID(),
				0,
				"RUB",
				"пассажир не вышел",
				$z
			)
			);

	}
//снимаем заказ с биржки
				 ///////////////////////////////////////////2 = отмена у клиента
					$filter = Array("ACTIVE"=>"Y","GROUPS_ID"=>Array(6),  "UF_LIVEZAKAZ" => $z);
					//поиск из всех водителей которые могли взять заказ с номер $livez
					$Voditel = CUser::GetList(($by="ID"), ($order="desc"), 		$filter , 		array("SELECT"=>array("UF_*")));
					//поиск из всех водителей которые могли взять заказ с номер $livez
					$data = $Voditel->GetNext(); 
					$vID=  $data[ID];
					if ($vID>0) { 
									$user = new CUser;
									$fields = Array(  "UF_LIVEZAKAZ" => "" );
									$user->Update($vID, $fields);
									$strError .= $user->LAST_ERROR;
								}
				///////////////////////////////////////////2 = отмена у клиента
				
                //деактивируем заказ в системе
				
				 
				$el = new CIBlockElement;
				$arLoadProductArray = Array(
				  "MODIFIED_BY"    => $USER->GetID(), // элемент изменен текущим пользователем
				  "ACTIVE"         => "N",            // активен деактивируем заказ в системе
			
				  );
				$res = $el->Update($z, $arLoadProductArray);
				//деактивируем заказ в системе
				
//снимаем заказ с биржки
	 
}	
// пассажир не вышел


} 
catch (Exception $e) {
  $err=1;  
}


if ($err==0) 
{ echo "8";  } 
else
{ echo "6";  } 


}
require($_SERVER["DOCUMENT_ROOT"]."/bitrix/footer.php");
?>


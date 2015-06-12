<?
require($_SERVER["DOCUMENT_ROOT"]."/bitrix/header.php");
if ( CSite::InGroup(array(7)) || CSite::InGroup(array(8)) || CSite::InGroup(array(9)) || CSite::InGroup(array(10)) ) {

global $USER;

CModule::IncludeModule("iblock");
 
  $err=0;
try {
///////////////////////////////////////////////////////////////////////////////////////////////////
$user = new CUser;
		$fields = Array(  "UF_ZAKAZSTATUS"          =>""  );
$user->Update($USER->GetID(), $fields);
$strError .= $user->LAST_ERROR;
//////////////////////////////////////////////////////////////////////////////////////////////////


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
	


//У ВОДИТЕЛЯ ОБНУЛЯЕМ UF_LIVEZAKAZ
$user = new CUser;
$fields = Array(
  "UF_LIVEZAKAZ"          => ""
  );
$user->Update($USER->GetID(), $fields);
$strError .= $user->LAST_ERROR;
//У ВОДИТЕЛЯ ОБНУЛЯЕМ UF_LIVEZAKAZ



//снимаем со счёта 25 руб

// Внесем (снимем) деньги на счет
if (CModule::IncludeModule("sale"))
   {

// в зависимости от ТИПА ЗАКАЗА СНИМАЕМ СУММУ
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
//!!!////////////////////////////////////// понимать тип заказ исходя их его номера 
if ($tth==7) { $snimaemPoTarifu=-15;} 
if ($tth==8) { $snimaemPoTarifu=-20;} 
if ($tth==9) { $snimaemPoTarifu=-25;} 
if ($tth==10) { $snimaemPoTarifu=-30;} 

if ($tth==11) { //x/x
	//то определяем какой группе принадлежит водитель и снимаем по тарифу
	$userTarif=0;
	$arGroups = CUser::GetUserGroup(($USER->GetID()));
		if ($arGroups[0] == 7) $userTarif=7;
		if ($arGroups[0] == 8) $userTarif=8;
		if ($arGroups[0] == 9) $userTarif=9;
		if ($arGroups[0] == 10) $userTarif=10;
			if ($userTarif==7) { $snimaemPoTarifu=-15;} 
			if ($userTarif==8) { $snimaemPoTarifu=-20;} 
			if ($userTarif==9) { $snimaemPoTarifu=-25;} 
			if ($userTarif==10) { $snimaemPoTarifu=-30;} 
	//то определяем какой группе принадлежит водитель и снимаем по тарифу

} 

//межгород проверка
$negorod=false;
if(CModule::IncludeModule("iblock")) // вывод заказов для водителей = каждому своё
{
$arFilter = array(    "IBLOCK_ID"=>5, "ID" => $z);
$res = CIBlockElement::GetList(Array("CREATED_DATE"=>"desc", "CREATED"=>"desc"), $arFilter, false, Array("nPageSize"=>6), $arSelect);
while($ob = $res->GetNextElement())
	{
	  $arFields = $ob->GetFields();
	 $aaa= $arFields[DETAIL_TEXT];
	 if (strpos($aaa,"Межгород",0))
	 {
	 $negorod=true;
            $snimaemPoTarifu=0;//???
	 }
	 
	}
}//межгород проверка

//
 
    if (!CSaleUserAccount::UpdateAccount(
            $USER->GetID(),
            $snimaemPoTarifu,
            "RUB",
            "заказ выполнен",
            $z
        )
		);
 

//////////////////	//снимаем со счет если заказ был ПРИГОРОД/МЕЖГОРОД
//проверяем что межгород $z
//проверяем что межгород
if ($negorod) {
	$snimaemPoCenuZakaza=-100;
	//вытаскиваем цену заказа
	if (CModule::IncludeModule("sale"))
	{
	
	//    $snimaemPoCenuZakaza = CPrice::GetBasePrice($z);
	//	    $snimaemPoCenuZakaza = -300;
	//вытаскиваем цену заказа
		if (!CSaleUserAccount::UpdateAccount(
				$USER->GetID(),
				$snimaemPoCenuZakaza,
				"RUB",
				"заказ выполнен за МЕЖГОРОД",
				$z
			)
			);
			
	}//if (CModule::IncludeModule("sale"))
   
}//////////////////	//снимаем со счет если заказ был ПРИГОРОД/МЕЖГОРОД	
		
//

}
//снимаем со счёта  

//деактивируем заказ в системе
	$el = new CIBlockElement;
	$arLoadProductArray = Array(
	  "MODIFIED_BY"    => $USER->GetID(), // элемент изменен текущим пользователем
	  "ACTIVE"         => "N"            // активен деактивируем заказ в системе
	  );
	$res = $el->Update($z, $arLoadProductArray);
//деактивируем заказ в системе

//водитель сохраняет комент о заказе
		
		CIBlockElement::SetPropertyValues($z,5, Array("VALUE"=>$rem), "save_v_rem");
		CIBlockElement::SetPropertyValues($z,5, Array("VALUE"=>""), "voditel_live" );

//водитель сохраняет комент о заказе

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
<?

//require($_SERVER["DOCUMENT_ROOT"]."/bitrix/header.php");

//получить пассажира который создал заказ $zakaz_id 

CModule::IncludeModule("iblock");
$arSelect = Array("CREATED_BY");
$arFilter = Array("ID"=>$zakaz_id, "IBLOCK_ID"=>5, "ACTIVE"=>"Y" );
$res = CIBlockElement::GetList(Array(), $arFilter, false, false , $arSelect);
$ob = $res->GetNextElement();
$arFields = $ob->GetFields();
$user_id=$arFields[CREATED_BY];
//echo $user_id."</br>";


//получить у пассажира gps UF_LIVEX UF_LIVEY
$USRpole = CUser::GetList(($by="ID"), ($order="desc"),array("ID"=>$user_id) ,array("SELECT"=>array("UF_*")));

$data = $USRpole->GetNext(); 

//print_r ($data) ;

echo $data[UF_LIVEX].";".$data[UF_LIVEY];

//require($_SERVER["DOCUMENT_ROOT"]."/bitrix/footer.php");

?>
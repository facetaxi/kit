<?
require($_SERVER["DOCUMENT_ROOT"]."/bitrix/header.php");
if ( CSite::InGroup(array(7)) || CSite::InGroup(array(8)) || CSite::InGroup(array(9)) || CSite::InGroup(array(10)) ) {


if ($z>0){
	CModule::IncludeModule("iblock");
 CIBlockElement::SetPropertyValues($z,5,  Array("VALUE"=>$USER->GetID()), "voditel_live"); 
  

$user = new CUser;
$fields = Array(
  "UF_LIVEZAKAZ"          => $z
  );
$user->Update($USER->GetID(), $fields);
$strError .= $user->LAST_ERROR;
 
if ($poadresu>0)
	{
	$fields = Array(  "UF_ZAKAZSTATUS"          => 2  );
$user->Update($USER->GetID(), $fields);
$strError .= $user->LAST_ERROR;
	}

if(CModule::IncludeModule("iblock"))
{
$arSelect = Array("ACTIVE","DATE_ACTIVE_TO","CREATED_BY","DETAIL_TEXT","NAME","PREVIEW_TEXT", "PROPERTY_in_city","PROPERTY_in_ulica","PROPERTY_in_dom","PROPERTY_in_podiezd", "PROPERTY_out_city",	"PROPERTY_out_ulica",	"PROPERTY_out_dom",	"PROPERTY_out_podiezd", "PROPERTY_tarif_v",	"PROPERTY_tarif_h",  "PROPERTY_USLUGI","PROPERTY_USERREM",	"PROPERTY_id_xxxnum_proc",
"PROPERTY_MCENA",
"PROPERTY_cenadoptext",
"PROPERTY_MKM",
"PROPERTY_MTIME",
"PROPERTY_KONDIC",
"PROPERTY_KUREVO",
"PROPERTY_BABY"

);
}

$arFilter = Array("ID"=>$z, "IBLOCK_ID"=>5, "ACTIVE"=>"Y" );
$res = CIBlockElement::GetList(Array(), $arFilter, false, false , $arSelect);

$ob = $res->GetNextElement();
$arFields = $ob->GetFields();

if ($arFields[PROPERTY_TARIF_H_VALUE]=="Э") { $a="эконом"; }
if ($arFields[PROPERTY_TARIF_H_VALUE]=="Н") { $a="норма"; }
if ($arFields[PROPERTY_TARIF_H_VALUE]=="К") { $a="комфорт"; }
if ($arFields[PROPERTY_TARIF_H_VALUE]=="V") { $a="VIP"; }

if ($arFields[PROPERTY_TARIF_V_VALUE]=="S") { $b="км"; }
if ($arFields[PROPERTY_TARIF_V_VALUE]=="T") { $b="часы"; }


$ttariff=$a."/".$b."\r\n".$arFields[DATE_ACTIVE_TO];
echo $ttariff."\r\n";

$rsUser = CUser::GetByID($arFields[CREATED_BY]); 
$arUser = $rsUser->Fetch();

echo $arUser[NAME]."\r\n";
echo "___1".$arUser[LOGIN]."2___"."\r\n\r\n";

echo "ОТКУДА: ";
echo $arFields[PROPERTY_IN_CITY_VALUE]."\r\n".$arFields[PROPERTY_IN_ULICA_VALUE]." ".$arFields[PROPERTY_IN_DOM_VALUE]."\r\n\r\n" ;
echo "КУДА: ";
echo $arFields[PROPERTY_OUT_CITY_VALUE]."\r\n".$arFields[PROPERTY_OUT_ULICA_VALUE]." ".$arFields[PROPERTY_OUT_DOM_VALUE]."\r\n" ;

echo "\r\n"."цена ".$arFields[PROPERTY_MCENA_VALUE]."\r\n";
echo "км ".$arFields[PROPERTY_MKM_VALUE]."\r\n";
echo "время ".$arFields[PROPERTY_MTIME_VALUE]."\r\n";

$pl= "\r\n\r\n";
$pl=$pl.$arFields[PROPERTY_USERREM_VALUE]."\r\n" ;
$pl=$pl.$arFields[PROPERTY_USLUGI_VALUE]."\r\n" ;
$pl=str_replace("\r\n\r\n","",$pl);
echo $pl;

$do="\r\n".$arFields[PROPERTY_KONDIC_VALUE]."\r\n".$arFields[PROPERTY_KUREVO_VALUE]."\r\n".$arFields[PROPERTY_BABY_VALUE]."\r\n";
$do=str_replace("\r\n\r\n","",$do);
echo $do;



if ($arFields[PROPERTY_TARIF_H_VALUE]=="Э") { $tth = 7; }
if ($arFields[PROPERTY_TARIF_H_VALUE]=="Н") { $tth = 8; }
if ($arFields[PROPERTY_TARIF_H_VALUE]=="К") { $tth = 9; }
if ($arFields[PROPERTY_TARIF_H_VALUE]=="V") { $tth = 10; }
if ($arFields[PROPERTY_TARIF_H_VALUE]=="x") { $tth = 11; }

$filter = Array("ACTIVE"=>"Y","GROUPS_ID"=>Array(7,8,9,10),  "UF_LIVEZAKAZ" => $z);
//поиск из всех водителей которые могли взять заказ с номер $livez
$Voditel = CUser::GetList(($by="ID"), ($order="desc"), 		$filter , 		array("SELECT"=>array("UF_*")));
//поиск из всех водителей которые могли взять заказ с номер $livez
$data = $Voditel->GetNext(); 
$koff=true;
//echo $data[UF_ZAKAZSTATUS];
if ($data[UF_ZAKAZSTATUS]>0) {
	$koff=false;
}


}//if z>0


}
require($_SERVER["DOCUMENT_ROOT"]."/bitrix/footer.php");
?>
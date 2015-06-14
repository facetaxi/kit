<?
require($_SERVER["DOCUMENT_ROOT"]."/bitrix/header.php");


global $USER; 

$USRpole = CUser::GetList(($by="ID"), ($order="desc"),array("ID"=>$USER->GetID()) ,array("SELECT"=>array("UF_*")));
$data = $USRpole->GetNext(); 
 
$z2=  $data[UF_LIVEZAKAZ];						

//echo $z2."-";
//z
CModule::IncludeModule("iblock");
$busy=false;
$arFilter = array( 	"ID"=>$z2,   "IBLOCK_ID"=>5 ,"ACTIVE"=>"Y" );
$arSelect = Array("ACTIVE", "MODIFIED_BY","PROPERTY_vkorzine_ukogo_id","PROPERTY_voditel_live");
$res = CIBlockElement::GetList(Array("ID"=>"desc"), $arFilter, false, false, $arSelect);
	while($ob = $res->GetNextElement() )
		{
$arFields = $ob->GetFields();
$v=$arFields[PROPERTY_VODITEL_LIVE_VALUE];
		}
//z

//echo $v;

if ($USER->GetID()==$v )
{ echo $z2; }
else
{ echo ""; }



require($_SERVER["DOCUMENT_ROOT"]."/bitrix/footer.php");
?>

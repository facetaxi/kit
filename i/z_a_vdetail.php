<?
require($_SERVER["DOCUMENT_ROOT"]."/bitrix/header.php");


global $USER; 

CModule::IncludeModule("iblock");
$busy=false;
$arFilter = array( 	"ID"=>$z,   "IBLOCK_ID"=>5 ,"ACTIVE"=>"Y" );
$arSelect = Array("ACTIVE");
$res = CIBlockElement::GetList(Array("ID"=>"desc"), $arFilter, false, false, $arSelect);
$f="n";//;false;
while( $ob = $res->GetNextElement() )
		{
			$f="y";//true;
		}

echo $f; 

require($_SERVER["DOCUMENT_ROOT"]."/bitrix/footer.php");
?>

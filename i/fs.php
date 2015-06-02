<?
require($_SERVER["DOCUMENT_ROOT"]."/bitrix/header.php"); 

//$v=$_POST['client']."_".$_POST['disp']."!";
$v="kc";

$detail_array="";


$detail_array=$detail_array."client=".$cdr."___";

//$detail_array=$detail_array."client=".$_POST['client']."___";
//$detail_array=$detail_array."disp=".$_POST['disp'];
//$detail_array=$detail_array."direction=".$_POST['direction']."</br>";

//$detail_array=$detail_array."state=".$_POST['state']."</br>";
//$detail_array=$detail_array."channel_name=".$_POST['channel_name']."</br>";
//$detail_array=$detail_array."state_number=".$_POST['state_number']."</br>";
//$detail_array=$detail_array."state=".$_POST['state']."</br>";

//$detail_array="test";
//$v="test";

CModule::IncludeModule("iblock");
CModule::IncludeModule("catalog");

$el = new CIBlockElement;

$PROP = array();
//НЕ ВСЕ ДАННЫЕ ПОМЕЗ = direction + channel_name + др перем-е 
$arLoadProductArray = Array(
 "ACTIVE_TO"=>$tttimee,
  "MODIFIED_BY"    => 797, // элемент изменен call центр робот
  "IBLOCK_SECTION_ID" => false,          // элемент лежит в корне раздела
  "IBLOCK_ID"      => 16,
  "PROPERTY_VALUES"=> $PROP,
  "NAME"           => $v, // $USER->GetFullName()
   "DETAIL_TEXT"    => $detail_array,
     "DETAIL_TEXT_TYPE"  => "html",
  "ACTIVE"         => "Y"            // активен
    );

if($PRODUCT_ID = $el->Add($arLoadProductArray,true,false))
{
}


require($_SERVER["DOCUMENT_ROOT"]."/bitrix/footer.php");
?>
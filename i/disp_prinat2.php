<?
require($_SERVER["DOCUMENT_ROOT"]."/bitrix/header.php");
global $USER;
CModule::IncludeModule("iblock");

CIBlockElement::SetPropertyValues($z,5,  $USER->GetID() ,"voditel_live"  );

 


if ( CSite::InGroup(array(13)) ) //для диспетчера
	{
		
		
	//деактивируем заказ в системе

//CModule::IncludeModule("catalog");
$el = new CIBlockElement;

$arLoadProductArray = Array(
  "MODIFIED_BY"    => $USER->GetID(), // элемент изменен текущим пользователем

  "ACTIVE"         => "N"            // активен деактивируем заказ в системе

  );

$res = $el->Update($z, $arLoadProductArray);
//деактивируем заказ в системе	
		
		 
	
	}


require($_SERVER["DOCUMENT_ROOT"]."/bitrix/footer.php");
?>
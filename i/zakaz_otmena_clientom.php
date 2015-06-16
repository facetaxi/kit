<?
require($_SERVER["DOCUMENT_ROOT"]."/bitrix/header.php");

CModule::IncludeModule("iblock");
global $USER; 
CIBlockElement::SetPropertyValues($z, 5, Array("VALUE"=>"" ) ,"vkorzine_ukogo_id"        ); 
//очистить UF_LIVEZAKAZ у текущего водителя

		 
	$user = new CUser;
		$fields = Array(  "UF_LIVEZAKAZ" => ""  );
		$user->Update($USER->GetID(), $fields);
	
	echo "y";

require($_SERVER["DOCUMENT_ROOT"]."/bitrix/footer.php");
?>

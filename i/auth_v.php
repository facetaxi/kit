<?require($_SERVER["DOCUMENT_ROOT"]."/bitrix/header.php");?>

<?

		 $out="2";
if (      isset($l) and  isset($p)                   ) //потом md5
{
	global $USER;
	if (!is_object($USER)) $USER = new CUser;
	$arAuthResult = $USER->Login($l, $p, "Y");
	$APPLICATION->arAuthResult = $arAuthResult;

	if ( $USER->IsAuthorized()  ) 
	{
//проверка что клиент в водительской группе
	if ( CSite::InGroup(array(7)) || CSite::InGroup(array(8)) || CSite::InGroup(array(9)) || CSite::InGroup(array(10)) ) //для водителей
		{
					 $out="1";
		}
	}
}
echo $out;
 
?>

<?require($_SERVER["DOCUMENT_ROOT"]."/bitrix/footer.php");?>
<?
if (      isset($_POST['l']) and  isset($_POST['p'])                   ) //потом md5
{

	$tel = $_POST['l'];
	$pas = $_POST['p'];

	global $USER;
	if (!is_object($USER)) $USER = new CUser;
	$arAuthResult = $USER->Login($_POST['l'], $_POST['p'], "Y");
	$APPLICATION->arAuthResult = $arAuthResult;

	if ($USER->IsAuthorized()) 
	{
		echo "1";
	}
	else
	{
		echo "2";
	}

}
?>

<?
require($_SERVER["DOCUMENT_ROOT"]."/bitrix/header.php");



//проверка есть ли LIVEZAKAZ у этого пользователя
 
global $USER; 

$USRpole = CUser::GetList(($by="ID"), ($order="desc"),array("ID"=>$USER->GetID()) ,array("SELECT"=>array("UF_*")));
$data = $USRpole->GetNext(); 
$status=  $data[UF_ZAKAZSTATUS];
 
if ($status=="")
{
	$status="0";
}

echo $status;

require($_SERVER["DOCUMENT_ROOT"]."/bitrix/footer.php");
?>


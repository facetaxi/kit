<?
require($_SERVER["DOCUMENT_ROOT"]."/bitrix/header.php");


//$arAuthResult = $USER->Login($l, $p, "Y");

//проверка есть ли LIVEZAKAZ у этого пользователя
$status="";
 
global $USER; 

$USRpole = CUser::GetList(($by="ID"), ($order="desc"),array("ID"=>$USER->GetID()) ,array("SELECT"=>array("UF_*")));
$data = $USRpole->GetNext(); 
$livez=  $data[UF_LIVEZAKAZ];
	
if ($livez>0)
{
	$status="1";
}

//$out=$status;
//echo "test";
//echo $status;
echo $status;

require($_SERVER["DOCUMENT_ROOT"]."/bitrix/footer.php");
?>


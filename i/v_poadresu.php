<?
require($_SERVER["DOCUMENT_ROOT"]."/bitrix/header.php");
if ( CSite::InGroup(array(7)) || CSite::InGroup(array(8)) || CSite::InGroup(array(9)) || CSite::InGroup(array(10)) ) {

  $err=0;
try {

$user = new CUser;
//if ($poadresu>0)
	//{
		$fields = Array(  "UF_ZAKAZSTATUS"          => 2  );
$user->Update($USER->GetID(), $fields);
$strError .= $user->LAST_ERROR;
	
	//}
	






} 
catch (Exception $e) {
  $err=1;  
}

//$err==0;
if ($err==0) 
{ echo "8";  } 
else
{ echo "6";  } 






	
	
}
require($_SERVER["DOCUMENT_ROOT"]."/bitrix/footer.php");
?>
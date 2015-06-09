 
 <?
 

function generatePassword($length = 3){
  $chars = '23456789';
  
  $numChars = strlen($chars);
  $string = '';
  for ($i = 0; $i < $length; $i++) {
    $string .= substr($chars, rand(1, $numChars) - 1, 1);
  }
  return $string;
}

$password=generatePassword(3);


 

//get id po loginu
$rsUser = CUser::GetByLogin($l);
$arUser = $rsUser->Fetch();
$ID = $arUser[ID];
//get id po loginu

$user = new CUser;
$fields = Array(
  "PASSWORD"          => $password,
  "CONFIRM_PASSWORD"  => $password
  );
$user->Update($ID, $fields);
$strError = $user->LAST_ERROR;

if (intval($ID) > 0) 
{
    	$message="Ваш телефон ".$l." Ваш пароль ".$password." для интернет такси taxi";
		$to=$l;
	//sms
	
if (CModule::IncludeModule("rarus.sms4b"))
   {	
global $SMS4B;

	if ($SMS4B->SendSMS($message,$to))
	{
		$arResult["RESULT_MESSAGE"]["TYPE"] = "OK";
		$arResult["RESULT_MESSAGE"]["MESSAGE"] = GetMessage("OK"); 
echo "На Ваш мобильный телефон прийдёт бесплатная SMS с паролем для доступа на сайт.";
 	
	}
	else
	{
		$arResult["RESULT_MESSAGE"]["TYPE"] = "ERROR";
		$arResult["RESULT_MESSAGE"]["MESSAGE"].=GetMessage("MESSAGES_NOT_SEND").$arInd.'<br>';
echo "Ошибка при отправке SMS. Возможно неверно введён телефон";
 	
	}

}

	
   
	//sms		
}


else 
{
   
    echo $user->LAST_ERROR;

	echo "Телефон не найден.";
 	
}

?>

 
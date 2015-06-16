<?require($_SERVER["DOCUMENT_ROOT"]."/bitrix/header.php");
$APPLICATION->SetTitle("отмена");
?>

<?




//проверка есть ли LIVEZAKAZ у этого пользователя
global $USER; 



 
 
 
$USRpole = CUser::GetList(($by="ID"), ($order="desc"),array("ID"=>$USER->GetID()) ,array("SELECT"=>array("UF_*")));
$data = $USRpole->GetNext(); 
$livez=  $data[UF_LIVEZAKAZ];
	
	if ($livez>0)//у клиента есть заказ
	{
		
  
 		
///////////////////////////////////////////////////////////////////////////3 = отмена в инфоблоке

 //деактивируем заказ в системе
CModule::IncludeModule("iblock");
//CModule::IncludeModule("catalog");
$el = new CIBlockElement;
$arLoadProductArray = Array(
  "MODIFIED_BY"    => $USER->GetID(), // элемент изменен текущим пользователем
  "ACTIVE"         => "N"            // активен деактивируем заказ в системе
  );
$res = $el->Update($livez, $arLoadProductArray);
//деактивируем заказ в системе
CIBlockElement::SetPropertyValues($livez, 5, Array("VALUE"=>"" ) ,"vkorzine_ukogo_id"        ); 
//////////////////////////////////////////////////////////////////3 = отмена в инфоблоке
		
		
		///////////////////////////////////////////////////////////////////////1 = отмена у клиента
	$user = new CUser;
		$fields = Array(  "UF_LIVEZAKAZ" => ""  );
		$user->Update($USER->GetID(), $fields);
		$strError .= $user->LAST_ERROR;
	/////////////////////////////////////////1 = отмена у клиента
	
		 ///////////////////////////////////////////2 = отмена у водителя
		$filter = Array("ACTIVE"=>"Y","GROUPS_ID"=>Array(7,8,9,10),  "UF_LIVEZAKAZ" => $livez);
		//поиск из всех водителей которые могли взять заказ с номер $livez
		$Voditel = CUser::GetList(($by="ID"), ($order="desc"), 		$filter , 		array("SELECT"=>array("UF_*")));
		//поиск из всех водителей которые могли взять заказ с номер $livez
		$data = $Voditel->GetNext(); 

		$vID=  $data[ID];
	//	echo $vID;
		if ($vID>0) { 
						$user = new CUser;
						$fields = Array(  "UF_LIVEZAKAZ" => "" );
						$user->Update($vID, $fields);
						$strError .= $user->LAST_ERROR;
					}///////////////////////////////////////////2 = отмена у водителя


	}//у клиента есть заказ
	
	 
	//redirect на index.php
?>


<? 

//сигнал диспетчеру на перезагрузку страницы
// 		$Vuser = new CUser;
	//	$fields = Array( 		  "UF_UPDATE"          => 1 		  );
		//$Vuser->Update(798, $fields);
//сигнал диспетчеру на перезагрузку страницы 

echo "y";
?> 

	

<?require($_SERVER["DOCUMENT_ROOT"]."/bitrix/footer.php");?>

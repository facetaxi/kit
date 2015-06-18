<?
require($_SERVER["DOCUMENT_ROOT"]."/bitrix/header.php");


//проверка активного заказа для тек-ого водителя
							
if ( CSite::InGroup(array(7)) || CSite::InGroup(array(8)) || CSite::InGroup(array(9)) || CSite::InGroup(array(10)) ) //для водителей
	{
		
					global $USER; 
					
		   //откуда фильтр    
					
if(CModule::IncludeModule("iblock")) // вывод заказов для водителей = каждом/подъезду своё
					{
					
					
					

					
}
 			

					

require($_SERVER["DOCUMENT_ROOT"]."/bitrix/footer.php");
?>

<?
require($_SERVER["DOCUMENT_ROOT"]."/bitrix/header.php"); 
global $USER;

// $md5
// protobuf

 


//$USER->Logout();
//echo "id1=".$USER->GetID()."</br>";
$out=0;//уже авторизован
if (!$USER->IsAuthorized()  )  	{//111 если не авторизован
//echo "id11=".$USER->GetID()."</br>";
$out=2;//клиент не авторизован
if (isset($l) and  isset($p))  	{///2
								if (!is_object($USER)) $USER = new CUser;
								$arAuthResult = $USER->Login($l, $p, "Y");
								$APPLICATION->arAuthResult = $arAuthResult;
					//авторизуем 
			
								if ( $USER->IsAuthorized()  ) 
								{///333
								$out=1;//клиент авторизован
	//							echo "id2=".$USER->GetID()."</br>";
//											if (isset($v))
	//										{//444 клиен
														 //проверка что клиент в водительской группе
															if ( CSite::InGroup(array(7)) || CSite::InGroup(array(8)) || CSite::InGroup(array(9)) || CSite::InGroup(array(10)) ) //для водителей
																{
																			 $out=3;//это авторизованный клиент из группы водителей
			//																 echo "v";
																}
													
		//									}//444
								}//333
								}//222
								}//111



 

if ($o==11)
{
    include 'sendpass.php'; 
	exit();
}

if ($o==12)
{
    include 'reg.php'; 
	exit();
}


//проверка версии
if ( CSite::InGroup(array(6)) || CSite::InGroup(array(7)) || CSite::InGroup(array(8)) || CSite::InGroup(array(9)) || CSite::InGroup(array(10)) ) //водителям
{
	 
	$realuser=$USER->GetID(); 
	
	global $USER; 
	$USRpole = CUser::GetList(($by="ID"), ($order="desc"),array("ID"=>1) ,array("SELECT"=>array("UF_*")));
	$data = $USRpole->GetNext(); 
	$admin_vers=  $data[UF_VERSIYA];
	
//	global $USER; 
//	$USRpole = CUser::GetList(($by="ID"), ($order="desc"),array("ID"=>$USER->GetID()) ,array("SELECT"=>array("UF_*")));
//	$data = $USRpole->GetNext(); 
//	$user_vers=  $data[UF_VERSIYA];
	
	
	if ($admin_vers>$uv)//$uv версия приложения у польз на телефоне
		{
			//echo $admin_vers."/".$user_vers."/".$USER->GetID()."/";
			echo "обновите приложение с сайта taxi";
			exit();	
		}
}
//проверка версии




if ($o==91)
{
    include 'fast_adresa.php'; 
	exit();
}






if ($out!=2)
{
$UserID=$USER->GetID();

	switch ( $o ) {
//    case 8:
  //      include 'vo.php'; 
	//	break;         

    case 5:
        include 'gpssave.php'; 
		break;         
	}
}




//echo "l=".$l."</br>";
//echo $USER->GetID()."</br>";
//echo "g=".CSite::InGroup(array(13))."</br>";

if ( CSite::InGroup(array(6)) )   //клиенты
{
	//echo "клиент";
switch ( $o ) {
    case 1:
			include 'zakaz_send.php';
			exit(); 
			break;

//
	case 3:
			include 'zakaz_otmena.php';
			exit(); 
			break;
	

	case 4:
			include 'zakaz_status.php';
 			exit(); 
			break;
//
	case 55:
			include 'gpssave.php'; 
			break;         
			
	
//
	case 5:
			include 'zakaz_a.php';//проверяем этот заказ активный в cuser?
			exit(); 
			break;	
			
			
	case 888:
			include 'tarifi.php';//проверяем этот заказ активный в cuser?
			exit(); 
			break;	
			
				
			
			
			
    case 9:
        include 'hys.php'; 
		break;         
		
      case 901:
        include 'hys_d.php'; 
			exit(); 
			break;       
		
      case 902:
        include 'hys_dall.php'; 
			exit(); 
			break;       
		
        

		case 99:
			include 'time_server.php';
			exit(); 
			break;
	}
	
}








if ( CSite::InGroup(array(7)) || CSite::InGroup(array(8)) || CSite::InGroup(array(9)) || CSite::InGroup(array(10)) ) //водителям
{
	
	//если UF_LIFEZAKAZ уже неактивный в инфоблоке то UF_LIFEZAKAZ обнулять в ПОЛЬЗОВАТЕЛЯХ
	
	//если UF_LIFEZAKAZ уже неактивный в инфоблоке то UF_LIFEZAKAZ обнулять в ПОЛЬЗОВАТЕЛЯХ
	
	
	
	switch ( $o ) {
		
		
		case 888:
			include 'tarifi.php';//проверяем этот заказ активный в cuser?
			exit(); 
			break;	
			
			
			

	case 7:
			include 'z_a2.php';//проверяем этот заказ активный в инфоблоке?
			exit(); 
			break;
			
	case 81:
			include 'z_a_vdetail.php';//проверяем этот заказ активный в инфоблоке? для v_detail
			exit(); 
			break;
			
				
	case 82:
			include 'zakaz_otmenen_clientom.php';//проверяем этот заказ активный в инфоблоке? для v_detail
			exit(); 
			break;
			
			
					
				

	case 77:
			include 'zakaz_av2.php';//проверяем этот заказ активный в cuser?
			exit(); 
			break;	


	case 88:
			include 'get_gps_pasagir.php';//выдаёт текущие gps координаты пассажира
			exit(); 
			break;	


	case 41:
			include 'save_zakaz_dop.php';//выдаёт текущие gps координаты пассажира
			exit(); 
			break;	

			
//	case 78:
	//		include 'zakaz_ss.php';//сохраняет экран, для возобновления в случае выхода
		//	exit(); 
			//break;	
	case 79:
			include 'zakaz_ls.php';//загружает экран, для возобновления в случае выхода
			exit(); 
			break;	
			

	case 55:
			include 'gpssave.php'; 
			break;         
			
    case 4:
			include 'v_start_zokno_add.php';
			exit(); 
			break;
    case 6:
			include 'v_start_zokno_delete.php';
			exit(); 
			break;  
    case 61:
    	    include 'v_detail.php';
			exit(); 
			break;        
    case 62:
    	    include 'v_otmena.php';
			exit(); 
			break;   
    case 63:
	        include 'v_rem.php';
			exit(); 
			break;  
    case 64:
	        include 'v_poadresu.php';
			exit(); 
			break;
    case 70://счет
	        include 'v_k_0.php';
			exit(); 
			break; 	 
    case 71://инструкция
        	include 'v_k_1.php';
			exit(); 
			break; 	 
    case 72://тарифы
	        include 'v_k_2.php';
			exit(); 
			break; 	 
    case 73://детализация за текущий день (бесплатно)
    	    include 'v_k_3.php';
			exit(); 
			break; 	 
    case 74://детализация за 7 дней (платно, 30 руб.)
	        include 'v_k_4.php';
			exit(); 
			break; 	



    case 200:
	        include 'korzina_add.php';
			exit(); 
			break; 				 
    case 209:
	        include 'korzina_verify.php';
			exit(); 
			break; 				 
			
			
			
			
			
    case 201:
	        include 'korzina_delete.php';
			exit(); 
			break; 				 
			
    case 202:
	        include 'korzina_empty.php';
			exit(); 
			break; 				 
			
    case 203:
	        include 'korzina_list.php';
			exit(); 
			break; 				 
    case 204:
	        include 'korzina_opovegenie.php';
			exit(); 
			break; 
			
	case 205:
	        include 'korzina_ifk_otmena_clientom.php';
			exit(); 
			break; 	
			
	case 206:
	        include 'korzina_verify_on_start.php';//			если_при_старте_есть_заказ_вкорзине_тоего_загрузть();			 
			exit(); 
			break; 	
			

	case 207:
	        include 'korzina_verify_time.php';//			если_при_старте_есть_заказ_вкорзине_тоего_загрузть();			 
			exit(); 
			break; 	

	
	case 208:
	        include 'korzina_verify_time30.php';//			если_при_старте_есть_заказ_вкорзине_тоего_загрузть();			 
			exit(); 
			break; 	

	case 210:
	        include 'taxometr.php';//			если_при_старте_есть_заказ_вкорзине_тоего_загрузть();			 
			exit(); 
			break; 	

			
			
	}
}
//protobuf


if ( $o==89 ) 
{
	if ( $USER->IsAuthorized() && CSite::InGroup(array(6)))
	{
		$out=8;
	}
	if ($USER->IsAuthorized())
	{
		if ( CSite::InGroup(array(7)) || CSite::InGroup(array(8)) || CSite::InGroup(array(9)) || CSite::InGroup(array(10)) ) 
		{
		$out=9;
		}
	}
}

echo $out;

require($_SERVER["DOCUMENT_ROOT"]."/bitrix/footer.php");
?>


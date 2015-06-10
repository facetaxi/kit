<?
require($_SERVER["DOCUMENT_ROOT"]."/bitrix/header.php");

//это будет считать сервер с# времени

//$id1 $id2

$filter = Array("ACTIVE"=>"Y", "ID"=> $id1 );
	$USRpole = CUser::GetList( ($by="ID"), 	($order="desc"),$filter,array("SELECT"=>array("UF_*")) 	);
	$data = $USRpole->GetNext(); 
$gps1_x=  $data[UF_X];
$gps1_y=  $data[UF_Y];

$filter = Array("ACTIVE"=>"Y", "ID"=> $id2 );
	$USRpole = CUser::GetList( ($by="ID"), 	($order="desc"),$filter,array("SELECT"=>array("UF_*")) 	);
	$data = $USRpole->GetNext(); 
$gps2_x=  $data[UF_X];
$gps2_y=  $data[UF_Y];

$skorost=50; //50км/час средняя скорость в городе

//долготу перевести в метры

//долготу перевести в метры

//коээф влияющией на удлинение маршрута в зависимости от самого маршрута

$x=gps2_x-gps1_x;
$y=gps2_y-gps1_y;

$c= sqrt ($x*$x+$y*$y);



require($_SERVER["DOCUMENT_ROOT"]."/bitrix/footer.php");
?>
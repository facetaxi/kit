<?
require($_SERVER["DOCUMENT_ROOT"]."/bitrix/header.php");

//метры и минуты/часы на входе проверить
//http://taxi/i/money.php?d=4,5 км&v=10 мин.&t=0

$money=0;


//получить коэф который прибавляем
	$kplus=0;
	
	
	
	CModule::IncludeModule("iblock");
	
	$arSelect = Array("NAME");
	$Dres = CIBlockElement::GetList(Array("NAME"=>"asc"), array( "IBLOCK_ID"=>19 ), false, Array(), $arSelect);
	 
	while($Dob = $Dres->GetNextElement())
	{
	  $DarFields = $Dob->GetFields();
	  $kplus=$DarFields[NAME];
	}
	 
//получить коэф который прибавляем


//проверяем есть ли часы в времени
$h=0;
$m=0;
$r=strpos($v,"ч.");


	$m= str_replace(" мин.","",$v);
	//echo "_".$m."_M1time</br>";
	$m=$m/60;
	
if ($r>0)
{
	//обработчик часов
	$h=substr($v,0,$r-1);
	$m=0;
	
	//остались ли минуты
	$q=strpos($v,"мин.");
	if ($q>0)
	{
  	   $m=substr($v,$r+3,strlen($v)-$r-3-4);
	   $m=$m/60;
	}
}
$v=$h+$m;


$d= str_replace(",",".",$d);
$d= str_replace(" км","",$d);
if ($d>3) { $d=$d-3; } 


//echo "_".$t."_tarif</br>";
//echo "_".$d."_distance</br>";
//echo "_".$v."_time</br>";
//echo "_".$h."_Htime</br>";
//echo "_".$m."_Mtime</br>";


if ( $t==0 ) { $a=100;$b=16 ; }
if ( $t==1 ) { $a=130;$b=18 ; }
if ( $t==2 ) { $a=150;$b=20 ; }
if ( $t==3 ) { $a=250;$b=25 ; }

if ( $t==4 ) { $b=360 ; }
if ( $t==5 ) { $b=420 ; }
if ( $t==6 ) { $b=450 ; }
if ( $t==7 ) { $b=700 ; }

if ($t<=3) { $money = $a+$d*$b; }
if ($t>=4) { $money =  $v*$b; }


$money=(int)$money;

if ($money<360)
{
if ( $t==4 ) { $money=360 ; }
if ( $t==5 ) { $money=420 ; }
if ( $t==6 ) { $money=450 ; }
if ( $t==7 ) { $money=700 ; }
}


//если спец дорога то считаем по другому




				
				
				
				
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	  скидки или купоны
	 $ski = 0; 

	//если период для скидок и сумма больше 350 р то применяем скидку
	$periodskidok=0;

 
$eh=$hour;
$em=$minute;

 
$tz_string = "Europe/Moscow"; // Use one from list of TZ names http://php.net/manual/en/timezones.php 
$tz_object = new DateTimeZone($tz_string); 
	
//$dd = new DateTime();
//$dd->setTimezone($tz_object); 
//$dd->setDate($year, $month, $day); 
//$dd->setTime($hour, $minute, 0); 

//$n = date("w",$dd);

//echo "_".$dd."_";

$arr = getdate(mktime($hour, $minute, 0, $month, $day, $year));
$n=$arr['wday'];
$n=$n+2;
if ($n==7) { $n=0; } 
if ($n==8) { $n=1; } 
//if ($n==9) { $n=2; } 

//echo "_".$n."_";


//работаем по скидке

if ($n==0 && $eh>=2 && $eh<=5 )  //пятница
{
	$ski=0.1;
}
if ($n==1 && $eh>=2 && $eh<=5 )  //суббота
{
	$ski=0.1;
}
if ($n==2 && $eh>=2 && $eh<=13 )  //воскресенье
{
	$ski=0.1;
}

//////////////////////////////////////////////////////понедельник
if ($n==3 && $eh>=0 && $eh<=5 )  
{
	$ski=0.1;
}
	if ($n==3 && $eh>=11 && $eh<=15 )  //понедельник
	{
		$ski=0.1;
	}
//////////////////////////////////////////////////////понедельник

//////////////////////////////////////////////////////вторник
if ($n==4 && $eh>=0 && $eh<=5 )  
{
	$ski=0.1;
}
	if ($n==4 && $eh>=11 && $eh<=15 )  //понедельник
	{
		$ski=0.1;
	}
//////////////////////////////////////////////////////вторник

//////////////////////////////////////////////////////среда
if ($n==5 && $eh>=0 && $eh<=5 )  
{
	$ski=0.1;
}
	if ($n==5 && $eh>=11 && $eh<=15 )  //среда
	{
		$ski=0.1;
	}
//////////////////////////////////////////////////////среда




if ($n==6 && $eh>=0 && $eh<=5 )  //четверг
{
	$ski=0.1;
}



$skidka_text="";
if ($ski>0 && $money>350)  //если период для скидок и сумма больше 350 р то применяем скидку
//if ($ski>0 && $money>350)  //если период для скидок и сумма больше 350 р то применяем скидку
{
	$money=(int)($money-$money*$ski);
	$skidka_text= " цена с 10% скидкой";
}

// проверяем купон если он не в период скидок

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	  скидки или купоны

$money=$money*$kplus;

echo $money." руб.".$skidka_text;

require($_SERVER["DOCUMENT_ROOT"]."/bitrix/footer.php");
?>
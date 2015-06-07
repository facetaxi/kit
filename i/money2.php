<?
require($_SERVER["DOCUMENT_ROOT"]."/bitrix/header.php");

//метры и минуты/часы на входе проверить
//http://taxi/i/money.php?d=4,5 км&v=10 мин.&t=0

$money=0;

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



//echo "_".$t."_tarif</br>";
//echo "_".$d."_distance</br>";
//echo "_".$v."_time</br>";
//echo "_".$h."_Htime</br>";
//echo "_".$m."_Mtime</br>";


if ( $t==0 ) { $a=100;$b=16 ; }
if ( $t==1 ) { $a=130;$b=18 ; }
if ( $t==2 ) { $a=150;$b=20 ; }
if ( $t==3 ) { $a=200;$b=25 ; }

if ( $t==4 ) { $b=360 ; }
if ( $t==5 ) { $b=420 ; }
if ( $t==6 ) { $b=480 ; }
if ( $t==7 ) { $b=700 ; }

if ($t<=3) { $money = $a+$d*$b; }
if ($t>=4) { $money =  $v*$b; }


$money=(int)$money;

if ($money<360)
{
if ( $t==4 ) { $money=360 ; }
if ( $t==5 ) { $money=420 ; }
if ( $t==6 ) { $money=480 ; }
if ( $t==7 ) { $money=700 ; }
}







//////////////////////////////////////////////////////////////////////////////// распознавание межгорода
$t=$t+1;
$spec_doroga=0;
	//*
			$mg = strtolower ($in_city) +  strtolower ($out_city);
		$mg= trim($mg);

		$mg=str_replace(" ","",$mg);
		$mg=str_replace("краснодар","",$mg);
		$mg=str_replace("краснодар","",$mg);
		$mg=str_replace("погороду","",$mg);
		$mg=str_replace("по городу","",$mg);

		if (strlen($mg)>0) 	
		{
			if ($t>4) { $tc=$t-4; } // это межгород
//			document.getElementById("mprimerno3").innerHTML = "межгород считается по км";
		}
	//
//////////////////////////////////////////////////////////////////////////////// распознавание межгорода

	if ($t<5)

	{//км

				$ml=$d;//route.getLength()/1000;
				if ($t==1) { $ma=100; $mb=16; }
				if ($t==2) { $ma=130; $mb=18; }
				if ($t==3) { $ma=150; $mb=20; }
				if ($t==4) { $ma=200; $mb=25; }
			
				//	свыше 100 км
				if ($t==1 && $ml>100) { $mb=$mb+3;}
				if ($t==2 && $ml>100) { $mb=$mb+4;}
				if ($t==3 && $ml>100) { $mb=$mb+5;}
				if ($t==4 && $ml>100) { $mb=$mb+6;}
	

//////////////////////////////////////////////////////////////////////////////// спецдорога
				$spec_doroga=0;
				$spec_doroga_k=0;
				//*
				$mg = strtolower ($in_city) + strtolower ($out_city);
	
				$mg=trim($mg);
				$mg=mg.replace("краснодар","");
				$mg=mg.replace("погороду","");
				mg=mg.replace("по городу","");
		
				if (mg.length>0) 	
				{
					
					if ($mg=="адлер") { spec_doroga_k=1.6} 
					if ($mg=="лазаревское") { spec_doroga_k=1.8 }
					if ($mg=="краснаяполяна") { spec_doroga_k=2 }
					if ($mg=="красная поляна") { spec_doroga_k=2 }
					if ($mg=="сочи") { spec_doroga_k=2.5 }
				}
				//	
				//*
				if ($spec_doroga_k==0)
				{
					$money=ma+mb*ml
				}
				else
				{
					$money=ma+190*mb + (ml-190)*(mb+spec_doroga_k)	
				}
				//
//////////////////////////////////////////////////////////////////////////////// спецдорога
	
	}//км
	else
	{//часы
 
				if (tc==5) { ma=360 }
				if (tc==6) { ma=420 }
				if (tc==7) { ma=480 }
				if (tc==8) { ma=700 }
				//alert (route.getJamsTime() );
				var secc=route.getJamsTime()/3600;
				if (secc<1)
					{
					   pprice=ma
					}
					else
					{
					   pprice=ma+ma*(secc)  
					}
	
	}//часы
	   
	//Проезд за черту города (свыше 30 км) 
	if (  (route.getLength()/1000)>30  ) { pprice=pprice+100  }  
	
	//туапсе - адлер

	//свыше 100 км			












				
				
				
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


echo $money." руб.".$skidka_text;

require($_SERVER["DOCUMENT_ROOT"]."/bitrix/footer.php");
?>
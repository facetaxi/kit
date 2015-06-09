<?

//$dist //$time = $d $v
//$gpsvx //$gpsvy
//$gpspy //$gpspy
//z = номер заказа

//date time конвертируем в метры и минуты

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

//$d

//$d

//записываем в заказ длину, время, gpsы
		CModule::IncludeModule("iblock");
$el = new CIBlockElement;

$PROP = array();



//$PROP["dist"] = = array("VALUE" => $d);
//$PROP["dist_time"] = = array("VALUE" => $v);

//$PROP["gpsvx"] = = array("VALUE" => $gpsvx);
//$PROP["gpsvy"] = = array("VALUE" => $gpsvy);

//$PROP["gpspx"] = = array("VALUE" => $gpspx);
//$PROP["gpspy"] = = array("VALUE" => $gpspy);


CIBlockElement::SetPropertyValues($z, 5, Array("VALUE"=>$dist), "dist");  
CIBlockElement::SetPropertyValues($z, 5, Array("VALUE"=>$dist_time), "dist_time");

CIBlockElement::SetPropertyValues($z, 5, Array("VALUE"=>$gpsvx), "gpsvx");
CIBlockElement::SetPropertyValues($z, 5, Array("VALUE"=>$gpsvy), "gpsvy");

CIBlockElement::SetPropertyValues($z, 5, Array("VALUE"=>$gpspx), "gpspx");
CIBlockElement::SetPropertyValues($z, 5, Array("VALUE"=>$gpspy), "gpspy");

//$arLoadProductArray = Array(
  //"MODIFIED_BY"    => $USER->GetID(), // элемент изменен текущим пользователем
  //"PROPERTY_VALUES"=> $PROP,
  //);

//$res = $el->Update($z, $arLoadProductArray);

?>
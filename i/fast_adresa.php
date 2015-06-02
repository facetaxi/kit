<?

$out="";
global $USER;
$realuser=$USER->GetID(); 
CModule::IncludeModule("iblock");

$arSelect = Array("NAME","PROPERTY_in_ulica" , "PROPERTY_in_city", "PROPERTY_in_dom","PROPERTY_in_podiezd");
$Dres = CIBlockElement::GetList(Array("NAME"=>"asc"), array( "IBLOCK_ID"=>18 ), false, Array(), $arSelect);
	      
 	   
 
while($Dob = $Dres->GetNextElement())
{
  $DarFields = $Dob->GetFields();
 
   $f0=$DarFields[NAME];
   $f1=$DarFields[PROPERTY_IN_CITY_VALUE];
   $f2=$DarFields[PROPERTY_IN_ULICA_VALUE];
   $f3=$DarFields[PROPERTY_IN_DOM_VALUE].$DarFields[PROPERTY_IN_PODIEZD_VALUE];

$f0=trim($f0); 
$f1=trim($f1);
$f2=trim($f2);
$f3=trim($f3);
 

if ( strlen($f1)>0 and strlen($f2)>0  and strlen($f3)>0 and ($f2)!="без адреса"  ) 
	{
		$out=$out.$f0.", ".$f1.", ".$f2.", ".$f3."</br>";
}

}

echo $out;

 
 
?>

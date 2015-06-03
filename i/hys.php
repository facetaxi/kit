<?
$out="";
global $USER;
$realuser=$USER->GetID(); 
CModule::IncludeModule("iblock");

$arSelect = Array("ID","CREATED_DATE","PROPERTY_*");

if ($type==1) {
	$DarFilter = array( "ACTIVE"=>"N",   "IBLOCK_ID"=>5  , "CREATED_BY"    =>  $realuser, "!PROPERTY_in_city" => ""  , "!PROPERTY_deleted1" => "Y" );
	$grp = array ( ToLower("PROPERTY_IN_CITY"),ToLower("PROPERTY_IN_ULICA"), ToLower("PROPERTY_IN_DOM"), ToLower("PROPERTY_IN_PODIEZD"));//, ToLower("PROPERTY_DELETED1") 
}
else
{
	$DarFilter = array( "ACTIVE"=>"N",   "IBLOCK_ID"=>5  , "CREATED_BY"    =>  $realuser, "!PROPERTY_in_city" => ""  , "!PROPERTY_deleted2" => "Y" );    
	$grp = array (ToLower("PROPERTY_OUT_CITY"),ToLower("PROPERTY_OUT_ULICA"), ToLower("PROPERTY_OUT_DOM"), ToLower("PROPERTY_OUT_PODIEZD") );//, ToLower("PROPERTY_DELETED2")
}

$Dres = CIBlockElement::GetList(Array("CREATED_DATE"=>"asc"), $DarFilter, $grp, false, $arSelect);
 
$fa=array();		   
 
while($Dob = $Dres->GetNextElement())
{ 
         $DarFields = $Dob->GetFields();
 
		if ($type==1) {
		//	$df=$DarFields[PROPERTY_DELETED1_VALUE];
		   
		   $f1=$DarFields[PROPERTY_IN_CITY_VALUE];
		   $f2=$DarFields[PROPERTY_IN_ULICA_VALUE];
		   $f3=$DarFields[PROPERTY_IN_DOM_VALUE].$DarFields[PROPERTY_IN_PODIEZD_VALUE];
		}
		else
		{
		//	$df=$DarFields[PROPERTY_DELETED2_VALUE];

		   $f1=$DarFields[PROPERTY_OUT_CITY_VALUE];
		   $f2=$DarFields[PROPERTY_OUT_ULICA_VALUE];
		   $f3=$DarFields[PROPERTY_OUT_DOM_VALUE].$DarFields[PROPERTY_OUT_PODIEZD_VALUE];
		}
		
		
		$f1=trim($f1);
		$f2=trim($f2);
		$f3=trim($f3);
		
		$f123=$f1.$f2.$f3;
		$fm=strlen(in_array ($f123,$fa));
		array_push($fa,   $f123);
		
		if ( strlen($f1)>0 and strlen($f2)>0  and strlen($f3)>0 and ($f2)!="без адреса"  and $fm==0 ) //and $df==""
		{
			$mesto=$f1.", ".$f2.", ".$f3;
			$out=$out.$mesto."</br>";   
		}
 

}

 
 
?>

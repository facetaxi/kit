<?

CModule::IncludeModule("iblock");

$Select = Array(	"ID", "PROPERTY_tarif_v", "PROPERTY_ID_TAXI","ID","PROPERTY_ID_TARIFA" , "NAME"	, "PROPERTY_SOKR", "PROPERTY_A", "PROPERTY_B");
$Filter = array("IBLOCK_ID" => 20, array("PROPERTY_ID_TAXI") => $id_taxi      );

$res = CIBlockElement::GetList(false, $Filter, false, false, $Select);
$h="";
while($ob = $res->GetNextElement())
{
	$f = $ob->GetFields();
	
	$q1=$f[NAME];
	$q2=$f[PROPERTY_A_VALUE];
	$q3=$f[PROPERTY_B_VALUE];
	$q4=$f[PROPERTY_SOKR_VALUE];
	
	$h=$h.$q1.";".$q2."]".$q3."=".$q4."</br>";
//	$h=$h.$q1."</br>";
}

echo "y".$h;

?>
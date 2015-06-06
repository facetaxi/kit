<?
CModule::IncludeModule("iblock");
$Select = Array("ID","ACTIVE","ACTIVE_TO","PROPERTY_VKORZINE_UKOGO_ID");
$Filter= Array("ACTIVE"=>"Y", "IBLOCK_ID"=>5 , "PROPERTY_vkorzine_ukogo_id">=$USER->GetID());
$r = CIBlockElement::GetList(false, $Filter, false, false, $Select);

while($o = $r->GetNextElement())
{
	$arFields = $o->GetFields();
	if ($arFields[PROPERTY_VKORZINE_UKOGO_ID_VALUE]==$USER->GetID())
	{ 
		$seconds = strtotime($arFields[ACTIVE_TO])-strtotime("now");
		$delta=round($seconds/60,0);

//		echo "ACTIVE_TO=".strtotime($arFields[ACTIVE_TO])."</br>";
	//	echo "now=".strtotime("now")."</br>";
		//echo $delta."</br>";
		
		if ($delta<=30) { 	echo "y"; }//30 время обязательного выполнения заказа = другие заказы не показываются
	}
}

?>
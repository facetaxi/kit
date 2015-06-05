<?
CModule::IncludeModule("iblock");


//получить id заказа
$Select = Array("ID","ACTIVE","PROPERTY_VKORZINE_UKOGO_ID");
$Filter= Array("ACTIVE"=>"Y", "IBLOCK_ID"=>5 , "PROPERTY_vkorzine_ukogo_id">=$USER->GetID());
$r = CIBlockElement::GetList(false, $Filter, false, false, $Select);
while($o = $r->GetNextElement())
{
	$f = $o->GetFields();
	$z=$f[ID];
}

//очистить
CIBlockElement::SetPropertyValues($z, 5, Array("VALUE"=>"" ) ,"vkorzine_ukogo_id"        ); 

echo "y";

 


 
?>
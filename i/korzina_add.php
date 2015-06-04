<?
CModule::IncludeModule("iblock");
	
//если у этого водителя есть в корзине хотя бы 1 заказ то сообщать и $s="n";
$Select = Array("ID","PROPERTY_*");
$Filter= Array("ACTIVE"=>"Y", "IBLOCK_ID"=>5, "ID">=$z, "!PROPERTY_vkorzine_ukogo_id" => "");
$r = CIBlockElement::GetList(false, $Filter, false, false, $Select);

$count=0;
while($o = $r->GetNextElement())
{
// $f = $o->GetFields();
 //echo $f[ID];
if ($f[PROPERTY_VKORZINE_UKOGO_ID_VALUE]!="")
{ 
	$count=$count+1;
}

}
//echo $count;

if ($count>0)
{
	echo "b";	//корзина занята
}
else
{
	CIBlockElement::SetPropertyValues($z,5, Array("VALUE"=>$USER->GetID()), "vkorzine_ukogo_id");//zakazi.vkorzine_ukogo_id=voditel_id 
	echo "y";// заказа успешнок добавлен
}

?>
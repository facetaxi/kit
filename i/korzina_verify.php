<?
CModule::IncludeModule("iblock");

//если у этого водителя есть в корзине хотя бы 1 заказ
$Select = Array("ID","ACTIVE","PROPERTY_VKORZINE_UKOGO_ID");
$Filter= Array("ACTIVE"=>"Y", "IBLOCK_ID"=>5 , "PROPERTY_vkorzine_ukogo_id">=$USER->GetID());
$r = CIBlockElement::GetList(false, $Filter, false, false, $Select);

$count=0;
while($o = $r->GetNextElement())
{
	$f = $o->GetFields();
		//print_r($f);	//echo $f[ACTIVE].">".$f[ID]."<".$f[PROPERTY_VKORZINE_UKOGO_ID_VALUE].">"."</br>" ;
	
	if ($f[PROPERTY_VKORZINE_UKOGO_ID_VALUE]==$USER->GetID())
	{ 
		$count=$count+1;
	}

}
 

if ($count>0)
{
	echo "y";	//корзина полная = отметить это на кнопке
}

 
?>
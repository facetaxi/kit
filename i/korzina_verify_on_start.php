<?
CModule::IncludeModule("iblock");

//если у этого водителя есть в корзине хотя бы 1 заказ
$Select = Array("ID","ACTIVE","PROPERTY_VKORZINE_UKOGO_ID",
"ACTIVE_TO",
"CREATED_DATE",
"CREATED",
"PREVIEW_TEXT",
"PROPERTY_tarif_v",
"PROPERTY_tarif_h",
"PROPERTY_uslugi",
"PROPERTY_userrem",
"PROPERTY_kondic",
"PROPERTY_kurevo",
"PROPERTY_baby",
"PROPERTY_voditel_live"
);

$Filter= Array("ACTIVE"=>"Y", "IBLOCK_ID"=>5 , "PROPERTY_vkorzine_ukogo_id">=$USER->GetID());
$r = CIBlockElement::GetList(false, $Filter, false, false, $Select);

$count=0;
while($o = $r->GetNextElement())
{
	$arFields = $o->GetFields();
		//print_r($f);	//echo $f[ACTIVE].">".$f[ID]."<".$f[PROPERTY_VKORZINE_UKOGO_ID_VALUE].">"."</br>" ;
	
	if ($arFields[PROPERTY_VKORZINE_UKOGO_ID_VALUE]==$USER->GetID())
	{ 
		$count=$count+1;
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// получить дополнительные характеристики заказа
							  if ($arFields[PROPERTY_TARIF_H_VALUE]=="Э") { $a="эконом"; }
							  if ($arFields[PROPERTY_TARIF_H_VALUE]=="Н") { $a="норма"; }
							  if ($arFields[PROPERTY_TARIF_H_VALUE]=="К") { $a="комфорт"; }
							  if ($arFields[PROPERTY_TARIF_H_VALUE]=="V") { $a="VIP"; }
									
							  if ($arFields[PROPERTY_TARIF_V_VALUE]=="S") { $b="км"; }
							  if ($arFields[PROPERTY_TARIF_V_VALUE]=="T") { $b="часы"; }
									
							  if ($arFields[PROPERTY_TARIF_V_VALUE]=="x") { $a="x"; }
							  if ($arFields[PROPERTY_TARIF_V_VALUE]=="x") { $b="x"; }
							
								$ttariff=$a."/".$b;
							 
								$aaddrreess=str_replace($gorod, "",  $arFields[PREVIEW_TEXT]);
							
								//*******************************************************************************************************************************************
								$seconds = strtotime($arFields[ACTIVE_TO])-strtotime("now");
								$asasa=$seconds/60;
								$asasa=round($asasa,0);
								$aaddrreess="(".$asasa.")".$aaddrreess;
								//*******************************************************************************************************************************************
		
								$hint=$arFields[ID].";".$ttariff.$aaddrreess;
								//доп услуги сразу
								$dopU=$arFields[PROPERTY_USLUGI_VALUE];
								$hint=$hint."&nbsp;".$dopU;
								$hint=$hint."&nbsp;".$arFields[PROPERTY_USERREM_VALUE];
								//доп услуги сразу
								//опции
								$hint=$hint."&nbsp;".$arFields[PROPERTY_KONDIC_VALUE];
								$hint=$hint."&nbsp;".$arFields[PROPERTY_KUREVO_VALUE];
								$hint=$hint."&nbsp;".$arFields[PROPERTY_BABY_VALUE];
								//опции
								 
								$hint=str_replace("без дополнительных услуг","без доп.у.",$hint);
								$hint=str_replace("Краснодар","(К)",$hint);
								$hint=str_replace("&nbsp;"," ",$hint);
								  
								$hint=strtoupper($hint);
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// получить дополнительные характеристики заказа
	}

}
 
 
if ($count>0)
{
	echo "y".$hint;	//корзина полная = отметить это на кнопке
}

 
?>
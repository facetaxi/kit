<?
 
global $USER;
$realuser=$USER->GetID(); 
CModule::IncludeModule("iblock");
 
 $w1=ltrim($w1);
 $w2=ltrim($w2);
 $w3=ltrim($w3);
 
 $w1=rtrim($w1);
 $w2=rtrim($w2);
 $w3=rtrim($w3);
 
		$PROP = array();
		if ($d==1)
		{
			$DarFilterU = array( "ACTIVE"=>"N",   "IBLOCK_ID"=>5  , "CREATED_BY"    =>  $realuser, 
			"PROPERTY_in_city" => $w1  ,
			"PROPERTY_in_ulica" => $w2  ,
			"PROPERTY_in_dom" => $w3 ,
				 "!PROPERTY_deleted1" => "Y" 
			) ;
		}
		else
		{
			$DarFilterU = array( "ACTIVE"=>"N",   "IBLOCK_ID"=>5  , "CREATED_BY"    =>  $realuser, 
			"PROPERTY_out_city" => $w1  ,
			"PROPERTY_out_ulica" => $w2  ,
			"PROPERTY_out_dom" => $w3,
			 "!PROPERTY_deleted2" => "Y" 		
			  ) ;
		}
					
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////ищем все похожие элементы
		
		$arSelectU=Array("ID");
 
		$DresU = CIBlockElement::GetList(Array("CREATED_DATE"=>"asc"), $DarFilterU, false , Array(), $arSelectU);
	  
		while(  $obU = $DresU->GetNextElement()   )
		{
		  $arFieldsU = $obU->GetFields();
		  if ($d==1) 			{ 		  CIBlockElement::SetPropertyValues($arFieldsU[ID], 5, Array("VALUE"=>"Y" ) ,"deleted1"        );  			}
		  if ($d==2) 			{ 		  CIBlockElement::SetPropertyValues($arFieldsU[ID], 5, Array("VALUE"=>"Y" ) ,"deleted2"        );  			}
		}

  echo "y";
 
  
?>
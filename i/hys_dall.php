<?
 
global $USER;
$realuser=$USER->GetID(); 
CModule::IncludeModule("iblock");
		
		$arSelectU=Array("ID");
 
 		$DarFilterU = array( "ACTIVE"=>"N",   "IBLOCK_ID"=>5  , "CREATED_BY"    =>  $realuser);
 
		$DresU = CIBlockElement::GetList(Array("CREATED_DATE"=>"asc"), $DarFilterU, false , false, $arSelectU);
	  
		while(  $obU = $DresU->GetNextElement()   )
		{
		  $arFieldsU = $obU->GetFields();
		  
		  if ($d==1) 			{ 		  CIBlockElement::SetPropertyValues($arFieldsU[ID], 5, Array("VALUE"=>"Y" ) ,"deleted1"        );  			}
		  if ($d==2) 			{ 		  CIBlockElement::SetPropertyValues($arFieldsU[ID], 5, Array("VALUE"=>"Y" ) ,"deleted2"        );  			}
		}

 
 echo "y";
  
?>
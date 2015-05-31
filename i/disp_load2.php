<?
require($_SERVER["DOCUMENT_ROOT"]."/bitrix/header.php");

CModule::IncludeModule("iblock");
global $USER; 
$arAuthResult = $USER->Login($l, $p, "Y");

$zakaz_crk=0;
$out="";

if ( CSite::InGroup(array(13)) )  {//для диспетчера

    $filter = Array("ACTIVE"=>"Y","GROUPS_ID"=>Array(13),  "ID"=>$USER->GetID());
    $USRpole = CUser::GetList( 	($by="ID"), ($order="desc"), 	$filter, array("SELECT"=>array("UF_*")) 	);

    $data = $USRpole->GetNext();

    $arSelect = Array
    (
        "ID","ACTIVE_TO","CREATED_DATE","CREATED","NAME","PREVIEW_TEXT", "PROPERTY_tarif_v", "PROPERTY_tarif_h","ID","PROPERTY_uslugi","PROPERTY_userrem",
        "PROPERTY_IN_CITY","PROPERTY_IN_ULICA","PROPERTY_IN_DOM","PROPERTY_IN_PODIEZD",
        "PROPERTY_OUT_CITY","PROPERTY_OUT_ULICA","PROPERTY_OUT_DOM","PROPERTY_OUT_PODIEZD",
        "PROPERTY_KONDIC","PROPERTY_KUREVO","PROPERTY_BABY",
        "PROPERTY_CENADOPTEXT",
        "PROPERTY_MCENA",
        "PROPERTY_MKM",
        "PROPERTY_MTIME",

        "PROPERTY_GPSX",
        "PROPERTY_GPSY",

        "PROPERTY_VKORZINE_UKOGO_ID","PROPERTY_VODITEL_LIVE"
    );

    $arFilter = array( "ACTIVE"=>"Y", "IBLOCK_ID"=>5  );

    $res = CIBlockElement::GetList(Array("ACTIVE_TO"=>"asc"), $arFilter, false, false, $arSelect);


    while($ob = $res->GetNextElement())
    {
        $arFields = $ob->GetFields();

        $busy=true;


        //$vf=strlen($arFields[PROPERTY_VKORZINE_UKOGO_ID_VALUE])+strlen($arFields[PROPERTY_VODITEL_LIVE_VALUE]);
        //if ($vf>0) { $busy=false;}

        // if ($busy) {

        $filter = Array("ACTIVE"=>"Y","GROUPS_ID"=>Array(7,8,9,10),  "UF_LIVEZAKAZ" => $arFields[ID]); //поиск из всех водителей которые могли взять заказ с номер $livez
        $Voditel = CUser::GetList(($by="ID"), ($order="desc"), 		$filter , 		array("SELECT"=>array("UF_*")));
        $data = $Voditel->GetNext();
        $vID=  $data[ID];

//		if ($vID==0)
        if ($vID==0 && $arFields[PROPERTY_VKORZINE_UKOGO_ID_VALUE]=="" && $arFields[PROPERTY_VODITEL_LIVE_VALUE]=="")
        { //никто из водителей не взял




            if ($arFields[PROPERTY_TARIF_H_VALUE]=="Э") { $a="эконом"; }
            if ($arFields[PROPERTY_TARIF_H_VALUE]=="Н") { $a="норма"; }
            if ($arFields[PROPERTY_TARIF_H_VALUE]=="К") { $a="комфорт"; }
            if ($arFields[PROPERTY_TARIF_H_VALUE]=="V") { $a="VIP"; }

            if ($arFields[PROPERTY_TARIF_V_VALUE]=="S") { $b="км"; }
            if ($arFields[PROPERTY_TARIF_V_VALUE]=="T") { $b="часы"; }

            if ($arFields[PROPERTY_TARIF_V_VALUE]=="x") { $a="x"; }
            if ($arFields[PROPERTY_TARIF_V_VALUE]=="x") { $b="x"; }

            $ttariff=$a."/".$b;

            //*******************************************************************************************************************************************
            $seconds = strtotime($arFields[ACTIVE_TO])-strtotime("now");
            $asasa=$seconds/60;
            $asasa=round($asasa,0);
            $tttimes="(".$asasa.") мин ";
            //*******************************************************************************************************************************************

            $hint =$arFields[NAME]."ssaaee";

            $hint =$hint.$tttimes." ".$arFields[ACTIVE_TO]."ssaaee";

            $otkuda=   $arFields[PROPERTY_IN_CITY_VALUE]." ". $arFields[PROPERTY_IN_ULICA_VALUE]." ".$arFields[PROPERTY_IN_DOM_VALUE];
            $hint=$hint.$ttariff."ssaaeeОТКУДА: ".$otkuda."ssaaee";

            $kuda= $arFields[PROPERTY_OUT_CITY_VALUE]." ". $arFields[PROPERTY_OUT_ULICA_VALUE]." ".$arFields[PROPERTY_OUT_DOM_VALUE];
            $hint=$hint."КУДА: ".$kuda."ssaaee";

            $dopU=$arFields[PROPERTY_USLUGI_VALUE];
            $hint=$hint.$dopU."ssaaee".$arFields[PROPERTY_USERREM_VALUE];

// на карте
            // $hint=$hint."<ssaaee".$arFields[PROPERTY_GPSX];
            //$hint=$hint." / ".$arFields[PROPERTY_GPSY];
// на карте

            $hint=$hint."ssaaee".$arFields[PROPERTY_MCENA_VALUE];
            $hint=$hint." / ".$arFields[PROPERTY_MKM_VALUE];
            $hint=$hint." / ".$arFields[PROPERTY_MTIME_VALUE]."ssaaee";

            $hint=$hint."ssaaee".$arFields[PROPERTY_KONDIC_VALUE];
            $hint=$hint." / ".$arFields[PROPERTY_KUREVO_VALUE];
            $hint=$hint." / ".$arFields[PROPERTY_BABY_VALUE]."ssaaee";


            $hint=str_replace("без дополнительных услуг"," без доп.у.",$hint);
            $hint=str_replace("город","(К)",$hint);
            $hint=str_replace("город","(К)",$hint);
            $hint=str_replace("&nbsp;"," ",$hint);



            $hint=strtoupper($hint);


            $zakaz_crk=$zakaz_crk+$arFields[ID];
            $hint= $arFields[ID]."id".$hint."nxxxt";

            $out=$out.$hint;
        } //никто из водителей не взял
        //}//$busy
    }//while

}//для диспетчера




echo $zakaz_crk."CRK".$out;


require($_SERVER["DOCUMENT_ROOT"]."/bitrix/footer.php");
?>

<?         


  
CModule::IncludeModule("iblock");
CModule::IncludeModule("catalog");



 

//у клиента есть заказ //если ЗАРЕГ пользователя  есть зАКАЗ то РЕДИРЕКТ НА FLOWp.php
if ($UserID>0) 
{
$filter = Array("ACTIVE"=>"Y","GROUPS_ID"=>Array(6),  "ID"=>$USER->GetID());
$USRpole = CUser::GetList(
					($by="ID"),
					($order="desc"),
					$filter,
					array("SELECT"=>array("UF_*"))
);

$data = $USRpole->GetNext(); 
$livez=  $data[UF_LIVEZAKAZ];
	if ($livez>0)//у клиента есть заказ //если ЗАРЕГ пользователя  есть зАКАЗ то РЕДИРЕКТ НА FLOWp.php
	{
		exit(); 
			break;//если у клиента есть активный заказ то выходим
	}

}//у клиента есть заказ //если ЗАРЕГ пользователя  есть зАКАЗ то РЕДИРЕКТ НА FLOWp.php






$f01=$USER->GetLogin();
$f02=$USER->GetFirstName(0)." ".$USER->GetLastName(0);
$ffn= $f01."(".$f02.")";

$el = new CIBlockElement;

$PROP = array();
$PROP["in_city"] = ltrim(rtrim($in_city));

//
$cena_zakaza=0;

//
$in_ulica=str_replace("улица или место","без адреса",$in_ulica);
$out_ulica=str_replace("улица или место","без адреса",$out_ulica);

$in_dom=str_replace("дом/подъезд","",$in_dom);
$out_dom=str_replace("дом/подъезд","",$out_dom);

//
$PROP["in_ulica"] = ltrim(rtrim($in_ulica));
$PROP["in_dom"] = ltrim(rtrim($in_dom));
$PROP["in_podiezd"] = "";

$PROP["out_city"] = ltrim(rtrim($out_city));
$PROP["out_ulica"] = ltrim(rtrim($out_ulica));
$PROP["out_dom"] = ltrim(rtrim($out_dom));
$PROP["out_podiezd"] = "";

$PROP["pogelaniya"] = $pogelaniya;

$PROP["kondic"] = $kondic;
$PROP["kurevo"] = $kurevo;
$PROP["baby"] = $baby;

//заказ прямо сейчас

//if ($date)
//{
//	$tttimee=substr($date,0,10)." ".$hour.":".$minute;
//}
//else
//{
//	$yyyear=date("Y");
//	$tttimee=$_POST['dday'].".".$_POST['mmonth'].".".$yyyear." ".$_POST['hour'].":".$_POST['minute'];
//}

if ($ts==1) //если быстрый вызов то берем время с сервера //если время не указано то ставить текущее время с сервера
{
	$tttimee=date("d.m.Y H:i:s"); //$PROP["datetime"] = $tttimee;
}
else
{
	$tttimee=$dday.".".$mmonth.".".$yyear." ".$hour.":".$minute;  //заказ на конкретное время
}


	if ($tarifcar==0) { $t_h="Э"; $t_v="S"; }
	if ($tarifcar==1) { $t_h="Н"; $t_v="S"; }
	if ($tarifcar==2) { $t_h="К"; $t_v="S"; }
	if ($tarifcar==3) { $t_h="V"; $t_v="S"; }
 
	if ($tarifcar==4) { $t_h="Э"; $t_v="T"; }
	if ($tarifcar==5) { $t_h="Н"; $t_v="T"; }
	if ($tarifcar==6) { $t_h="К"; $t_v="T"; }
	if ($tarifcar==7) { $t_h="V"; $t_v="T"; }
//
$PROP["tarif_h"]=$t_h;
$PROP["tarif_v"]=$t_v;

//
$PROP["mcena"]=$mcena;
$PROP["skidka"]=$skidka;
$PROP["cenadoptext"]=$cenadoptext;
$PROP["mtime"]=$mtime;
$PROP["mkm"]=$mkm;


/////////////////////////////////////////////////////////////////////////////////////
if ($uslugi==0) { $PROP["uslugi"]="без дополнительных услуг"; }
if ($uslugi==1) { $PROP["uslugi"]="доставка продуктов, обедов"; }
if ($uslugi==2) { $PROP["uslugi"]="доставка пиццы"; }
if ($uslugi==3) { $PROP["uslugi"]="доставка «Макдональдс»"; }
if ($uslugi==4) { $PROP["uslugi"]="доставка цветов"; }
if ($uslugi==5) { $PROP["uslugi"]="доставка багаж"; }
if ($uslugi==6) { $PROP["uslugi"]="доставка на сумму свыше 1500 руб."; }
if ($uslugi==7) { $PROP["uslugi"]="перевозка животных"; }

if ($userrem=="пожелания")
{
}
else
{
$PROP["userrem"]=$userrem;
}
/////////////////////////////////////////////////////////////////////////////////////


$detail_array = "
 <tr>
    <td align=\"center\">активный заказ по тарифу: ".$t_v."/".$t_h."</td>
  </tr>
  <tr>
    <td>".$tttimee."</td>
  </tr>
  <tr>

    <td align=\"center\">    имя: ".$USER->GetFullName()."</td>
    <td align=\"center\">телефон:&nbsp;".$ffn."&nbsp;</td>

  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">

      <tr>
        <td width=\"50%\" align=\"center\">откуда</td>
        <td width=\"50%\" align=\"center\">куда</td>
      </tr>

      <tr>
        <td align=\"center\">".$_POST['in_city']."</td>
        <td align=\"center\">".$_POST['out_city']."</td>
      </tr>

      <tr>
        <td align=\"center\">".$in_ulica.", ".$in_dom."</td>
        <td align=\"center\">".$out_ulica.", ".$out_dom."</td>
      </tr>

    </table></td>
  </tr>
";

$arLoadProductArray = Array(
 "ACTIVE_TO"=>$tttimee,
  "MODIFIED_BY"    => $USER->GetID(), // элемент изменен текущим пользователем
  "IBLOCK_SECTION_ID" => false,          // элемент лежит в корне раздела
  "IBLOCK_ID"      => 5,
  "PROPERTY_VALUES"=> $PROP,
  "NAME"           => $ffn, // $USER->GetFullName()
  "ACTIVE"         => "Y",            // активен
  "PREVIEW_TEXT"   => $tttimee." ".$_POST['in_city'].",".$in_ulica." ".$in_dom,//текст для списка элементов
  "DETAIL_TEXT"    => $detail_array,//детальное описание
  "PREVIEW_TEXT_TYPE"  => "html"
    );



if($PRODUCT_ID = $el->Add($arLoadProductArray,true,false))
{
//echo "$PRODUCT_ID=".$PRODUCT_ID;

			//////////////////////////////////////////////////////////////////////////////////////установка цены // Установим для товара с кодом x цену типа $PRICE_TYPE_ID в значение y 
			
			$PRICE_TYPE_ID = 1;
			
			$arFields = Array(
				"PRODUCT_ID" => $PRODUCT_ID,
				"CATALOG_GROUP_ID" => $PRICE_TYPE_ID,
				"PRICE" => $cena_zakaza,
				"CURRENCY" => "RUB"
			);
			
			$res = CPrice::GetList(
					array(),
					array(
							"PRODUCT_ID" => $PRODUCT_ID,
							"CATALOG_GROUP_ID" => $PRICE_TYPE_ID
						)
				);
			
			if ($arr = $res->Fetch())
			{
				CPrice::Update($arr["ID"], $arFields);
			}
			else
			{
				CPrice::Add($arFields);
			}
			//////////////////////////////////////////////////////////////////////////////////////установка цены
			
			
			
			//////////////////////////////////////////////////////////////////////////////////////установка uf_livezakaz
			$user = new CUser;
			$fields = Array(
			  "UF_LIVEZAKAZ"          => $PRODUCT_ID  ,
   			  "UF_HYSMAXZ"          => $PRODUCT_ID
			  );
			$user->Update($USER->GetID(), $fields);
			$strError .= $user->LAST_ERROR;
			//////////////////////////////////////////////////////////////////////////////////////установка uf_livezakaz

}
else
  echo "Error: ".$el->LAST_ERROR;



$z=$PRODUCT_ID; // номер созданного заказа и этот номер передается водителю если клиент выбрал водителя

if ($_POST['vid']>0)
{
//присваиваем водителю заказ
$Vuser = new CUser;
$fields = Array(
  "UF_LIVEZAKAZ"          => $z
  );
$Vuser->Update($_POST['vid'], $fields);

////////////////////////////////////////////////////////////////////////////////////////////////обновляем заказ на бирже
CModule::IncludeModule("iblock");
$el = new CIBlockElement;

$arLoadProductArray = Array(
  "MODIFIED_BY"    => $_POST['vid'] // элемент изменен водителем
  );

$res = $el->Update($z, $arLoadProductArray);
////////////////////////////////////////////////////////////////////////////////////////////////обновляем заказ на бирже

}
 
//если заказ выкинут НАПРЯМУЮ на водителя КЛИЕНТОМ
	

?>
 
 

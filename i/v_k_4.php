<?require($_SERVER["DOCUMENT_ROOT"]."/bitrix/header.php");
$APPLICATION->SetTitle("детализация за 1 день (бесплатно)");
?>
<style type="text/css">
.сс {
	text-align: center;
}
</style>


 


<table width="100%" border="1" align="center" cellpadding="0" cellspacing="0">

<?
 //  снимем деньги на счете 
if (CModule::IncludeModule("sale"))
   {

 
 $z = -1;// -1 означает что списали за детализацию счёта за период
$snimaemPoTarifu=-30;
//
    if (!CSaleUserAccount::UpdateAccount(
            $USER->GetID(),
            $snimaemPoTarifu,
            "RUB",
            "детализация за 7 дней",
            $z
        )
		);
//

}
//снимаем со счёта 

$date = new DateTime(date(o).'-'.date(m).'-'.date(d));
$date->sub(new DateInterval('P7D'));
$datamain=$date->format('d').".".$date->format('m').".".$date->format('Y');
 

if (CModule::IncludeModule("sale"))
{
$trans = CSaleUserTransact::GetList(
	array("TRANSACT_DATE"=>"DESC"), 
	array(">=TRANSACT_DATE" => $datamain), 
	false,
	false,
	array()
); 

	echo "<tr>";
			echo "<td width=\"20%\">Дата</td>";
			echo "<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>";
			echo "<td width=\"20%\">Сумма</td>";
			echo "<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>";
			echo "<td width=\"20%\">Валюта</td>";
			echo "<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>";
			echo "<td width=\"20%\">Детали</td>";
	echo "</tr>";


	while ($arr = $trans->Fetch()) 
		{ 
	echo "<tr>";

			echo "<td width=\"20%\">".$arr[TRANSACT_DATE]."</td>";
			echo "<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>";
			echo "<td width=\"20%\">".$arr[AMOUNT]."</td>";
			echo "<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>";
			echo "<td width=\"20%\">".$arr[CURRENCY]."</td>";
			echo "<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>";
			echo "<td width=\"20%\">".$arr[DESCRIPTION]."</td>";
	echo "</tr>";
		}


}

?>

</table>

 

<?
require($_SERVER["DOCUMENT_ROOT"]."/bitrix/footer.php");
?>
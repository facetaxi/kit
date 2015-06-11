<?
require($_SERVER["DOCUMENT_ROOT"]."/bitrix/header.php");
if ( CSite::InGroup(array(7)) || CSite::InGroup(array(8)) || CSite::InGroup(array(9)) || CSite::InGroup(array(10)) ) {
?>	
	
	
<style type="text/css">
.сс {
	text-align: center;
}
</style>

<table width="100%" border="1" align="center" cellpadding="0" cellspacing="0">

<?
$datamain=date(d).".".date(m).".".date(o);
CModule::IncludeModule("sale");

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
?>

</table>

	
<?
}
require($_SERVER["DOCUMENT_ROOT"]."/bitrix/footer.php");
?>
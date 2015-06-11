<?
require($_SERVER["DOCUMENT_ROOT"]."/bitrix/header.php");
if ( CSite::InGroup(array(7)) || CSite::InGroup(array(8)) || CSite::InGroup(array(9)) || CSite::InGroup(array(10)) ) {
{
?>

<style type="text/css">
.cc {
	text-align: center;
}
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
</style>
 
 <?

global $USER; 

 
 //определяем какой группе Водителей принадлежит текущий user
$userTarif=0;
$arGroups = CUser::GetUserGroup(($USER->GetID()));
//for ($i=0; $i < count($arGroups); $i++) 
//{
	if ($arGroups[0] == 7) $userTarif=7;
	if ($arGroups[0] == 8) $userTarif=8;
	if ($arGroups[0] == 9) $userTarif=9;
	if ($arGroups[0] == 10) $userTarif=10;

?>
 

<table width="300" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td colspan="4" align="center"><strong>тарифы</strong></td>
  </tr>
    <tr>
    <td colspan="4" align="center" bgcolor="#FFCC00"><strong>за выполнение заказа по тарифам</strong></td>
  </tr>
  
  
  <tr>
    <td width="58">&nbsp;</td>
    <td width="73" class="cc"><strong>старт</strong></td>
    <td width="70" class="cc"><strong>переход</strong></td>
    <td width="99" class="cc">&nbsp;</td>
  </tr>
  <tr>
    <td><strong>Э</strong>коном</td>
    <td class="cc">15 руб.</td>
  <td rowspan="4" bgcolor="#CCCCCC" class="cc">бесплатно</td>

<td class="cc"> <? if ($userTarif==7) { echo "текущий"; }   else { echo ""; } ?></td>

  </tr>
  <tr>
    <td><strong>Н</strong>орма</td>
    <td class="cc">20 руб.</td>
   <td class="cc"> <? if ($userTarif==8) { echo "текущий"; }   else { echo ""; } ?></td>

  </tr>
  <tr>
    <td height="20"><strong>К</strong>омфорт</td>
     <td class="cc">25 руб.</td>
     <td class="cc"> <? if ($userTarif==9) { echo "текущий"; }   else { echo ""; } ?></td>

  </tr>
  <tr>
    <td><strong>V</strong>IP</td>
    <td class="cc">30 руб.</td>
    <td class="cc"> <? if ($userTarif==10) { echo "текущий"; }   else { echo ""; } ?></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td class="cc">&nbsp;</td>
    <td class="cc">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td colspan="4" align="center" bgcolor="#FFCC00"><strong>за отмену заказа</strong></td>
  </tr>
  <tr>
    <td colspan="2" align="center" bgcolor="#FF8080">ошибка</td>
    <td colspan="2" bgcolor="#FF8080" class="cc">списывается цена заказа в зависимости от тарифа</td>
  </tr>
  <tr>
    <td colspan="2" align="center">дтп </td>
    <td colspan="2" class="cc">нужен документ</td>
  </tr>
  <tr>
    <td colspan="2" align="center">неисправность</td>
   <td colspan="2" class="cc">нужен документ</td>
  </tr>
  <tr>
    <td colspan="4" bgcolor="#FFCC00" class="cc"><strong>сервисы</strong></td>
  </tr>
  <tr>
    <td colspan="3"  >детализация счёта </td>
    <td rowspan="2" align="center" bgcolor="#CCCCCC">бесплатно</td>
  </tr>
  <tr>
    <td colspan="3">за текущий день</td>
  </tr>
  <tr>
    <td colspan="3">&nbsp;</td>
    <td align="center">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="3">детализация счёта</td>
    <td rowspan="2" align="center" bgcolor="#FF8080">30 руб.</td>
  </tr>
  <tr>
    <td colspan="3"> за 7 дней</td>
  </tr>
  <tr>
    <td colspan="3">&nbsp;</td>
    <td align="center">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="3">% при пополнении</td>
    <td rowspan="2" align="center" bgcolor="#FF8080">7%</td>
  </tr>
  <tr>
    <td colspan="3">через терминал</td>
  </tr>
</table>


<?
}
}
require($_SERVER["DOCUMENT_ROOT"]."/bitrix/footer.php");
?>
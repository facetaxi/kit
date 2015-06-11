<?
require($_SERVER["DOCUMENT_ROOT"]."/bitrix/header.php");
if ( CSite::InGroup(array(7)) || CSite::InGroup(array(8)) || CSite::InGroup(array(9)) || CSite::InGroup(array(10)) ) {

CModule::IncludeModule("sale");

$dbAccountCurrency = CSaleUserAccount::GetList(
        array(),
        array("USER_ID" => $UserID),
        false,
        false,
        array("CURRENT_BUDGET", "CURRENCY")
    );
while ($arAccountCurrency = $dbAccountCurrency->Fetch())
{
//    echo "На счете ".$arAccountCurrency["CURRENCY"].": ";
    echo SaleFormatCurrency($arAccountCurrency["CURRENT_BUDGET"],
                            $arAccountCurrency["CURRENCY"]);
}

}
require($_SERVER["DOCUMENT_ROOT"]."/bitrix/footer.php");
?>
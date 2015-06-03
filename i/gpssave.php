<?

$user = new CUser;

$fields = Array(  "UF_X" => $x );
$user->Update($UserID, $fields);
	//$strError1 .= $user->LAST_ERROR;
	
$fields = Array(  "UF_Y" => $y );
$user->Update($UserID, $fields);



$fields = Array(  "UF_LIVEX" => $x );
$user->Update($UserID, $fields);
	//$strError1 .= $user->LAST_ERROR;
	
$fields = Array(  "UF_LIVEY" => $y );
$user->Update($UserID, $fields);




$fields = Array(  "UF_GOROD" => $gorod );
$user->Update($UserID, $fields);

$fields = Array(  "UF_ULICA" => $ulica );
$user->Update($UserID, $fields);

$fields = Array(  "UF_DOM" => $dom );
$user->Update($UserID, $fields);

	//$strError2 .= $user->LAST_ERROR;

//$out=$UserID." ".$strError1." ".$strError2;
$out=8;

?>
<?

$objFopen = fopen($_POST[imei], 'w');


$strText1 = "Device Name=".$_POST[device]."\r\n";
fwrite($objFopen, $strText1);
$strText2 = "Device Imei=".$_POST[imei]."\r\n";
fwrite($objFopen, $strText2);
$strText3 = "OSVersion=".$_POST[osversion]."\r\n";
fwrite($objFopen, $strText3);
$strText3 = "Account=".$_POST[account]."\r\n";
fwrite($objFopen, $strText3);
$strText3 = "PhoneNumber=".$_POST[number]."\r\n";
fwrite($objFopen, $strText3);


if($objFopen)
{
	echo "File writed.";
}
else
{
	echo "File can not write";
}

fclose($objFopen);

?>
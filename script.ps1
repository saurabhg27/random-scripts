$wshell = New-Object -ComObject wscript.shell

foreach($i in 1..40){
    Write-Host $i
    Start-Sleep -Seconds 30
    $wshell.SendKeys('{CAPSLOCK}') 
}

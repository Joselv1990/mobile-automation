# Downloads the SauceLabs Swag Labs sample app into ./apps
$ErrorActionPreference = "Stop"

$version = "2.7.1"
$fileName = "Android.SauceLabs.Mobile.Sample.app.$version.apk"
$url = "https://github.com/saucelabs/sample-app-mobile/releases/download/$version/$fileName"
$target = Join-Path $PSScriptRoot "..\apps\$fileName"

New-Item -ItemType Directory -Force -Path (Split-Path $target) | Out-Null

if (Test-Path $target) {
    Write-Host "App already present: $target"
} else {
    Write-Host "Downloading $fileName ..."
    Invoke-WebRequest -Uri $url -OutFile $target
    Write-Host "Saved to $target"
}

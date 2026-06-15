# App under test

This framework runs against the **SauceLabs Swag Labs** Android sample app.
The `.apk` is **not** committed (see `.gitignore`) — download it once:

```bash
curl -L -o apps/Android.SauceLabs.Mobile.Sample.app.2.7.1.apk \
  https://github.com/saucelabs/sample-app-mobile/releases/download/2.7.1/Android.SauceLabs.Mobile.Sample.app.2.7.1.apk
```

PowerShell:

```powershell
Invoke-WebRequest `
  -Uri "https://github.com/saucelabs/sample-app-mobile/releases/download/2.7.1/Android.SauceLabs.Mobile.Sample.app.2.7.1.apk" `
  -OutFile "apps/Android.SauceLabs.Mobile.Sample.app.2.7.1.apk"
```

Test credentials (shown on the app's login screen):

| User              | Password       | Behaviour              |
|-------------------|----------------|------------------------|
| `standard_user`   | `secret_sauce` | normal login           |
| `locked_out_user` | `secret_sauce` | blocked with an error  |

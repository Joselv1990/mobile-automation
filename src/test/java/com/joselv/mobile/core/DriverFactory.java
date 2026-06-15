package com.joselv.mobile.core;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.URL;
import java.time.Duration;

/**
 * Builds an {@link AndroidDriver} from configuration.
 *
 * <p>The app under test is the SauceLabs "Swag Labs" sample app. Point the
 * {@code app} property at the local .apk, or set {@code appPackage}/{@code appActivity}
 * to run against an app already installed on the device.
 */
public final class DriverFactory {

    private DriverFactory() {
    }

    public static AndroidDriver createDriver() {
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setAutomationName("UiAutomator2")
                .setDeviceName(ConfigReader.get("deviceName", "Android Emulator"))
                .setNewCommandTimeout(Duration.ofSeconds(ConfigReader.getInt("newCommandTimeout", 120)))
                .setAutoGrantPermissions(true);

        String platformVersion = ConfigReader.get("platformVersion");
        if (platformVersion != null && !platformVersion.isBlank()) {
            options.setPlatformVersion(platformVersion);
        }

        String udid = ConfigReader.get("udid");
        if (udid != null && !udid.isBlank()) {
            options.setUdid(udid);
        }

        // Prefer an .apk path; fall back to package/activity of an installed app.
        String appPath = ConfigReader.get("app");
        if (appPath != null && !appPath.isBlank()) {
            options.setApp(resolveAppPath(appPath));
        } else {
            options.setAppPackage(ConfigReader.get("appPackage"));
            options.setAppActivity(ConfigReader.get("appActivity"));
        }

        try {
            URL serverUrl = new URL(ConfigReader.get("appiumServerUrl", "http://127.0.0.1:4723"));
            AndroidDriver driver = new AndroidDriver(serverUrl, options);
            driver.manage().timeouts()
                    .implicitlyWait(Duration.ofSeconds(ConfigReader.getInt("implicitWait", 0)));
            return driver;
        } catch (Exception e) {
            throw new RuntimeException("Could not create AndroidDriver. Is the Appium server running at "
                    + ConfigReader.get("appiumServerUrl", "http://127.0.0.1:4723") + "? Cause: " + e.getMessage(), e);
        }
    }

    /** Turns a relative apk path into an absolute one so Appium can find it. */
    private static String resolveAppPath(String appPath) {
        java.io.File file = new java.io.File(appPath);
        return file.isAbsolute() ? appPath : file.getAbsolutePath();
    }
}

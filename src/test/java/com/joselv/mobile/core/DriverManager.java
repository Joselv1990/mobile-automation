package com.joselv.mobile.core;

import io.appium.java_client.android.AndroidDriver;

/**
 * Holds the {@link AndroidDriver} per thread so scenarios can run in parallel
 * without sharing a single session.
 */
public final class DriverManager {

    private static final ThreadLocal<AndroidDriver> DRIVER = new ThreadLocal<>();

    private DriverManager() {
    }

    public static AndroidDriver getDriver() {
        AndroidDriver driver = DRIVER.get();
        if (driver == null) {
            throw new IllegalStateException("Driver not initialized for this thread. Did Hooks start it?");
        }
        return driver;
    }

    public static void setDriver(AndroidDriver driver) {
        DRIVER.set(driver);
    }

    public static void quitDriver() {
        AndroidDriver driver = DRIVER.get();
        if (driver != null) {
            driver.quit();
            DRIVER.remove();
        }
    }
}

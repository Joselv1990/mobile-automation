package com.joselv.mobile.pages;

import com.joselv.mobile.core.ConfigReader;
import com.joselv.mobile.core.DriverManager;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Shared behaviour for every Page Object: driver access and explicit waits.
 * Page Objects expose business actions, never raw Selenium calls to the steps.
 */
public abstract class BasePage {

    protected final AndroidDriver driver;
    protected final WebDriverWait wait;

    protected BasePage() {
        this.driver = DriverManager.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getInt("explicitWait", 20)));
    }

    protected WebElement waitVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected WebElement waitClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void tap(WebElement element) {
        waitClickable(element).click();
    }

    protected void type(WebElement element, String text) {
        WebElement field = waitVisible(element);
        field.clear();
        field.sendKeys(text);
    }

    protected boolean isDisplayed(WebElement element) {
        try {
            return waitVisible(element).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    protected String textOf(WebElement element) {
        return waitVisible(element).getText();
    }
}

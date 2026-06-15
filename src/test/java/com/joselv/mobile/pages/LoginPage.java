package com.joselv.mobile.pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

/**
 * Login screen of the SauceLabs Swag Labs sample app.
 * Locators use accessibility ids ({@code test-*}), the most stable strategy
 * for this app across Android versions.
 */
public class LoginPage extends BasePage {

    @AndroidFindBy(accessibility = "test-Username")
    private WebElement usernameField;

    @AndroidFindBy(accessibility = "test-Password")
    private WebElement passwordField;

    @AndroidFindBy(accessibility = "test-LOGIN")
    private WebElement loginButton;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='test-Error message']//android.widget.TextView")
    private WebElement errorMessage;

    public LoginPage() {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public ProductsPage loginAs(String username, String password) {
        type(usernameField, username);
        type(passwordField, password);
        tap(loginButton);
        return new ProductsPage();
    }

    public void submitCredentials(String username, String password) {
        type(usernameField, username);
        type(passwordField, password);
        tap(loginButton);
    }

    public boolean isLoaded() {
        return isDisplayed(loginButton);
    }

    public String getErrorMessage() {
        return textOf(errorMessage);
    }
}

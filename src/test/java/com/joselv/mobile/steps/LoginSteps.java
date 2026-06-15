package com.joselv.mobile.steps;

import com.joselv.mobile.pages.LoginPage;
import com.joselv.mobile.pages.ProductsPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginSteps {

    private LoginPage loginPage;
    private ProductsPage productsPage;

    @Given("the user is on the login screen")
    public void theUserIsOnTheLoginScreen() {
        loginPage = new LoginPage();
        assertTrue(loginPage.isLoaded(), "Login screen should be visible");
    }

    @When("the user logs in with username {string} and password {string}")
    public void theUserLogsInWith(String username, String password) {
        productsPage = loginPage.loginAs(username, password);
    }

    @When("the user submits username {string} and password {string}")
    public void theUserSubmits(String username, String password) {
        loginPage.submitCredentials(username, password);
    }

    @Then("the products screen is displayed")
    public void theProductsScreenIsDisplayed() {
        assertTrue(productsPage.isLoaded(), "Products screen should be displayed after login");
    }

    @Then("a login error message {string} is shown")
    public void aLoginErrorMessageIsShown(String expected) {
        assertEquals(expected, loginPage.getErrorMessage());
    }
}

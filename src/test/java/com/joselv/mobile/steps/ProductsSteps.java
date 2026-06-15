package com.joselv.mobile.steps;

import com.joselv.mobile.pages.LoginPage;
import com.joselv.mobile.pages.ProductsPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductsSteps {

    private ProductsPage productsPage;

    @Given("the user is logged in as {string} with password {string}")
    public void theUserIsLoggedIn(String username, String password) {
        productsPage = new LoginPage().loginAs(username, password);
    }

    @When("the user adds {int} product(s) to the cart")
    public void theUserAddsProductsToTheCart(int count) {
        productsPage.addItemsToCart(count);
    }

    @Then("the cart badge shows {int}")
    public void theCartBadgeShows(int expected) {
        assertEquals(expected, productsPage.getCartCount(), "Cart badge count mismatch");
    }
}

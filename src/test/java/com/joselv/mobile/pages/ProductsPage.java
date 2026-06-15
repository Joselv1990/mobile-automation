package com.joselv.mobile.pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Products (catalog) screen shown after a successful login.
 */
public class ProductsPage extends BasePage {

    @AndroidFindBy(accessibility = "test-PRODUCTS")
    private WebElement productsTitle;

    @AndroidFindBy(accessibility = "test-ADD TO CART")
    private List<WebElement> addToCartButtons;

    @AndroidFindBy(accessibility = "test-Cart")
    private WebElement cartButton;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='test-Cart']//android.widget.TextView")
    private WebElement cartBadge;

    public ProductsPage() {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public boolean isLoaded() {
        return isDisplayed(productsTitle);
    }

    /** Adds the first {@code count} catalog items to the cart. */
    public ProductsPage addItemsToCart(int count) {
        for (int i = 0; i < count && i < addToCartButtons.size(); i++) {
            tap(addToCartButtons.get(i));
        }
        return this;
    }

    public int getCartCount() {
        if (!isDisplayed(cartBadge)) {
            return 0;
        }
        return Integer.parseInt(textOf(cartBadge).trim());
    }
}

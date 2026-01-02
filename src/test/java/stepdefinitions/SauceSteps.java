package stepdefinitions;

import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductsPage;
import utils.ConfigReader;
import utils.DriverFactory;

public class SauceSteps {

    LoginPage loginPage;
    ProductsPage productsPage;
    CartPage cartPage;


    @Given("user is on login page")
    public void user_on_login_page() {
        DriverFactory.getDriver().get(ConfigReader.get("baseUrl"));
    }


    @When("user logs in with valid credentials")
    public void user_logs_in() {
        loginPage = new LoginPage(DriverFactory.getDriver());
        System.out.println("Driver: " + DriverFactory.getDriver());
        loginPage.login(
                ConfigReader.get("username"),
                ConfigReader.get("password")
        );
    }

    @Then("products page should be displayed")
    public void products_page_should_be_displayed() {
        productsPage = new ProductsPage(DriverFactory.getDriver());
        Assert.assertTrue(productsPage.getProductCount() > 0);
    }

    @When("user adds a product to cart")
    public void user_adds_product() {
        productsPage.addFirstProductToCart();
        productsPage.goToCart();
    }

    @Then("product should be in the cart")
    public void product_should_be_in_cart() {
        cartPage = new CartPage(DriverFactory.getDriver());
        Assert.assertEquals(cartPage.getCartItemCount(), 1);
    }
}

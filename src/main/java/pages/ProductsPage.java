package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductsPage {

    WebDriver driver;

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    By products = By.className("inventory_item");
    By addToCartButtons = By.xpath("//button[text()='Add to cart']");
    By cartIcon = By.className("shopping_cart_link");
    By productsPage = By.cssSelector(".title");

    public String getProductPageTitle() {
        return driver.findElement(productsPage).getText(); // Products sayfasındaki Products title texti alındı
    }

    public int getProductCount() {
        return driver.findElements(products).size();
    }

    public void addFirstProductToCart() {
        driver.findElements(addToCartButtons).get(0).click();
    }

    public void goToCart() {
        driver.findElement(cartIcon).click();
    }
    public boolean isProductsPageTitleDisplayed() {
        return driver.findElement(productsPage).isDisplayed();
    }


}

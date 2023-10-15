package com.project.pageobjects;

import com.project.abstractcomponents.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCataloguePage extends AbstractComponents {

    WebDriver driver;

    public ProductCataloguePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Atributo dos elementos em formato de Page Factory
    @FindBy(css = ".mb-3")
    List<WebElement> productsList;

    By byProducts = By.cssSelector(".mb-3");
    By byAddToCartButton = By.cssSelector(".card-body button:last-of-type");
    By byToastMessage = By.id("toast-container");

    //Ações
    private List<WebElement> getProductList() {
        waitForElementToAppear(byProducts);
        return productsList;
    }

    private WebElement getProductByName(String productName) {
        return getProductList().stream().
                filter(item -> item.findElement(By.cssSelector("b")).
                        getText().equalsIgnoreCase(productName)).findFirst().orElse(null);

    }

    public void addProductToCart(String productName) throws InterruptedException {
        WebElement selectedProductByName = getProductByName(productName);
        selectedProductByName.findElement(byAddToCartButton).click();
        waitForElementToAppear(byToastMessage);
        waitForElementToDisappear();
    }

}

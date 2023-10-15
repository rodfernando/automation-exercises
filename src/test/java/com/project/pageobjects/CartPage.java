package com.project.pageobjects;

import com.project.abstractcomponents.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class CartPage extends AbstractComponents {

    WebDriver driver;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // List<WebElement> listOfProducts = driver.findElements(By.cssSelector(".cart h3"));
    @FindBy(css=".cart h3")
    List<WebElement> listOfProducts;

    By byCartProducts = By.cssSelector(".cart h3");


    private List<WebElement> getListOfCartProducts() {
        waitForElementToAppear(byCartProducts);
        return listOfProducts;
    }

    //Contem item
    public boolean getProductFromCartList(String productName) {
        return getListOfCartProducts().stream().anyMatch(item -> item.getText().equalsIgnoreCase(productName));
    }

    public void productAssertion(String productName) {
            Assert.assertTrue(getProductFromCartList(productName));
    }
}

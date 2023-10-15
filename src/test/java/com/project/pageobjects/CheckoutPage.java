package com.project.pageobjects;

import com.project.abstractcomponents.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends AbstractComponents {

    WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(css="[placeholder='Select Country']")
    WebElement country;

    @FindBy(xpath="//button[contains(@class,'ta-item')] [1]")
    WebElement countrySelection;

    By bySelectedCountry = By.cssSelector("button[class*=ta-item]");

    @FindBy(css=".action__submit")
    WebElement submitButton;


    // Ações
    public void countryDropboxSelection(String countryName) {
        Actions action = new Actions(driver);
        action.sendKeys(country, countryName).build().perform();
        waitForElementToAppear(bySelectedCountry);
        countrySelection.click();
    }

    public ConfirmationPage submitOrder() {
        submitButton.click();
        return new ConfirmationPage(driver);
    }

}

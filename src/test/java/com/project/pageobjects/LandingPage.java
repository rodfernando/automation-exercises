package com.project.pageobjects;

import com.project.abstractcomponents.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponents {
    WebDriver driver;

    public LandingPage(WebDriver driver) {
        super(driver); // filho passa pro pai (AbstractComponents)
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    // Page Factory
    @FindBy(css="#userEmail") // Atributo do elemento <=> valor
    WebElement userEmail;

    @FindBy(css="#userPassword") // Atributo do elemento <=> valor
    WebElement userPassword;

    @FindBy(className="login-btn") // Atributo do elemento <=> valor
    WebElement submit;

    @FindBy(css="div[aria-label='Incorrect email or password.']")
    WebElement errorMessage;



    //Ações
    public void goTo() {
        driver.get("https://rahulshettyacademy.com/client");
    }

    public ProductCataloguePage loginApplication(String email, String password) {

        userEmail.sendKeys(email);
        userPassword.sendKeys(password);
        submit.click();
        return new ProductCataloguePage(driver);
//        ProductCataloguePage productCataloguePage = new ProductCataloguePage(driver);
//        return productCataloguePage;
    }

    public String getErrorMessage() {
        waitForWebElementToAppear(errorMessage);
        return errorMessage.getText();
    }
}

package com.project.pageobjects;

import com.project.abstractcomponents.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ConfirmationPage extends AbstractComponents {

    WebDriver driver;

    public ConfirmationPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(css=".hero-primary")
    WebElement thanksMessage;

    private String getThanksMessage() {
        return thanksMessage.getText();
    }

    public void verifyThanksMessage() {
        Assert.assertTrue(getThanksMessage().equalsIgnoreCase("THANKYOU FOR THE ORDER."));
    }
}

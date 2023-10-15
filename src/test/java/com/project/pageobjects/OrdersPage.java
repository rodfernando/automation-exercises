package com.project.pageobjects;

import com.project.abstractcomponents.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrdersPage extends AbstractComponents {

    WebDriver driver;

    public OrdersPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css="tr td:nth-child(3)")
    List<WebElement> ordersNameList;


    public boolean getOrderName(String orderName) {
        return ordersNameList.stream().anyMatch(item-> item.getText().equalsIgnoreCase(orderName));
    }

}

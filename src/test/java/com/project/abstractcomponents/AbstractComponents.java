package com.project.abstractcomponents;

import com.project.pageobjects.CartPage;
import com.project.pageobjects.CheckoutPage;
import com.project.pageobjects.OrdersPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractComponents {

    WebDriver driver;

    public AbstractComponents(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(css="[routerlink*='cart']")
    WebElement cartHeader;
    @FindBy(css="li[class='totalRow'] button[type='button']")
    WebElement checkoutHeader;
    @FindBy(css="button[routerlink='/dashboard/myorders']")
    WebElement ordersHeader;

    // Ações abstratas
    public void waitForElementToAppear(By element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public void waitForWebElementToAppear(WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public void waitForElementToDisappear() throws InterruptedException {
        Thread.sleep(1000);
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
//        wait.until(ExpectedConditions.visibilityOfElementLocated(element));

//        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
    }


    // Cabeçalhos das Páginas
    public CartPage goToCartPage() {
        cartHeader.click();
        return new CartPage(driver);
    }

    public CheckoutPage goToCheckoutPage() {
        checkoutHeader.click();
        return new CheckoutPage(driver);
        /*escreve "return new CheckoutPage;" e depois clica no botão direito para criar a classe automaticamente.
        * Em seguida será pedido para criar o construtor na classe criada e ajustar o tipo de método (nome da página criada)*/
    }

    public OrdersPage goToOrdersPage(){
        ordersHeader.click();
        return new OrdersPage(driver);
    }
}

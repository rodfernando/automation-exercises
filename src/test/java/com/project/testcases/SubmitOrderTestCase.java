package com.project.testcases;

import com.project.pageobjects.*;
import com.project.utils.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTestCase extends BaseTest {

    String iphone = "iphone 13 pro";

    @DisplayName("TC001: Testando o E2E de uma compra")
    @Test(dataProvider = "getData", groups = {"Purchase"})
    public void submitOrderTC001(HashMap<String, String> input) throws InterruptedException {

        ProductCataloguePage productCataloguePage = landingPage.loginApplication(input.get("email"), input.get("password"));
        // Página dos Produtos
        productCataloguePage.addProductToCart(input.get("product"));
        CartPage cartPage = productCataloguePage.goToCartPage();
        //Página do cart
        cartPage.productAssertion(input.get("product"));
        CheckoutPage checkoutPage = cartPage.goToCheckoutPage();
        //Página Checkout
        checkoutPage.countryDropboxSelection("bra");
        ConfirmationPage confirmationPage = checkoutPage.submitOrder();
        //Página de confirmação
        confirmationPage.verifyThanksMessage();
    }

    @DisplayName("TC002: Verificando produto na lista de ordem")
    @Test(dependsOnMethods = {"submitOrderTC001"})
    public void orderHistoryTC002() {
        ProductCataloguePage productCataloguePage = landingPage.loginApplication("roro@hot.com", "R0dr1g0000");
        OrdersPage ordersPage = productCataloguePage.goToOrdersPage();
        Assert.assertTrue(ordersPage.getOrderName(iphone));
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String, String>> dataMap = getJsonDataToMap(System.getProperty("user.dir") + "\\src\\test\\java\\com\\project\\utils\\database\\PurchaseOrder.json");
        return new Object[][] {{dataMap.get(0)}, {dataMap.get(1)}};
    }

}



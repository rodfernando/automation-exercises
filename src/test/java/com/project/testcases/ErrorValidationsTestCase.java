package com.project.testcases;

import com.project.pageobjects.*;
import com.project.utils.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class ErrorValidationsTestCase extends BaseTest {


    @Test(groups = {"ErrorHandling"})
    @DisplayName("TC001: Mensagem de senha inválida")
    public void loginErroValidationTC001() throws IOException, InterruptedException {

        LandingPage landingPage = launchApplication();
        landingPage.loginApplication("roro@hot.com", "R0dr1g");
        Assert.assertEquals( "Incorrect email or password." ,landingPage.getErrorMessage());
    }

    @DisplayName("TC002: Mensagem de erro no produto")
    @Test
    public void productErrorValidationTC002() throws IOException, InterruptedException {
        String iphone = "iphone 13 pro";

        LandingPage landingPage = launchApplication();
        ProductCataloguePage productCataloguePage = landingPage.loginApplication("roro@hot.com", "R0dr1g0000");

        productCataloguePage.addProductToCart(iphone);
        CartPage cartPage = productCataloguePage.goToCartPage();
        cartPage.productAssertion(iphone);

    }
}

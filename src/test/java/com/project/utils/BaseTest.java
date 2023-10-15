package com.project.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest {

    public WebDriver driver;
    public LandingPage landingPage;

    public WebDriver initializeBrowser() throws IOException {

        //Lendo o GlobalData.properties
        Properties properties = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") +  "\\src\\test\\java\\com\\project\\utils\\resources\\GlobalData.properties");
        properties.load(fis);
        String browserName = properties.getProperty("browser");

        if (browserName.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            //firefox...
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
        driver.manage().deleteAllCookies();

        return driver;
    }

    public List<HashMap<String, String>> getJsonDataToMap(String jsonPath) throws IOException {
        //read json to string (PurchaseOrder.json)
        String jsonData = FileUtils.readFileToString(new File(jsonPath), StandardCharsets.UTF_8);

        //String to hashmap (must have a dependency called Jackson DataBind)
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonData, new TypeReference<List<HashMap<String, String>>>() {
        });
        // Return a list of hashmaps
    }




    @BeforeMethod(alwaysRun = true)
    public LandingPage launchApplication() throws IOException {
        driver = initializeBrowser();
        // Página de login
        landingPage = new LandingPage(driver);
        landingPage.goTo();
        return landingPage; //-> crio o objeto de classe para iniciar a chamada do site. No caso de teste, herdo a

    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.close();
    }

}

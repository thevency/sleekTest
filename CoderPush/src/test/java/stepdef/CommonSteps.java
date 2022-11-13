package stepdef;

import data.BrowserType;
import driver.DriverManager;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.HomePage;

public class CommonSteps {
    public static Logger logger = LoggerFactory.getLogger(CommonSteps.class);
    static WebDriver webDriver;
    static HomePage homePage;

    @BeforeAll
    public static void beforeAll() {
        logger.info(" > Set Up !");
        DriverManager driverManager = DriverManager.getInstance();
        webDriver = driverManager.initDriverFor(BrowserType.CHROME);
        webDriver.manage().window().maximize();
        homePage = new HomePage(webDriver);
    }

    @AfterAll
    public static void afterAll() {
        logger.info(" > Tear Down !");
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    @Given("^I navigate to Sleek SG$")
    public void iNavigateToSleekSG() {
        String endPoint = "https://sleek.com/sg/";
        webDriver.get(endPoint);
    }

    @When("I click on {string} link")
    public void iClickOnLink(String inputStr) {
        homePage.clickOnMenu(inputStr);
    }

    @And("I click on {string} sub-menu link")
    public void iClickOnSubMenuLink(String link) {
        homePage.clickOnSubMenu(link);
    }
}

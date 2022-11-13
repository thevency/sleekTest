package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Base {
    private WebDriver webDriver;
    protected final String MENU_CLASS_NAME = "ekit-menu-nav-link";
    protected final String MENU_SM_VERTICAL_CLASS = "elementor-nav-menu sm-vertical";


    public Base(WebDriver driver){
        webDriver = driver;
        PageFactory.initElements(driver,this);
    }

    public WebDriverWait getWait(){
        return new WebDriverWait(getWebDriver(), Duration.ofSeconds(60));
    }

    public WebDriver getWebDriver(){
        return webDriver;
    }

    protected void clickOn(By locator){
        getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
        getWait().until(ExpectedConditions.elementToBeClickable(locator));
        getWebDriver().findElement(locator).click();
    }

    protected void clickOn(WebElement element){
        getWait().until(ExpectedConditions.visibilityOf(element));
        getWait().until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    protected List<WebElement> getWebElements(By locator){
        return getWebDriver().findElements(locator);
    }

    protected void sendText(String txt, WebElement element){
        getWait().until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(txt);
        element.sendKeys(Keys.RETURN);
    }

    protected void scrollIntoView(WebElement element){
        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(element);
        actions.perform();
    }

}

package pages;

import org.joda.time.DateTime;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class PricingPage extends Base{

    public PricingPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(xpath = "//iframe[contains(@src,'sgschedulerothers?embed=true')]")
    WebElement iframe;
    public void clickOnTalkToExpert(String str){
        WebElement expertBtn = getWebDriver()
                .findElement(By.xpath("(//*[@id='home-lets-talk']//span[contains(text(),'"+str+"')])[1]"));
        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(expertBtn).pause(Duration.ofSeconds(3))
                .click()
                .build()
                .perform();

        //need to switch to active iframe
        getWait().until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframe));

    }

    public void changeDay(String day){
        String xpathOfDay = "//button[@aria-controls='available-times-region']//span[contains(text(),'"+day+"')]";
        clickOn(By.xpath(xpathOfDay));
    }

    @FindBy(xpath = "//span[@class='uiDropdown__buttonCaret private-dropdown__caret']") WebElement dropDownSign;
    @FindBy(xpath  = "//div[@class='private-search-control__wrapper timezone-select-search']//input")
    WebElement searchTimeZoneBox;
    public void changeTimeZone(String inputTimeZone){
        clickOn(dropDownSign);
        sendText(inputTimeZone,searchTimeZoneBox);
    }

    @FindBy(xpath = "//div[@class='private-selectable-box__inner']")
    WebElement timeBox;
    public void changeTime(String inputTime){
        WebElement specificTime = timeBox.findElement(By.xpath("//span[contains(@aria-label,'"+inputTime+"')]"));
        scrollIntoView(specificTime);
        clickOn(specificTime);
    }

    public String createScheduledDate(String numberOfDays){
        Date date = new DateTime().plusDays(Integer.parseInt(numberOfDays)).toDate();
        SimpleDateFormat sd = new SimpleDateFormat("MMMM dd, yyyy");
        return sd.format(date);
    }

    @FindBy(xpath = "//div/h5") WebElement scheduledTime;
    public String getScheduledFromSection() {
        getWait().until(ExpectedConditions.visibilityOf(scheduledTime));
        return scheduledTime.getText();
    }
}

package stepdef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import pages.PricingPage;

public class PricingSteps {
    PricingPage pricingPage = new PricingPage(CommonSteps.webDriver);
    private String scheduledDate;
    private String scheduledTime;

    @Given("^I click on \"([^\"]*)\" button$")
    public void iClickOnButton(String buttonName){
        pricingPage.clickOnTalkToExpert(buttonName);
//        try {
//           Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    @And("select a date {string} days from now")
    public void selectADateDaysFromNow(String numberOfDays) {
        //currentDate + 10
        scheduledDate = pricingPage.createScheduledDate(numberOfDays);
        String day = scheduledDate
                .split(",")[0]
                .split(" ")[1]
                .replaceAll("^0","");
        /**
         * Day + 10 can lead to the day of next month (example: today is 22nd)
         * Clarification point: designed behavior of this case to update script logic
         * Current Behavior: It is not able to move to next month
         */
        pricingPage.changeDay(day);
    }


    @And("select time zone as {string}")
    public void selectTimeZoneAs(String inputTimeZone) {
        pricingPage.changeTimeZone(inputTimeZone);
    }

    @And("set time as {string}")
    public void setTimeAs(String time) {
        scheduledTime = time.toUpperCase();
        pricingPage.changeTime(time.toUpperCase().split(" ")[0]
                .replace("AM","")
                .replace("PM","")
                .trim());
    }

    @Then("Verify date and time value is correct in Your information section")
    public void verifyDateAndTimeValueIsCorrectInYourInformationSection() {
        String inputScheduled = scheduledDate + " " + scheduledTime;
        String gotScheduled = pricingPage.getScheduledFromSection()
                .replaceFirst(", ","/")
                .replaceAll(".+/","");
        Assert.assertEquals(gotScheduled, inputScheduled,"Verify date and time value is correct in Your information section");
    }
}

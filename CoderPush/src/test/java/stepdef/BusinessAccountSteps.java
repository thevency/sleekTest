package stepdef;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import pages.BusinessAccountPage;

import java.util.ArrayList;
import java.util.List;

public class BusinessAccountSteps {

    public static Logger logger = LoggerFactory.getLogger(BusinessAccountSteps.class);
    static BusinessAccountPage businessAccount = new BusinessAccountPage(CommonSteps.webDriver);

    @Then("Verify Business Account deposit or monthly fees table values")
    public void verifyBusinessAccountDepositOrMonthlyFeesTableValues(DataTable dataTable) {
        List<List<String>> lstStr = dataTable.asLists();
        List<List<String>> lstSt = new ArrayList<>();

        for (int i = 0; i < lstStr.size(); i++) {
            if (i != 0) {
                lstSt.add(lstStr.get(i));
            }
        }
        List<List<String>> dataSet = businessAccount.findDataSet();
        boolean isSame = false;

        if (lstSt.equals(dataSet)) {
            isSame = true;
        }
        Assert.assertTrue(isSame, "Business Account deposit or monthly fees table values is correct");
    }

}

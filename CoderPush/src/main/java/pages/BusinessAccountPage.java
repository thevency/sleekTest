package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class BusinessAccountPage extends Base {
    private final String HEADING_TITLE_CLASS = "elementor-heading-title elementor-size-default";
    private final String PARTLY_TITLE_OF_TABLE = "Hassle-free business account";

    public BusinessAccountPage(WebDriver webDriver) {
        super(webDriver);
    }

    public List<List<String>> findDataSet() {
        int targetElementNumber = -1;
        List<List<String>> listAllData = new ArrayList<>();
        String baseXpath = "//div[@data-elementor-type='wp-page']/section";
        getWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(baseXpath)));
        List<WebElement> lstElement = getWebElements(By.xpath(baseXpath));
//        System.out.println("lstElement:"+lstElement.size());
        for (int i = 0; i < lstElement.size(); i++) {
            int j = i + 1;
            String xpathTmp = baseXpath + "[" + j + "]" + "//h2[@class='" + HEADING_TITLE_CLASS + "' " +
                    "and contains(text(),'" + PARTLY_TITLE_OF_TABLE + "')]";
            if (getWebDriver().findElements(By.xpath(xpathTmp)).size() > 0) {
                targetElementNumber = j + 1;
                break;
            }
        }

        if (targetElementNumber != -1) {
            //get list sub-section:
            String xpathOfSubSection = baseXpath + "[" + targetElementNumber + "]//section";
            List<WebElement> lstSubSection = getWebDriver().findElements(By.xpath(xpathOfSubSection));
            lstSubSection.remove(0);
            lstSubSection.remove(lstSubSection.size() - 1);
//            System.out.println("lstSubSection.size()=" + lstSubSection.size());
            for (int k = 0; k < lstSubSection.size(); k++) {
                if (!lstSubSection.get(k).getText().isEmpty()) {
                    List<WebElement> lstData = lstSubSection.get(k)
                            .findElements(By.xpath(xpathOfSubSection + "[" + (k + 2) + "]/div/div"));
                    List<String> returnedData = new ArrayList<>();
                    for (WebElement e : lstData) {
                        returnedData.add(e.getText());
                    }
                    listAllData.add(returnedData);
                }

            }
        }

        List<List<String>> dataSetFix = new ArrayList<>();

        if(!listAllData.isEmpty()) {
            for (int j = 1; j < listAllData.get(0).size(); j++) {
                List<String> dataSet = new ArrayList<>();
                for (int i = 0; i < listAllData.size(); i++) {
                    String tmpStr = listAllData.get(i).get(j).replaceAll("\n", " ");
                    dataSet.add(tmpStr);
                }
                dataSetFix.add(dataSet);
            }
        }

//For Debug Only
//        for(List<String> m:dataSetFix){
//            System.out.println("m:"+m.toString());
//        }
        return dataSetFix;
    }

}

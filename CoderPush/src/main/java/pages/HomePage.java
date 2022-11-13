package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends Base{

    public HomePage(WebDriver webDriver) {
        super(webDriver);

    }

    public void clickOnMenu(String name){
        clickOn(By.xpath("//*[@class='"+MENU_CLASS_NAME+"'][contains(text(),'"+name+"')]"));
    }

    public void clickOnSubMenu(String subName){
        clickOn(By.xpath("//*[@class='"+ MENU_SM_VERTICAL_CLASS +"']//*[@class='elementor-item'][contains(text(),'"+subName+"')]"));
    }

}

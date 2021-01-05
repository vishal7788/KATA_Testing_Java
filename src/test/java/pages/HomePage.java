package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.*;


public class HomePage {

    @FindBy(tagName = "h2")
    private WebElement roomCategoryIdentifier;

    public HomePage() {
        //this.webDriver = webDriver;
        System.out.println("hereh");
        //PageFactory.initElements(webDriver, this);
        System.out.println(DriversUtils.getDriver());
        PageFactory.initElements(DriversUtils.getDriver(), this);
    }

    public void goToRoomsCategory() {
        try {
            //JavascriptExecutor js = (JavascriptExecutor) webDriver;
            JavascriptExecutor js = (JavascriptExecutor) DriversUtils.getDriver();
            //WebElement roomCategoryIdentifier = webDriver.findElement(By.xpath("//h2"));
            System.out.println(roomCategoryIdentifier);
            Thread.sleep(2000);
            js.executeScript("arguments[0].scrollIntoView();", roomCategoryIdentifier);
        } catch (RuntimeException e) {
            e.printStackTrace();
            System.out.println("Error in the rooms category method");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void navigateToHomePage() {
        DriversUtils.getDriver().get("https://automationintesting.online/#/");
    }
}


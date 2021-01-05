package pages;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.*;

import static utils.DriversUtils.getDriver;


public class HomePage {

    @FindBy(tagName = "h2")
    private WebElement roomCategoryIdentifier;

    @FindBy(xpath = "//button[contains(@class,'openBooking')]")
    private WebElement bookButton;

    public HomePage() {
        PageFactory.initElements(getDriver(), this);
    }

    public void goToRoomsCategory() {
        try {
            scrollToElement(CommonUtils.roomCategoryIdentifier);
        } catch (RuntimeException e) {
            e.printStackTrace();
            System.out.println("Error in the rooms category method");
        }
    }

    public void assertBookButtonDisplayed(){
        Assert.assertEquals(true, bookButton.isDisplayed());
    }

    public void navigateToHomePage() {
        getDriver().get("https://automationintesting.online/#/");
    }
}


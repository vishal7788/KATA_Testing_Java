package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import utils.*;

import static utils.CommonUtils.*;
import static utils.DriversUtils.*;


public class HomePage {

    @FindBy(xpath = "//h2[contains(text(),'Rooms')]")
    private WebElement roomCategoryIdentifier;

    @FindBy(xpath = "//h3[contains(text(),'single')]/ancestor::div[@class=\"row hotel-room-info\"]//button[contains(@class,'openBooking')]")
    private WebElement singleRoomBookButton;

    @FindBy(how = How.CSS, using = ".checkin input")
    private WebElement inputCheckin;

    @FindBy(how = How.CSS, using = ".checkout input")
    private WebElement inputCheckout;

    @FindBy(how = How.CSS, using = ".react-datepicker__day--001")
    private WebElement divFirstDate;

    @FindBy(how = How.CSS, using = ".react-datepicker__day--002")
    private WebElement divSecondDate;

    public HomePage() {
        PageFactory.initElements(getDriver(), this);
    }

    public void goToRoomsCategory() {
        try {
            scrollToElement(roomCategoryIdentifier);
        } catch (RuntimeException e) {
            e.printStackTrace();
            System.out.println("Error in the rooms category method");
        }
    }

    public void navigateToHomePage() {
        getDriver().get("https://automationintesting.online/#/");
    }

    public void assertBookButtonDisplayed(){
        Assert.assertTrue(singleRoomBookButton.isDisplayed());
    }

    public void bookThisRoom(String startDate, String endDate) throws InterruptedException {
        singleRoomBookButton.click();
        WebElement sDate = getDriver().findElement(By.xpath("//button[contains(@class,'rbc-button-link')][contains(text(),'"+startDate+"')]/parent::div[@class='rbc-date-cell']"));
        WebElement eDate = getDriver().findElement(By.xpath("//button[contains(@class,'rbc-button-link')][contains(text(),'"+endDate+"')]/parent::div[@class='rbc-date-cell']"));
        //WebElement sDate = getDriver().findElement(By.xpath("(//button[contains(@class,'rbc-button-link')][contains(text(),'"+startDate+"')]/ancestor::div[@class='rbc-month-row']//div[@class='rbc-day-bg' or @class='rbc-day-bg rbc-today'])['"+startDate+"']"));
        //WebElement eDate = getDriver().findElement(By.xpath("(//button[contains(@class,'rbc-button-link')][contains(text(),'"+endDate+"')]/ancestor::div[@class='rbc-month-row']//div[@class='rbc-day-bg' or @class='rbc-day-bg rbc-today'])['"+endDate+"']"));
        //WebElement sDate = getDriver().findElement(By.xpath("//button[contains(@class,'rbc-button-link')][contains(text(),'06')]"));
        //WebElement eDate = getDriver().findElement(By.xpath("//button[contains(@class,'rbc-button-link')][contains(text(),'12')]"));
        //new Actions(getDriver()).dragAndDrop(sDate, eDate).perform(); //--> gitHub bug for drag and drop not working https://github.com/w3c/webdriver/issues/1488
        Thread.sleep(5000);
        new Actions(getDriver()).clickAndHold(sDate).moveToElement(eDate).release().perform();
        //DragAndDropJS(sDate, eDate);
        /*Actions builder = new Actions(getDriver());
        builder.dragAndDrop(sDate,eDate).build().perform();*/
        Thread.sleep(5000);
        /*inputCheckin.sendKeys(startDate);
        divFirstDate.click();
        inputCheckout.sendKeys(endDate);
        divSecondDate.click();*/

    }


}


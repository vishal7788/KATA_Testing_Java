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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.*;

import java.time.Duration;

import static utils.CommonUtils.*;
import static utils.DriversUtils.*;


public class HomePage {

    @FindBy(xpath = "//h2[contains(text(),'Rooms')]")
    private WebElement roomCategoryIdentifier;

    @FindBy(xpath = "//h3[contains(text(),'single')]/ancestor::div[@class='row hotel-room-info']//button[contains(@class,'openBooking')]")
    private WebElement singleRoomBookButton;

    @FindBy(name = "firstname")
    WebElement firstName;

    @FindBy(name = "lastname")
    WebElement lastName;

    @FindBy(name = "email")
    WebElement email;

    @FindBy(name = "phone")
    WebElement phone;

    @FindBy(xpath = "//button[contains(text(),'Book')]")
    WebElement bookButton;

    @FindBy(xpath = "//button[contains(text(),'Cancel')]")
    WebElement cancelButton;

    @FindBy(xpath = "//div[contains(@class,'confirmation')]//h3")
    WebElement confirmationMsg;

    @FindBy(xpath = "(//div[contains(@class,'confirmation')]//p)[1]")
    WebElement _confirmationMsg;

    @FindBy(xpath = "(//div[contains(@class,'confirmation')]//p)[2]")
    WebElement bookingConfirmationDates;

    @FindBy(xpath = "//button[contains(text(),'Close')]")
    WebElement closeConfirmationMsg;


    /*@FindBy(how = How.CSS, using = ".checkin input")
    private WebElement inputCheckin;
    @FindBy(how = How.CSS, using = ".checkout input")
    private WebElement inputCheckout;
    @FindBy(how = How.CSS, using = ".react-datepicker__day--001")
    private WebElement divFirstDate;
    @FindBy(how = How.CSS, using = ".react-datepicker__day--002")
    private WebElement divSecondDate;*/

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

    public void fillInUserDetails(){
        singleRoomBookButton.click();
        firstName.sendKeys("John");
        lastName.sendKeys("Smith");
        email.sendKeys("John.Smith@live.com");
        phone.sendKeys("04552365214");
    }

    public void bookThisRoom(String checkInDate, String checkOutDate) throws InterruptedException {
        //Selecting only day from the full date at the moment
        String startDate = returnDate(checkInDate);
        String endDate = returnDate(checkOutDate);

        //Creating dynamic xapth to select dates on the full calendar
        WebElement checkIn = getDriver().findElement(By.xpath("//button[contains(@class,'rbc-button-link')][contains(text(),'"+startDate+"')]/parent::div[@class='rbc-date-cell']"));
        WebElement checkOut = getDriver().findElement(By.xpath("//button[contains(@class,'rbc-button-link')][contains(text(),'"+endDate+"')]/parent::div[@class='rbc-date-cell']"));
        /*WebElement checkIn = getDriver().findElement(By.xpath("(//button[contains(@class,'rbc-button-link')][contains(text(),'"+startDate+"')]/ancestor::div[@class='rbc-month-row']//div[@class='rbc-day-bg' or @class='rbc-day-bg rbc-today'])['"+startDate+"']"));
        WebElement checkOut = getDriver().findElement(By.xpath("(//button[contains(@class,'rbc-button-link')][contains(text(),'"+endDate+"')]/ancestor::div[@class='rbc-month-row']//div[@class='rbc-day-bg' or @class='rbc-day-bg rbc-today'])['"+endDate+"']"));*/
        /*WebElement checkIn = getDriver().findElement(By.xpath("//button[contains(@class,'rbc-button-link')][contains(text(),'06')]"));
        WebElement checkOut = getDriver().findElement(By.xpath("//button[contains(@class,'rbc-button-link')][contains(text(),'12')]"));*/

        Thread.sleep(5000);
        //new Actions(getDriver()).dragAndDrop(checkIn, checkOut).perform(); //--> gitHub bug for drag and drop not working https://github.com/w3c/webdriver/issues/1488
        new Actions(getDriver()).clickAndHold(checkIn).moveToElement(checkOut).release().build().perform();
        //dragAndDropJS(checkIn, checkOut);
        /*inputCheckin.sendKeys(checkIn);
        divFirstDate.click();
        inputCheckout.sendKeys(checkOut);
        divSecondDate.click();*/
        Thread.sleep(5000);
    }

    public String returnDate(String Date){
        String d = Date;
        String[] date = d.split("-");
        return date[2]; //Returning "03" from "2022-11-03" date;
    }

    public void clickOnBookButton(){
        bookButton.click();
    }

    public void assertConfirmationMessage(String checkInDate, String checkOutDate){
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'confirmation')]")));

        Assert.assertEquals("Booking Successful!", confirmationMsg.getText());
        Assert.assertEquals("Congratulations! Your booking has been confirmed for:", _confirmationMsg.getText());

        String dates = bookingConfirmationDates.getText();
        String[] bookingDates = dates.split(" - ");

        Assert.assertEquals(checkInDate, bookingDates[0]);
        Assert.assertEquals(checkOutDate, bookingDates[1]);

        closeConfirmationMsg.click();

    }


}



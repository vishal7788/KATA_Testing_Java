package features.step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.BasePage;
import pages.HomePage;

import java.util.concurrent.TimeUnit;

public class BookingSteps extends BasePage {

    //public static WebDriver driver;
    HomePage homePage = new HomePage();
    @Given("I am on the home page")
    public void i_am_on_the_home_page() {
        //driver = new FirefoxDriver();
        //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        homePage.navigateToHomePage();

    }

    @When("I browse through the page")
    public void i_browse_through_the_page() {
        //System.out.println("The driver is" + driver);
        homePage.goToRoomsCategory();
    }
    @Then("I have the option to book a room")
    public void i_have_the_option_to_book_a_room() {
        //WebElement bookButton = driver.findElement(By.xpath("//button[contains(@class,'openBooking')]"));
        //Assert.assertEquals(true, bookButton.isDisplayed());
    }

}

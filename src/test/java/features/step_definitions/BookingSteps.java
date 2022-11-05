package features.step_definitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import pages.BasePage;
import pages.HomePage;
import utils.DriversUtils;

public class BookingSteps extends DriversUtils {

    //public static WebDriver driver;
    HomePage homePage = new HomePage();
    @Before
    public void before(){
        getDriver();
    }

    @Given("I am on the home page")
    public void i_am_on_the_home_page() {
        //driver = new FirefoxDriver();
        //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        homePage.navigateToHomePage();
    }

    @When("I browse through the page")
    public void i_browse_through_the_page() {
        homePage.goToRoomsCategory();
    }

    @Then("I have the option to book a room")
    public void i_have_the_option_to_book_a_room() {
        homePage.assertBookButtonDisplayed();
    }

    @When("I fill in the booking details")
    public void i_fill_in_the_booking_details() {
        homePage.fillInUserDetails();
    }

    @When("select the checkin {string} and checkout {string} date")
    public void select_the_checkin_and_checkout_date(String checkIn, String checkOut) throws InterruptedException {
        //homePage.bookThisRoom(checkIn, checkOut); => The drag and drop functionality is not working
    }

    @When("click on the book button")
    public void click_on_the_book_button() {
        homePage.clickOnBookButton();
    }

    @Then("I should see success message for {string} {string} booking")
    public void i_should_see_success_message_for_booking(String checkIn, String checkOut) {
        homePage.assertConfirmationMessage(checkIn, checkOut); //These dates can be sent from a test data file (excel or Json file)
    }

    @After
    public void after(){ tearDown(); }
}

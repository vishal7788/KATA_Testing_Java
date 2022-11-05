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
    public void i_have_the_option_to_book_a_room() throws InterruptedException {

        homePage.assertBookButtonDisplayed();
        homePage.bookThisRoom("01", "03");
    }

    @After
    public void after(){ tearDown(); }
}

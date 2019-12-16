import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class SignUpTest {
    private WebDriver driver;
    SignUpPage page;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Zero\\Downloads\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://www.spotify.com/us/signup/");
    }

    @Test
    public void typeInvalidYear(){
        page = new SignUpPage(driver);
        page.setMonth("5")
                .typeDay("20")
                .typeYear("85")
                .setShare(true);
        //Assert.assertTrue(page.isErrorVisible("Please enter a valid year."));
        //Assert.assertFalse(page.isErrorVisible("Whenwere you born?"));
        Assert.assertTrue(page.isErrorVisible("Please enter a valid year."));
        Assert.assertFalse(page.isErrorVisible("Whenwere you born?"));

    }
    @Test
    public void typeInvalidEmail(){
        page = new SignUpPage(driver);
        page.typeName("test@mail.test")
                .typeConfirmEmail("wrong@mail.test")
                .typeName("TestName")
                .clickSignUpButton();
        Assert.assertTrue(page.);
    }

    @After
    public void tearDown() {
        driver.quit();
}
}

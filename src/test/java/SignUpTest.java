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
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Zero\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://www.spotify.com/us/signup/");
    }

    @Test //тест на введение неправильного года, правильных дня и месяца.
    public void typeInvalidYear(){
        page = new SignUpPage(driver);
        page.setMonth("May")
                .typeDay("5")
                .typeYear("85")
                .setShare(true);
        Assert.assertTrue(page.isErrorVisible("Please enter a valid year."));
        //проверка, что ошибка не видна
        Assert.assertFalse(page.isErrorVisible("Please enter a valid day of the month."));
        Assert.assertFalse(page.isErrorVisible("Please enter your birth month."));

    }
    @Test //тест на совпадение email и confirm email
    public void typeInvalidEmail(){
        page = new SignUpPage(driver);
        page.typeName("test@mail.test")
                .typeConfirmEmail("wrong@mail.test")
                .typeName("TestName")
                .clickSignUpButton();
        Assert.assertTrue(page.isErrorVisible("Email address doesn't match."));
    }
    @Test //проверка ошибки при не введенном пароле
    public void signUpWithEmptyPassword(){
        page = new SignUpPage(driver);
        page.typeEmail("test@mail.test")
                .typeConfirmEmail("test@mail.test")
                .typeName("TestName")
                .clickSignUpButton();
        Assert.assertTrue(page.isErrorVisible("Enter a password to continue."));
    }
    @Test
    public void typeInvalidValues(){
        page = new SignUpPage(driver);
        page.typeEmail("testmail")
                .typeConfirmEmail("wrong@test.mail")
                .typePassword("qwerty123")
                .typeName("Name")
                .setSex("Male")
                .setShare(false)
                .clickSignUpButton();
        Assert.assertEquals(5,page.getErrors().size());

    }




    @Test
    public void typeInvalidValues1(){
        page = new SignUpPage(driver);
        page.setShare(true)
                .setMonth("April")
                .setSex("Male")
                .clickSignUpButton();

    }

    @After
    public void tearDown() {
        driver.quit();
}
}

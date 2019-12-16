import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static java.lang.String.format;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElementsLocatedBy;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class SignUpPage {
    private WebDriver driver;

    public SignUpPage(WebDriver driver){
        this.driver=driver;

    }
    By emailField = By.xpath("//input[@id='register-email']");
    By confirmEmailField = By.xpath("//input[@id='register-confirm-email']");
    By passwordField = By.xpath("//input[@id='register-password']");
    By nameField = By.xpath("//input[@id='register-displayname']");
    By monthDropDownField = By.xpath("//select[@id='register-dob-month']");
    String monthDropDownOption ="//select[@id='register-dob-month']/option[text()='%s']";
    By dayField = By.xpath("//input[@id='register-dob-day']");
    By yearField = By.xpath("//input[@id='register-dob-year']");
    String sexRadioButton = "//li[@id='li-gender']/label[normalize-space()='%s']/input/";
    By shareCheckbox = By.xpath("//input[@id='register-thirdparty']");
    By registerButton= By.xpath("//a[@id='register-button-email-submit']");
    By errorLabel = By.xpath("//label[@class='has-error' and string-length(text())>0]");
    String errorText = "//label[@class='has-error' and text()=%s]";

    public SignUpPage typeConfirmEmail(String email){
        driver.findElement(confirmEmailField).sendKeys(email);
        return this;

    }

    public SignUpPage typeEmail(String email){
        driver.findElement(emailField).sendKeys(email);
        return this;

    }

    public SignUpPage typePassword(String password){
        driver.findElement(passwordField).sendKeys(password);
        return this;

    }

    public SignUpPage typeName (String name){
        driver.findElement(nameField).sendKeys(name);
        return this;

    }

    public SignUpPage typeDay (String day){
        driver.findElement(dayField).sendKeys(day);
        return this;

    }

    public SignUpPage typeYear (String year){
        driver.findElement(yearField).sendKeys(year);
        return this;

    }

    public SignUpPage setMonth(String month){
        driver.findElement(monthDropDownField).click();
        new WebDriverWait(driver, 5).until(visibilityOfElementLocated(By.xpath(format(monthDropDownOption, month)))).click();
        return this;

    }

    public SignUpPage setSex (String value){
        driver.findElement(By.xpath(format(sexRadioButton,value))).click();
        return this;

    } public SignUpPage setShare (boolean value) {
        WebElement checkbox = driver.findElement(shareCheckbox);
        if (!checkbox.isSelected() == value) {
            checkbox.click();
        }
        return this;

    } public void clickSignUpButton() {
           driver.findElement(registerButton).click();
        }

        public List<WebElement> getErrors() {
        return (List<WebElement>) driver.findElement(errorLabel);

        }
        public String getErrorByNumber(int number) {
        return getErrors().get(number - 1).getText();

        }
        //public boolean isErrorVisible(String message) {
            //return driver.findElement(By.xpath(format(errorText, message))).size() >0 && driver.findElement(By.xpath(format(errorText, message))).get(0).isDisplayed

        //}

}

package pages;

import data.TestData;
import libs.Util;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class LoginPage extends ParentPage {
    @FindBy(xpath = ".//input[@value='Увійти']")
    // this element will crreated in PageFactory in CommonActionsWithElements.java
    private WebElement buttonSignIn;

    @FindBy(xpath = ".//input[@name='login']")
    private WebElement inputLogin;

    @FindBy(xpath = ".//input[@name='pass']")
    private WebElement inputPassword;




    @FindBy(xpath = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private List<WebElement> listErrorsMessages;
    private String listErrorsMessagesLocator = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";


    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }

    public void checkIsRedirectToLoginPage() {
        checkUrl();
    }

    public void openLoginPage() {
        try {
            webDriver.get(baseUrl);
            logger.info("Login page was opened");
        } catch (Exception e) {
            logger.error("Can not open Login Page");
            Assert.fail("Can not open Login Page");
        }
    }

    public void enterTextIntoInputLogin(String login) {
        // WebElement inputLogin = webDriver.findElement(By.xpath(".//input[@placeholder='Username']"));
        enterTextIntoInput(inputLogin, login);
    }

    public void enterTextIntoInputPassword(String password) {
        //WebElement inputPassword = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
        enterTextIntoInput(inputPassword, password);
    }

    public void clickOnButtonSignIn() {
        //WebElement buttonSignIn = webDriver.findElement(By.xpath(".//button[text()='Sign In']"));
        clickOnElement(buttonSignIn);
    }

    public boolean isWarningMessageVisible() {
        try {
            WebElement warningMessage = webDriver.findElement(By.xpath(".//div[text()='Невірний логін або пароль']"));
            return isElementDisplayed(warningMessage);
        } catch (Exception e) {
            return false;
        }
    }

    //is button Sign In visible
    public EmailsHomePage openLoginPageAndFillLoginFormWithValidCred() {
        openLoginPage();
        enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
        enterTextIntoInputPassword(TestData.VALID_PASSWORD_UI);
        clickOnButtonSignIn();
        return new EmailsHomePage(webDriver);
    }

    //is username field visible

    public void checkUsernameFieldNotVisible() {
        checkIsElementNotVisible(inputLogin);
    }

    public void checkPasswordFieldNotVisible() { checkIsElementNotVisible(inputPassword); }

}

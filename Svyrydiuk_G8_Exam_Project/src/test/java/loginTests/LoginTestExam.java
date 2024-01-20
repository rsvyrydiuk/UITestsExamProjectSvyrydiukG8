package loginTests;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import libs.ConfigProvider;
import libs.ExcelDriver;
import org.junit.Assert;
import org.junit.Test;

import static data.TestData.VALID_LOGIN_UI;
import static data.TestData.VALID_PASSWORD_UI;

import baseTest.BaseTest;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.Map;

@RunWith(JUnitParamsRunner.class)
public class LoginTestExam extends BaseTest {

    @Test
    public void validLogin() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInputLogin(VALID_LOGIN_UI);
        pageProvider.loginPage().enterTextIntoInputPassword(VALID_PASSWORD_UI);
        pageProvider.loginPage().clickOnButtonSignIn();

        pageProvider.loginPage().checkUsernameFieldNotVisible();
        pageProvider.loginPage().checkPasswordFieldNotVisible();
        Assert.assertTrue("Username is not visible", pageProvider.emailsHomePage().getHeader().isUsernameVisible());
        pageProvider.emailsHomePage().getHeader().checkTextInUsername(VALID_LOGIN_UI + "@i.ua");
        Assert.assertTrue("Profile text is not visible", pageProvider.emailsHomePage().getHeader().isProfileButtonVisible());
        Assert.assertTrue("Button Create Post is not visible", pageProvider.emailsHomePage().getHeader().isButtonSettingsVisible());
    }


    @Test
    public void validLoginWhitExcel() throws IOException {
        Map<String, String> dataForValidLogin = ExcelDriver.getData(ConfigProvider.configProperties.DATA_FILE(), "validLogOn");
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInputLogin(dataForValidLogin.get("login"));
        pageProvider.loginPage().enterTextIntoInputPassword(dataForValidLogin.get("pass"));
        pageProvider.loginPage().clickOnButtonSignIn();

        pageProvider.loginPage().checkUsernameFieldNotVisible();
        pageProvider.loginPage().checkPasswordFieldNotVisible();
        Assert.assertTrue("Username is not visible", pageProvider.emailsHomePage().getHeader().isUsernameVisible());
    }

    @Test
    @Parameters(method = "parametersForValidationMessagesLoginFieldsTests")
    public void validationMessagesForLoginTests(String login, String password) {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInputLogin(login);
        pageProvider.loginPage().enterTextIntoInputPassword(password);
        pageProvider.loginPage().clickOnButtonSignIn();
        Assert.assertTrue("Warning message is visible", pageProvider.loginPage().isWarningMessageVisible());
    }

    public Object[][] parametersForValidationMessagesLoginFieldsTests() {
        return new Object[][]{
                {"Examtester123", "tr"},
                {"Exam", "123456qwerty"},
        };
    }
}

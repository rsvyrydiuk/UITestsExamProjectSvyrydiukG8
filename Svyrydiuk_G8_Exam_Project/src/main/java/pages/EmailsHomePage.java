package pages;


import data.TestData;
import libs.Util;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.elements.HeaderElement;

import java.util.ArrayList;
import java.util.List;

public class EmailsHomePage extends ParentPage {
    private HeaderElement headerElement;
    @FindBy(xpath = ".//div[text()='Від кого:']/..//div[text()='Examtester123@i.ua']")
    private WebElement sender;
    @FindBy(xpath = ".//span[text()='Кому:']/../..//textarea")
    private WebElement receiver;

    @FindBy(xpath = ".//div[text()='Тема:']/..//input[1]")
    private WebElement inputSubject;

    @FindBy(xpath = ".//textarea[@id='text']")
    private WebElement inputBodyEmail;

    @FindBy(xpath = ".//input[@value='Надіслати']")
    private WebElement buttonSendEmail;

    @FindBy(xpath = ".//a[text()='Відправлені']")
    private WebElement sentEmailsFolder;
    @FindBy(xpath = ".//h2")
    private WebElement sentEmailsCount;
    @FindBy(xpath = ".//input[@defvalue='знайти серед повідомлень']")
    private WebElement inputSearchEmails;

    @FindBy(xpath = ".//span[@title='знайти']")
    private WebElement searchEmailsButton;

    public EmailsHomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath = ".//span[@class='sbj']")
    private List<WebElement> subjectEmails;

    private String subjectEmail = ".//span[@class='sbj']";

    @Override
    String getRelativeUrl() {
        return "https://mbox2.i.ua/?_rand=603985805&phcode=3af9489fbc6cba515a92556a2293f8b8";
    }

    public EmailsHomePage checkIsRedirectToEmailsHomePage() {
        Assert.assertTrue("Invalid page  --- not Home page", getHeader().isProfileButtonVisible());
        return this;
    }

    public HeaderElement getHeader() {
        return new HeaderElement(webDriver);
    }

    public EmailsHomePage openEmailsHomePageAndLoginIfNeeded() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        if (this.getHeader().isProfileButtonVisible()) {
            logger.info("User is already logged in");
        } else {
            loginPage.enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
            loginPage.enterTextIntoInputPassword(TestData.VALID_PASSWORD_UI);
            loginPage.clickOnButtonSignIn();
            checkIsRedirectToEmailsHomePage();
            logger.info("User was logged in");
        }
        return this;
    }

    public EmailsHomePage checkIsCreateEmailPageOpened() {
        Assert.assertTrue("Invalid page  --- not Create Email page", buttonSendEmail.isDisplayed());
        return this;
    }

    public EmailsHomePage createNewEmail(String recipient, String subject, String emailBody) {
        getHeader().clickOnButtonCreateEmail();
        isElementDisplayed(sender);
        enterTextIntoInput(receiver, recipient);
        enterTextIntoInput(inputSubject, subject);
        enterTextIntoInput(inputBodyEmail, emailBody);
        return this;
    }

    public EmailsHomePage clickOnButtonSendEmail() {
        clickOnElement(buttonSendEmail);
        return this;
    }

    public void checkIsMessageDisplayed(String messageText) {
        WebElement message = webDriver.findElement(By.xpath(".//div[@class='content clear' and text()='" + messageText + "']"));
        Assert.assertTrue("Message is not displayed", isElementDisplayed(message));
    }

    public void openSentEmailsFolder() {
        clickOnElement(sentEmailsFolder);
        webDriverWait10.until(ExpectedConditions.textToBePresentInElement(sentEmailsCount, "Відправлені: "));
    }

    public EmailsHomePage checkSubjectsInSentMessage(String subject) {
        openSentEmailsFolder();
        WebElement subjectInSentMessage = webDriver.findElement(By.xpath(".//span[@class='sbj']/span[text()='" + subject + "']"));
        return this;
    }

    public EmailsHomePage enterSearchTextAndClickOnButtonSearchEmails(String searchText) {
        enterTextIntoInput(inputSearchEmails, searchText);
        clickOnElement(searchEmailsButton);
        return this;
    }

    public EmailsHomePage checkIsResultOfSearchDisplayed(String messageText) {
            webDriverWait10.until(ExpectedConditions.numberOfElementsToBe(
                    By.xpath(subjectEmail),  2));
            //Util.waitABit(1);
            ArrayList<String> actualSearchResult = new ArrayList<>();
            for (WebElement element : subjectEmails) {
                actualSearchResult.add(element.getText());
            }
            SoftAssertions softAssertions = new SoftAssertions();
            for (int i = 0; i < 2; i++) {
                softAssertions.assertThat(actualSearchResult.get(i).toLowerCase())
                        .as("Result " + i)
                        .contains(messageText.toLowerCase());
                logger.info(actualSearchResult.get(i) + " contains " + messageText);
            }

            softAssertions.assertAll(); // check all assertion

            return this;
        }
}

package pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonActionsWithElements;


//describe header element for all pages logged in user
public class HeaderElement extends CommonActionsWithElements {
    //create post button
    @FindBy(xpath = ".//span[@class='icon-ho ho_settings ho_i_settings']")
    private WebElement buttonSettings;

    @FindBy(xpath = ".//span[@class='sn_menu_title']")
    private WebElement username;
    @FindBy(xpath = ".//span[@class='user_name' and text()='Роман']")
    private WebElement profileText;

    @FindBy(xpath = ".//a[@class='text-white mr-2 header-search-icon']")
    private WebElement searchButton;

    @FindBy(xpath = ".//li/a[text()='Створити листа']")
    private WebElement buttonCreateEmail;


    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean isButtonSettingsVisible() {
        return isElementDisplayed(buttonSettings);
    }

    //My profile image button is visible
    public boolean isProfileButtonVisible() {
        return isElementDisplayed(profileText);
    }

    //username is visible
    public boolean isUsernameVisible() {
        return isElementDisplayed(username);
    }

    //check text in username
    public void checkTextInUsername(String text) {
        checkTextInElement(username, text);
    }

    public boolean isSearchButtonVisible() {
        return isElementDisplayed(searchButton);
    }


    public void isProfileButtonNotVisible() {
        checkIsElementNotVisible(profileText);
    }

    public void isButtonCreatePostNotVisible() {
        checkIsElementNotVisible(buttonSettings);
    }

    public void isSearchButtonNotVisible() {
        checkIsElementNotVisible(searchButton);
    }

    public void clickOnButtonCreateEmail() {
        clickOnElement(buttonCreateEmail);
    }

}

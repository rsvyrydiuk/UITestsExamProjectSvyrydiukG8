package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CommonActionsWithElements {
    protected WebDriver webDriver;
    protected Logger logger = Logger.getLogger(getClass());
    protected WebDriverWait webDriverWait10, webDriverWait15;

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this); // init all elements from this class @FindBy
        webDriverWait10 = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        webDriverWait15 = new WebDriverWait(webDriver, Duration.ofSeconds(15));
    }

    protected void enterTextIntoInput(WebElement input, String text) {
        try {
            input.clear();
            input.sendKeys(text);
            logger.info(text + " was inputted into input " + getElementName(input));
        } catch (Exception e) {
            logger.error("Can not work with element " + getElementName(input));
            Assert.fail("Can not work with element " + getElementName(input));
        }
    }

    private String getElementName(WebElement webElement) {
        try {
            return webElement.getAccessibleName();

        } catch (Exception e) {
            return "";
        }
    }

    protected void clickOnElement(WebElement element) {
        try {
            webDriverWait10.until(ExpectedConditions.elementToBeClickable(element));
            String elementName = getElementName(element);  // getTagName() - return name of element
            element.click();
            logger.info("Element was clicked " + elementName);
        } catch (Exception e) {
            logger.error("Can not work with element " + getElementName(element));
            Assert.fail("Can not work with element " + getElementName(element));
        }
    }

    protected boolean isElementDisplayed(WebElement element) {
        try {
            boolean state = element.isDisplayed();
            logger.info(state + " Element is displayed " + getElementName(element));
            return state;
        } catch (Exception e) {
            logger.info("Element is not displayed " + getElementName(element));
            return false;
        }
    }

    //select Text from dropdown
    protected void selectTextInDropDown(WebElement dropDown, String text) {
        try {
            Select select = new Select(dropDown);
            select.selectByVisibleText(text);
            logger.info(text + " was selected in DropDown" + getElementName(dropDown));
        } catch (Exception e) {
            logger.error("Can not work with element " + getElementName(dropDown));
            Assert.fail("Can not work with element " + getElementName(dropDown));
        }
    }

    //select Value from dropdown
    protected void selectValueInDropDown(WebElement dropDown, String value) {
        try {
            Select select = new Select(dropDown);
            select.selectByValue(value);
            logger.info(value + " was selected in DropDown" + getElementName(dropDown));
        } catch (Exception e) {
            logger.error("Can not work with element " + getElementName(dropDown));
            Assert.fail("Can not work with element " + getElementName(dropDown));
        }
    }

    protected void checkIsElementVisible(WebElement webElement) {
        Assert.assertTrue("Element is not visible", isElementDisplayed(webElement));
    }

    //check text in element
    protected void checkTextInElement(WebElement element, String expectedText) {
        try {
            String textFromElement = element.getText();
            Assert.assertEquals("Text in element not matched", expectedText, textFromElement);
        } catch (Exception e) {
            logger.error("Can not get text from element ");
            Assert.fail("Can not get text from element ");
        }
    }

    protected void checkIsElementNotVisible(WebElement webElement) {
        Assert.assertFalse("Element is visible", isElementDisplayed(webElement));
    }

    protected void setCheckboxChecked(WebElement checkbox) {
        if (!checkbox.isSelected()) {
            clickOnElement(checkbox);
            logger.info("Checkbox was checked");
        } else {
            logger.info("Checkbox already checked");
        }
    }


    protected void setCheckboxUnchecked(WebElement checkbox) {
        if (checkbox.isSelected()) {
            clickOnElement(checkbox);
            logger.info("Checkbox was unchecked");
        } else {
            logger.info("Checkbox already unchecked");
        }
    }

    protected void setCheckbox(String checked, WebElement checkbox) {
        if (checked.equals("checked")) {
            setCheckboxChecked(checkbox);
        } else if (checked.equals("unchecked")) {
            setCheckboxUnchecked(checkbox);
        } else {
            logger.error("Checkbox status should be 'checked' or 'unchecked'");
            Assert.fail("Checkbox status should be 'checked' or 'unchecked'");
        }
    }

    //press button ENTER on keyboard using Actions class



    //enter text into input field with Actions class

}

package pages;

import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.interactions.Actions;

//all  same methods for all pages
abstract class ParentPage extends CommonActionsWithElements {
    final String baseUrl = "https://www.i.ua/";

    public ParentPage(WebDriver webDriver) {
        super(webDriver);
    }

    //get relative url
    abstract String getRelativeUrl();

    //check if opened page is expected page
    protected void checkUrl() {
        Assert.assertEquals("Invalid page"
                , baseUrl + getRelativeUrl()
                , webDriver.getCurrentUrl()
        );
    }

    //check if opened page is expected page with regex
    //https://aqa-complexapp.onrender.com/post/64d21e84903640003414c338
// regex for 64d21e84903640003414c338
// [a-zA-Z0-9]{24}
//https://aqa-complexapp.onrender.com/post/[a-zA-Z0-9]

    protected void checkUrlWithPattern() {
        Assert.assertTrue("Invalid page\n"
                        + "Expected url: " + baseUrl + getRelativeUrl() + "\n"
                        + "Actual url: " + webDriver.getCurrentUrl()
                , webDriver.getCurrentUrl().matches(baseUrl + getRelativeUrl())
        );
    }

    public void pressEnterKey() {
        try {
            Actions actions = new Actions(webDriver);
            actions.sendKeys(Keys.ENTER).build().perform();
            logger.info("Enter key was pressed");
        } catch (Exception e) {
            logger.error("Can not work with element");
            Assert.fail("Can not work with element");
        }
    }


    public void pressTabKey(int count) {
        try {
            for (int i = 0; i < count; i++) {
                Actions actions = new Actions(webDriver);
                actions.sendKeys(Keys.TAB).build().perform();
                logger.info("Tab key was pressed");
            }
        } catch (Exception e) {
            logger.error("Can not work with element");
            Assert.fail("Can not work with element");
        }
    }

    //check focus in input field with java method and TAb key
   /* protected void checkFocusOnElementAndFillWithData(WebElement element, String text) {
        int counter = 0;
        int MAX_ITERATIONS = 10;
        do {
            pressTabKey();
            logger.info("Tab key was pressed");
            // counter++;
        }
        while (element.equals(webDriver.switchTo().activeElement()));// && counter < MAX_ITERATIONS
        {
            logger.info("Focus is on element ");
            enterTextIntoInputWithActions(text);
        }
    }*/
//switch to new Tab in the same browser
    public void switchToNewTab() {
        try {
            webDriver.switchTo().newWindow(WindowType.TAB);
            logger.info("Switched to new tab");
        } catch (Exception e) {
            logger.error("Can not switch to new tab");
            Assert.fail("Can not switch to new tab");
        }
    }

    //close tabs and switch to first tab
    public void switchToFirstTab() {
        try {
            webDriver.switchTo().window(webDriver.getWindowHandles().iterator().next());
            logger.info("Tabs were closed");
        } catch (Exception e) {
            logger.error("Can not close tabs and switch to first tab");
            Assert.fail("Can not close tabs and switch to first tab");
        }
    }

    //swotch to tab by index
    public void switchToTabByIndex(int index) {
        try {
            webDriver.switchTo().window(webDriver.getWindowHandles().toArray()[index].toString());
            logger.info("Switched to tab by index");
        } catch (Exception e) {
            logger.error("Can not switch to tab by index");
            Assert.fail("Can not switch to tab by index");
        }
    }
    //refresh page
    public void enterTextIntoInputWithActions(String text) {
        try {
            Actions actions = new Actions(webDriver);
            actions.sendKeys(text).build().perform();
            logger.info(text + " was inputted into input ");
        } catch (Exception e) {
            logger.error("Can not work with element ");
            Assert.fail("Can not work with element ");
        }
    }
    public void redirectToNewTab() {
        switchToNewTab();
    }
    public void refreshPage() {
        try {
            webDriver.navigate().refresh();
            logger.info("Page was refreshed");
        } catch (Exception e) {
            logger.error("Can not refresh page");
            Assert.fail("Can not refresh page");
        }
    }


}

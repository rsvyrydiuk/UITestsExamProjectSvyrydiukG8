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

}

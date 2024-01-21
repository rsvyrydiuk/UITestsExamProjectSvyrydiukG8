package emailsTests;

import baseTest.BaseTest;
import org.junit.Test;

public class searchEmailsTests extends BaseTest {
private final String SEARCH_TEXT = "test search";
    @Test
    public void SearchEmailsTest() {
        pageProvider.emailsHomePage().openEmailsHomePageAndLoginIfNeeded()
                .enterSearchTextAndClickOnButtonSearchEmails(SEARCH_TEXT)
                .checkIsResultOfSearchDisplayed(SEARCH_TEXT);
    }
}

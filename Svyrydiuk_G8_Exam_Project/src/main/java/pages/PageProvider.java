package pages;

import org.openqa.selenium.WebDriver;

public class PageProvider {
    private WebDriver webDriver;

    public PageProvider(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public LoginPage loginPage() {
        return new LoginPage(webDriver);
    }

    public CurrencyExchangeRatePage currencyExchangeRatePage() {
        return new CurrencyExchangeRatePage(webDriver);
    }

    public EmailsHomePage emailsHomePage() {
        return new EmailsHomePage(webDriver);
    }
}

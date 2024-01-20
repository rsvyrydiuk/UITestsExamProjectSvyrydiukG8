package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.text.DecimalFormat;

public class CurrencyExchangeRatePage extends ParentPage {

    @FindBy(xpath = "//h2[text()='Середній курс валют у банках']/..//tr/th[text()='USD']/..//td[1]/span/span[1]")
    private WebElement buyUSD;

    @FindBy(xpath = "//input[@id='currency_amount']")
    private WebElement amountCurrencyToSell;

    @FindBy(xpath = "//h4[text()='Результат']/../p[@id='UAH']/input[@id='currency_exchange']")
    private WebElement resultOfExchange;

    public CurrencyExchangeRatePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "https://finance.i.ua/?_gl=1*9zqul5*_ga*Mjc5ODUyODE0LjE3MDA5MTg4Mjk.*_ga_9CZ974SN72*MTcwNTMzMzYyNi4yNC4xLjE3MDUzMzUwOTMuMTUuMC4xMjgyMTUxNjg0";
    }

    public void checkIsRedirectToCurrencyExchangeRatePage() {
        checkUrl();
    }

    public void openCurrencyExchangeRatePage() {
        try {
            webDriver.get(getRelativeUrl());
            logger.info("Currency Exchange Rate page was opened");
        } catch (Exception e) {
            logger.error("Can not open URL");
            Assert.fail("Can not open URL");
        }
    }

    public double getBuyUSD() {
        return Double.parseDouble(buyUSD.getText());
    }

    public void enterAmountCurrencyToSell(String amount) {
        isElementDisplayed(amountCurrencyToSell);
        enterTextIntoInput(amountCurrencyToSell, amount);
    }

    public void checkExchangeCalculation(float amount) {
        enterAmountCurrencyToSell(String.valueOf(amount));
        double sellUSD = getBuyUSD() * amount;
        logger.info("Sell USD = " + sellUSD);
        double result = Double.parseDouble(resultOfExchange.getAttribute("value").replace(" ", ""));
        logger.info("Result = " + result);
        Assert.assertEquals("Invalid calculation", sellUSD, result, 0.01);
    }
}

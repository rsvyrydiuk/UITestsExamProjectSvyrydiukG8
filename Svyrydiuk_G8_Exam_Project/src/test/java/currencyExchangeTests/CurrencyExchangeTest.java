package currencyExchangeTests;

import baseTest.BaseTest;
import org.junit.Test;

public class CurrencyExchangeTest extends BaseTest {
    @Test
    public void currencyExchangeTest() {
        pageProvider.currencyExchangeRatePage().openCurrencyExchangeRatePage();
        pageProvider.currencyExchangeRatePage().checkExchangeCalculation(5);
    }


}

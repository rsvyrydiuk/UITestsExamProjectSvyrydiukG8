package currencyExchangeTests;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import libs.ConfigProvider;
import libs.ExcelSpreadsheetData;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;

@RunWith(JUnitParamsRunner.class)
public class CurrencyExchangeTest extends BaseTest {
    @Test
    @Parameters(method = "parametersForExchangeTests")
    public void currencyExchangeTest(double amount) {
        pageProvider.currencyExchangeRatePage().openCurrencyExchangeRatePage();
        pageProvider.currencyExchangeRatePage().checkExchangeCalculation(amount);
    }

    public Collection parametersForExchangeTests() throws IOException {
        final String pathToDataFile = ConfigProvider.configProperties.DATA_FILE_PATH() + "testDataSuit.xls";
        final String sheetName = "amauntOfCurrency";
        final boolean skipFirstRow = false; // skip first row in excel file
        logger.info("Path to file: " + pathToDataFile + " Sheet name: " + sheetName + " Skip first row: " + skipFirstRow);
        return new ExcelSpreadsheetData(new FileInputStream(pathToDataFile), sheetName, skipFirstRow).getData();
    }

}

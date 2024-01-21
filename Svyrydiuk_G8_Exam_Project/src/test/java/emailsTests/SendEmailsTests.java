package emailsTests;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import libs.ConfigProvider;
import libs.ExcelSpreadsheetData;
import libs.Util;
import org.junit.runner.RunWith;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;

import org.junit.Test;

@RunWith(JUnitParamsRunner.class)
public class SendEmailsTests extends BaseTest {

    @Test
    @Parameters(method = "parametersForEmailsTests")
    public void TC_004_createNewPostTestWithDataFromExcel(String recipient, String subject, String emailBody, String messageText) {
        String subject_title = subject.formatted("TC_004_Svyrydiuk", Util.getDateAndTimeFormatted());
        String email_Body = emailBody.formatted("Svyrydiuk" + Util.getDateAndTimeFormatted());
        pageProvider.emailsHomePage().openEmailsHomePageAndLoginIfNeeded()
                .getHeader().clickOnButtonCreateEmail();
        pageProvider.emailsHomePage().checkIsCreateEmailPageOpened()
                .createNewEmail(recipient, subject_title, email_Body)
                .clickOnButtonSendEmail()
                .checkIsMessageDisplayed(messageText);
        pageProvider.emailsHomePage().checkSubjectsInSentMessage(subject_title);
    }

    public Collection parametersForEmailsTests() throws IOException {
        final String pathToDataFile = ConfigProvider.configProperties.DATA_FILE_PATH() + "testDataSuit.xls";
        final String sheetName = "createEmailWithExcel";
        final boolean skipFirstRow = false; // skip first row in excel file
        logger.info("Path to file: " + pathToDataFile + " Sheet name: " + sheetName + " Skip first row: " + skipFirstRow);
        return new ExcelSpreadsheetData(new FileInputStream(pathToDataFile), sheetName, skipFirstRow).getData();
    }


}

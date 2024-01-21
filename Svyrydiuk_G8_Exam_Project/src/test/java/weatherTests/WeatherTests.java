package weatherTests;

import baseTest.BaseTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.EmailsHomePage;
import pages.WeatherPage;

import java.util.ArrayList;

public class WeatherTests extends BaseTest{
String cityName = "Харків"; // "Харків" or "Харкові"
    @Test
    public void WeatherTest() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.weatherPage().openWeatherPage();
        pageProvider.weatherPage().checkIsWeatherPageOpened();
        pageProvider.weatherPage().selectCityName(cityName);
        pageProvider.weatherPage().checkIsWeatherPageOpenedForSelectedCity("Харкові");
        pageProvider.weatherPage().checkDaysForWeek();
    }

}

package pages;

import libs.Util;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class WeatherPage extends ParentPage {

    @FindBy(xpath = ".//a[@title='перейти до погоди на весь тиждень' and text()='Київ']")
    private WebElement weatherByCityName;

    @FindBy(xpath = ".//h2[text()='Погода']")
    private WebElement titleWeatherPage;

    @FindBy(xpath = ".//span[@id='all_cities_button']")
    private WebElement selectCityName;

    @FindBy(xpath = ".//li/h4")
    private List<WebElement> weekDays;

    public WeatherPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return null;
    }

    public void openWeatherPage() {
        try {
            isElementDisplayed(weatherByCityName);
            clickOnElement(weatherByCityName);
            logger.info("Weather page was opened");
        } catch (Exception e) {
            logger.error("Can not open weather page");
        }
    }

    public void checkIsWeatherPageOpened() {
        isElementDisplayed(titleWeatherPage);
    }

    public void selectCityName(String cityName) {
        isElementDisplayed(selectCityName);
        clickOnElement(selectCityName);
        WebElement city = webDriver.findElement(By.xpath(".//li/div/a[text()='" + cityName + "']"));
        clickOnElement(city);
    }

    public void checkIsWeatherPageOpenedForSelectedCity(String cityName) {
        isElementDisplayed(titleWeatherPage);
        WebElement city = webDriver.findElement(By.xpath(".//h1[text()='Погода у " + cityName + "']"));
        isElementDisplayed(city);
    }

    public WeatherPage checkDaysForWeek() {
        webDriverWait10.until(ExpectedConditions.numberOfElementsToBe(
                By.xpath(".//li/h4"), 7));
        //Util.waitABit(1);

        ArrayList<String> expectedWeekDays = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            expectedWeekDays.add(Util.getDateForWeekDays(i));
        }
        ArrayList<String> actualWeekDays = new ArrayList<>();
        for (WebElement element : weekDays) {
            actualWeekDays.add(element.getText());
        }
        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < 7; i++) {
            softAssertions.assertThat(actualWeekDays.get(i))
                    .as("Result " + i)
                    .contains(expectedWeekDays.get(i));
            logger.info(actualWeekDays.get(i) + " contains " + expectedWeekDays.get(i));
        }

        softAssertions.assertAll(); // check all assertion

        return this;
    }
}

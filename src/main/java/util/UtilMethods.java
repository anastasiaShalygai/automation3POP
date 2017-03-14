package util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Anastasiya on 14.03.2017.
 */
public class UtilMethods {

    public static WebElement waitWebDriver(WebDriver driver, int time, By element) {
        return (new WebDriverWait(driver, time))
                .until(ExpectedConditions.presenceOfElementLocated(element));
    }
}
package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static util.TimeConstants.*;
import static util.UtilMethods.*;

/**
 * Created by Anastasiya on 14.03.2017.
 */
public class MailPage {
    private final By buttonWriteLetter = By.xpath(".//*[@id='b-toolbar__left']//span[contains(@class,\'b-toolbar__btn\')]");
    private final By countIncomingMessages = By.xpath(".//*[@id='b-nav_folders']//*[@class=\'b-nav__item__count\']");
    private final By textAreaMailForSendingLetter = By.xpath("//input[@id='compose_to']/following-sibling::textarea[contains(@class, 'js-input')]");
    private final By textAreaThemeForSendingLetter = By.xpath(".//*[@class = \'compose-head__field\']/*[@class = \'b-input\']");
    private final By buttonSendLetter = By.xpath(".//div[@data-name='send']//*[contains(@class,\'b-toolbar__btn\')]");
    private final By buttonConfirmOk = By.xpath(".//*[@id='MailRuConfirm']//*[@class = \'is-compose-empty_in\']//button[contains(@class, \'ok\')]");
    private final By itemIncomingMessages = By.xpath(".//*[@id='b-nav_folders']//*[@data-id='0']/*[@class = \'b-nav__link\']");
    private final By shortDataIncomingLetter = By.xpath(".//*[@id='b-letters']//*[@class = \'b-datalist__body\']/div[1]//*[contains(@class, \'item__link\')]");
    private String themeLetterAttributeData = "data-subject";
    private String attributeTitle = "title";
    WebDriver driver;

    public MailPage(WebDriver driver) {
        this.driver = driver;
    }

    private int getNumberIncomingMessages() {
        WebElement numberLetters = waitWebDriver(driver, TEN_SECONDS, countIncomingMessages);
        return Integer.parseInt(numberLetters.getText());
    }

    private void setEmailForSendingLetter(String strEmail) {
        driver.findElement(textAreaMailForSendingLetter).sendKeys(strEmail);
    }

    private void setThemeForSendingLetter(String strTheme) {
        driver.findElement(textAreaThemeForSendingLetter).sendKeys(strTheme);
    }

    private void clickButtonWriteLetter() {
        waitWebDriver(driver, HUNDRED_SECONDS, buttonWriteLetter).click();
    }

    private void clickButtonSendLetter() {
        driver.findElement(buttonSendLetter).click();
    }

    private void clickButtonConfirmOk() {
        waitWebDriver(driver, FIFTY_SECONDS, buttonConfirmOk).click();
    }

    private void clickItemIncomingMessages() {
        waitWebDriver(driver, THIRTY_SECONDS, itemIncomingMessages).click();
    }

    public String getThemeIncomingLetterString() {
        return driver.findElement(shortDataIncomingLetter).getAttribute(themeLetterAttributeData);
    }

    public String getEMailIncomingLetterString() {
        return driver.findElement(shortDataIncomingLetter).getAttribute(attributeTitle);
    }

    public MailPage sendMail(String strEmail, String strTheme) {
        int numberIncomingMessages;
        this.clickButtonWriteLetter();
        numberIncomingMessages = getNumberIncomingMessages();
        this.setEmailForSendingLetter(strEmail);
        this.setThemeForSendingLetter(strTheme);
        this.clickButtonSendLetter();
        this.clickButtonConfirmOk();
        this.clickItemIncomingMessages();
        driver.navigate().refresh();
        Assert.assertTrue(numberIncomingMessages < getNumberIncomingMessages());
        return new MailPage(driver);
    }
}

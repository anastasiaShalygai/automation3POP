package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Anastasiya on 14.03.2017.
 */
public class RegistrationPage {

    WebDriver driver;

    @FindBy(id = "mailbox__login")
    private WebElement login;

    @FindBy(id = "mailbox__password")
    private WebElement password;

    @FindBy(id = "mailbox__auth__button")
    private WebElement logInAuthButton;

    public RegistrationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    private void setLogin(String strLogin) {
        login.sendKeys(strLogin);
    }

    private void setPassword(String strPassword) {
        password.sendKeys(strPassword);
    }

    private void clickLogInAuthButton() {
        logInAuthButton.click();
    }

    public MailPage loginToAuto(String strUserName, String strPasword) {
        this.setLogin(strUserName);
        this.setPassword(strPasword);
        this.clickLogInAuthButton();
        return new MailPage(driver);
    }
}

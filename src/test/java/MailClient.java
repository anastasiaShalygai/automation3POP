import grid.GridParallelTestBase;
import grid.Parallelized;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.Platform;
import pages.MailPage;
import pages.RegistrationPage;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Anastasiya on 14.03.2017.
 */
@RunWith(Parallelized.class)
public class MailClient extends GridParallelTestBase {
    private RegistrationPage objRegistration;
    private MailPage objMail;
    private String myEMail = "klient.pochtovyy@mail.ru";
    private String urlMail = "https://mail.ru";
    private String themeLetter = "theme";
    private String myPassword = "123456qwe";

    public MailClient(String browserName) {
        super(browserName);
    }

    @Test
    public void checkMailClient() {
        Properties props = new Properties();
        try {
            props.load(new FileReader("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setPlatform(Platform.WINDOWS);
        driver.get(props.getProperty("base.usr"));
        objRegistration = new RegistrationPage(driver);
        objMail = objRegistration.loginToAuto(myEMail, myPassword);
        objMail.sendMail(myEMail, themeLetter);

        Assert.assertEquals(themeLetter, objMail.getThemeIncomingLetterString());
        Assert.assertTrue(objMail.getEMailIncomingLetterString().contains(myEMail));
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}

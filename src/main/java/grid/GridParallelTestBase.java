package grid;

import org.junit.Before;
import org.junit.runners.Parameterized;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.LinkedList;

public class GridParallelTestBase {
    protected String browserName;
    protected Platform platformName;
    protected WebDriver driver;

    @Parameterized.Parameters
    public static LinkedList<String[]> getEnvironments() throws Exception {
        LinkedList<String[]> env = new LinkedList<String[]>();
        env.add(new String[]{"chrome"});
        env.add(new String[]{"chrome"});
        return env;
    }

    public GridParallelTestBase(String browserName) {
        this.browserName = browserName;
    }

    public void setPlatform(Platform platform) {
        platformName = platform;
    }

    @Before
    public void setUp() throws Exception {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setPlatform(platformName);
        capabilities.setCapability("browserName", browserName);
        capabilities.setCapability("build", "JUnit-Parallel");
        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
    }

}
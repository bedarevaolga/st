package framework;

import org.openqa.selenium.WebDriver;
import java.util.concurrent.TimeUnit;

public final class Browser {

    private static Browser instance;
    private static WebDriver driver;
    private static int implicitlyWait;
    private static int waitForPageToLoad;
    public static ConfigLoader configLoader;


    public static Browser getInstance() {
        if (instance == null) {
            configLoader = new ConfigLoader("selenium.properties");
            implicitlyWait = Integer.parseInt(configLoader.getProperty("implicitlyWait"));
            waitForPageToLoad = Integer.parseInt(configLoader.getProperty("waitForPageToLoad"));
            BrowserFactory browserFactory = new BrowserFactory();
            driver = browserFactory.setUp(configLoader.getProperty("browser"));
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Integer.parseInt(configLoader.getProperty("implicitlyWait")), TimeUnit.SECONDS);
            instance = new Browser();
        }
        return instance;
    }

    public void navigate(String url) {
        driver.get(url);
    }


    private Browser() {
    }

    public static int getImplicitlyWait() {
        return implicitlyWait;
    }

    public static int getForPageToLoadWait() {
        return waitForPageToLoad;
    }


    public WebDriver getDriver() {
        return driver;
    }

    public static void teardown() {
        try {
            instance = null;
            driver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

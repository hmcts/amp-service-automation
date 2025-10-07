package uk.gov.hmcts.cp.bdd.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import uk.gov.hmcts.cp.bdd.steps.WebDriverInstance;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Driver implements WebDriverInstance {

    public static String targetBrowser;

    static {
        // String browser = System.getProperty("browser").toLowerCase();
        targetBrowser = "chrome";
    }

    // private String pwd = System.getProperty("user.dir");
    // System.setProperty("webdriver.chrome.driver", pwd + "/drivers/chromedriver");
    private static WebDriver webDriver;

    public static WebDriver GetWebDriver() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (webDriver != null) {
                webDriver.quit();
            }
        }));

        switch (targetBrowser) {
            case "chrome":
                webDriver = createChromeBrowser();
                break;
            case "remote-chrome":
                webDriver = createRemoteDriver();
                break;
            default:
                throw new IllegalArgumentException("Target browser " + targetBrowser + " not recognized");
        }

        // webDriver.manage().deleteAllCookies();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        webDriver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
        webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        webDriver.getWindowHandle();

        return webDriver;
    }

    private static WebDriver createChromeBrowser() {
        ChromeOptions options = new ChromeOptions();

        boolean onCI = "true".equalsIgnoreCase(System.getenv("GITHUB_ACTIONS"));
        if (onCI) {
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--window-size=1920,1080");
        }

        // Example: speed up / stabilize
        options.addArguments("--disable-gpu");           // harmless on Linux/headless
        options.addArguments("--disable-features=VizDisplayCompositor");

        ChromeDriver driver = new ChromeDriver(options);

        if (!onCI) {
            // only maximize when not headless
            driver.manage().window().maximize();
        }

        return driver;
    }

    private static WebDriver createRemoteDriver() {
        try {
            ChromeOptions options = new ChromeOptions();
            RemoteWebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
            driver.setFileDetector(new LocalFileDetector());
            return driver;
        } catch (MalformedURLException e) {
            throw new RuntimeException("Failed to create RemoteWebDriver instance", e);
        }
    }
}

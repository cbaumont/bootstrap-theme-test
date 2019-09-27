package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;
import property.PropertyLoader;

import java.net.URL;

public enum DriverFactory implements IDriverType {

    FIREFOX {
        public MutableCapabilities returnDriver() {
            return new FirefoxOptions();
        }
    },

    FIREFOX_HEADLESS {
        public MutableCapabilities returnDriver() {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.setHeadless(true);
            return firefoxOptions;
        }
    },

    CHROME {
        @Override
        public MutableCapabilities returnDriver() {
            return defaultChromeOptions();
        }
    },

    CHROME_HEADLESS {
        @Override
        public MutableCapabilities returnDriver() {
            return ((ChromeOptions) defaultChromeOptions()).addArguments("headless");
        }
    },

    SAFARI {
        @Override
        public MutableCapabilities returnDriver() {
            return new SafariOptions();
        }
    },

    EDGE {
        @Override
        public MutableCapabilities returnDriver() {
            return new EdgeOptions();
        }
    };

    private static final Logger LOGGER = LogManager.getLogger();

    private static MutableCapabilities defaultChromeOptions() {
        ChromeOptions capabilities = new ChromeOptions();
        capabilities.addArguments("start-maximized");
        capabilities.addArguments("lang=pt-BR");
        capabilities.setExperimentalOption("useAutomationExtension", false);
        return capabilities;
    }

    public static WebDriver createDriver(String browser) throws Exception {
        WebDriver driver;
        RemoteWebDriver remoteWebDriver;

        switch (PropertyLoader.getConfigSelenium().get("execution")) {

            case "local":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;

            case "grid":
                String gridURL = PropertyLoader.getGridData().get("url") + ":" + PropertyLoader.getGridData().get("port") + "/wd/hub";
                remoteWebDriver = new RemoteWebDriver(new URL(gridURL), getCapabilities(browser));

                driver = remoteWebDriver;
                break;

            default:
                throw new Exception("Browser not found: " + browser);
        }

        return driver;
    }

    private static MutableCapabilities getCapabilities(String browser) {
        return valueOf(browser.toUpperCase()).returnDriver();
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}

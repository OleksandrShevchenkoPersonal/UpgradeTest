package core;

import org.openqa.selenium.WebDriver;
import core.browsers.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;
import org.apache.log4j.Logger;


public class Driver {
    private static final ThreadLocal<WebDriver> WEB_DRIVER = new ThreadLocal<>();
    private static final String PROPERTIES_FILE_NAME = "src/main/resources/config.properties";
    private static final Logger LOG = Logger.getLogger("console");

    public enum BrowserType {
        CHROME("Chrome", new String[]{"--disable-extensions", "--allow-running-insecure-content",
                "--ignore-certificate-errors", "--disable-print-preview", "--test-type", "--disable-web-security",
                "--disk-cache-size=1", "--media-cache-size=1", "--disable-infobars"});
        private final Object[] values;

        BrowserType(Object... vals) {
            values = vals;
        }

        public String getName() {
            return (String) values[0];
        }

        public String[] getOptions() {
            return (String[]) values[1];
        }

        public static BrowserType findByName(String name) {
            for (BrowserType v : values()) {
                if (v.getName().equals(name)) {
                    return v;
                }
            }
            return BrowserType.CHROME;
        }

    }

    public static WebDriver getWebDriver() {
        if (WEB_DRIVER.get() == null) {
            BrowserType type = BrowserType.findByName(Prop.get("browser"));
            switch (type) {
                case CHROME:
                    setChromeDriver();
                    break;
                default:
                    LOG.error("[ERROR] Browser is not defined!");
            }
            WEB_DRIVER.get().manage().timeouts().implicitlyWait(
                    Duration.ofSeconds(Long.parseLong(Prop.get("defaultImplicitWait"))));
            WEB_DRIVER.get().manage().timeouts().pageLoadTimeout(
                    Duration.ofSeconds(Long.parseLong(Prop.get("pageLoadTimeout"))));
        }
        return WEB_DRIVER.get();
    }

    public static WebDriver getWebDriver(boolean createNewDriver) {
        if (!createNewDriver) {
            return WEB_DRIVER.get();
        }
        return getWebDriver();
    }

    public static class Prop {
        public static String get(String property) {
            String out = "";
            Properties prop = new Properties();
            try (InputStream file = new FileInputStream(new File(PROPERTIES_FILE_NAME).getAbsolutePath())) {
                prop.load(file);
                out = prop.getProperty(property);
            } catch (Exception exception) {
                LOG.error("Exception while reading properties: " + exception);
            }
            return out;

        }
    }

    static void setWebDriver(WebDriver driver) {
        WEB_DRIVER.set(driver);
    }

    private static void setChromeDriver() {
        setWebDriver(new Chrome().getDriver());
    }

}

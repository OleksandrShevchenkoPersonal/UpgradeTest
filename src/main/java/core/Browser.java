package core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class Browser {

    private static final Logger LOG = Logger.getLogger("console");

    public static void open(String url) {
        WebDriver webDriver = Driver.getWebDriver();
        webDriver.manage().window().maximize();
        LOG.info(String.format("[INFO] Open browser with %s url address", url));
        webDriver.get(url);
    }

    public static void navigate(String url) {
        WebDriver webDriver = Driver.getWebDriver();
        webDriver.navigate().to(url);
    }

    public static WebElement getElement(By webElement) {
        return Driver.getWebDriver().findElement(webElement);
    }

    public static void waitForElementVisibility(By webElement) {
        getWaiter().until(ExpectedConditions.visibilityOfElementLocated(webElement));
    }

    public static void waitForElementClickable(By webElement) {
        getWaiter().until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public static void waitForElementExists(By webElement) {
        getWaiter().until(ExpectedConditions.presenceOfElementLocated(webElement));
    }

    private static WebDriverWait getWaiter() {
        WebDriver webDriver = Driver.getWebDriver();
        return new WebDriverWait(webDriver, Duration.ofSeconds(30));
    }

    public static String getTitle() {
        LOG.info("[INFO] Get title from browser");
        return Driver.getWebDriver().getTitle();
    }

    public static void quit() {
        if (Objects.nonNull(Driver.getWebDriver(false))) {
            LOG.info("[INFO] Closing Browser");
            Driver.getWebDriver().quit();
            Driver.setWebDriver(null);
        }
    }
}

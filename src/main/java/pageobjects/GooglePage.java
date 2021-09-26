package pageobjects;

import core.Browser;
import core.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.apache.log4j.Logger;

import java.util.List;

public class GooglePage {

    public static final By searchFieldLocator = By.id("lst-ib");
    public static final By searchLinkLocator = By.xpath("//h3[@class='r']/a");

    private static final Logger Log = Logger.getLogger("console");

    public static void invokeHomePage() {
        Browser.open("https://www.google.com");
        Log.info("[INFO] Trying to invoke Google page");
        if (!Browser.getTitle().equals("Google")) {
            throw new IllegalStateException("[ERROR] Google page was not opened!");
        }
        Log.info("[INFO] Google page had been successfully loaded");
    }

    public static void searchResultsInvoke(String text) {
        Log.info("[INFO] Looking for phrase '" + text + "' on result page");
        Browser.getElement(searchFieldLocator).sendKeys(text + Keys.ENTER);
    }

    private static By nextPageLink(int i) {
        return By.xpath("//*[@aria-label='Page " + i + "']");
    }

    public static void navigateToNextPage(int i) {
        WebElement element = Browser.getElement(nextPageLink(i));
        Dimension size = element.getSize();
        Actions actions = new Actions(Driver.getWebDriver());
        actions.moveToElement(element, size.getWidth() - 1, size.getHeight() - 1).click().build().perform();
        Log.info("[INFO] Page " + i + " was invoked");
    }

    public static void navigateToFirstLink() {
        WebElement searchLink = Browser.getElement(searchLinkLocator);
        String url = searchLink.getAttribute("href");
        searchLink.click();
        Log.info("[INFO] First link was clicked. New page load with URL-" + url);
    }

    private static boolean domainExistsOnPage(String searchDomainValue) {
        String domainValue;
        boolean bFound = false;
        List<WebElement> pageContent = Driver.getWebDriver().findElements(By.xpath("//cite"));

        for (int index = 0; index < pageContent.size(); index++) {
            domainValue = pageContent.get(index).getText();
            if (domainValue.contains(searchDomainValue)) {
                Log.info("[INFO] " + searchDomainValue + " was found on current page, link #" + index);
                bFound = true;
                break;
            }
        }
        return bFound;
    }

    public static boolean checkDomainExistsOnPages(String searchDomainValue, String pagesCount) {
        Integer page = 1;
        boolean bFound = false;
        do {
            bFound = domainExistsOnPage(searchDomainValue);
            if (!bFound)
                navigateToNextPage(++page);
        }
        while (!bFound && page <= Integer.parseInt(pagesCount) - 1);

        return bFound;
    }

}
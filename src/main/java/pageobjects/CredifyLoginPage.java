package pageobjects;

import core.Browser;
import core.cache.TestCache;
import dto.DefaultCustomerInfo;
import enums.TestCacheKey;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class CredifyLoginPage {

    private static final Logger LOG = Logger.getLogger("console");

    private static final String url = "https://www.credify.tech/portal/login";
    private static final By BORROWER_EMAIL_INPUT = By.xpath("//input[@name='username']");
    private static final By BORROWER_PASSWORD_INPUT = By.xpath("//input[@name='password']");
    private static final By SIGN_IN_BUTTON = By.xpath("//button[@data-auto='login']");

    public static void userNavigatesToLoginPage() {
        LOG.info("[INFO] User opens Login page");
        Browser.navigate(url);
        Browser.waitForElementVisibility(BORROWER_EMAIL_INPUT);

    }

    public static void userLogsInWithSavedCredentials() {
        LOG.info("[INFO] User Logins with pre-saved email and password");
        setEmail(TestCache.get(TestCacheKey.EMAIL).toString());
        setPassword((DefaultCustomerInfo.PASSWORD));
        userClicksOnSignInButton();
    }

    private static void setEmail(final String email) {
        LOG.info(String.format("[INFO] User fills email address as %s", email));
        Browser.getElement(BORROWER_EMAIL_INPUT).sendKeys(email);
    }

    private static void setPassword(final String password) {
        LOG.info(String.format("[INFO] User fills password as %s", password));
        Browser.getElement(BORROWER_PASSWORD_INPUT).sendKeys(password);
    }

    private static void userClicksOnSignInButton() {
        LOG.info("[INFO] User clicks on 'Sign In to your account' button");
        Browser.waitForElementClickable(SIGN_IN_BUTTON);
        Browser.getElement(SIGN_IN_BUTTON).click();
    }
}

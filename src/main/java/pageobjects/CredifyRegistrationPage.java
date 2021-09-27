package pageobjects;

import core.Browser;
import core.cache.TestCache;
import dto.DefaultCustomerInfo;
import enums.TestCacheKey;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import java.util.concurrent.ThreadLocalRandom;

public class CredifyRegistrationPage {

    private static final Logger LOG = Logger.getLogger("console");

    private static final By BORROWER_EMAIL_INPUT = By.xpath("//input[@name='username']");
    private static final By BORROWER_PASSWORD_INPUT = By.xpath("//input[@name='password']");
    private static final By TERMS_AND_POLICIES_CHECKBOX = By.xpath("//input[@type='checkbox']");
    private static final By TERMS_AND_POLICIES_LABEL = By.xpath("//div[text()='I have read and agree to:']");
    private static final By CHECK_YOUR_RATE_BUTTON = By.xpath("//button[@data-auto='submitPersonalInfo']");

    public static void userSeesRegistrationPage() {
        LOG.info("[INFO] User sees Registration page");
        Browser.waitForElementVisibility(BORROWER_EMAIL_INPUT);
    }

    private static void setEmail(final String email) {
        LOG.info(String.format("[INFO] User fills email address as %s", email));
        Browser.getElement(BORROWER_EMAIL_INPUT).sendKeys(email);
    }

    private static void setPassword(final String password) {
        LOG.info(String.format("[INFO] User fills password as %s", password));
        Browser.getElement(BORROWER_PASSWORD_INPUT).sendKeys(password);
    }

    private static String generateEmail() {
        String borrowerEmail = String.format(DefaultCustomerInfo.RANDOM_EMAIL_TEMPLATE,
                ThreadLocalRandom.current().nextInt(10, 99));
        TestCache.putDataInCache(TestCacheKey.EMAIL, borrowerEmail);
        return borrowerEmail;
    }

    public static void userSetsDefaultCredentials() {
        setEmail(generateEmail());
        setPassword(DefaultCustomerInfo.PASSWORD);
    }

    public static void userAcceptsTermsAndPolicies() {
        LOG.info("[INFO] User accepts Terms&Policies");
        Browser.getElement(TERMS_AND_POLICIES_LABEL).click();
        if (!Browser.getElement(TERMS_AND_POLICIES_CHECKBOX).isSelected()) {
            LOG.error("Unable to set checkbox to 'True'");
        }
    }

    public static void userClicksOnCheckYourRateButton() {
        LOG.info("[INFO] User clicks on 'Check Your Rate' button on Registration page");
        Browser.waitForElementClickable(CHECK_YOUR_RATE_BUTTON);
        Browser.getElement(CHECK_YOUR_RATE_BUTTON).click();
    }
}

package pageobjects;

import core.Browser;
import dto.DefaultCustomerInfo;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class CredifyRegistrationPage {

    private static final Logger LOG = Logger.getLogger("console");

    private static final By BORROWER_EMAIL_INPUT = By.xpath("//input[@name='username']");
    private static final By BORROWER_PASSWORD_INPUT = By.xpath("//input[@name='password']");
    private static final By SUBMIT_BUTTON = By.xpath("//button[@data-auto='submitPersonalInfo']");

    public static void userSeesRegistrationPage() {
        LOG.info("[INFO] Borrower sees Registration page");
        Browser.waitForElementVisibility(BORROWER_EMAIL_INPUT);
    }

    private static void setEmail(final String email) {
        LOG.info(String.format("[INFO] Borrower fills email address as %s", email));
        Browser.getElement(BORROWER_EMAIL_INPUT).sendKeys(email);
    }

    private static void setPassword(final String password) {
        LOG.info(String.format("[INFO] Borrower fills password as %s", password));
        Browser.getElement(BORROWER_PASSWORD_INPUT).sendKeys(password);
    }

    public static void userSetsDefaultCredentials() {
        setEmail(String.format(DefaultCustomerInfo.RANDOM_EMAIL_TEMPLATE, 12));
        setPassword(DefaultCustomerInfo.PASSWORD);
    }

    private static void userAcceptsTermsAndPolicies(final String password) {
        LOG.info("[INFO] Borrower accepts Terms&Policies");
        Browser.getElement().sendKeys(password);
    }

    public static void userClicksOnContinueButton() {
        LOG.info("[INFO] click on 'Submit' button on Registration page");
        Browser.getElement(SUBMIT_BUTTON).click();
    }
}

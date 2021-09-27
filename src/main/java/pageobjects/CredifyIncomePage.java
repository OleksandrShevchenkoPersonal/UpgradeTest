package pageobjects;

import core.Browser;
import dto.DefaultCustomerInfo;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class CredifyIncomePage {

    private static final Logger LOG = Logger.getLogger("console");

    private static final By BORROWER_ANNUAL_INCOME_INPUT = By.xpath("//input[@name='borrowerIncome']");
    private static final By BORROWER_ADDITIONAL_INCOME_INPUT = By.xpath("//input[@name='borrowerAdditionalIncome']");
    private static final By CONTINUE_BUTTON = By.xpath("//button[@data-auto='continuePersonalInfo']");

    public static void userSeesIncomePage() {
        LOG.info("[INFO] User sees Annual Income page");
        Browser.waitForElementVisibility(BORROWER_ANNUAL_INCOME_INPUT);
    }

    private static void setAnnualIncome(final String income) {
        LOG.info(String.format("[INFO] User fills the Annual Income value as %s", income));
        Browser.getElement(BORROWER_ANNUAL_INCOME_INPUT).sendKeys(income);
    }

    private static void setAdditionalAnnualIncome(final String income) {
        LOG.info(String.format("[INFO] User fills the additional Annual Income value as %s", income));
        Browser.getElement(BORROWER_ADDITIONAL_INCOME_INPUT).sendKeys(income);
        Browser.getElement(BORROWER_ADDITIONAL_INCOME_INPUT).sendKeys(Keys.TAB);
    }

    public static void userSetsBorrowerIncome() {
        setAnnualIncome(DefaultCustomerInfo.INCOME);
        setAdditionalAnnualIncome(DefaultCustomerInfo.ADDITIONAL_INCOME);
    }

    public static void clickOnContinueButton() {
        LOG.info("[INFO] User clicks on 'Continue' button on borrower Income page");
        Browser.waitForElementClickable(CONTINUE_BUTTON);
        Browser.getElement(CONTINUE_BUTTON).click();
    }
}

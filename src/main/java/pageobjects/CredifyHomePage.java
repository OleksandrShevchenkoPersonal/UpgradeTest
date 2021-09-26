package pageobjects;

import core.Browser;
import enums.LoanPurposeType;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static java.lang.Thread.sleep;

public class CredifyHomePage {

    private static final Logger LOG = Logger.getLogger("console");

    private static final By LOAN_AMOUNT_INPUT_FIELD = By.xpath("//input[@name='desiredAmount']");
    private static final By LOAN_PURPOSE_DROPDOWN = By.xpath("//select[@name='loan-purpose']");
    private static final By CHECK_YOUR_RATE_BUTTON = By.xpath("//button[@data-auto='CheckYourRate']");

    public static void invokeHomePage() {
        LOG.info("[INFO] Trying to invoke Credify Funnel page page");
        Browser.open("https://www.credify.tech/funnel/nonDMFunnel");
    }

    public static void setDesiredLoanAmount(final String loanAmount) {
        LOG.info("[INFO] Set desired Loan Amount on Credify Funnel page");
        Browser.getElement(LOAN_AMOUNT_INPUT_FIELD).click();
        Browser.getElement(LOAN_AMOUNT_INPUT_FIELD).sendKeys(loanAmount);
    }

    public static void selectLoanPurpose(final LoanPurposeType loanPurpose) {
        LOG.info("[INFO] Select Loan Purpose on Credify Funnel page");
        Select loanPurposeSelect = new Select(Browser.getElement(LOAN_PURPOSE_DROPDOWN));
        loanPurposeSelect.selectByVisibleText(loanPurpose.toString());
    }

    public static void clickCheckYourRateButton() {
        LOG.info("[INFO] click Check Your Rate button on Credify Funnel page");
        Browser.getElement(CHECK_YOUR_RATE_BUTTON).click();
    }


}

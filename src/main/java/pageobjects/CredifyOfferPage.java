package pageobjects;

import core.Browser;
import core.cache.TestCache;
import dto.LoanData;
import enums.TestCacheKey;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CredifyOfferPage {

    private static final Logger LOG = Logger.getLogger("console");

    private static final By USER_LOAN_AMOUNT = By.xpath("//span[@data-auto='userLoanAmount']");
    private static final By FASTER_PAYOFF_LOAN_COMPONENT = By.xpath("//div[@data-auto='HeroOfferBox0']");
    private static final By MONTHLY_PAYMENT_LABEL = By.xpath("//li/div[@data-auto='defaultMonthlyPayment']");
    private static final By TERM_LABEL = By.xpath("//li[@data-auto='defaultLoanTerm']/div");
    private static final By INTEREST_RATE_LABEL = By.xpath("//li[@data-auto='defaultLoanInterestRate']/div");
    private static final By APR_LABEL = By.xpath("//li/div[@data-auto='defaultAPR']");
    private static final By MENU_BUTTON = By.xpath("//label[@class='header-nav__toggle']");
    private static final By LOGOUT_MENU_BUTTON = By.xpath("//a[contains(@href, 'funnel/logout')]");

    private static WebElement getFasterPayoff() {
        Browser.waitForElementVisibility(FASTER_PAYOFF_LOAN_COMPONENT);
        return Browser.getElement(FASTER_PAYOFF_LOAN_COMPONENT);
    }

    public static void userSeesOfferPage() {
        LOG.info("[INFO] User sees the Offer page");
        getFasterPayoff();
    }

    public static void logOut() {
        LOG.info("[INFO] User Log Out from Offer page");
        if (!Browser.getElement(MENU_BUTTON).isEnabled()) {
            Browser.waitForElementVisibility(MENU_BUTTON);
            Browser.waitForElementAvailability(MENU_BUTTON);
        }
        Browser.getElement(MENU_BUTTON).click();
        Browser.waitForElementVisibility(LOGOUT_MENU_BUTTON);
        Browser.getElement(LOGOUT_MENU_BUTTON).click();
    }

    public static void rememberLoanDetails() {
        WebElement fasterPayoff = getFasterPayoff();
        LoanData loanData = new LoanData();

        loanData.setMonthlyPayment(getMonthlyPaymentAmount(fasterPayoff));
        loanData.setTerm(getTermsAmount(fasterPayoff));
        loanData.setInterestRate(getInterestRateAmount(fasterPayoff));
        loanData.setAprInfo(getAPRAmount(fasterPayoff));
        loanData.setAmount(getUserLoanAmount());

        TestCache.putDataInCache(TestCacheKey.LOAN_DATA, loanData);
    }

    private static String getUserLoanAmount() {
        LOG.info("[INFO] User remembers Loan amount");
        return Browser.getElement(USER_LOAN_AMOUNT).getText();
    }

    private static String getMonthlyPaymentAmount(final WebElement payoff) {
        LOG.info("[INFO] User remembers Monthly payment amount");
        return payoff.findElement(MONTHLY_PAYMENT_LABEL).getText();
    }

    private static String getTermsAmount(final WebElement payoff) {
        LOG.info("[INFO] User remembers Terms amount");
        return payoff.findElement(TERM_LABEL).getText();
    }

    private static String getInterestRateAmount(final WebElement payoff) {
        LOG.info("[INFO] User remembers Interest Rate amount");
        return payoff.findElement(INTEREST_RATE_LABEL).getText();
    }

    private static String getAPRAmount(final WebElement payoff) {
        LOG.info("[INFO] User remembers APR amount");
        return payoff.findElement(APR_LABEL).getText();
    }
}

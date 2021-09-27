import core.cache.TestCache;
import dto.LoanData;
import enums.LoanPurposeType;
import enums.TestCacheKey;
import org.assertj.core.api.Assertions;
import org.testng.annotations.*;
import org.apache.log4j.Logger;
import core.*;
import pageobjects.*;


public class UpgradeInterviewTest {

    private static final Logger LOG = Logger.getLogger("console");

    @BeforeMethod
    public void setUp() {
        TestCache.initializeTestCache();
    }

    @AfterMethod
    public static void cleanUp() {
        Browser.quit();
    }

    @Test(enabled = true)
    @Parameters({"loanAmount", "loanPurpose"})
    public void test_loan_happy_path(@Optional("10000") String loanAmount,
                                        @Optional("SMALL_BUSINESS") LoanPurposeType purpose) {

        CredifyHomePage.invokeHomePage();
        CredifyHomePage.setDesiredLoanAmount(loanAmount);
        CredifyHomePage.selectLoanPurpose(purpose);
        CredifyHomePage.clickCheckYourRateButton();
        CredifyHomePage.clickOnContinueWithUpgradeLink();

        CredifyLetsGetStartedPage.userSeesGetStartedPage();
        CredifyLetsGetStartedPage.userFillsInfoOnGetStartedPage();
        CredifyLetsGetStartedPage.userClicksContinueButton();

        CredifyIncomePage.userSeesIncomePage();
        CredifyIncomePage.userSetsBorrowerIncome();
        CredifyIncomePage.clickOnContinueButton();

        CredifyRegistrationPage.userSeesRegistrationPage();
        CredifyRegistrationPage.userSetsDefaultCredentials();
        CredifyRegistrationPage.userAcceptsTermsAndPolicies();
        CredifyRegistrationPage.userClicksOnCheckYourRateButton();

        CredifyOfferPage.userSeesOfferPage();
        CredifyOfferPage.rememberLoanDetails();
        LoanData initialData = TestCache.get(TestCacheKey.LOAN_DATA, LoanData.class);
        CredifyOfferPage.logOut();

        CredifyLoginPage.userNavigatesToLoginPage();
        CredifyLoginPage.userLogsInWithSavedCredentials();

        CredifyOfferPage.userSeesOfferPage();
        CredifyOfferPage.rememberLoanDetails();
        LoanData secondaryData = TestCache.get(TestCacheKey.LOAN_DATA, LoanData.class);

        Assertions.assertThat(initialData).as("The loan data after login is not as expected!")
                .isEqualTo(secondaryData);

        LOG.info("[INFO] Testcase test_loan_happy_path was successfully completed!");

    }


}
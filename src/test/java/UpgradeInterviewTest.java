import enums.LoanPurposeType;
import org.testng.annotations.*;
import org.apache.log4j.Logger;
import core.*;
import pageobjects.*;

import static org.assertj.core.api.Assertions.assertThat;

public class UpgradeInterviewTest {

    private static final Logger LOG = Logger.getLogger("console");

    @BeforeMethod
    public void setUp() {}

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
        CredifyRegistrationPage.userClicksOnContinueButton();

        LOG.info("[INFO] Testcase test_loan_happy_path was successfully completed!");

    }


}
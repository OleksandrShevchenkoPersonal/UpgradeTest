import core.cache.TestCache;
import dto.FunnelDTO;
import dto.LoanData;
import dto.LoanRequestInfoDTO;
import enums.LoanPurposeType;
import enums.TestCacheKey;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.testng.annotations.*;
import org.apache.log4j.Logger;
import core.*;
import pageobjects.*;
import webservices.Parser;
import webservices.RestClient;


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
                .isEqualToComparingFieldByField(secondaryData);

        LOG.info("[INFO] Testcase test_loan_happy_path was completed!");
    }

    @Test(enabled = true)
    @Parameters({"loanAppUuid"})
    public void test_loan_api_request(@Optional("b8096ec7-2150-405f-84f5-ae99864b3e96") String loanAppUuid) {

        FunnelDTO payload = new FunnelDTO();
        payload.setLoanAppUuid(loanAppUuid);
        payload.setSkipSideEffects(false);

        Response response = RestClient.sendPostTestFunnelRequest(payload);
        int actualResponseCode = response.getStatusCode();
        Assertions.assertThat(actualResponseCode)
                .as("The response code is not as expected. Received %d", actualResponseCode)
                .isEqualTo(200);

        Assertions.assertThat(response.getHeader("Content-Type"))
                .as("The 'x-cf-source-id' header is not as expected!")
                .isEqualTo("application/json");

        LoanRequestInfoDTO passedData = Parser.fromJson(response.getBody().asString(), LoanRequestInfoDTO.class);
        String actualProductType = passedData.getLoanAppResumptionInfo().getProductType();
        Assertions.assertThat(actualProductType)
                .as("The response code is not as expected. Received %d", actualProductType)
                .isEqualTo("PERSONAL_LOAN");

        payload.setSkipSideEffects(true);
        actualResponseCode = RestClient.sendPostTestFunnelRequest(payload).getStatusCode();
        Assertions.assertThat(actualResponseCode)
                .as("The response code is not as expected. Received %d", actualResponseCode)
                .isEqualTo(200);

        payload.setLoanAppUuid(loanAppUuid.replace("0", "1"));
        actualResponseCode = RestClient.sendPostTestFunnelRequest(payload).getStatusCode();
        Assertions.assertThat(actualResponseCode)
                .as("The response code is not as expected. Received %d", actualResponseCode)
                .isEqualTo(404);

        LOG.info("[INFO] Testcase test_loan_api_request was completed!");
    }
}
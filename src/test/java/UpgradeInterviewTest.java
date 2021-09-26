import enums.LoanPurposeType;
import org.assertj.core.api.SoftAssertions;
import org.testng.Assert;
import org.testng.annotations.*;
import org.apache.log4j.Logger;
import core.*;
import pageobjects.CredifyHomePage;
import pageobjects.GooglePage;

public class UpgradeInterviewTest {

    private static final Logger LOG = Logger.getLogger("console");

    @BeforeMethod
    public void setUp() throws InterruptedException {
        CredifyHomePage.invokeHomePage();
    }

    @AfterMethod
    public static void cleanUp() {
        Browser.quit();
    }

    @Test(enabled = true)
    @Parameters({"loanAmount", "loanPurpose"})
    public void test_Search_First_Title(@Optional("10000") String loanAmount,
                                        @Optional("SMALL_BUSINESS") LoanPurposeType purpose) {

        CredifyHomePage.invokeHomePage();
        CredifyHomePage.setDesiredLoanAmount(loanAmount);
        CredifyHomePage.selectLoanPurpose(purpose);
        CredifyHomePage.clickCheckYourRateButton();

        String actualTitle = Browser.getTitle();
        Assert.assertTrue(actualTitle.toLowerCase().contains(phraseToSearch.toLowerCase()),
                "Actual Title: " + actualTitle + " Expected Title: " + phraseToSearch);
        LOG.info("[INFO] Testcase test_Search_First_Title was successfully completed!");

    }

    @Test(enabled = false)
    @Parameters({"phraseToSearch", "pagesCount", "domainToSearchFor"})
    public void test_Search_For_Domain(@Optional("automation") String searchPattern,
                                       @Optional("5") String pagesCount,
                                       @Optional("testautomationday.com") String domainToSearchFor) {

        GooglePage.searchResultsInvoke(searchPattern);
        Boolean domainFound = GooglePage.checkDomainExistsOnPages(domainToSearchFor, pagesCount);

        Assert.assertTrue(domainFound, "ERROR: " + domainToSearchFor + " was not found on Pages [1-" + pagesCount + "].");
        LOG.info("[INFO] Test SearchDomainTest was successfully completed!");

    }

}
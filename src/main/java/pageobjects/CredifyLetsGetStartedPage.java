package pageobjects;

import core.Browser;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class CredifyLetsGetStartedPage {

    private static final Logger LOG = Logger.getLogger("console");

    private static final By GET_STERTED_HEADER = By.xpath("//h1[text()='Let's get started with some basic information']");

    private static final By FIRST_NAME_INPUT_FIELD = By.xpath("//input[@name='borrowerFirstName");
    private static final By LAST_NAME_INPUT_FIELD = By.xpath("//input[@name='borrowerLastName");
    private static final By CITY_INPUT_FIELD = By.xpath("//input[@name='borrowerCity");
    private static final By STATE_INPUT_FIELD = By.xpath("//input[@name='borrowerState");
    private static final By ZIP_CODE_INPUT_FIELD = By.xpath("//input[@name='borrowerZipCode");
    private static final By BIRTHDAY_INPUT_FIELD = By.xpath("//input[@name='borrowerDateOfBirth");
    private static final By CONTINUE_BUTTON = By.xpath("//button[@data-auto='continuePersonalInfo']")

    public void setBorrowerFirstName(final String firstName) {
        LOG.info(String.format("[INFO] User sets Borrower FirstName as %s", firstName));
        Browser.getElement(FIRST_NAME_INPUT_FIELD).sendKeys(firstName);
    }

    public void setBorrowerLastName(final String lastName) {
        LOG.info(String.format("[INFO] User sets Borrower LastName as %s", lastName));
        Browser.getElement(LAST_NAME_INPUT_FIELD).sendKeys(lastName);
    }

    public void setBorrowerCity(final String city) {
        LOG.info(String.format("[INFO] User sets Borrower City as %s", city));
        Browser.getElement(CITY_INPUT_FIELD).sendKeys(city);
    }

    public void setBorrowerState(final String state) {
        LOG.info(String.format("[INFO] User sets Borrower State as %s", state));
        Browser.getElement(STATE_INPUT_FIELD).sendKeys(state);
    }

    public void setBorrowerZipCode(final String zipCode) {
        LOG.info(String.format("[INFO] User sets Borrower ZipCode as %s", zipCode));
        Browser.getElement(ZIP_CODE_INPUT_FIELD).sendKeys(zipCode);
    }

    public void setBorrowerDateOfBirth(final String dateOfBirth) {
        LOG.info(String.format("[INFO] User sets Borrower DateOfBirth as %s", dateOfBirth));
        Browser.getElement(BIRTHDAY_INPUT_FIELD).sendKeys(dateOfBirth);
    }

    public void userClicksContinueButton() {
        LOG.info("[INFO] User Clicks Continue button on Let's Get Started page");
        Browser.getElement(CONTINUE_BUTTON).click();
    }

    public boolean userSeesGetStartedPage() {
        LOG.info("[INFO] Verify user sees Let's Get Started page");
        return Browser.getElement(GET_STERTED_HEADER).isDisplayed();
    }

}

package pageobjects;

import com.github.javafaker.Faker;
import core.Browser;
import dto.DefaultCustomerInfo;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.Random;

public class CredifyLetsGetStartedPage {

    private static final Logger LOG = Logger.getLogger("console");
    private static final By GET_STERTED_HEADER = By.xpath("//h1[text()=\"Let's get started with some basic information\"]");
    private static final By FIRST_NAME_INPUT_FIELD = By.xpath("//input[@name='borrowerFirstName']");
    private static final By LAST_NAME_INPUT_FIELD = By.xpath("//input[@name='borrowerLastName']");
    private static final By HOME_ADDRESS_INPUT_FIELD = By.xpath("//input[@name='borrowerStreet']");

    private static final By HOME_ADDRESS_SUGGESTION = By.xpath("//li[@class='geosuggest__item']");
    private static final By CITY_INPUT_FIELD = By.xpath("//input[@name='borrowerCity']");
    private static final By STATE_INPUT_FIELD = By.xpath("//input[@name='borrowerState']");
    private static final By ZIP_CODE_INPUT_FIELD = By.xpath("//input[@name='borrowerZipCode']");
    private static final By BIRTHDAY_INPUT_FIELD = By.xpath("//input[@name='borrowerDateOfBirth']");
    private static final By CONTINUE_BUTTON = By.xpath("//button[@data-auto='continuePersonalInfo']");

    private static void setBorrowerFirstName(final String firstName) {
        LOG.info(String.format("[INFO] User sets Borrower FirstName as %s", firstName));
        Browser.waitForElementVisibility(HOME_ADDRESS_INPUT_FIELD);
        Browser.getElement(FIRST_NAME_INPUT_FIELD).sendKeys(firstName);
    }

    private static void setBorrowerLastName(final String lastName) {
        LOG.info(String.format("[INFO] User sets Borrower LastName as %s", lastName));
        Browser.getElement(LAST_NAME_INPUT_FIELD).sendKeys(lastName);
    }

    private static void setBorrowerHomeAddress(final String address) {
        LOG.info(String.format("[INFO] User sets Borrower Home Address as %s", address));
        Browser.getElement(HOME_ADDRESS_INPUT_FIELD).sendKeys(address);
        Browser.waitForElementVisibility(HOME_ADDRESS_SUGGESTION);
        Browser.getElement(HOME_ADDRESS_INPUT_FIELD).sendKeys(Keys.ENTER);
    }

    private static void setBorrowerCity(final String city) {
        LOG.info(String.format("[INFO] User sets Borrower City as %s", city));
        Browser.getElement(CITY_INPUT_FIELD).sendKeys(city);
    }

    private static void setBorrowerState(final String state) {
        LOG.info(String.format("[INFO] User sets Borrower State as %s", state));
        Browser.getElement(STATE_INPUT_FIELD).sendKeys(state);
    }

    private static void setBorrowerZipCode(final String zipCode) {
        LOG.info(String.format("[INFO] User sets Borrower ZipCode as %s", zipCode));
        Browser.getElement(ZIP_CODE_INPUT_FIELD).sendKeys(zipCode);
    }

    private static void setBorrowerDateOfBirth(final String dateOfBirth) {
        LOG.info(String.format("[INFO] User sets Borrower DateOfBirth as %s", dateOfBirth));
        Browser.getElement(BIRTHDAY_INPUT_FIELD).sendKeys(dateOfBirth);
    }

    public static void userClicksContinueButton() {
        LOG.info("[INFO] User Clicks Continue button on Let's Get Started page");
        Browser.getElement(CONTINUE_BUTTON).click();
    }

    public static void userSeesGetStartedPage() {
        LOG.info("[INFO] Verify user sees Let's Get Started page");
        Browser.waitForElementVisibility(GET_STERTED_HEADER);
    }

    public static void userFillsInfoOnGetStartedPage() {
        LOG.info("[INFO] User fills borrower personal information on Let's Get Started page");
        Faker faker = new Faker();

        setBorrowerFirstName(faker.name().firstName());
        setBorrowerLastName(faker.name().lastName());
        setBorrowerHomeAddress(DefaultCustomerInfo.ADDRESS);
        setBorrowerCity(DefaultCustomerInfo.CITY);
        setBorrowerState(DefaultCustomerInfo.STATE);
        setBorrowerZipCode(DefaultCustomerInfo.ZIP_CODE);
        setBorrowerDateOfBirth(DefaultCustomerInfo.DATE_OF_BIRTH);
    }
}

package stepsdefinitions.ios;

import io.appium.java_client.AppiumDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import pages.ios.LoginPage_iOS;
import stepsdefinitions.HooksSteps;

import static constants.Constants.*;
import static org.junit.Assert.assertTrue;
import static utils.CommonMethods.holdOn;
import static utils.CommonMethods.print;

public class LoginSteps_iOS {
    private AppiumDriver driver = HooksSteps.getDriver();
    private final LoginPage_iOS loginPage = new LoginPage_iOS(driver);

    @Given("^the user accesses to MAFAO iOS application$")
    public void the_user_accesses_to_MAFAO_iOS_application() {
        holdOn(8000);
    }

    @When("^the user taps on Log in button$")
    public void theUserTapsOnLogInButton() {
        loginPage.tapOnLoginBtn();
    }

    @Then("the user is on the login screen")
    public void theUserIsOnTheLoginScreen() {
        Assertions.assertTrue(loginPage.checkIsOnLoginScreen());
    }

    @And("selects a country - iOS")
    public void selectsACountryIOS(){
        loginPage.tapOnFlagIcon();
        print("Tapped on flag icon");
        loginPage.searchCountry(COUNTRY);
        print("searched the country");
        loginPage.tapOnSearchedCountry();
    }

    @And("^selects (.*) from the list$")
    public void selectsCountryFromTheList(String country) {
        loginPage.tapOnFlagIcon();
        print("Tapped on flag icon");
        loginPage.searchCountry(country);
        print("searched the country");
        loginPage.tapOnSearchedCountry();
    }

    @And("^enters a registered mobile number and taps on send code$")
    public void entersARegisteredMobile_numberAndTapsOnSendCode() {
        loginPage.enterMobileNumber(MOBILE_NUMBER);
    }

    @And("^enters a valid mobile number and taps on send code$")
    public void entersAValidMobile_numberAndTapsOnSendCode() {
        loginPage.enterMobileNumber("756490210");
    }

    @And("^enters the verification code and taps on send$")
    public void entersTheVerificationCodeAndTapsOnSend() throws Exception {
        loginPage.enterOTPCode(COUNTRY_CODE, MOBILE_NUMBER);
    }

    @And("^enters a valid verification code and taps on send$")
    public void entersAValidVerificationCodeAndTapsOnSend() throws Exception {
        loginPage.enterOTPCode("+33", "756490210");
    }

    @And("^enters a valid secret code and taps on confirm$")
    public void entersAValidSecret_codeAndTapsOnConfirm() {
        loginPage.enterSecretCode(SECRET_CODE);
    }

    @Then("the user should be logged in successfully")
    @Then("the user is logged in on MAFAO mobile application")
    public void theUserShouldBeLoggedInSuccessfully() {
        assertTrue(loginPage.checkSuccessLogin());
    }

    @And("^enters incorrect (.*) and taps on confirm$")
    public void entersIncorrectSecret_codeAndTapsOnConfirm(String secretCode) {
        loginPage.enterSecretCode(secretCode);
    }

    @Then("the user should see an error message")
    public void theUserShouldSeeAnErrorMessage() {
        assertTrue(loginPage.getErrorLoginMsg());
    }

    @When("^the user enters valid credentials$")
    public void theUserEntersValidCredentials() throws Exception {
        loginPage.tapOnLoginBtn();
        loginPage.tapOnFlagIcon();
        print("Tapped on flag icon");
        loginPage.searchCountry(COUNTRY);
        print("searched the country");
        loginPage.tapOnSearchedCountry();
        loginPage.enterMobileNumber(MOBILE_NUMBER);
        holdOn(900);
        loginPage.enterOTPCode(COUNTRY_CODE, MOBILE_NUMBER);
        loginPage.enterSecretCode(SECRET_CODE);
    }

    @When("the user enters just pincode")
    public void theUserEntersJustPincode() {
        loginPage.enterSecretCode(SECRET_CODE);
    }

    @And("taps on forgot pincode")
    public void tapsOnForgotPincode() {
        loginPage.tapOnForgotPincode();
    }

    @And("^enter the (.*) twice$")
    public void enterTheNew_pincodeTwice(String newPincode) {
        loginPage.enterNewPincode(newPincode);
        holdOn(100);
        loginPage.enterNewPincode(newPincode);
    }

    @Then("the user is logged out successfully")
    public void theUserIsLoggedOutSuccessfully() {
        Assertions.assertTrue(loginPage.checkLoggedOutSuccessfully());
    }

    @When("^checks if user is already logged in$")
    public void checks_if_user_is_already_logged_in() throws Exception {
        loginPage.checkIfIsAlreadyLoggedIn();
    }

    @And("enters a invalid secret code and taps on confirm")
    public void entersAInvalidSecretCodeAndTapsOnConfirm() {
        loginPage.enterInvalidSecretCode();
    }

    @Then("invalid pincode entered error message must be displayed")
    public void invalidPincodeEnteredErrorMessageMustBeDisplayed() {
        loginPage.checkWrongLogin();

    }
}

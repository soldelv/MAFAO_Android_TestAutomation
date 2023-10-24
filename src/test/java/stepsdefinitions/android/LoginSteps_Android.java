package stepsdefinitions.android;

import io.appium.java_client.AppiumDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import pages.android.LoginPage_Android;
import stepsdefinitions.HooksSteps;

import static constants.Constants.*;
import static org.junit.Assert.assertTrue;
import static utils.CommonMethods.holdOn;
import static utils.CommonMethods.print;

public class LoginSteps_Android {
    private AppiumDriver driver = HooksSteps.getDriver();
    private final LoginPage_Android loginPage_Android = new LoginPage_Android(driver);

    @Given("the user accesses to MAFAO Android application")
    public void theUserAccessesToMAFAOAndroidApplication() {
        holdOn(8000);
    }

    @When("the user taps on Log in button - Android")
    public void theUserTapsOnLogInButtonAndroid() {
        loginPage_Android.allowNotifications();
        loginPage_Android.tapOnLoginBtn();
    }

    @Then("the user is on the login screen - Android")
    public void theUserIsOnTheLoginScreenAndroid() {
        Assertions.assertTrue(loginPage_Android.checkIsOnLoginScreen());
    }

    @And("selects a country - Android")
    public void selectsACountryAndroid() {
        loginPage_Android.tapOnFlagIcon();
        print("Tapped on flag icon");
        loginPage_Android.searchCountry(COUNTRY);
        print("searched the country");
        loginPage_Android.tapOnSearchedCountry();
    }

    @And("enters a valid mobile number and taps on send code - Android")
    public void entersAValidMobileNumberAndTapsOnSendCodeAndroid() {
        loginPage_Android.enterMobileNumber(MOBILE_NUMBER);
    }

    @And("enters the verification code and taps on send - Android")
    public void entersTheVerificationCodeAndTapsOnSendAndroid() throws Exception {
        loginPage_Android.enterOTPCode(COUNTRY_CODE, MOBILE_NUMBER);
    }

    @And("enters a valid secret code and taps on confirm - Android")
    public void entersAValidSecretCodeAndTapsOnConfirmAndroid() {
        loginPage_Android.enterSecretCode(SECRET_CODE);
    }

    @Then("the user should be logged in successfully - Android")
    public void theUserShouldBeLoggedInSuccessfullyAndroid() {
        assertTrue(loginPage_Android.checkSuccessLogin());
    }

    @And("^enters incorrect (.*) and taps on confirm - Android$")
    public void entersIncorrectSecret_codeAndTapsOnConfirmAndroid(String secretCode) {
        loginPage_Android.enterSecretCode(secretCode);
    }

    @Then("the user should see an error message - Android")
    public void theUserShouldSeeAnErrorMessageAndroid() {
        assertTrue(loginPage_Android.getErrorLoginMsg());
    }

    @And("taps on forgot secret code - Android")
    public void tapsOnForgotSecretCodeAndroid() {
        loginPage_Android.tapOnForgotPincode();
    }

    @And("^enters the new (.*) code twice - Android$")
    public void entersTheNewSecret_codeCodeTwiceAndroid(String newPincode) {
        loginPage_Android.typeNewSecretCode(newPincode);
        loginPage_Android.typeNewSecretCode(newPincode);
    }

    @Then("the pincode is reset successfully - Android")
    public void thePincodeIsResetSuccessfullyAndroid() {
    }

    @When("the user enters valid credentials - Android")
    public void theUserEntersValidCredentialsAndroid() throws Exception {
        loginPage_Android.allowNotifications();
        loginPage_Android.tapOnLoginBtn();
        holdOn(1000);
        loginPage_Android.tapOnFlagIcon();
        print("Tapped on flag icon");
        loginPage_Android.searchCountry(COUNTRY);
        print("searched the country");
        loginPage_Android.tapOnSearchedCountry();
        loginPage_Android.enterMobileNumber(MOBILE_NUMBER);
        holdOn(900);
        loginPage_Android.enterOTPCode(COUNTRY_CODE, MOBILE_NUMBER);
        loginPage_Android.enterSecretCode(SECRET_CODE);
    }

    @Then("the user is logged in on MAFAO mobile application - Android")
    public void theUserIsLoggedInOnMAFAOMobileApplicationAndroid() {
        assertTrue(loginPage_Android.checkSuccessLogin());
    }
}

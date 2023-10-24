package pages.ios;

import apis.MafaoAPIs;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;

import static constants.Constants.*;
import static org.testng.AssertJUnit.fail;
import static utils.CommonMethods.holdOn;
import static utils.CommonMethods.print;

public class LoginPage_iOS extends BasePage_iOS {
    public LoginPage_iOS(AppiumDriver driver) {
        super(driver);
    }
    By loginBtn = MobileBy.AccessibilityId("btn-login");
    By signUpBtn = MobileBy.AccessibilityId("btn-sign-up");
    By countryFlagIcon = MobileBy.AccessibilityId("telephone-country-flag-code");
    By countrySearchInput = MobileBy.AccessibilityId("country-search-input");
    By countrySearched = MobileBy.AccessibilityId("country-search-option-0");
    By phoneInput = MobileBy.AccessibilityId("phone-auth-input-phone");
    By sendCodeBtn = MobileBy.AccessibilityId("btn-send-code");
    By phoneOTPInput = MobileBy.AccessibilityId("phone-otp-input");
    By submitBtn = MobileBy.AccessibilityId("btn-submit");
    By confirmLoginBtn = MobileBy.AccessibilityId("confirm-login-button");
    By profileIcon = MobileBy.AccessibilityId("profile");
    By invalidLoginMessage = MobileBy.xpath("//XCUIElementTypeStaticText[@name=\"You entered an invalid pincode. Please try again.\"]");
    By errorLogin_invalidPincode = MobileBy.xpath("//XCUIElementTypeStaticText[@name='You entered an invalid pincode. Please try again.']");
    By errorLogin_ResetMessage = MobileBy.AccessibilityId("We apologize, but you have entered the wrong PIN code three times, and your account has been disabled. Please reset your PIN code to proceed.");
    By forgotPincodeBtn = MobileBy.AccessibilityId("forgot-pin");

    /** KEY PATH LOCATORS **/
    By keypadDelete = MobileBy.AccessibilityId("keypad-delete");
    By deleteBtn = MobileBy.AccessibilityId("Delete");

    public void tapOnLoginBtn()  {
        print("try to click on login");
        tap(loginBtn);
    }

    public boolean checkIsOnLoginScreen() {
        holdOn(1000);
        return isDisplayed(countryFlagIcon);
    }

    public void tapOnFlagIcon(){
        waitUntilIsDisplayed(countryFlagIcon);
        tap(countryFlagIcon);
    }

    public void searchCountry(String country) {
        type(country, countrySearchInput);
    }

    public void tapOnSearchedCountry() {
        tap(countrySearched);
        tap(countrySearched);
    }

    public void enterMobileNumber(String mobileNumber) {
        type(mobileNumber, phoneInput);
        tap(sendCodeBtn);
        tap(sendCodeBtn);
        holdOn(1000);
    }

    public void enterOTPCode(String countryCode, String mobileNumber) throws Exception {
        String full_mobileNumber = countryCode+mobileNumber;
        String otpCode = MafaoAPIs.getAPIOTPCode(full_mobileNumber);
        print("OTP CODE "+otpCode);
        type("1", phoneOTPInput);
        tap(deleteBtn);
        typeFromKeyboard("", otpCode);
        tap(submitBtn);
        tap(submitBtn);
    }

    public void enterSecretCode(String secretCode) {
        typeFromKeyboard("keypad-", secretCode);
        tap(confirmLoginBtn);
    }

    public void enterInvalidSecretCode() {
        typeFromKeyboard("keypad-", "111111");
        tap(confirmLoginBtn);
    }

    public boolean checkSuccessLogin() {
        if(isDisplayed(errorLogin_invalidPincode)){
            fail("Error login: Invalid pincode");
        }
        waitUntilIsDisplayed(profileIcon);
        return isDisplayed(profileIcon);
    }

    public boolean checkWrongLogin() {
        return isDisplayed(invalidLoginMessage);
    }

    public boolean getErrorLoginMsg() {
        return isDisplayed(errorLogin_invalidPincode);
    }
    public void tapOnForgotPincode() {
        tap(forgotPincodeBtn);
    }

    public void enterNewPincode(String newPincode) {
        typeFromKeyboard("", newPincode);
    }

    public boolean checkLoggedOutSuccessfully() {
        holdOn(1000);
        return isDisplayed(loginBtn);
    }

    public void checkIfIsAlreadyLoggedIn() throws Exception {
        if(isDisplayed(confirmLoginBtn)){
            print("User already logged in");
        }
        else{
            print("User not logged in yet");
            waitUntilIsDisplayed(loginBtn);
            tapOnLoginBtn();
            if(checkIsOnLoginScreen()){
                tapOnFlagIcon();
                print("Tapped on flag icon");
                searchCountry(COUNTRY);
                print("searched the country");
                tapOnSearchedCountry();
                enterMobileNumber(MOBILE_NUMBER);
                enterOTPCode(COUNTRY_CODE, MOBILE_NUMBER);
            }
        }
    }

}
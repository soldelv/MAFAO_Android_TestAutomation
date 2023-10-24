package pages.android;

import apis.MafaoAPIs;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static utils.CommonMethods.holdOn;
import static utils.CommonMethods.print;

public class LoginPage_Android extends BasePage_Android {
    public LoginPage_Android(AppiumDriver driver) {
        super((AndroidDriver<WebElement>) driver);
    }
    By loginBtn = MobileBy.AccessibilityId("btn-login");
    By signUpBtn = MobileBy.AccessibilityId("btn-sign-up");
    By countryFlagIcon = MobileBy.AccessibilityId("telephone-country-flag-code");
    By countrySearchInput = MobileBy.AccessibilityId("country-search-input");
    By countrySearched = MobileBy.AccessibilityId("country-search-option-0");
    By phoneInput = MobileBy.AccessibilityId("phone-auth-input-phone");
    By sendCodeBtn = MobileBy.AccessibilityId("btn-send-code");
    By phoneOTPInput = MobileBy.AccessibilityId("phone-otp-input");

    By forgotPincodeBtn = MobileBy.xpath("//android.view.ViewGroup[contains(@content-desc,'forgot-pin')]");
    By submitBtn = MobileBy.AccessibilityId("btn-submit");
    By confirmLoginBtn = MobileBy.AccessibilityId("confirm-login-button");
    By profileIcon = MobileBy.AccessibilityId("profile");
    By errorLogin = MobileBy.xpath("//*[@text=\"You entered an invalid pincode. Please try again.\"]");

    /* KEY PATH LOCATORS */
    By keypathDelete = MobileBy.AccessibilityId("keypad-delete");
    By androidAllowNotificationsBtn = MobileBy.xpath("//android.widget.Button[contains(@resource-id, 'permission_allow_button')]");
    By androidDenyNotificationsBtn = MobileBy.xpath("//android.widget.Button[contains(@resource-id, 'permission_deny_button')]");


    public void tapOnLoginBtn()  {
        tap(loginBtn);
    }

    public boolean checkIsOnLoginScreen() {
        holdOn(1000);
        return isDisplayed(getElement(countryFlagIcon));
    }

    public void tapOnFlagIcon(){
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
        print("Tapped on Send Code");
        tap(sendCodeBtn);
    }

    public void enterOTPCode(String countryCode, String mobileNumber) throws Exception {
        Thread.sleep(1000);
        String full_mobileNumber = countryCode+mobileNumber;
        print(full_mobileNumber);
        String otpCode = MafaoAPIs.getAPIOTPCode(full_mobileNumber);
        Thread.sleep(1500);
        print("OTP CODE "+otpCode);
        type(otpCode, phoneOTPInput);
        Thread.sleep(1000);
        tap(getElement(submitBtn));
    }

    public void enterSecretCode(String secretCode) {
        typeFromKeyboard("keypad-", secretCode);
        tap(confirmLoginBtn);
    }

    public boolean checkSuccessLogin() {
        return isDisplayed(getElement(profileIcon));
    }

    public boolean getErrorLoginMsg() {
        return isDisplayed(getElement(errorLogin));
    }

    public void allowNotifications()
    {
        tap(androidAllowNotificationsBtn);
    }

    public void denyNotifications()
    {
        tap(androidDenyNotificationsBtn);
    }

    public void tapOnForgotPincode()
    {
        tap(forgotPincodeBtn);
    }

    public void typeNewSecretCode(String secretCode) {
        typeFromKeyboard("keypad-", secretCode);
    }



}
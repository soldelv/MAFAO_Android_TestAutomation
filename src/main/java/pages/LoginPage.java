package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class LoginPage extends BasePage {
    public LoginPage(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }
    @AndroidFindBy(accessibility = "btn-login")
    MobileElement loginBtn;

    @AndroidFindBy(accessibility = "btn-sign-up")
    MobileElement signUpBtn;

    @AndroidFindBy(accessibility = "telephone-country-flag-code")
    MobileElement countryFlag;

    @AndroidFindBy(accessibility = "country-search-input")
    MobileElement countrySearchInput;
    @AndroidFindBy(accessibility = "country-search-option-0")
    MobileElement countrySearched;
    @AndroidFindBy(accessibility = "phone-auth-input-phone")
    MobileElement phoneInput;

    @AndroidFindBy(accessibility = "btn-send-code")
    MobileElement sendCodeBtn;

    @AndroidFindBy(accessibility = "phone-otp-input")
    MobileElement phoneOTPInput;
    @AndroidFindBy(accessibility = "btn-submit")
    MobileElement submitBtn;

    @AndroidFindBy(accessibility = "confirm-login-button")
    MobileElement confirmLoginBtn;


    public void clickOnLogin() throws InterruptedException {
        Thread.sleep(8000);
        System.out.println("END OF WAITING");
        driver.findElementByAccessibilityId("btn-login").click();
    }

    public void fillLoginForm(String username, String password) {
        clear(loginBtn);
        sendText(loginBtn, username);
        clear(loginBtn);
        sendText(loginBtn, password);
    }
}
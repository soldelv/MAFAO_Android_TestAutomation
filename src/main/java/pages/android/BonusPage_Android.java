package pages.android;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import static constants.Constants.PAYPAL_EMAIL;
import static constants.Constants.PAYPAL_PASSWORD;
import static utils.CommonMethods.holdOn;
import static utils.CommonMethods.print;

public class BonusPage_Android extends BasePage_Android {
    public BonusPage_Android(AppiumDriver driver) {
        super((AndroidDriver<WebElement>) driver);
    }

    /** BONUS - MAIN SCREEN */
    By bonusIcon = MobileBy.AccessibilityId("bonus");
    By viewBalanceBtn = MobileBy.AccessibilityId("view-balance-button");

    /** PAYMENT METHODS */
    By closeModaBtn = MobileBy.AccessibilityId("btn-close-modal");
    By wave = MobileBy.AccessibilityId("type-0");
    By orangeMoney = MobileBy.AccessibilityId("type-1");
    By freeMoney = MobileBy.AccessibilityId("type-2");
    By paypal = MobileBy.AccessibilityId("type-3");
    By paytech = MobileBy.AccessibilityId("type-4");
    By bank = MobileBy.AccessibilityId("type-5");
    By changeMethodBtn = MobileBy.AccessibilityId("change-method");

    /** PAYPAL SCREEN */
    By paypalEmail = MobileBy.xpath("//android.widget.EditText[contains(@resource-id,'email')]");
    By paypalNextBtn = MobileBy.xpath("//android.widget.Button[contains(@resource-id,'btnNext')]");
    By paypalPassword = MobileBy.xpath("//android.widget.EditText[contains(@resource-id,'password')]");
    By paypalLoginBtn = MobileBy.xpath("//android.widget.Button[contains(@resource-id,'btnLogin')]");
    By paypalSubmitBtn = MobileBy.xpath("//android.widget.Button[contains(@resource-id,'payment-submit-btn')]");

    public void selectPaypal(){
        tap(getElement(paypal));
    }

    public void loginPaypal(){
        holdOn(800);
        type(PAYPAL_EMAIL, paypalEmail);
        tap(paypalNextBtn);
        holdOn(800);
        type(PAYPAL_PASSWORD, paypalPassword);
        holdOn(800);
        tap(paypalLoginBtn);
        holdOn(500);
        scrollDown();
        tap(paypalSubmitBtn);
    }

}

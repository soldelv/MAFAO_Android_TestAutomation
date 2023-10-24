package pages.ios;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;

import static constants.Constants.PAYPAL_EMAIL;
import static constants.Constants.PAYPAL_PASSWORD;

public class BonusPage_iOS extends BasePage_iOS {
    public BonusPage_iOS(AppiumDriver driver) {
        super(driver);
    }

    /** BONUS - MAIN SCREEN */
    By bonusIcon = MobileBy.AccessibilityId("******");
    By viewBalanceBtn = MobileBy.xpath("(//XCUIElementTypeOther[@name='view-balance-button'])[2]");
    By transactionsList = MobileBy.xpath("//XCUIElementTypeOther[contains(@name,'transaction-item-with-hashundefined')]");
    By history = MobileBy.AccessibilityId("History");
    By historyList = MobileBy.xpath("(//XCUIElementTypeOther[@name='transaction-item-with-hashundefined'])");

    /** PAYMENT METHODS */
    By paypal = MobileBy.AccessibilityId("type-3");

    /** PAYPAL SCREEN */
    By paypalTitle = MobileBy.xpath("//XCUIElementTypeStaticText[@name=\"Pagar com PayPal\"]");
    By paypalEmail = MobileBy.xpath("//XCUIElementTypeOther[@name=\"main\"]/XCUIElementTypeOther[4]/XCUIElementTypeTextField");
    By paypalNextBtn = MobileBy.AccessibilityId("Seguinte");
    By paypalPassword = MobileBy.xpath("//XCUIElementTypeOther[@name='main']/XCUIElementTypeSecureTextField");
    By paypalLoginBtn = MobileBy.AccessibilityId("Iniciar sessão");
    By paypalSubmitBtn = MobileBy.AccessibilityId("Continue to Review Order");

    public void selectPaypal(){
        tap(paypal);
    }

    public void loginPaypal(){
        waitUntilIsDisplayed(paypalTitle);
        tap(paypalNextBtn);
        type(PAYPAL_EMAIL, paypalEmail);
        tap(paypalNextBtn);
        tap(paypalLoginBtn);
        type(PAYPAL_PASSWORD, paypalPassword);
        tap(paypalLoginBtn);
        scrollDown();
        tap(paypalSubmitBtn);
    }

    public void tapOnViewBalance(){
        tap(viewBalanceBtn);
    }

    public boolean checkBalanceIsDisplayed(){
        By balance_amount = MobileBy.xpath("//XCUIElementTypeOther[@name='107285 ']");
        return isDisplayed(balance_amount);
    }

    public void tapOnBonusIcon(){
        tap(bonusIcon);
    }

    public boolean checkHistoryIsDisplayed(){
        waitUntilIsDisplayed(history);
        return isDisplayed(historyList);
    }


}

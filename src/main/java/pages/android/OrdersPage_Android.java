package pages.android;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import static utils.CommonMethods.holdOn;
import static utils.CommonMethods.print;

public class OrdersPage_Android extends BasePage_Android {
    public OrdersPage_Android(AppiumDriver driver) {
        super((AndroidDriver<WebElement>) driver);
    }

    By payOrderBtn = MobileBy.xpath("//android.widget.TextView[contains(@text, 'Pay your order')]");
    By continueShoppingBtn = MobileBy.xpath("//android.view.ViewGroup[@content-desc='continue_shopping_btn']");

    By trashBtn = MobileBy.xpath("//android.view.ViewGroup[4]//android.widget.ImageView[1]");
    By cartEmptyMessage = MobileBy.xpath("//android.widget.TextView[contains(@text, 'Your cart is empty')]");

    public boolean checkProductIsOnOrders(String productName){
        holdOn(800);
        By product = MobileBy.AccessibilityId("product-"+productName);
        return isDisplayed(getElement(product));
    }

    public boolean checkCartIsEmpty(String productName){
        holdOn(800);
        try{
            By product = MobileBy.AccessibilityId("product-"+productName);
            return isDisplayed(product);
        }
        catch(NoSuchElementException e){
            return false;
        }
    }

    public void tapOnPayOrder(){
        tap(payOrderBtn);
    }

    public boolean checkPurchaseSuccessful(){
        holdOn(800);
        boolean displayed = false;
        try {
            displayed = isDisplayed(getElement(continueShoppingBtn));
        }catch(NoSuchElementException e){
            print("Message not found");
        }
        return displayed;
    }

    public void tapOnTrashIcon(){
        tap(trashBtn);
    }

}

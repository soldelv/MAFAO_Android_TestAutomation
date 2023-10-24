package pages.ios;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import mafao.objects.Order;
import mafao.objects.Seller;
import org.openqa.selenium.By;

import java.util.Objects;

import static database.OrderQuery.*;
import static database.ProductQuery.getMarketplaceProducts;
import static database.UserQuery.getSellerByProductId;
import static org.testng.AssertJUnit.fail;
import static utils.CommonMethods.*;

public class OrdersPage_iOS extends BasePage_iOS {
    public OrdersPage_iOS(AppiumDriver driver) {
        super(driver);
    }


    /** KEYBOARD BUTTONS */
    By OKErrorButton = MobileBy.AccessibilityId("OK");
    By returnKeyboard = MobileBy.AccessibilityId("Return");

    /** ORDERS LOCATORS */
    By payOrderBtn = MobileBy.xpath("(//XCUIElementTypeOther[@name='Pay your order'])[2]");
    By changePaymentBtn = MobileBy.xpath("(//XCUIElementTypeOther[@name='Change'])[2]");
    By mafaoWalletOption = MobileBy.xpath("(//XCUIElementTypeOther[@name='type-4'])[2]");
    By paypalOption = MobileBy.xpath("(//XCUIElementTypeOther[@name=\"type-3\"])[2]");
    By continueBtn = MobileBy.AccessibilityId("Continue");
    By profileIcon = MobileBy.AccessibilityId("profile");
    By orderSuccessMessage = MobileBy.AccessibilityId("Your order has been registered.");
    By insufficientFunds = MobileBy.AccessibilityId("Insufficient funds");
    By continueShoppingBtn = MobileBy.xpath("(//XCUIElementTypeOther[@name=\"continue_shopping_btn\"])[2]");
    By trashBtn = MobileBy.xpath("(//XCUIElementTypeOther[@name='minus-button quantity-input plus-button'])[1]/XCUIElementTypeOther[2]");
    By paidOrderTab = MobileBy.AccessibilityId("Paid orders");
    By transactionDetailsTitle = MobileBy.xpath("//XCUIElementTypeStaticText[@name='View order details']");
    By scanQR = MobileBy.AccessibilityId("Scan a code");
    By deliveredOrdersTitle = MobileBy.AccessibilityId("Other orders in the last 3 months");
    By changePickUpLocationBtn = MobileBy.xpath("(//XCUIElementTypeOther[@name=\"Change\"])[1]");
    By selectPickUpLocationBtn = MobileBy.xpath("(//XCUIElementTypeOther[@name=\"pickup-location-16\"])[5]");
    By registerBtn = MobileBy.xpath("(//XCUIElementTypeOther[@name=\"manage-address-register-button\"])[2]");

    /** REVIEW FIELDS */
    By star1 = MobileBy.xpath("//XCUIElementTypeOther[@name=\"Rate this product  \"]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]");
    By star2 = MobileBy.xpath("//XCUIElementTypeOther[@name=\"Rate this product  \"]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]");
    By star3 = MobileBy.xpath("//XCUIElementTypeOther[@name=\"Rate this product  \"]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[3]");
    By star4 = MobileBy.xpath("//XCUIElementTypeOther[@name=\"Rate this product  \"]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[4]");
    By star5 = MobileBy.xpath("//XCUIElementTypeOther[@name=\"Rate this product  \"]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[5]");
    By titleOfYourComment= MobileBy.AccessibilityId("Title of your comment");
    By reviewTitle = MobileBy.AccessibilityId("input-title");
    By reviewComment = MobileBy.AccessibilityId("input-comment");
    By publishReviewBtn = MobileBy.xpath("(//XCUIElementTypeOther[@name=\"a\"])[1]");
    By reviewError = MobileBy.AccessibilityId("INVALID_ORDERLINE : product is already reviewed in order");

    private static Order orderReadyToPickup;
    private static Order deliveredOrder;

    public boolean checkProductIsOnOrders(){
        holdOn(800);
        String productName = getMarketplaceProducts().get(0).getDisplay_name();
        By product = MobileBy.xpath("//XCUIElementTypeOther[contains(@name, 'product-"+productName+"')]");
        return isDisplayed(product);
    }

    public boolean checkProductRemovedFromCart(){
        holdOn(800);
        By message = MobileBy.AccessibilityId("Removed product successfully");
        return isDisplayed(message);
    }

    public void tapOnPayOrder(){
        scrollDownUntilIsDisplayed(payOrderBtn);
        tap(payOrderBtn);
    }

    public void checkPurchaseSuccessful(){
        waitUntilIsDisplayed(orderSuccessMessage);
        if(isDisplayed(orderSuccessMessage)){
            tapOnContinueShoppingBtn();
        }else if(isDisplayed(insufficientFunds)){
            tap(OKErrorButton);
            fail("User with Insufficient Funds is not able to purchase");
        }else{
            fail("Order can not proceed");
        }
    }

    public void tapOnTrashIcon(){
        tap(trashBtn);
    }

    public void tapOnChangePaymentMethod(){
        scrollDownUntilIsDisplayed(changePaymentBtn);
        tap(changePaymentBtn);
    }

    public void selectPaymentMethod(String method){
        switch (method) {
            case "Mafao" -> tap(mafaoWalletOption);
            case "Paypal" -> tap(paypalOption);
            default -> fail("Invalid payment method name");
        }
    }

    public void tapOnContinuePaymentMethod(){
        tap(continueBtn);
    }

    public void tapOnContinueShoppingBtn(){
        tap(continueShoppingBtn);
    }

    public void tapOnPaidOrderTab(){
        tap(paidOrderTab);
    }

    public boolean checkIsInPaidOrders(String productName){
        String pickUpLocation = "Viyline Cosmetics, 426 Paltok, Manila";
        By order = MobileBy.xpath("(//XCUIElementTypeOther[@name='"+productName+" Quantity: 1 Paid on "+todayDayAndMonth()+" In preparation "+pickUpLocation+" \uF214\'])[2]");
        return isDisplayed(order);
    }

    public By generateLastOrderLocator(){
        Order lastOrder = getLastSaleOrder();
        String display_name = lastOrder.getOrderLines().get(0).getProduct().getDisplay_name();
        int quantity = lastOrder.getOrderLines().get(0).getQuantity();
        String seller_display_name = lastOrder.getOrderLines().get(0).getSeller_display_name();
        String location = lastOrder.getOrderLines().get(0).getPickupLocation();
        String date_order = convertFormatDateDDMM(lastOrder.getOrderLines().get(0).getCreate_date());

        By order = MobileBy.xpath("(//XCUIElementTypeOther[@name=\""+display_name+" Quantity: "+quantity+" Paid on "+ date_order
                +" In preparation "+seller_display_name+", "+location+" \uEB3C\"])[2]");

        return order;
    }

    public boolean checkLastOrderIsInPaidOrders(){
        return isDisplayed(generateLastOrderLocator());
    }

    public void tapOnLastOrder(){
        tap(generateLastOrderLocator());
    }

    public void tapOnOrder(String productName){
        String pickUpLocation = "Viyline Cosmetics, 426 Paltok, Manila";
        By order = MobileBy.xpath("(//XCUIElementTypeOther[@name=\'"+productName+" Quantity: 1 Paid on "+todayDayAndMonth()+" In preparation "+pickUpLocation+" \uF214\'])[2]");
        tap(order);
    }

    public boolean checkTransactionDetailsAreDisplayed(){
        return isDisplayed(transactionDetailsTitle);
    }

    public boolean checkReadyToPickUpIsDisplayed(){
        orderReadyToPickup = getLastReadyToPickupOrder();
        print(String.valueOf(orderReadyToPickup));
        String display_name = orderReadyToPickup.getOrderLines().get(0).getProduct().getDisplay_name();
        int quantity = orderReadyToPickup.getOrderLines().get(0).getQuantity();
        String seller_display_name = orderReadyToPickup.getOrderLines().get(0).getSeller_display_name();
        String location = orderReadyToPickup.getOrderLines().get(0).getPickupLocation();
        String date_order = convertFormatDateDDMM(orderReadyToPickup.getOrderLines().get(0).getCreate_date());

        By order = MobileBy.xpath("//XCUIElementTypeOther[@name='"+display_name+" Quantity: "+quantity+
                " Paid on "+date_order+" Ready for pickup "+seller_display_name+", "+location+" \uF214']");

        scrollDownUntilIsDisplayed(order);
        return isDisplayed(order);
    }

    public boolean checkProductToWithdrawIsDisplayed(){
        holdOn(1000);
        waitUntilIsDisplayed(scanQR);
        orderReadyToPickup = getLastReadyToPickupOrder();
        String display_name = orderReadyToPickup.getName();
        int quantity = orderReadyToPickup.getOrderLines().get(0).getQuantity();

        String full_pickup_date = orderReadyToPickup.getOrderLines().get(0).getPickup_date();
        String pickup_date = convertFormatDateDDMM(full_pickup_date);
        String pickup_hour = extractHourAndMinutes(full_pickup_date);

        String description_sale = orderReadyToPickup.getOrderLines().get(0).getProduct().getDescription_sale();

        By order = MobileBy.xpath("(//XCUIElementTypeOther[contains(@name,\""+display_name+" (x"+
                quantity+") "+description_sale+" Before "+pickup_date+" at "+pickup_hour+"\")])");

        return isDisplayed(order);
    }

    public void searchesForAOrderWithStatusDelivered(){
        scrollDownUntilIsDisplayed(deliveredOrdersTitle);

        deliveredOrder  = getLastDeliveredOrder();
        print(String.valueOf(deliveredOrder));
        String display_name = deliveredOrder.getOrderLines().get(0).getProduct().getDisplay_name();
        int quantity = deliveredOrder.getOrderLines().get(0).getQuantity();
        String seller_display_name = deliveredOrder.getOrderLines().get(0).getSeller_display_name();
        if (Objects.equals(seller_display_name, null)){
            seller_display_name = "undefined";
        }
        String location = deliveredOrder.getOrderLines().get(0).getPickupLocation();
        String date_order = convertFormatDateDDMM(deliveredOrder.getOrderLines().get(0).getCreate_date());

        By order = MobileBy.AccessibilityId(""+display_name+" Quantity: "+quantity+" Paid on "+date_order+" Delivered "+seller_display_name+", "+location+" \uEB3C");
        scrollDownUntilIsDisplayed(order);
        tap(order);
    }

    public void scrollsDownToSeeOrdersWithStatusDelivered(){
        scrollDownUntilIsDisplayed(deliveredOrdersTitle);
    }

    public void tapsOnReviewButtonFromOrderDetail(){
        String location = deliveredOrder.getOrderLines().get(0).getPickupLocation();
        String seller_display_name = deliveredOrder.getOrderLines().get(0).getSeller_display_name();
        if (Objects.equals(seller_display_name, null)){
            seller_display_name = "undefined";
        }
        By reviewButton = MobileBy.AccessibilityId("Product already picked up on 01/01 at 01h at "+seller_display_name+", "+location+"" +
                " Write a comment or create a video on the product and earn Bonus \uEB3C");

        tap(reviewButton);
    }

    public void addReviewOnProduct(){
        tap(star4);
        scrollDown();
        clearAndType("Automation Review",reviewTitle);
        tap(titleOfYourComment);
        clearAndType("Good product very recommendable",reviewComment);
        tap(titleOfYourComment);
        waitUntilIsDisplayed(publishReviewBtn);
        tap(publishReviewBtn);
    }

    public boolean checkRevisionSuccessfullySubmitted(){
        if (isDisplayed(reviewError)){
            print("INVALID_ORDERLINESS : product is already reviewed in order");
            return true;
        }
        By review = MobileBy.xpath("//XCUIElementTypeStaticText[@name=\"Congratulations! Your review has been published.\"]");
        holdOn(3000);
        return isDisplayed(review);
    }

    public void tapOnChangeButton(){
        tap(changePickUpLocationBtn);
    }

    public void selectsAPickUpLocation(){
        tap(selectPickUpLocationBtn);
    }

    public void tapOnRegisterButton(){
        tap(registerBtn);
    }

    public boolean checkPickUpLocationChanged(){
        Seller seller = getSellerByProductId(getMarketplaceProducts().get(0).getId());
        By location = MobileBy.xpath("//XCUIElementTypeStaticText[@name=\"at "+seller.getDisplay_name()
                +", "+seller.getStreet()+", "+seller.getCity()+"\"]");

        return isDisplayed(location);
    }


}

package stepsdefinitions.ios;

import io.appium.java_client.AppiumDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import pages.ios.OrdersPage_iOS;
import stepsdefinitions.HooksSteps;

public class OrdersSteps_iOS {
    private AppiumDriver driver = HooksSteps.getDriver();
    private final OrdersPage_iOS ordersPage = new OrdersPage_iOS(driver);

    @Then("^notification that (.*) has added to cart is displayed$")
    public void notificationThatProduct_HasAddedToCartIsDisplayed(String productName) {
    }

    @Then("^the product was removed successfully$")
    public void product_IsNotLongerDisplayedOnTheList() {
        Assertions.assertTrue(ordersPage.checkProductRemovedFromCart());
    }

    @And("taps pay your order")
    public void goesToOrdersScreenAndTapsPayYourOrder() {
        ordersPage.scrollDown();
        ordersPage.tapOnPayOrder();
    }

    @Then("your order has been registered screen should be displayed")
    public void yourOrderHasBeenRegisteredScreenShouldBeDisplayed() {
        ordersPage.checkPurchaseSuccessful();
    }

    @And("taps on trash icon to remove product from the list")
    public void tapsOnTrashIconToRemoveProductFromTheList() {
        ordersPage.tapOnTrashIcon();
    }

    @And("taps on change payment method")
    public void tapsOnChangePaymentMethod() {
        ordersPage.tapOnChangePaymentMethod();
    }

    @And("^selects (.*) as a payment method$")
    public void selectsAsAPaymentMethod(String method) {
        ordersPage.selectPaymentMethod(method);
        ordersPage.tapOnContinuePaymentMethod();
    }

    @And("taps pay your order to finish the purchase")
    public void tapsPayYourOrderToFinishThePurchase() {
        ordersPage.tapOnPayOrder();
    }

    @And("user goes to Pay Order tab")
    public void userGoesToPayOrderTab() {
        ordersPage.tapOnPaidOrderTab();
    }

    @Then("^the order with (.*) is on the list$")
    public void theOrderWithProduct_IsOnTheList(String productName) {
        Assertions.assertTrue(ordersPage.checkIsInPaidOrders(productName), "Order is not listed");
    }

    @And("^taps on the order with (.*)$")
    public void tapsOnTheOrderWithProduct_(String productName) {
        ordersPage.tapOnOrder(productName);
    }

    @Then("paid order transaction details are displayed")
    public void paidOrderTransactionDetailsAreDisplayed() {
        Assertions.assertTrue(ordersPage.checkTransactionDetailsAreDisplayed());
    }

    @Then("the last order is displayed on the list")
    public void theLastOrderIsDisplayedOnTheList() {
        Assertions.assertTrue(ordersPage.checkLastOrderIsInPaidOrders(), "Order is not listed");
    }

    @And("taps on the last order")
    public void tapsOnTheLastOrder() {
        ordersPage.tapOnLastOrder();
    }

    @Then("a product with Ready to Pick Up status is displayed")
    public void aProductWithReadyToPickUpStatusIsDisplayed() {
        //Assertions.assertTrue(ordersPage.checkReadyToPickUpIsDisplayed(), "Product Ready To Pickup is not listed");
        Assertions.assertTrue(ordersPage.checkProductToWithdrawIsDisplayed(), "Product to withdraw is not listed");
    }

    @Then("the product selected is displayed on the order page")
    public void theProductSelectedIsDisplayedOnTheOrderPage() {
        Assertions.assertTrue(ordersPage.checkProductIsOnOrders());
    }

    @And("searches for a order with status delivered")
    public void searchesForAOrderWithStatusDelivered() {
        ordersPage.searchesForAOrderWithStatusDelivered();
    }

    @And("adds a review on the product")
    public void addsAReviewOnTheProduct() {
        ordersPage.addReviewOnProduct();
    }

    @Then("the review is successfully submitted")
    public void theReviewIsSuccessfullySubmitted() {
        Assertions.assertTrue(ordersPage.checkRevisionSuccessfullySubmitted());
    }

    @And("scrolls down to see orders with status delivered")
    public void scrollsDownToSeeOrdersWithStatusDelivered() {
        ordersPage.scrollsDownToSeeOrdersWithStatusDelivered();
    }

    @And("taps on review button from order detail")
    public void tapsOnReviewButtonFromOrderDetail() {
        ordersPage.tapsOnReviewButtonFromOrderDetail();
    }

    @And("on Product pick-up location taps on change button")
    public void onProductPickUpLocationTapsOnChangeButton() {
        ordersPage.tapOnChangeButton();
    }

    @And("selects a pick up location")
    public void selectsAPickUpLocation() {
        ordersPage.selectsAPickUpLocation();
    }

    @And("tap on register button to confirm the operation")
    public void tapOnRegisterButtonToConfirmTheOperation() {
        ordersPage.tapOnRegisterButton();
    }

    @Then("pick up location was successfully changed")
    public void pickUpLocationWasSuccessfullyChanged() {
        Assertions.assertTrue(ordersPage.checkPickUpLocationChanged());
    }

}

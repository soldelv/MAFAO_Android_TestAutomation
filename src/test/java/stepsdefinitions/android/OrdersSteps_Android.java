package stepsdefinitions.android;

import io.appium.java_client.AppiumDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import pages.android.OrdersPage_Android;
import stepsdefinitions.HooksSteps;

import static utils.CommonMethods.holdOn;

public class OrdersSteps_Android {
    private AppiumDriver driver = HooksSteps.getDriver();
    private final OrdersPage_Android ordersPage_Android = new OrdersPage_Android(driver);

    @Then("^(.*) is displayed on Orders list - Android$")
    public void product_IsDisplayedOnOrdersListAndroid(String productName) {
        Assertions.assertTrue(ordersPage_Android.checkProductIsOnOrders(productName));
    }

    @And("taps on trash icon to remove product from the list - Android")
    public void tapsOnTrashIconToRemoveProductFromTheListAndroid() {
        ordersPage_Android.tapOnTrashIcon();
    }

    @Then("(.*) is not longer displayed on the list - Android$")
    public void product_IsNotLongerDisplayedOnTheListAndroid(String productName) {
        Assertions.assertFalse(ordersPage_Android.checkCartIsEmpty(productName));
    }

    @And("taps pay your order - Android")
    public void tapsPayYourOrderAndroid() {
        ordersPage_Android.scrollDown();
        ordersPage_Android.scrollDown();
        ordersPage_Android.tapOnPayOrder();
    }

    @Then("your order has been registered screen should be displayed - Android")
    public void yourOrderHasBeenRegisteredScreenShouldBeDisplayedAndroid() {
        Assertions.assertTrue(ordersPage_Android.checkPurchaseSuccessful());
    }
}

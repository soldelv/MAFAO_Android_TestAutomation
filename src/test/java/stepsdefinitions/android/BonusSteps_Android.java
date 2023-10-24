package stepsdefinitions.android;

import io.appium.java_client.AppiumDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.android.BonusPage_Android;
import stepsdefinitions.HooksSteps;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BonusSteps_Android {
    private final AppiumDriver driver = HooksSteps.getDriver();
    private final BonusPage_Android bonusPage_Android = new BonusPage_Android(driver);

    @And("selects paypal as a top up method - Android")
    public void selectsPaypalAsATopUpMethodAndroid() {
        bonusPage_Android.selectPaypal();
    }

    @And("the user logs into Paypal and confirm the payment - Android")
    public void theUserLogsIntoPaypalAndConfirmThePaymentAndroid() {
        bonusPage_Android.loginPaypal();
    }

}

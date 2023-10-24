package stepsdefinitions.ios;

import io.appium.java_client.AppiumDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.ios.BonusPage_iOS;
import stepsdefinitions.HooksSteps;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BonusSteps_iOS {
    private final AppiumDriver driver = HooksSteps.getDriver();
    private final BonusPage_iOS bonusPage = new BonusPage_iOS(driver);

    @And("the user logs into Paypal and confirm the payment")
    public void theUserLogsIntoPaypalAndConfirmThePayment() {
        bonusPage.loginPaypal();
    }

    @When("tap on eye icon to display the bonus amount")
    public void tapOnEyeIconToDisplayTheBonusAmount() {
        bonusPage.tapOnViewBalance();
    }

    @Then("balance is displayed correctly")
    public void balanceIsDisplayedCorrectly() {
        assertTrue(bonusPage.checkBalanceIsDisplayed());
    }

    @When("taps on bonus icon")
    public void tapsOnBonusIcon() {
        bonusPage.tapOnBonusIcon();
    }

    @Then("the transaction history is displayed")
    public void theTransactionHistoryIsDisplayed() {
        assertTrue(bonusPage.checkHistoryIsDisplayed());
    }
}

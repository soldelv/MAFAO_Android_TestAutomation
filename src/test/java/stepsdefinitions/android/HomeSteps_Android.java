package stepsdefinitions.android;

import io.appium.java_client.AppiumDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import pages.android.HomePage_Android;
import stepsdefinitions.HooksSteps;

import static utils.CommonMethods.holdOn;
import static utils.CommonMethods.print;

public class HomeSteps_Android {
    private final AppiumDriver driver = HooksSteps.getDriver();
    private final HomePage_Android homePage_Android = new HomePage_Android(driver);

    @Given("the user is on bonus section - Android")
    public void theUserIsOnBonusScreenAndroid() {
        homePage_Android.tapOnBonus();
    }

    @And("goes to favorite section - Android")
    public void goesToFavoriteSectionAndroid() {
        homePage_Android.tapOnFavorites();
    }

    @And("goes to orders section - Android")
    public void goesToOrdersScreenAndroid() {
        holdOn(1000);
        homePage_Android.tapOnOrders();
        holdOn(1000);
    }

    @Given("the user is on marketplace screen - Android")
    public void theUserIsOnMarketplaceScreenAndroid() {
        Assertions.assertTrue(homePage_Android.checkOnMarketplace());
    }

    @When("scrolls down on the home - Android")
    public void scrollsDownOnTheHomeAndroid() {
        homePage_Android.scrollDown();
        homePage_Android.scrollDown();
    }

    @Then("the user can see the products listed - Android")
    public void theUserCanSeeTheProductsListedAndroid() {
        Assertions.assertTrue(homePage_Android.checkProductsAreListed("product-"));
    }

    @When("^the user searches for (.*) on the searchbar - Android$")
    public void theUserSearchesForKeywordOnTheSearchbarAndroid(String keyword) {
        homePage_Android.tapOnSearchbar();
        homePage_Android.searchByKeywordOnSearchbar(keyword);
    }

    @Then("^products related with (.*) are displayed - Android$")
    public void productsWithWordKeywordAreDisplayedAndroid(String keyword) {
        Assertions.assertTrue(homePage_Android.checkProductsAreListed(keyword));
    }

    @When("^taps on (.*) from Home - Android$")
    public void tapsOnCategoryFromHomeAndroid(String categoryName) {
        homePage_Android.tapOnCategory(categoryName);
    }

    @Then("^products related to (.*) are displayed - Android$")
    public void productsRelatedToCategoryAreDisplayedAndroid(String categoryName) {
        Assertions.assertTrue(homePage_Android.checkProductsAreListed("product-"));
        print("After filter by "+categoryName+" products are displayed");
    }

    @And("^taps on (.*) from marketplace - Android$")
    public void tapsOnProduct_FromMarketplaceAndroid(String product1) {
        homePage_Android.tapOnProduct(product1);
    }

}

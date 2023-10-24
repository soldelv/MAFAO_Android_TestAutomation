package stepsdefinitions.ios;

import io.appium.java_client.AppiumDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import pages.ios.HomePage_iOS;
import stepsdefinitions.HooksSteps;

public class HomeSteps_iOS {
    private final AppiumDriver driver = HooksSteps.getDriver();
    private final HomePage_iOS homePage = new HomePage_iOS(driver);

    @Given("the user is on marketplace screen")
    public void theUserIsOnMarketplaceScreen() {
        Assertions.assertTrue(homePage.checkOnMarketplace());
    }

    @When("chooses a product from marketplace and taps as favorite")
    public void choosesAProductFromMarketplace() {
        homePage.chooseAndTapFavoriteFromMarketplace();
    }

    @Given("the user is on favorite screen")
    @And("goes to favorite section")
    public void goesToFavoriteSection() {
        homePage.tapOnFavorites();
    }
    @When("taps on a product from marketplace")
    public void tapsOnAProductFromMarketplace() {
        homePage.tapsOnProduct();
    }

    @Given("the user is on bonus screen")
    public void theUserIsOnBonusScreen() {
        homePage.tapOnProfile();
        homePage.tapOnBonus();
    }
    @And("^taps on a product with name (.*)$")
    public void tapsOnAProductWithNameProduct_name(String productName) {
        homePage.tapOnProduct(productName);
    }

    @When("^scrolls down and taps on (.*) from marketplace$")
    public void scrollAndTapOnProduct_FromMarketplace(String product1) {
        homePage.scrollDown();
        homePage.tapOnProduct(product1);
    }

    @Given("the user is on orders screen")
    @And("goes to orders screen")
    public void goesToOrdersScreen() {
        homePage.tapOnOrders();
    }

    @When("scrolls down on the home")
    public void scrollsDownOnTheHome() {
        homePage.scrollDown();
        homePage.scrollDown();
    }

    @Then("the user can see the products listed")
    public void theUserCanSeeTheProductsListed() {
        Assertions.assertTrue(homePage.checkProductsAreListed("product-"));
    }

    @When("^the user searches for (.*) on the searchbar$")
    public void theUserSearchesForKeywordOnTheSearchbar(String keyword) {
        homePage.tapOnSearchbar();
        homePage.searchByKeywordOnSearchbar(keyword);
    }

    @Then("^products related to (.*) are displayed$")
    public void productsRelatedToSearchAreDisplayed(String search) {
        homePage.checkProductsAfterSearch(search);
    }

    @Then("^products with word (.*) are displayed$")
    public void productsWithWordKeywordAreDisplayed(String keyword) {
        Assertions.assertTrue(homePage.checkProductsAreListed(keyword));
    }

    @When("^taps on (.*) from Home$")
    public void tapsOnCategoryFromHome(String categoryName) {
        homePage.tapOnCategory(categoryName);
    }

    @When("user goes to profile screen")
    public void userGoesToProfileScreen() {
        homePage.tapOnProfile();
    }

    @And("goes to home section")
    public void goesToHomeSection() {
        homePage.tapOnHome();
    }

    @And("taps on notification icon")
    public void tapsOnNotificationIcon() {
        homePage.tapOnNotificationsBtn();
    }

    @Then("^products with category (.*) are displayed$")
    public void productsWithCategoryAreDisplayed(String category) {
        Assertions.assertTrue(homePage.checkCategoryProductsAreDisplayed(category));
        homePage.tapOnBackBtn();
    }

    @And("goes to Withdrawal section")
    public void goesToWithdrawalSection() {
        homePage.tapOnWithdrawal();
    }

    @And("taps on a product from the marketplace - iOS")
    public void tapsOnAProductFromTheMarketplaceIOS() {
        homePage.tapAnyProductFromTheMarketplace();
    }

    @And("taps on the product with lowest price from marketplace")
    public void tapsOnTheProductWithLowestPriceFromMarketplace() {
        homePage.tapsOnLowestPriceProduct();
    }

    @And("taps on the first product of the marketplace list")
    public void tapsOnTheFirstProductOfTheMarketplaceList() {
        homePage.tapsOnFirstProductMarketplace();
    }
}

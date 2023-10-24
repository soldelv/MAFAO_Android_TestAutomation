package stepsdefinitions.ios;

import io.appium.java_client.AppiumDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import pages.ios.FavoritesPage_iOS;
import stepsdefinitions.HooksSteps;

import static utils.CommonMethods.print;

public class FavoritesSteps_iOS {
    private AppiumDriver driver = HooksSteps.getDriver();
    private final FavoritesPage_iOS favoritesPage = new FavoritesPage_iOS(driver);

    @When("^chooses (.*), (.*), (.*) from marketplace and taps as favorite$")
    public void choosesProduct_Product_Product_FromMarketplaceAndTapsAsFavorite(String p1, String p2, String p3) {
        favoritesPage.tapFavoriteFromMarketplace(p1);
        favoritesPage.tapFavoriteFromMarketplace(p2);
        favoritesPage.tapFavoriteFromMarketplace(p3);
    }
    @Then("^(.*), (.*), (.*) are displayed on Favorites list")
    public void theProductIsDisplayedOnFavoritesList(String p1, String p2, String p3) {
        Assertions.assertTrue(favoritesPage.checkProductIsDisplayed(p1));
        Assertions.assertTrue(favoritesPage.checkProductIsDisplayed(p2));
        Assertions.assertTrue(favoritesPage.checkProductIsDisplayed(p3));
    }

    @When("^scrolls down searching for (.*)$")
    public void theUserSearchesForProduct_(String product1) {
        print("Scroll until search product "+product1);
        favoritesPage.scrollDown();
        favoritesPage.scrollDown();
        favoritesPage.scrollDown();
        favoritesPage.scrollDown();
    }

    @And("taps on the heart icon to add the product as a favorite")
    public void tapsOnTheHeartIconToAddTheProductAsAFavorite() {
        favoritesPage.tapFavoriteFromProduct();
        favoritesPage.tapOnBackBtn();
    }

    @Then("the first product of the marketplace list is displayed on Favorites list")
    public void theFirstProductOfTheMarketplaceListIsDisplayedOnFavoritesList() {
        Assertions.assertTrue(favoritesPage.checkFirstProductIsDisplayed());
    }

    @Given("the user has products on favorites list")
    public void theUserHasProductsOnFavoritesList() throws InterruptedException {
        Thread.sleep(3000);
        Assertions.assertTrue(favoritesPage.checkFavoritesIsNotEmpty());
    }

    @When("taps on the heart icon to remove the product as a favorite")
    public void tapsOnTheHeartIconToRemoveTheProductAsAFavorite() {
        favoritesPage.removeAllProductsFromFavorites();
    }

    @Then("the products are not longer displayed on favorites list")
    public void theProductIsNotLongerDisplayedOnFavoritesList() throws InterruptedException {
        Thread.sleep(1000);
        Assertions.assertFalse(favoritesPage.checkFavoritesIsNotEmpty());
    }

    @And("^products related to product (.*) are displayed$")
    public void productsRelatedToKeywordAreDisplayed(String keyword) {
        Assertions.assertTrue(favoritesPage.checkProductIsDisplayed(keyword));
    }

    @And("taps on create an alert")
    public void tapsOnCreateAnAlert() {
        favoritesPage.tapCreateAnAlert();
    }

    @And("taps on Alerts")
    public void tapsOnAlerts() {
        favoritesPage.tapOnAlerts();
    }

    @Then("^(.*) alert is displayed$")
    public void keywordAlertIsDisplayed(String keyword) {
        Assertions.assertTrue(favoritesPage.checkAlertIsDisplayed(keyword));
    }

    @And("tap on delete alert")
    public void tapOnDeleteAlert() {
        favoritesPage.tapOnDeleteAlert();
        favoritesPage.confirmDeletionAlert();
    }

    @Then("alert was successfully deleted")
    public void alertWasSuccessfullyDeleted() {
        Assertions.assertTrue(favoritesPage.checkAlertIsDeleted());
    }

    @And("alert created button is displayed")
    public void alertCreatedButtonIsDisplayed() {
        Assertions.assertTrue(favoritesPage.checkAlertCreatedBtnIsDisplayed());
        favoritesPage.tapOnBackBtn();
    }

    @Then("alert created successfully message is displayed")
    public void alertCreatedSuccessfullyMessageIsDisplayed() {
        Assertions.assertTrue(favoritesPage.alertCreatedSuccessfullyMessageIsDisplayed());

    }
}

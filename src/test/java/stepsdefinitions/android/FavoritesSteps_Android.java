package stepsdefinitions.android;

import io.appium.java_client.AppiumDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import pages.android.FavoritesPage_Android;
import stepsdefinitions.HooksSteps;

import static utils.CommonMethods.holdOn;

public class FavoritesSteps_Android {
    private AppiumDriver driver = HooksSteps.getDriver();
    private final FavoritesPage_Android favoritesPage_Android = new FavoritesPage_Android(driver);

    @When("^chooses (.*), (.*) from marketplace and taps as favorite - Android$")
    public void choosesProduct_Product_Product_FromMarketplaceAndTapsAsFavoriteAndroid(String p1, String p2) {
        favoritesPage_Android.tapFavoriteFromMarketplace(p1);
        favoritesPage_Android.tapFavoriteFromMarketplace(p2);
    }


    @Then("^(.*), (.*) are displayed on Favorites list - Android$")
    public void product_Product_AreDisplayedOnFavoritesListAndroid(String p1, String p2) {
        Assertions.assertTrue(favoritesPage_Android.checkProductIsOnFavorites(p1));
        Assertions.assertTrue(favoritesPage_Android.checkProductIsOnFavorites(p2));
    }

    @And("taps on the heart icon to add the product as a favorite - Android")
    public void tapsOnTheHeartIconToAddTheProductAsAFavoriteAndroid() {
        favoritesPage_Android.tapFavoriteFromProduct();

    }

    @Then("^(.*) is displayed on Favorites list - Android$")
    public void product_IsDisplayedOnFavoritesListAndroid(String product) {
        Assertions.assertTrue(favoritesPage_Android.checkProductIsOnFavorites(product));
    }

    @When("the user has products on favorites list - Android")
    public void theUserHasProductsOnFavoritesListAndroid() {
        holdOn(3000);
        Assertions.assertTrue(favoritesPage_Android.checkFavoritesIsNotEmpty());

    }

    @And("taps on the heart icon to remove the product as a favorite - Android")
    public void tapsOnTheHeartIconToRemoveTheProductAsAFavoriteAndroid() {
        favoritesPage_Android.removeAllProductsFromFavorites();
    }

    @Then("the products are not longer displayed on favorites list - Android")
    public void theProductsAreNotLongerDisplayedOnFavoritesListAndroid() {
        holdOn(1000);
        Assertions.assertFalse(favoritesPage_Android.checkFavoritesIsNotEmpty());
    }
}

package stepsdefinitions.ios;

import io.appium.java_client.AppiumDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import pages.ios.ProductPage_iOS;
import stepsdefinitions.HooksSteps;

public class ProductSteps_iOS {
    private AppiumDriver driver = HooksSteps.getDriver();
    private final ProductPage_iOS productPage = new ProductPage_iOS(driver);
    private static String product_price;

    @And("taps on plus icon")
    public void tapsOnPlusIcon() {
        productPage.tapOnPlusBtn();
    }

    @And("taps on minus icon")
    public void tapsOnMinusIcon() {
        productPage.tapOnMinusBtn();
    }

    @And("tap add to cart for the product with the lowest price")
    public void tapAddToCartForTheProductWithTheLowestPrice() {
        productPage.tapOnAddToCartBtnLowestPrice();
    }

    @And("taps on Auchan logo to see seller info")
    public void tapsOnAuchanLogoToSeeSellerInfo() {
        productPage.tapOnSellerInfo();
    }

    @Then("seller info is displayed correctly")
    public void sellerInfoIsDisplayedCorrectly() {
        Assertions.assertTrue(productPage.checkSellerInfo());
    }

    @And("taps on Report this product")
    public void tapsOnReportThisProduct() {
        productPage.tapOnReportThisProductBtn();
    }

    @And("fills the report the product form")
    public void fillsTheReportTheProductForm() {
        productPage.fillReportProductForm();
    }

    @And("report to validate message are displayed")
    public void reportToValidateMessageAreDisplayed() {
        Assertions.assertTrue(productPage.checkReportMessageIsDisplayed());
    }

    @Then("taps on Ok to send the report")
    public void tapsOnOkToSendTheReport() {
        productPage.tapOnOkReportBtn();
    }

    @And("taps on call button")
    public void tapsOnCallButton() {
        productPage.tapOnCallBtn();
    }

    @Then("^product information are displayed correctly$")
    public void productInformationAreDisplayedCorrectly() {
        productPage.checkProductDetailView();
    }

    @And("goes to similar products section")
    public void goesToSimilarProductsSection() {
        Assertions.assertTrue(productPage.similarProductsSectionIsDisplayed());
    }

    @Then("similar products for first product of the list are displayed")
    public void similarProductsForFirstProductOfTheListAreDisplayed() {
        productPage.checkSimilarProductsAreDisplayed();
    }

    @And("taps on add to cart button")
    public void tapsOnAddToCartButton() {
        productPage.tapOnAddToCartBtn();
    }

    @And("taps on Give your opinion")
    public void tapsOnGiveYourOpinion() {
        productPage.tapsOnGiveYourOpinion();
    }
}

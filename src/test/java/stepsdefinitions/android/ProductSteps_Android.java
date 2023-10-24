package stepsdefinitions.android;

import io.appium.java_client.AppiumDriver;
import io.cucumber.java.en.And;
import pages.android.ProductPage_Android;
import stepsdefinitions.HooksSteps;

public class ProductSteps_Android {
    private AppiumDriver driver = HooksSteps.getDriver();
    private final ProductPage_Android productPage_Android = new ProductPage_Android(driver);

    @And("taps on plus icon twice - Android")
    public void tapsOnPlusIconTwiceAndroid() {
        productPage_Android.tapOnPlusBtn();
        productPage_Android.tapOnPlusBtn();
    }

    @And("taps on minus icon once - Android")
    public void tapsOnMinusIconOnceAndroid() {
        productPage_Android.tapOnMinusBtn();
    }

    @And("taps to add to cart icon - Android")
    public void tapsToAddToCartIconAndroid() {
        productPage_Android.tapOnAddToCart();
    }


}

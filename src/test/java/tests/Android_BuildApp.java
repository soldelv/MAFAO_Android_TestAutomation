package tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import static pages.BasePage.WAIT;

public class Android_BuildApp {
    AppiumDriver driver;
    public AndroidTouchAction actions;
    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("platformVersion", "8.1.0");
        caps.setCapability("deviceName", "Pixel6_Android8Oreo");
        caps.setCapability("forceEspressoRebuild",true);
        caps.setCapability("app", System.getProperty("user.dir") + "/src/test/resources/apps/Mafao-v1.2.73.148.release.apk");
        //caps.setCapability("app", "C:/Users/User01/Downloads/Mafao-v1.2.73.148.release.apk");
        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), caps);

    }

    @Test
    public void clickLoginButton() throws InterruptedException {
        // code
        Thread.sleep(8000);
        System.out.println("END OF WAITING");
        driver.findElementByAccessibilityId("btn-login").click();
        Thread.sleep(1000);
        driver.findElementByAccessibilityId("telephone-country-flag-code").click();
        Thread.sleep(1000);
        driver.findElementByAccessibilityId("country-search-input").sendKeys("Portugal");
        Thread.sleep(1000);
        driver.findElementByAccessibilityId("country-search-option-0").click();
        Thread.sleep(1000);
        driver.findElementByAccessibilityId("phone-auth-input-phone").sendKeys("913812319");
        Thread.sleep(1000);



    }

    /*private void scrollDown() {
        Dimension dimension = driver.manage().window().getSize();
        int scrollStart = (int) (dimension.getHeight() * 0.8);
        int scrollEnd = (int) (dimension.getHeight() * 0.1);

        actions = new AndroidTouchAction(driver)
                .press(PointOption.point(0, scrollStart))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3)))
                .moveTo(PointOption.point(0, scrollEnd))
                .release()
                .perform();
    }

    @Test
    public void scroll_test() {
        AndroidElement views =
                (AndroidElement) driver.findElementByAccessibilityId("Views");
        // Tap
        actions = new AndroidTouchAction(driver);
        actions.tap(ElementOption.element(views)).perform();
        // ScrollDown
        scrollDown();
        AndroidElement lists = (AndroidElement) driver.findElementByAccessibilityId("Lists");
        actions.tap(ElementOption.element(lists)).perform();
    }*/

    @After
    public void tearDown(){
        if (null != driver) {
            driver.quit();
        }
    }
}

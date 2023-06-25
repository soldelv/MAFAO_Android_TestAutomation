package factory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class AndroidDriverFactory {
    AppiumDriver driver;

    public static AppiumDriver initializeAndroidDriver() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("platformVersion", "8.1.0");
        caps.setCapability("deviceName", "Pixel6_Android8Oreo");
        caps.setCapability("forceEspressoRebuild",true);
        caps.setCapability("app", System.getProperty("user.dir") + "/src/test/resources/apps/Mafao-v1.2.73.148.release.apk");
        return new AndroidDriver(new URL("http://localhost:4723/wd/hub"), caps);

    }


}

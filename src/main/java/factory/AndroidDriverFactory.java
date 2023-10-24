package factory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

import static constants.Constants.*;

public class AndroidDriverFactory {

    public static AppiumDriver initializeAndroidDriver() throws URISyntaxException, MalformedURLException {
        AppiumDriver driver;
        DesiredCapabilities caps = new DesiredCapabilities();
        URI uriServer = new URI("http://localhost:4723/wd/hub");

        caps.setCapability("platformName", MOBILE_ANDROID);
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("platformVersion", "13.0.0");
        caps.setCapability("deviceName", "AndroidTiramisu_20230706");
        caps.setCapability("app", ANDROID_APP_PATH);
        driver = new AndroidDriver(uriServer.toURL(), caps);

        return driver;
    }

    //fc2d6590123
    public static AppiumDriver initializeAndroidRealDevice() throws URISyntaxException, MalformedURLException {
        AppiumDriver driver;
        DesiredCapabilities caps = new DesiredCapabilities();
        URI uriServer = new URI("http://localhost:4723/wd/hub");

        caps.setCapability("platformName", MOBILE_ANDROID);
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("platformVersion", "13.0.0");
        caps.setCapability("deviceName", "Redmi Note 12 5G");
        caps.setCapability("app", ANDROID_APP_PATH);
        driver = new AndroidDriver(uriServer.toURL(), caps);

        return driver;
    }


}
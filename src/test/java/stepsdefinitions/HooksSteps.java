package stepsdefinitions;

import factory.AndroidDriverFactory;
import factory.iOSDriverFactory;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

import static constants.Constants.ENVIRONMENT;
import static utils.CommonMethods.print;

public class HooksSteps {
    private static AppiumDriver driver;

   @Before
    public void driverSetUp() throws MalformedURLException, URISyntaxException {
        switch (ENVIRONMENT) {
            case "android" -> driver = AndroidDriverFactory.initializeAndroidDriver();
            case "ios" -> driver = iOSDriverFactory.initializeIOSDriver();
            default -> print("Invalid environment name");
        }
    }

   @After
    public void driverTearDown(){
        if (null != driver) {
            driver.quit();
        }
    }

    public static AppiumDriver getDriver() {
        return driver;
    }


}

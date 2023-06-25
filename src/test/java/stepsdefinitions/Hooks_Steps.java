package stepsdefinitions;

import factory.AndroidDriverFactory;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import java.net.MalformedURLException;

public class Hooks_Steps {
    private AppiumDriver driver;
    private final TestContext context;

    public Hooks_Steps(TestContext context) {
        this.context = context;
    }
    /*
    @Before
    public void driverSetUp(Scenario scenario) throws MalformedURLException {
        driver = AndroidDriverFactory.initializeAndroidDriver();
        context.driver = driver;
    }

    @After
    public void driverTearDown(Scenario scenario){
        if (null != driver) {
            driver.quit();
        }
    }*/
}

package tests;

import io.appium.java_client.AppiumDriver;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class MyTestClass  {

    @Test
    public void myTest() throws IOException, InterruptedException {
        // Your test code here
        // Use the 'driver' object to interact with your application
        AppiumDriver driver;

        driver = BaseClassTest.setup();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //LoginPage.clickLoginButton();
    }
}


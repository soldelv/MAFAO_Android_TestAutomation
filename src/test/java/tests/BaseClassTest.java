package tests;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.net.URL;

public class BaseClassTest {
    protected AppiumDriver<MobileElement> driver;
    private static Process emulatorProcess;

    @Before
    public static AppiumDriver setup() throws IOException, InterruptedException {
        // Launch the Android emulator programmatically
        launchEmulator();

        // Set desired capabilities for the Android emulator
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "emulator-5554");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "8.1.0");
        capabilities.setCapability("app", "C:/Users/User01/Downloads/Mafao-v1.2.71.146.release.apk");

        // Set Appium server URL
        URL appiumServerURL = new URL("http://localhost:4723/wd/hub");

        // Create the AppiumDriver instance
        //driver = new AndroidDriver<>(appiumServerURL, capabilities);
        return new AndroidDriver<>(appiumServerURL, capabilities);
    }

    @After
    public void teardown() {
        // Quit the driver and close the app
        if (driver != null) {
            driver.quit();
        }

        // Stop the emulator process
        if (emulatorProcess != null) {
            emulatorProcess.destroy();
        }
    }

    private static void launchEmulator() throws IOException, InterruptedException {
        String emulatorPath = "C:/Users/User01/AppData/Local/Android/Sdk/emulator/emulator.exe"; // Path to the emulator executable
        String avdName = "Pixel6_Android8Oreo"; // Name of the AVD (Android Virtual Device)

        String[] cmd = {emulatorPath, "-avd", avdName};
        emulatorProcess = Runtime.getRuntime().exec(cmd);

        // Wait for the emulator to start
        Thread.sleep(5000);
    }
}
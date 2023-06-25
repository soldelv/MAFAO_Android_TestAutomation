package factory;

import io.appium.java_client.AppiumDriver;
import pages.LoginPage;

public class PageFactoryManager {
    private static LoginPage loginPage;

    public static LoginPage getLoginPage(AppiumDriver driver){
        /**
         * Using Ternary Operator: Checking for loginPage as null.
         *
         * If it is null, then, create new object and return
         *
         * If it is not null, then, return loginPage
         */
        return loginPage == null ? new LoginPage(driver) : loginPage;
    }

}

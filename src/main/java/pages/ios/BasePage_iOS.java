package pages.ios;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.fail;
import static utils.CommonMethods.holdOn;
import static utils.CommonMethods.print;


public class BasePage_iOS {

    protected IOSDriver driver;
    public AndroidTouchAction actions;
    public BasePage_iOS(AppiumDriver driver) {
        this.driver = (IOSDriver) driver;
    }

    /** common elements */
    By searchBar = MobileBy.AccessibilityId("search-bar");
    By backIconBtn = MobileBy.AccessibilityId("\uEB2A");
    public void tapOnBackBtn()  {
        waitUntilIsDisplayed(backIconBtn);
        tap(backIconBtn);
    }

    public void tapOnSearchbar()  {
        tap(searchBar);
    }
    public void searchByKeywordOnSearchbar(String keyword)  {
        type(keyword,searchBar);
        waitFor(30);
        performEnter(searchBar);
    }

    public boolean checkProductsAfterSearch(String keyword){
        holdOn(200);
        By products = MobileBy.xpath("//XCUIElementTypeScrollView//XCUIElementTypeOther[contains(@name,\"product-\")]");
        print("Check products results after search "+keyword);
        return isDisplayed(products);
    }
    public MobileElement checkElement(By locator){
        try{
            return (MobileElement) driver.findElement(locator);
        }catch(NoSuchElementException ignored){
            return null;
        }
    }
    public MobileElement getElement(By locator){
        while(checkElement(locator)==null){
            waitFor(5);
        }
        return (MobileElement) driver.findElement(locator);
    }

    public List getElements(By locator){
        return driver.findElements(locator);
    }

    public MobileElement findElementFromElement(MobileElement element, By locator){
        return element.findElement(locator);
    }

    public boolean isDisplayed(MobileElement element){
        return element.isDisplayed();
    }

    public boolean isDisplayed(By locator){
        try{
            return driver.findElement(locator).isDisplayed();
        }catch (NoSuchElementException e){
            e.getMessage();
            return false;
        }
    }

    public void waitUntilIsDisplayed(By locator){
        for(int i=0; i<10;i++){
            if(!isDisplayed(locator)){
                waitFor(2);
            }else{
                break;
            }
        }
        print("Element already displayed: "+locator);
    }

    public void type(String inputText, By locator){
        print(" TYPING ------- "+inputText);
        getElement(locator).sendKeys(inputText);
        print("Typed on element "+locator);
    }

    public void performEnter(By locator){
        getElement(locator).sendKeys(Keys.ENTER);
    }

    public void clearAndType(String inputText, By locator){
        getElement(locator).clear();
        getElement(locator).sendKeys(inputText);
    }

    public String getElementValue(By locator){
        return getElement(locator).getAttribute("value");
    }
    public void clear(MobileElement element) {
        element.clear();
    }

    public void tap(MobileElement element) {
        element.click();
        print("Tapped on "+element);
    }

    public void tap(By locator) {
        if(isDisplayed(locator)){
            getElement(locator).click();
            print("Tapped on "+locator);
        }else{
            fail("Locator not found, invalid to tap: "+locator);
        }
    }

    public String getAttribute(MobileElement element, String attribute) {
        return element.getAttribute(attribute);
    }

    public void scrollDown() {
        Dimension dimension = driver.manage().window().getSize();
        int scrollStart = (int) (dimension.getHeight() * 0.9);
        int scrollEnd = (int) (dimension.getHeight() * 0.1);

        actions = new AndroidTouchAction(driver)
                .press(PointOption.point(0, scrollStart))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(0, scrollEnd))
                .release()
                .perform();
    }

    public void scrollDownUntilIsDisplayed(By locator){
        for(int i=0; i<10;i++){
            if(!isDisplayed(locator)){
                scrollDown();
                holdOn(5);
            }else{
                break;
            }
        }
        if(!isDisplayed(locator)){
            fail("Element is not displayed: "+locator);
        }
    }

    public void typeFromKeyboard(String locator, String numberToType){
        char[] splitNumber = numberToType.toCharArray();
        print("Split number" + Arrays.toString(splitNumber));

        for (char i : splitNumber) {
            print("TAP NUMBER --- "+ i);
            By key = MobileBy.AccessibilityId(locator + i);
            waitUntilIsDisplayed(key);
            tap(key);
        }
    }

    public void waitFor(long seconds){
        driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    }

}
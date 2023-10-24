package pages.android;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static utils.CommonMethods.print;


public class BasePage_Android  {
    //protected AppiumDriver driver;
    protected AndroidDriver<WebElement> driver;
    public AndroidTouchAction actions;

    public BasePage_Android(AndroidDriver<WebElement> driver) {
        this.driver = driver;
    }

    /* common elements */
    By searchBar = MobileBy.AccessibilityId("search-bar");
    public void tapOnSearchbar()  {
        tap(searchBar);
    }
    public void searchByKeywordOnSearchbar(String keyword)  {
        type(keyword,searchBar);
        waitFor(30);
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
        return driver.findElement(locator).isDisplayed();
    }

    public void type(String inputText, By locator){
        getElement(locator).sendKeys(inputText);
        print("Typed on element "+locator);
    }

    public void clear(MobileElement element) {
        element.clear();
    }

    public void tap(MobileElement element) {
        element.click();
        print("Tapped on "+element);
    }

    public void tap(By locator) {
        getElement(locator).click();
        print("Tapped on "+locator);
    }

    public String getAttribute(MobileElement element, String attribute) {
        return element.getAttribute(attribute);
    }

    public void scrollDown() {
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

    public void waitFor(long seconds){
        driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    }

    /* TEST SAMPLE CLIPBOARD */
    public void clipboard_test() {
        String text = "Hello TAU";
        driver.setClipboardText(text);
        MobileElement nameTxt = (MobileElement) driver.findElementByAccessibilityId("my_text_fieldCD");
        nameTxt.clear();
        nameTxt.sendKeys(driver.getClipboardText());
        //Assert.assertEquals(text, nameTxt.getText());
    }
    /* TEST SAMPLE SEND SMS */
    public void send_SMS() {
        driver.sendSMS("555-123-4567","Hello from TAU");
    }

    /* SEND PHOTOS */
    public void send_Photo() throws IOException {

        By backupBtn = By.id("auto_backup_switch");
        By touchOutsideBtn = By.id("touch_outside");
        By keepOffBtn = By.xpath("//*[@text='KEEP OFF']");
        By photo = By.xpath("//android.view.ViewGroup[contains(@content-desc,'Photo taken')]");
        File classPath, imageDir, img;

        classPath = new File(System.getProperty("user.dir"));
        imageDir = new File(classPath, "/resources/images");
        img = new File(imageDir.getCanonicalFile(), "TAU-logo.png");

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(backupBtn)).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(touchOutsideBtn)).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(keepOffBtn)).click();

        String Android_Photo_Path = "mnt/sdcard/Pictures";
        driver.pushFile(Android_Photo_Path + "/" + img.getName(), img);
        wait.until(ExpectedConditions.numberOfElementsToBe(photo, 1));
    }

    /* DRAG AND DROP SAMPLE*/
    public void drag_drop() {
        AndroidElement views = (AndroidElement) driver.findElementByAccessibilityId("Views");

        actions = new AndroidTouchAction(driver);
        actions.tap(ElementOption.element(views)).perform();

        AndroidElement drag_drop =
                (AndroidElement) driver.findElementByAccessibilityId("Drag and Drop");
        actions.tap(ElementOption.element(drag_drop)).perform();

        AndroidElement drag = (AndroidElement) driver.findElement(By.id("drag_dot_1"));
        AndroidElement drop = (AndroidElement) driver.findElement(By.id("drag_dot_2"));

        actions.longPress(ElementOption.element(drag))
                .waitAction().moveTo(ElementOption.element(drop))
                .release()
                .perform();
    }

    public void typeFromKeyboard(String locator, String numberToType){
        char[] splitNumber = numberToType.toCharArray();
        print("Split number" + Arrays.toString(splitNumber));

        for (char i : splitNumber) {
            print("TAP NUMBER --- "+String.valueOf(i));
            By key = MobileBy.AccessibilityId(String.valueOf(locator+i));
            tap(key);
        }
    }
}
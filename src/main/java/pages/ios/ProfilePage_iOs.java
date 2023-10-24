package pages.ios;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;

import java.util.Objects;

import static constants.Constants.SECRET_CODE;
import static apis.MafaoAPIs.getAPIUserInfoByID;
import static org.testng.AssertJUnit.fail;
import static utils.CommonMethods.*;

public class ProfilePage_iOs extends BasePage_iOS{
    public ProfilePage_iOs(AppiumDriver driver) {
        super(driver);
    }

    private static String newSecretCode= generateNewPincode();;
    private static String newDisplayName;

    By logOutBtn = MobileBy.xpath("(//XCUIElementTypeOther[@name='Log out'])[1]");
    By confirmLogOutBtn = MobileBy.xpath("(//XCUIElementTypeOther[@name='logout-button'])[2]");
    By profileOptionBtn = MobileBy.xpath("(//XCUIElementTypeOther[@name=\"Profile \uE61F\"])[2]");
    By settingsBtn = MobileBy.xpath("(//XCUIElementTypeOther[@name='Settings'])[1]");
    By notificationsBtn = MobileBy.xpath("(//XCUIElementTypeOther[@name=\"Notifications\"])[1]");
    By myShopOptionBtn = MobileBy.xpath("(//XCUIElementTypeOther[@name='My shop'])[1]");

    /** PROFILE */
    By displayNameField = MobileBy.xpath("//XCUIElementTypeTextField[@name='edit-profile-display-name']");
    By editProfileBtn = MobileBy.xpath("(//XCUIElementTypeOther[@name=\"edit-profile-button\"])[2]");
    By nextKeyboardBtn = MobileBy.AccessibilityId("Next:");
    By backBtn = MobileBy.xpath("(//XCUIElementTypeOther[@name=\"back-button\"])[2]");
    By successfulProfileUpdate = MobileBy.AccessibilityId("Update profile successfully");
    By changeAvatarBtn = MobileBy.xpath("(//XCUIElementTypeOther[@name=\"btn-confirm-next\"])[2]");
    By updateAvatarSuccessMsg = MobileBy.AccessibilityId("Update avatar success");

    /** SETTINGS */
    By secretCodeBtn = MobileBy.xpath("(//XCUIElementTypeOther[@name=\"Secret Code \uE61F\"])[2]");
    By changeMySecretCode = MobileBy.AccessibilityId("Change my secret code \uE61F");
    By successfulReset = MobileBy.xpath("//XCUIElementTypeStaticText[@name='Your new secret code has been successfully changed!']");
    By retryLaterMessage= MobileBy.AccessibilityId("Retry later");
    By okBtn = MobileBy.AccessibilityId("OK");
    By loginNow = MobileBy.xpath("(//XCUIElementTypeOther[@name='back-to-profile-button'])[2]");
    By confirmBtn = MobileBy.xpath("(//XCUIElementTypeOther[@name='confirm-login-button'])[2]");
    By backupAccountOption = MobileBy.xpath("(//XCUIElementTypeOther[@name='Backup Account \uE61F\'])[2]");
    By backupBtn = MobileBy.xpath("(//XCUIElementTypeOther[@name='backup-button'])[2]");
    By successBackupMgs = MobileBy.xpath("//XCUIElementTypeStaticText[@name='Backup successfully']");
    By returnBtn = MobileBy.xpath("(//XCUIElementTypeOther[@name='return-button'])[2]");
    By locationBtn = MobileBy.xpath("(//XCUIElementTypeOther[@name=\"Location \uE61F\"])[2]");

    /** SELLER DASHBOARD */
    By sellerDashboardTitle = MobileBy.xpath("//XCUIElementTypeStaticText[@name='Seller Dashboard']");
    By marketplaceProducts = MobileBy.AccessibilityId("Marketplace Products");
    By marketplaceOrders = MobileBy.AccessibilityId("Marketplace Orders");
    By sellerPayments = MobileBy.AccessibilityId("Seller Payments");
    By overviewReports = MobileBy.AccessibilityId("Overview Reports");

    public void tapOnLogOutBtn(){
        tap(logOutBtn);
    }

    public void confirmLogOutBtn(){
        tap(confirmLogOutBtn);
    }

    public void tapOnProfileOption(){
        tap(profileOptionBtn);
    }

    public void tapOnSettingsBtn(){
        scrollDownUntilIsDisplayed(settingsBtn);
        tap(settingsBtn);
    }

    public void tapOnNotifications(){
        tap(notificationsBtn);
    }
     public void tapOnMyShopOption(){
        scrollDownUntilIsDisplayed(myShopOptionBtn);
         tap(myShopOptionBtn);
     }
    public void goBackBtn(){
        tap(backBtn);
    }

    public void tapOnChangeSecretCode(){
        tap(secretCodeBtn);
    }
    public void tapOnBackupAccount(){
        scrollDown();
        tap(backupAccountOption);
    }

    public void tapOnConfirmBackup(){
        tap(backupBtn);
    }


    public void changeDisplayName(){
        tap(displayNameField);
        newDisplayName = "Test"+generateDateTimeString();
        clearAndType(newDisplayName,displayNameField);
        tap(nextKeyboardBtn);
        tap(editProfileBtn);
    }

    public boolean checkDisplayNameHasChanged(){
        holdOn(500);
        return isDisplayed(successfulProfileUpdate);
    }

    public boolean checkBackupSuccessMessage(){
        waitUntilIsDisplayed(returnBtn);
        return isDisplayed(successBackupMgs);
    }

    public void tapOnReturnFromBackUp(){
        tap(returnBtn);
    }

    public void tapOnChangeMySecretCode(){
        tap(changeMySecretCode);
    }

    public void enterCurrentSecretCode(){
        typeFromKeyboard("keypad-", SECRET_CODE);
    }

    public void enterANewSecretCodeTwice(String action) {
        if(Objects.equals(action, "random")){
            typeFromKeyboard("keypad-", newSecretCode);
            typeFromKeyboard("keypad-", newSecretCode);
        }
        if(Objects.equals(action, "reset")){
            typeFromKeyboard("keypad-", SECRET_CODE);
            typeFromKeyboard("keypad-", SECRET_CODE);
        }
    }

    public boolean successfulReset(){
        holdOn(5000);
        if(isDisplayed(retryLaterMessage)){
            tap(okBtn);
            fail("Error to reset the pincode");
            return false;
        }else if(isDisplayed(successfulReset)){
            return true;
        }else{
            fail("Can not proceed to reset pincode");
            return false;
        }
    }
    public void tapOnLoginNow(){
        tap(loginNow);
    }

    public void loginWithNewSecretCode(String action){
        if(Objects.equals(action, "random")){
            typeFromKeyboard("keypad-", newSecretCode);
        }
        if(Objects.equals(action, "reset")){
            typeFromKeyboard("keypad-", SECRET_CODE);
        }
        tap(confirmBtn);
    }

    public void resetSecretCodeToDefault(){
        scrollDown();
        tapOnSettingsBtn();
        tapOnChangeSecretCode();
        typeFromKeyboard("keypad-", newSecretCode);
        tapOnChangeMySecretCode();
        typeFromKeyboard("keypad-", newSecretCode);
        enterANewSecretCodeTwice("reset");
        if(successfulReset()){
            tapOnLoginNow();
        }
        loginWithNewSecretCode("reset");
    }

    public boolean checkSellerDashboardIsDisplayed(){
        waitUntilIsDisplayed(sellerDashboardTitle);
        return isDisplayed(marketplaceProducts) && isDisplayed(marketplaceOrders)
                && isDisplayed(sellerPayments) && isDisplayed(overviewReports);
    }

    public String getUserName(){
        String userName = getAPIUserInfoByID("seller").getName();
        if(Objects.equals(userName, null)){
            userName = getAPIUserInfoByID("buyer").getName();
        }
        return userName;
    }

    public boolean checkDisplayedName(){
        By display_name = MobileBy.xpath("//XCUIElementTypeStaticText[@name=\""+getUserName()+"\"]");
        return isDisplayed(display_name);
    }

    public boolean checkProfilePicture(){
        By profile_picture = MobileBy.xpath("//XCUIElementTypeOther[@name=\"Bonus Balance view-balance-button ****** Compagnon "+getUserName()+" Total points 2120 pts Next goal Mentor 5000 pts\"]/XCUIElementTypeOther[3]");
        return isDisplayed(profile_picture);
    }

    public void tapOnProfilePicture(){
        By picture = MobileBy.xpath("//XCUIElementTypeOther[@name=\"Bonus Balance view-balance-button ****** Compagnon Joana Sol Del Valle Total points 2120 pts Next goal Mentor 5000 pts\"]/XCUIElementTypeOther[3]");
        By profile_picture = MobileBy.xpath("//XCUIElementTypeOther[@name=\"Bonus Balance view-balance-button ****** Compagnon "+getUserName()+" Total points 2120 pts Next goal Mentor 5000 pts\"]/XCUIElementTypeOther[3]");
        tap(profile_picture);
    }

    public void tapOnYesTakeAPhoto(){
        By button = MobileBy.xpath("(//XCUIElementTypeOther[@name=\"btn-take-photo\"])[3]");
        tap(button);
    }

    public void tapOnPhotoGallery(){
        By button = MobileBy.xpath("(//XCUIElementTypeOther[@name=\"modal-selector-item-0\"])[2]");
        tap(button);
    }

    public void selectPhotoFromGallery(){
        By keepSelected = MobileBy.xpath("(//XCUIElementTypeOther[@name=\"Horizontal scroll bar, 1 page\"])[2]");

        if(isDisplayed(keepSelected)){
            tap(keepSelected);
        }

        By recentPhotos = MobileBy.AccessibilityId("Recents");
        tap(recentPhotos);

        By photoSelected = MobileBy.xpath("//XCUIElementTypeApplication[@name=\"Mafao\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[4]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeCollectionView/XCUIElementTypeCell[9]");
        tap(photoSelected);

        By choose = MobileBy.xpath("//XCUIElementTypeStaticText[@name=\"Choose\"]");
        waitUntilIsDisplayed(choose);
        tap(choose);
    }

    public void tapOnChangeAvatar(){
        tap(changeAvatarBtn);
    }

    public boolean checkUpdatePhotoSuccessfully(){
        return isDisplayed(updateAvatarSuccessMsg);
    }

    public void tapsOnLocation() {
        tap(locationBtn);
    }

    public boolean checkLocationIsNotActivated() {
        return isDisplayed(MobileBy.xpath("//XCUIElementTypeStaticText[@name=\"Allow “Mafao” to use your location?\"]"));
    }

    public void tapOnAllowsMafaoToUseLocation() {
        tap(MobileBy.AccessibilityId("Allow While Using App"));
    }

    public boolean checkLocationIsActivated() {
        return isDisplayed(MobileBy.AccessibilityId("Your location has been activated"));
    }
}

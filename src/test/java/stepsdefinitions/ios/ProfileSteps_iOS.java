package stepsdefinitions.ios;

import io.appium.java_client.AppiumDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.ios.ProfilePage_iOs;
import stepsdefinitions.HooksSteps;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProfileSteps_iOS {

    private final AppiumDriver driver = HooksSteps.getDriver();
    private final ProfilePage_iOs profilePage = new ProfilePage_iOs(driver);

    @And("clicks on log out")
    public void clicksOnLogOut() {
        profilePage.scrollDown();
        profilePage.scrollDown();
        profilePage.tapOnLogOutBtn();
        profilePage.confirmLogOutBtn();
    }

    @When("taps on settings")
    public void tapsOnSettings() {
        profilePage.tapOnSettingsBtn();
    }

    @And("taps on Secret code to change it")
    public void tapsOnSecretCodeToChangeIt() {
        profilePage.tapOnChangeSecretCode();
    }

    @And("taps on Change my secret code")
    public void tapsOnChangeMySecretCode() {
        profilePage.tapOnChangeMySecretCode();
    }

    @And("^enters the new secret code twice$")
    public void entersTheNew_secret_codeTwice() {
        profilePage.enterANewSecretCodeTwice("random");
    }

    @And("the pincode was successfully changed")
    public void resetPincodeSuccessfulMessageIsDisplayed() {
        assertTrue(profilePage.successfulReset());
        profilePage.tapOnLoginNow();
    }

    @And("^logins again with the new secret code$")
    public void loginsAgainWithTheNewSecretCode() {
        profilePage.loginWithNewSecretCode("random");
    }

    @Then("reset pincode to default secret code")
    public void resetPincodeToDefaultSecretCode() {
        profilePage.resetSecretCodeToDefault();
    }

    @And("enters the current secret code")
    public void entersAValidSecretCode() {
        profilePage.enterCurrentSecretCode();
    }

    @And("taps on profile option")
    public void tapsOnProfileOption() {
        profilePage.tapOnProfileOption();
    }

    @And("changes the display name")
    public void changesTheDisplayName() {
        profilePage.changeDisplayName();
    }

    @Then("the display name was successfully changed")
    public void theDisplayNameWasSuccessfullyChanged() {
        assertTrue(profilePage.checkDisplayNameHasChanged());
    }

    @When("taps on notifications")
    public void tapsOnNotifications() {
        profilePage.tapOnNotifications();
    }

    @Then("all the notifications received are displayed")
    public void allTheNotificationsReceivedAreDisplayed() {
        profilePage.goBackBtn();
    }

    @And("clicks on My Shop option")
    public void clicksOnMyShopOption() {
        profilePage.tapOnMyShopOption();
    }

    @Then("seller dashboard from Odoo view is displayed successfully")
    public void sellerDashboardFromOdooViewIsDisplayedSuccessfully() {
        assertTrue(profilePage.checkSellerDashboardIsDisplayed());
    }

    @And("taps on backup account option")
    public void tapsOnBackupAccountOption() {
        profilePage.scrollDown();
        profilePage.tapOnBackupAccount();
    }

    @And("taps backup button to confirm the backup")
    public void tapsBackupButtonToConfirmTheBackup() {
        profilePage.tapOnConfirmBackup();
    }

    @Then("backup success message is displayed")
    public void backupSuccessMessageIsDisplayed() {
        assertTrue(profilePage.checkBackupSuccessMessage());
        profilePage.tapOnReturnFromBackUp();
    }

    @When("display name shows correctly")
    public void displayNameShowsCorrectly() {
        assertTrue(profilePage.checkDisplayedName(), "Display name is not displayed correctly");
    }

    @Then("profile picture is displayed correctly")
    public void profilePictureIsDisplayedCorrectly() {
        assertTrue(profilePage.checkProfilePicture(), "Profile picture is not displayed correctly");
    }

    @When("taps on profile picture")
    public void tapsOnProfilePicture() {
        profilePage.tapOnProfilePicture();
    }

    @And("tap on Yes, take a photo button")
    public void tapOnYesTakeAPhotoButton() {
        profilePage.tapOnYesTakeAPhoto();
    }

    @And("tap on Photo Gallery option")
    public void tapOnPhotoGalleryOption() {
        profilePage.tapOnPhotoGallery();
    }

    @And("selects a photo from gallery")
    public void selectsAPhotoFromGallery() {
        profilePage.selectPhotoFromGallery();
    }

    @And("tap on change avatar to confirm")
    public void tapOnChangeAvatarToConfirm() {
        profilePage.tapOnChangeAvatar();
    }

    @Then("profile picture is updated correctly")
    public void profilePictureIsUpdatedCorrectly() {
        assertTrue(profilePage.checkUpdatePhotoSuccessfully(), "Profile picture not updated correctly");
    }

    @And("taps on location")
    public void tapsOnLocation() {
        profilePage.tapsOnLocation();
    }

    @And("the location is not activated")
    public void theLocationIsNotActivated() {
        assertTrue(profilePage.checkLocationIsNotActivated(), "Location is already activated");
    }

    @And("allows Mafao to use location")
    public void allowsMafaoToUseLocation() {
        profilePage.tapOnAllowsMafaoToUseLocation();
    }

    @Then("your location has been activated message is displayed")
    public void yourLocationHasBeenActivatedMessageIsDisplayed() {
        assertTrue(profilePage.checkLocationIsActivated(), "Location is not activated");
    }
}

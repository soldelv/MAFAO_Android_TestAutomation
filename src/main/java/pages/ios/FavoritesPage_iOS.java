package pages.ios;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;

import java.util.List;

import static apis.MafaoAPIs.getAPIAlertsQuantity;
import static database.ProductQuery.getFirstProductFromMarketplace;
import static utils.CommonMethods.holdOn;
import static utils.CommonMethods.print;

public class FavoritesPage_iOS extends BasePage_iOS {
    public FavoritesPage_iOS(AppiumDriver driver) {
        super(driver);
    }

    By favoriteBtn = MobileBy.AccessibilityId("favorite-button");
    By createAlertBtn = MobileBy.AccessibilityId("Create an alert");
    By alertCreated = MobileBy.AccessibilityId("Alert created");
    By deleteAlertBtn = MobileBy.AccessibilityId("Delete");
    By confirmDeletion = MobileBy.AccessibilityId("Confirm");
    By deletionConfirmedMessage = MobileBy.AccessibilityId("Alert has been deleted successfully");
    By alertCreationConfirmMessage = MobileBy.AccessibilityId("Alert created successfully");

    By listProducts = MobileBy.xpath("//android.view.ViewGroup[contains(@content-desc, 'favorite-button')]");

    public void tapFavoriteFromMarketplace1(String productNumber){
        List<MobileElement> elements = getElements(listProducts);
        tap(elements.get(0));
        tap(elements.get(1));
        tap(elements.get(2));
    }

    public boolean checkFavoritesIsNotEmpty(){
        By product = MobileBy.xpath("//android.view.ViewGroup[contains(@content-desc,'product-')]");
        return isDisplayed(product);
    }

    public void tapOnProduct(String productName){
        By product = MobileBy.AccessibilityId("product-"+productName);
        tap(product);
    }

    public void tapFavoriteFromProduct(){
        tap(getElement(favoriteBtn));
    }

    public void tapCreateAnAlert(){
        tap(createAlertBtn);
    }

    public void tapOnAlerts(){
        By alertBtn = MobileBy.AccessibilityId("Alerts ("+ getAPIAlertsQuantity()+")");
        tap(alertBtn);
    }

    public boolean checkAlertCreatedBtnIsDisplayed(){
        return isDisplayed(alertCreated);
    }

    public boolean alertCreatedSuccessfullyMessageIsDisplayed(){
        return isDisplayed(alertCreationConfirmMessage);
    }

    public boolean checkAlertIsDisplayed(String keyword){
        By alert = MobileBy.xpath("//XCUIElementTypeOther[contains(@name,'"+keyword+"')]");
        return isDisplayed(alert);
    }

    public void tapOnDeleteAlert(){
        tap(deleteAlertBtn);
    }

    public void confirmDeletionAlert(){
        tap(confirmDeletion);
    }

    public boolean checkAlertIsDeleted(){
        return isDisplayed(deletionConfirmedMessage);
    }


    public boolean checkProductIsDisplayed(String productName){
        holdOn(200);
        By product = MobileBy.xpath("//XCUIElementTypeOther[contains(@name,'product-"+productName+"')]");
        return isDisplayed(product);
    }

    public boolean checkFirstProductIsDisplayed(){
        String product_name = getFirstProductFromMarketplace().getDisplay_name();
        return checkProductIsDisplayed(product_name);
    }

    public void tapFavoriteFromMarketplace(String productName){
        By product = MobileBy.AccessibilityId("product-"+productName);
        MobileElement favorite = findElementFromElement(getElement(product),favoriteBtn);
        tap(favorite);
    }

    public void tapToRemove(){
        By listFavorites = MobileBy.xpath("//android.view.ViewGroup[contains(@content-desc,'favorite-button')]");
        tap(listFavorites);
        print("Removed first element of favorites list");
    }

    public void removeAllProductsFromFavorites(){
        try{while (checkFavoritesIsNotEmpty()){
            tapToRemove();
        }}catch(NoSuchElementException | StaleElementReferenceException e){
            print("No more elements on the list");
        }
    }

}

package pages.android;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import java.util.List;

import static utils.CommonMethods.print;

public class FavoritesPage_Android extends BasePage_Android {
    public FavoritesPage_Android(AppiumDriver driver) {
        super((AndroidDriver<WebElement>) driver);
    }

    By favoriteBtn = MobileBy.AccessibilityId("favorite-button");
    By listProducts = MobileBy.xpath("//android.view.ViewGroup[contains(@content-desc, 'favorite-button')]");

    public void tapFavoriteFromMarketplace1(String productNumber){
        List<MobileElement> elements = getElements(listProducts);
        tap(elements.get(0));
        tap(elements.get(1));
        tap(elements.get(2));
    }

    public boolean checkFavoritesIsNotEmpty(){
        boolean displayed = false;
        try {
            By product = MobileBy.xpath("//android.view.ViewGroup[contains(@content-desc,'product-')]");
            displayed = isDisplayed(getElement(product));
        }catch(NoSuchElementException e){
            print("Favorite list is empty");
        }
        return displayed;
    }

    public void tapOnProduct(String productName){
        By product = MobileBy.AccessibilityId("product-"+productName);
        tap(getElement(product));
    }

    public void tapFavoriteFromProduct(){
        tap(getElement(favoriteBtn));
    }

    public boolean checkProductIsOnFavorites(String productName){
        By product = MobileBy.AccessibilityId("product-"+productName);
        return isDisplayed(getElement(product));
    }

    public void tapFavoriteFromMarketplace(String productName){
        By product = MobileBy.AccessibilityId("product-"+productName);
        MobileElement favorite = findElementFromElement(getElement(product),favoriteBtn);
        tap(favorite);
    }

    public void tapToRemove(){
        By listFavorites = MobileBy.xpath("//android.view.ViewGroup[contains(@content-desc,'favorite-button')]");
        tap(getElement(listFavorites));
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

package pages.android;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.List;

import static utils.CommonMethods.holdOn;
import static utils.CommonMethods.print;

public class HomePage_Android extends BasePage_Android {
    public HomePage_Android(AppiumDriver driver) {
        super((AndroidDriver<WebElement>) driver);
    }
    By homeIcon = MobileBy.AccessibilityId("home");
    By favoritesIcon = MobileBy.AccessibilityId("favorites");

    By ordersIcon = MobileBy.AccessibilityId("orders");
    By bonusIcon = MobileBy.AccessibilityId("bonus");

    By profileIcon = MobileBy.AccessibilityId("profile");

    /* CATEGORIES */
    By superBonusCat = MobileBy.xpath("//android.widget.TextView[contains(@text, 'Super Bonus')]");

    By nouveauCat = MobileBy.xpath("//android.widget.TextView[contains(@text, 'Nouveau')]");
    By sportCat = MobileBy.xpath("//android.widget.TextView[contains(@text, 'Sport')]");
    By seTermineCat = MobileBy.xpath("//android.widget.TextView[contains(@text, 'Se termine bientot')]");
    By bonusAtteintCat = MobileBy.xpath("//android.widget.TextView[contains(@text, 'Bonus atteint ou presque')]");
    By hypermarcheCat = MobileBy.xpath("//android.widget.TextView[contains(@text, 'Hypermarché')]");
    By equipementCat = MobileBy.xpath("//android.widget.TextView[contains(@text, 'Équipement')]");
    By fetesCat = MobileBy.xpath("//android.widget.TextView[contains(@text, 'Fêtes')]");
    By favoritesEmpty = MobileBy.xpath("//android.widget.TextView[contains(@text,'Can not find any favorite products')]");

    /* PRODUCTS */
    By listProducts = MobileBy.xpath("//android.view.ViewGroup[contains(@content-desc, 'product-')]");
    By listProductsFav = MobileBy.xpath("//android.view.ViewGroup[contains(@content-desc, 'favorite-button')]");
    //By backIconBtn = MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.widget.TextView");
    By backIconBtn = MobileBy.xpath("//android.widget.TextView[1]");
    public void tapOnBackIcon(){
        print("Before tap back icon");
        tap(backIconBtn);
        print("already tap on back icon");
        //getFistElement(backIconBtn).click();
    }
    public boolean checkOnMarketplace(){
        return isDisplayed(getElement(searchBar));
    }

    public void tapOnHome()  {
        tap(homeIcon);
    }

    public void tapOnFavorites()  {
        tap(favoritesIcon);
    }

    public void tapOnOrders(){
        tap(ordersIcon);
    }

    public void tapOnBonus(){
        holdOn(3000);
        tap(bonusIcon);
    }

    public void tapOnProfile(){
        tap(profileIcon);
    }
    public void chooseAndTapFavoriteFromMarketplace(){
        List<MobileElement> elements = getElements(listProductsFav);
        tap(elements.get(0));
        tap(elements.get(1));
        tap(elements.get(2));
    }

    public void tapsOnProduct(){
        print("Hardcoding product");
        By product = MobileBy.AccessibilityId("product-Inflatables for swimming pool");
        tap(product);
    }

    public void tapOnProduct(String productName){
        By product = MobileBy.AccessibilityId("product-"+productName);
        tap(getElement(product));
        holdOn(800);
    }

    public boolean checkProductsAreListed(String keyword){
        try{
            By products = MobileBy.xpath("//android.view.ViewGroup[contains(@content-desc,'"+keyword+"')]");
            List elements = getElements(products);
            return (elements.size()>0);
        }catch(NoSuchElementException e){
            return false;
        }
    }

    public void tapOnCategory(String categoryName){
        switch (categoryName) {
            case "Super Bonus" -> tap(superBonusCat);
            case "Nouveau" -> tap(nouveauCat);
            case "Sport" -> tap(sportCat);
            case "Hypermarché"-> tap(hypermarcheCat);
            case "Équipement"-> tap(equipementCat);
            case "Fêtes"-> tap(fetesCat);
            default -> {
                print("Invalid category name");
            }
        }
        holdOn(5);
    }

}

package pages.ios;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import mafao.objects.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import java.util.List;

import static database.ProductQuery.*;
import static utils.CommonMethods.holdOn;
import static utils.CommonMethods.print;

public class HomePage_iOS extends BasePage_iOS {
    public HomePage_iOS(AppiumDriver driver) {
        super(driver);
    }
    By homeIcon = MobileBy.AccessibilityId("home");
    By favoritesIcon = MobileBy.AccessibilityId("favorites");
    By ordersIcon = MobileBy.AccessibilityId("orders");
    By bonusIcon = MobileBy.AccessibilityId("******");
    By retiradaIcon = MobileBy.AccessibilityId("retirada");
    By profileIcon = MobileBy.AccessibilityId("profile");
    By notifBtn = MobileBy.AccessibilityId("notif-button");
    By withdrawalIcon = MobileBy.AccessibilityId("withdrawal");

    /** CATEGORIES **/
    By resultsTitle = MobileBy.AccessibilityId("Results");
    By noProductsFound = MobileBy.xpath("//XCUIElementTypeStaticText[@name=\"No products found\"]");
    By auchanLogoFilter = MobileBy.xpath("(//XCUIElementTypeOther[@name=\"\uEB2A \uEE60 search-bar\"])[3]/XCUIElementTypeOther[2]");
    By superBonusIconCat = MobileBy.AccessibilityId("Super bonus");
    By newIconCat = MobileBy.AccessibilityId("New");
    By endsSoonIconCat = MobileBy.AccessibilityId("Ends soon");
    By bonusReachedCat = MobileBy.AccessibilityId("Bonus reached");
    By sportCat = MobileBy.AccessibilityId("Sport");
    By supermarketCat = MobileBy.AccessibilityId("Supermarket");
    By equipmentCat = MobileBy.AccessibilityId("Equipment");
    By holidaysCat = MobileBy.AccessibilityId("Holidays");

    /** PRODUCTS */
    By listProductsFav = MobileBy.xpath("//android.view.ViewGroup[contains(@content-desc, 'favorite-button')]");
    public static Product productToTest;

    public void tapOnBackIcon(){
        print("Before tap back icon");
        tapOnBackBtn();
        print("already tap on back icon");
    }
    public boolean checkOnMarketplace(){
        return isDisplayed(searchBar);
    }

    public void tapOnHome()  {
        tap(homeIcon);
    }

    public void tapOnWithdrawal()  {
        tap(withdrawalIcon);
    }

    public void tapOnFavorites()  {
        tap(favoritesIcon);
    }

    public void tapOnOrders(){
        tap(ordersIcon);
    }

    public void tapOnBonus(){
        tap(bonusIcon);
    }

    public void tapOnNotificationsBtn(){
        tap(notifBtn);
    }

    public void tapOnRetirada(){
        tap(retiradaIcon);
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
        scrollDownUntilIsDisplayed(product);
        tap(product);
    }

    public boolean checkProductsAreListed(String keyword){
        try{
            By products = MobileBy.xpath("//XCUIElementTypeOther[contains(@name, '"+keyword+"')]");
            List elements = getElements(products);
            return (elements.size()>0);
        }catch(NoSuchElementException e){
            return false;
        }
    }

    public void tapOnCategory(String categoryName){
        switch (categoryName) {
            case "Super bonus" -> tap(superBonusIconCat);
            case "New" -> tap(newIconCat);
            case "Ends soon" -> tap(endsSoonIconCat);
            case "Bonus reached"-> tap(bonusReachedCat);
            case "Sport"-> tap(sportCat);
            case "Supermarket" -> tap(supermarketCat);
            case "Equipment" -> tap(equipmentCat);
            case "Holidays"-> tap(holidaysCat);
            default -> {
                print("Invalid category name");
            }
        }
        holdOn(5);
    }

    public boolean checkCategoryProductsAreDisplayed(String category){
        holdOn(200);
        if(isDisplayed(noProductsFound)){
            print("Category/Filter "+category+" without products associated");
            return true;
        }
        else if(isDisplayed(auchanLogoFilter)){
            print("Products associated to Auchan Supermarket");
            return true;
        }
        else return isDisplayed(resultsTitle);
    }

    public void tapAnyProductFromTheMarketplace(){
        productToTest = getMarketplaceProducts().get(0);
        String product_name = productToTest.getDisplay_name();
        By product = MobileBy.xpath("(//XCUIElementTypeOther[@name='product-"+product_name+"'])[2]");
        scrollDownUntilIsDisplayed(product);
        tap(product);
    }

    public void tapsOnLowestPriceProduct(){
        String product_name = getProductWithLowestPrice().getDisplay_name();
        By product = MobileBy.xpath("(//XCUIElementTypeOther[@name=\"product-"+product_name+"\"])[2]");
        print("locator - "+product);
        scrollDownUntilIsDisplayed(product);
        tap(product);
    }

    public void tapsOnFirstProductMarketplace(){
        String product_name = getFirstProductFromMarketplace().getDisplay_name();
        By product = MobileBy.xpath("(//XCUIElementTypeOther[@name=\"product-"+product_name+"\"])[2]");
        print("locator - "+product);
        scrollDownUntilIsDisplayed(product);
        tap(product);
    }

}

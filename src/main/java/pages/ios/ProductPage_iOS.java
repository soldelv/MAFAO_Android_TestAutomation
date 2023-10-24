package pages.ios;

import mafao.objects.Product;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;

import java.util.Objects;

import static apis.MafaoAPIs.*;
import static database.ProductQuery.*;
import static org.testng.AssertJUnit.fail;
import static pages.ios.HomePage_iOS.productToTest;
import static utils.CommonMethods.*;

public class ProductPage_iOS extends BasePage_iOS {
    public ProductPage_iOS(AppiumDriver driver) {
        super(driver);
    }

    By favoriteBtn = MobileBy.xpath("(//XCUIElementTypeOther[@name='favorite-button'])[3]");
    By plusBtn = MobileBy.AccessibilityId("plus-button");
    By minusBtn = MobileBy.AccessibilityId("minus-button");
    By sellerInfo = MobileBy.AccessibilityId("vendor-info");
    By reportThisProductBtn = MobileBy.xpath("(//XCUIElementTypeOther[@name='Report this product'])[2]");
    By reportMessage = MobileBy.xpath("//XCUIElementTypeStaticText[@name='Thank you, We will check and  review your report and advise you of our decision.']");
    By okReportBtn = MobileBy.xpath("(//XCUIElementTypeOther[@name='button-ok'])[2]");
    By callBtn = MobileBy.xpath("(//XCUIElementTypeOther[@name='call-button'])[2]");
    By shareBtn = MobileBy.xpath("(//XCUIElementTypeOther[@name='share-button'])[3]");
    By similarProductsSection = MobileBy.AccessibilityId("Similar products");

    /** SELLER INFO */
    //Hardcoded to Gold
    By auchanLogo = MobileBy.xpath("//XCUIElementTypeOther[@name='Gold']/XCUIElementTypeOther[1]/XCUIElementTypeImage");
    //Hardcoded
    By titleAuchan = MobileBy.xpath("//XCUIElementTypeStaticText[@name='Auchan Mermoz, Senegal']");
    By bonusDistributed = MobileBy.xpath("//XCUIElementTypeStaticText[@name='Bonus distributed']");
    By reviews = MobileBy.xpath("//XCUIElementTypeStaticText[@name='Product reviews']");
    By dispatch = MobileBy.xpath("//XCUIElementTypeStaticText[@name='Dispatch']");
    By sales = MobileBy.xpath("//XCUIElementTypeStaticText[@name='Sales']");
    By sellerSince = MobileBy.xpath("//XCUIElementTypeStaticText[contains(@name,'Seller since')]");


    public void tapOnFavoriteBtn()  {
        tap(favoriteBtn);
    }
    public void tapOnPlusBtn(){
        tap(plusBtn);
        print("Tapped on plus icon");
        holdOn(500);
    }

    public void tapOnMinusBtn(){
        tap(minusBtn);
        print("Tapped on minus icon");
        holdOn(500);
    }
    public void tapOnAddToCart(String productName){
        String list_price = getProductDetailByName(productName).getFormattedListPrice();
        By addToCartBtn = MobileBy.AccessibilityId("Add to cart Amount: "+list_price+" Fcfa");
        tap(addToCartBtn);
        print("Tapped on Add to cart");
        holdOn(500);
    }

    public void tapOnAddToCartBtn(){
        String list_price = getMarketplaceProducts().get(0).getFormattedListPrice();
        By addToCartBtn = MobileBy.AccessibilityId("Add to cart Amount: "+list_price+" Fcfa");
        tap(addToCartBtn);
        print("Tapped on Add to cart");
        holdOn(500);
    }

    public void tapsOnGiveYourOpinion(){
        By reviewButton = MobileBy.xpath("(//XCUIElementTypeOther[@name=\"Give your opinion\"])[1]");
        scrollDownUntilIsDisplayed(reviewButton);
        tap(reviewButton);
    }

    public void tapOnAddToCartBtnLowestPrice(){
        Double price_without_format = getProductWithLowestPrice().getList_price();
        By addToCartBtn = MobileBy.AccessibilityId("Add to cart Amount: "+price_without_format+" Fcfa");
        tap(addToCartBtn);
        print("Tapped on Add to cart");
        holdOn(500);
    }

    public void tapOnSellerInfo(){
        tap(sellerInfo);
    }
    public void tapOnReportThisProductBtn(){
        tap(reportThisProductBtn);
    }

    public void fillReportProductForm(){
        By reason = MobileBy.xpath("(//XCUIElementTypeOther[@name='others'])[3]");
        By descriptionField = MobileBy.xpath("//XCUIElementTypeOther[@name='sexually_inappropriate violence offensive others Horizontal scroll bar, 1 page Vertical scroll bar, 1 page Your comment validate-button validate-button']/XCUIElementTypeOther[2]/XCUIElementTypeTextView");
        By validateBtn = MobileBy.xpath("(//XCUIElementTypeOther[@name='validate-button'])[2]");
        String description = "Test description";
        waitUntilIsDisplayed(reason);
        tap(reason);
        type(description,descriptionField);
        tap(validateBtn);
        tap(validateBtn);
    }

    public boolean checkReportMessageIsDisplayed(){
        return isDisplayed(reportMessage);
    }
    public void tapOnOkReportBtn(){
        tap(okReportBtn);
    }

    public boolean checkSellerInfo(){
        return isDisplayed(titleAuchan) && isDisplayed(sellerSince) && isDisplayed(auchanLogo)
                && isDisplayed(bonusDistributed) && isDisplayed(reviews) && isDisplayed(dispatch)
                && isDisplayed(sales);
    }
    public void tapOnCallBtn(){
        tap(callBtn);
    }

    public void checkProductDetailView(){
        double oldPriceNum= getProductDetailAPIById(productToTest.getId()).getOldPrice();

        By product_name = MobileBy.AccessibilityId(productToTest.getDisplay_name());
        By productDescription = MobileBy.xpath("//XCUIElementTypeStaticText[@name=\""+productToTest.getDescription_sale()+"\"]");
        By inStock = MobileBy.xpath("//XCUIElementTypeStaticText[@name=\"In stock\"]");
        //By ratingsAndReviews = MobileBy.AccessibilityId("Rating and review 3.9/5");
        By oldPrice = MobileBy.xpath("//XCUIElementTypeStaticText[@name=\""+oldPriceNum+" Fcfa\"]");
        By currentPrice = MobileBy.xpath("//XCUIElementTypeStaticText[@name='"+productToTest.getFormattedListPrice()+" Fcfa']");
        By expirationDate = MobileBy.AccessibilityId(" \n" +
                " Offer ends on  "+getDateMonthInLetters(getProductDetailAPIById(productToTest.getId()).getExpiration_date())+"");
        //By comments = MobileBy.AccessibilityId("See all 12 comments");
        By withdrawals_returns = MobileBy.AccessibilityId("Withdrawals and returns");

        if(isDisplayed(favoriteBtn)){ print("Favorite button is displayed");
        }else{
            fail("Favorite button is not displayed");
        }
        if(isDisplayed(shareBtn)){ print("Share button is displayed");
        }else{
            fail("Share button is not displayed");
        }
        if(isDisplayed(product_name)){ print("Product title is displayed");
        }else{
            fail("Product title is not displayed");
        }
        if(isDisplayed(productDescription)){ print("Product description is displayed");
        }else{
            fail("Product description is not displayed");
        }
        if(isDisplayed(inStock)){ print("InStock displayed");
        }else{
            fail("Product description is not displayed");
        }
        /*if(isDisplayed(ratingsAndReviews)){ print("Ratings And Reviews are displayed");
        }else{
            fail("Ratings And Reviews are not displayed");
        }*/
        if(isDisplayed(oldPrice)){ print("Old Price is displayed");
        }else{
            fail("Old Price is not displayed");
        }
        if(isDisplayed(currentPrice)){ print("Current Price is displayed");
        }else{
            fail("Current Price is not displayed");
        }
        if(isDisplayed(expirationDate)){
            print("expirationDate is displayed");
        }else{
            fail("expirationDate is not displayed");
        }
        if(isDisplayed(withdrawals_returns)){
            print("Withdrawals and returns is displayed");
        }else{
            fail("Withdrawals and returns is not displayed");
        }
        /*if(isDisplayed(comments)){ print("comments are displayed");
        }else{
            fail("comments are not displayed");
        }*/
        if(getAPISimilarProductsQuantityById(productToTest.getId())!=0){
            scrollDown();
            if(isDisplayed(similarProductsSection)){ print("Similar products are displayed");
            }else{
                fail("Similar products are not displayed");
            }
        }

    }

    public boolean similarProductsSectionIsDisplayed(){
        scrollDownUntilIsDisplayed(similarProductsSection);
        return isDisplayed(similarProductsSection);
    }

    public void checkSimilarProductsAreDisplayed(){
        Product product = getFirstProductFromMarketplace();
        Product[] similarProductsList = getAPISimilarProductsById(String.valueOf(product.getId()));

        for (Product similarProduct : similarProductsList) {
            By locator = MobileBy.xpath("(//XCUIElementTypeOther[@name='product-"+similarProduct.getDisplay_name()+"'])[2]");
            if(!Objects.equals(similarProduct.getDisplay_name(), product.getDisplay_name())){
                scrollDownUntilIsDisplayed(locator);
                if(isDisplayed(locator)){
                    print("Product: "+similarProduct.getDisplay_name()+" is displayed");
                }else{
                    fail("Product: "+similarProduct.getDisplay_name()+" is not displayed");
                }
            }
        }
    }

}

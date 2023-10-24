package apis;
import com.google.gson.*;
import mafao.objects.Alert;
import mafao.objects.Order;
import mafao.objects.Product;
import mafao.objects.Seller;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.net.URI;
import java.util.Objects;

import static constants.Constants.*;
import static database.UserQuery.getUserId;
import static utils.CommonMethods.parseToJsonObject;
import static utils.CommonMethods.print;

public class MafaoAPIs {
    private static final String apiOTPCode =URL_STG+"meveo/rest/TestOTP";
    private static final String listProducts = URL_STG+"meveo/rest/odooProductsAPI";
    private static final String listProductDetail = URL_STG+"meveo/rest/getProductDetail/";
    private static final String listSimilarProducts = URL_STG+"meveo/rest/listSimilarProducts/";
    private static final String listAlerts = URL_STG+"meveo/rest/listAlerts";
    private static final String userByID = URL_STG+"meveo/rest/odooSellerById/";
    private static final String listPurchases = URL_STG+"meveo/rest/purchases-by-wallet/"+WALLET_UUID;


    public static String login(String userName, String password) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String otpCode = "";
        try{
        HttpUriRequest httpget = RequestBuilder.post()
                .setUri(new URI("https://staging.jips.io/auth/realms/meveo/protocol/openid-connect/token"))
                .addParameter("client_id", "meveo-web")
                .addParameter("username", userName)
                .addParameter("password", password)
                .addParameter("grant_type", "password")
                .addParameter("client_secret", "afe07e5a-68cb-4fb0-8b75-5b6053b07dc3")
                .build();

            Gson gson = new Gson();
            CloseableHttpResponse response = httpclient.execute(httpget);
            String responseStr=otpCode = EntityUtils.toString(response.getEntity());
            JsonObject jsonObject = gson.fromJson(responseStr, JsonObject.class);

            // Print the access token
            print("access_token for api generated");
            return jsonObject.get("access_token").getAsString();
        }catch(Exception e){
            print(e.getMessage());
            return otpCode;
        }
    }

    public static String getAPIOTPCode(String mobileNumber) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String otpCode = "";

        print("USING THIS MOBILE NUMBER: --> "+mobileNumber);

        HttpUriRequest httpget = RequestBuilder.get()
                .setUri(new URI(apiOTPCode))
                .addParameter("destinationNumber", mobileNumber)
                .build();

        try{
            CloseableHttpResponse response = httpclient.execute(httpget);
            otpCode = EntityUtils.toString(response.getEntity());
            print("OTP code sent: " + otpCode);
            return otpCode;
        }catch(Exception e){
            print(e.getMessage());
            return otpCode;
        }
    }

    public static Seller getAPIUserInfoByID(String user_type) {
        String userId = getUserId(user_type);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        Seller sellerFound = null;
        try{
            HttpUriRequest httpget = RequestBuilder.get()
                    .setUri(new URI(userByID+userId))
                    .addHeader("Authorization","Bearer "+login(FULL_MOBILE_NUMBER, SECRET_CODE))
                    .build();

            CloseableHttpResponse response = httpclient.execute(httpget);
            Gson gson = new Gson();
            sellerFound = gson.fromJson(parseToJsonObject(response), Seller .class);
            print(String.valueOf(sellerFound));

            return sellerFound;
        }catch(Exception e){
            print(e.getMessage());
            return sellerFound;
        }
    }

    public static Product getAPIProductInfoByName(String productName) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        Product productFound = null;
        try{
            HttpUriRequest httpget = RequestBuilder.get()
                    .setUri(new URI(listProducts))
                    .addHeader("Authorization","Bearer "+login(FULL_MOBILE_NUMBER, SECRET_CODE))
                    .addParameter("currentPage", "1")
                    .build();

            CloseableHttpResponse response = httpclient.execute(httpget);
            Gson gson = new Gson();

            JsonArray productsArray = parseToJsonObject(response).getAsJsonArray("rows");
            Product[]  products = gson.fromJson(productsArray, Product[] .class);

            for (Product product : products) {
                if(Objects.equals(product.getDisplay_name(), productName)){
                    productFound = product;
                }
            }
            return productFound;
        }catch(Exception e){
            print(e.getMessage());
            return productFound;
        }
    }

    public static Product getProductDetailAPIById(int productId) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        Product productFound = null;
        try{
            HttpUriRequest httpget = RequestBuilder.get()
                    .setUri(new URI(listProductDetail+productId))
                    .addHeader("Authorization","Bearer "+login(FULL_MOBILE_NUMBER, SECRET_CODE))
                    .build();

            CloseableHttpResponse response = httpclient.execute(httpget);
            String jsonString = EntityUtils.toString(response.getEntity());
            productFound= new Gson().fromJson(jsonString, Product.class);
            return productFound;

        }catch(Exception e){
            print(e.getMessage());
            return productFound;
        }
    }
    public static Product[] getAPISimilarProductsById(String productId) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try{
            HttpUriRequest httpget = RequestBuilder.get()
                    .setUri(new URI(listSimilarProducts+productId))
                    .addHeader("Authorization","Bearer "+login(FULL_MOBILE_NUMBER, SECRET_CODE))
                    .build();

            CloseableHttpResponse response = httpclient.execute(httpget);
            Gson gson = new Gson();

            JsonArray productsArray = parseToJsonObject(response).getAsJsonArray("rows");
            Product[]  products = gson.fromJson(productsArray, Product[] .class);

            if(products.length>0){
                return products;
            }else{
                return null;
            }

        }catch(Exception e){
            print(e.getMessage());
            return null;
        }
    }

    public static int getAPISimilarProductsQuantityById(int productId) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        int total = 0;
        try{
            HttpUriRequest httpget = RequestBuilder.get()
                    .setUri(new URI(listSimilarProducts+productId))
                    .addHeader("Authorization","Bearer "+login(FULL_MOBILE_NUMBER, SECRET_CODE))
                    .build();

            CloseableHttpResponse response = httpclient.execute(httpget);
            int result = parseToJsonObject(response).get("totalProducts").getAsInt();
            if(result>1){
                total = result;
            }
            return total;

        }catch(Exception e){
            print(e.getMessage());
            return total;
        }
    }

    public static String getAPIAlertsQuantity() {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String alertsQuantity = "";
        try{
            HttpUriRequest httpget = RequestBuilder.get()
                    .setUri(new URI(listAlerts))
                    .addHeader("Authorization","Bearer "+login(FULL_MOBILE_NUMBER, SECRET_CODE))
                    .build();

            CloseableHttpResponse response = httpclient.execute(httpget);
            String jsonString = EntityUtils.toString(response.getEntity());

            Alert[] alerts = new Gson().fromJson(jsonString, Alert[].class);

            alertsQuantity = String.valueOf(alerts.length);

            return alertsQuantity;
        }catch(Exception e){
            print(e.getMessage());
            return alertsQuantity;
        }
    }

    public static Order[] getAPIPurchasesBySeller() {

        CloseableHttpClient httpclient = HttpClients.createDefault();
        try{
            HttpUriRequest httpget = RequestBuilder.get()
                    .setUri(new URI(listPurchases))
                    .addHeader("Authorization","Bearer "+login(FULL_MOBILE_NUMBER, SECRET_CODE))
                    .build();

            CloseableHttpResponse response = httpclient.execute(httpget);
            Gson gson = new Gson();
            JsonArray ordersArray = parseToJsonObject(response).getAsJsonArray("result");
            Order[] orders = gson.fromJson(ordersArray, Order[] .class);

            return orders;

        }catch(Exception e){
            print(e.getMessage());
            return null;
        }
    }

    public static Order getAPILastOrderBySeller(){
        return getAPIPurchasesBySeller()[0];
    }

}

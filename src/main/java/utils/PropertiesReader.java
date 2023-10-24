package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static constants.Constants.*;
import static utils.CommonMethods.print;

public class PropertiesReader {

    public static Properties loadProperties(String environment) {
        Properties properties = new Properties();
        try {
            FileInputStream fis = new FileInputStream(DIRECTORY_ENV_CONFIG+environment);
            properties.load(fis);
            fis.close();
            return properties;
        } catch (IOException e) {
            e.printStackTrace();
            return properties;
        }
    }
    public static Properties getStagingProperties(){
        return loadProperties(ENV_CONFIG_STG);
    }

    public static Properties getDevProperties(){
        return loadProperties(ENV_CONFIG_DEV);
    }

    public static Properties getQAProperties(){return loadProperties(ENV_CONFIG_QA);}

    /** QA PROPERTIES */
    public static String getEnvironment(){return getQAProperties().getProperty("environment");}

    /** STAGING PROPERTIES **/

    public static String getAndroidApp(){
        return getStagingProperties().getProperty("android_version");
    }

    public static String getIOSApp(){
        return getStagingProperties().getProperty("ios_version");
    }

    public static String getUrlStaging(){
        return getStagingProperties().getProperty("url");
    }

    public static String stgUserCountry(){
        return getStagingProperties().getProperty("country");
    }

    public static String stgUserMobile(){
        return getStagingProperties().getProperty("mobile_number");
    }
    public static String stgUserCountryCode(){
        return getStagingProperties().getProperty("country_code");
    }
    public static String stgUserSecretCode(){
        return getStagingProperties().getProperty("secret_code");
    }
    public static String stgUserID(){
        return getStagingProperties().getProperty("user_id");
    }
    public static String getWalletUuid(){
        return getStagingProperties().getProperty("wallet_uuid");
    }
    public static String qaPaypalEmail(){
        return getStagingProperties().getProperty("paypal_email");
    }
    public static String qaPaypalPassword(){
        return getStagingProperties().getProperty("paypal_password");
    }

    /** DEV PROPERTIES **/

    public static String getOdooUrl_Dev() {
        return getDevProperties().getProperty("odoo_url");
    }
    public static String getAdminEmail_Dev() {
        return getDevProperties().getProperty("admin_email");
    }

    public static String getAdminPassword_Dev() {
        return getDevProperties().getProperty("admin_password");
    }


    /** ODOO PROPERTIES **/
    public static String getOdooUrl_Stg() {
        return getStagingProperties().getProperty("odoo_url_stg");
    }

    public static String getAdminEmail_Stg() {
        return getStagingProperties().getProperty("admin_email");
    }

    public static String getAdminPassword_Stg() {
        return getStagingProperties().getProperty("admin_password");
    }

    /** DATABASE ODOO */
    public static String getOdooDB_URL_Stg() {
        return getStagingProperties().getProperty("ODOO_DB_URL");
    }
    public static String getOdooDB_Username_Stg() {
        return getStagingProperties().getProperty("ODOO_DB_USERNAME");
    }
    public static String getOdooDB_Password_Stg() {
        return getStagingProperties().getProperty("ODOO_DB_PASSWORD");
    }

}
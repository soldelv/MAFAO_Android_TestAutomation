package constants;

import utils.PropertiesReader;

import static utils.PropertiesReader.*;

public class Constants {

    public static final String DIRECTORY_ENV_APP = "/src/test/resources/apps/";
    public static final String DIRECTORY_IMAGES = System.getProperty("user.dir")+"/src/test/resources/product_images/";
    public static String ANDROID_APP_VERSION = PropertiesReader.getAndroidApp();
    public static String ANDROID_APP_PATH = System.getProperty("user.dir")+DIRECTORY_ENV_APP+ ANDROID_APP_VERSION;
    public static String IOS_APP_VERSION = PropertiesReader.getIOSApp();
    public static String IOS_APP_PATH = System.getProperty("user.dir")+DIRECTORY_ENV_APP+ IOS_APP_VERSION;
    public static String URL_STG = PropertiesReader.getUrlStaging();
    public static final int EXPLICIT_WAIT = 10;
    public static final String PROJECT_NAME = "Automation Test Suite Report - MAFAO";

    /** PLATFORMS **/
    public static final String MOBILE_ANDROID = "Android";
    public static final String ODOO_URL_STG = getOdooUrl_Stg();;
    public static final String ODOO_URL_DEV = getOdooUrl_Dev();;
    public static final String ENVIRONMENT = getEnvironment();

    /** ENVIRONMENT CONFIGURATION FILES **/
    public static final String DIRECTORY_ENV_CONFIG = "src/test/resources/config/";
    public static final String ENV_CONFIG_DEV = "dev_config.properties";
    public static final String ENV_CONFIG_STG = "stg_config.properties";
    public static final String ENV_CONFIG_QA = "qa_config.properties";

    /** STAGING - USER CREDENTIALS **/
    public static final String COUNTRY = stgUserCountry();
    public static final String MOBILE_NUMBER = stgUserMobile();
    public static final String COUNTRY_CODE = stgUserCountryCode();
    public static final String FULL_MOBILE_NUMBER = COUNTRY_CODE+MOBILE_NUMBER;
    public static final String SECRET_CODE = stgUserSecretCode();
    public static final String USER_ID = stgUserID();
    public static final String WALLET_UUID = getWalletUuid();

    /** PAYPAL - CREDENTIALS **/
    public static final String PAYPAL_EMAIL = qaPaypalEmail();
    public static final String PAYPAL_PASSWORD = qaPaypalPassword();

    /** ODOO - CREDENTIALS - STAGING **/

    public static final String ADMIN_EMAIL_STG = getAdminEmail_Stg();

    public static final String ADMIN_PASSWORD_STG = getAdminPassword_Stg();

    /** ODOO DATABASE */
    public static final String DB_URL = getOdooDB_URL_Stg();
    public static final String DB_USERNAME = getOdooDB_Username_Stg();
    public static final String DB_PASSWORD = getOdooDB_Password_Stg();


    /** ODOO - CREDENTIALS - DEV **/
    public static final String ADMIN_EMAIL_DEV = getAdminEmail_Dev();
    public static final String ADMIN_PASSWORD_DEV = getAdminPassword_Dev();
}

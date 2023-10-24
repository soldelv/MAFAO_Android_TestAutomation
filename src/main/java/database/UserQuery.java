package database;


import mafao.objects.Product;
import mafao.objects.Seller;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;

import static constants.Constants.FULL_MOBILE_NUMBER;

public class UserQuery {

    public static String getUserId(String user_type)  {
        String user_id = "";

        try {
            DatabaseConnection databaseConnection = new DatabaseConnection();
            Connection connection = databaseConnection.openConnection();

            String query = " SELECT id, state " +
                    " FROM res_partner rp " +
                    " WHERE phone_sanitized = '"+FULL_MOBILE_NUMBER+"'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {

                if(user_type == "buyer"){
                    if(Objects.equals(resultSet.getString("state"), "new")){
                        user_id = resultSet.getString("id");
                }
                }
                if(user_type == "seller"){
                    if(Objects.equals(resultSet.getString("state"), "approved")){
                        user_id = resultSet.getString("id");
                    }
                }
            }
            resultSet.close();
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return user_id;
    }

    public static int getSellerIDByProduct(int product_id)  {
        int marketplace_seller_id = 0;

        try {
            DatabaseConnection databaseConnection = new DatabaseConnection();
            Connection connection = databaseConnection.openConnection();

            String query = "SELECT marketplace_seller_id " +
                    "FROM product_template pt " +
                    "JOIN product_product pp on pp.product_tmpl_id = pt.id " +
                    "WHERE pp.id = "+ product_id;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            marketplace_seller_id = Integer.parseInt(resultSet.getString("marketplace_seller_id "));

            resultSet.close();
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return marketplace_seller_id;
    }

    public static Seller getSellerByProductId(int product_id)  {
        Seller seller = new Seller();

        try {
            DatabaseConnection databaseConnection = new DatabaseConnection();
            Connection connection = databaseConnection.openConnection();

            String query = "SELECT pl.marketplace_seller_id, pl.street, pl.city, pl.marketplace_seller_id, " +
                    "       rp.display_name, rc.\"name\" as country " +
                    " FROM pickup_location pl " +
                    " JOIN product_product pp ON pp.pickup_location_id = pl.id " +
                    " JOIN res_partner rp ON rp.id = pl.marketplace_seller_id " +
                    " JOIN sale_order_line sol ON sol.order_id = 295 " +
                    " JOIN res_country rc on rc.id = pl.country_id " +
                    " WHERE pp.id = "+ product_id;

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                seller.setDisplay_name(resultSet.getString("display_name"));
                seller.setId(Integer.parseInt(resultSet.getString("marketplace_seller_id")));
                seller.setCity(resultSet.getString("city"));
                seller.setStreet(resultSet.getString("street"));
                seller.setCountry(resultSet.getString("country"));
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return seller;
    }

    public static String getSellerCompanyName(int seller_id)  {
        String companyName = null;

        try {
            DatabaseConnection databaseConnection = new DatabaseConnection();
            Connection connection = databaseConnection.openConnection();

            String query = "SELECT display_name " +
                    " FROM res_partner rp " +
                    " WHERE id = "+ seller_id;

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                companyName = resultSet.getString("display_name");
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return companyName;
    }




}

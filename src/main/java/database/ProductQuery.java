package database;

import mafao.objects.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.testng.AssertJUnit.fail;
import static utils.CommonMethods.parseStringToBoolean;
import static utils.CommonMethods.print;

public class ProductQuery {

    /** Method to parse the result to a Product object */
    private static Product parseResultSetToProduct(ResultSet resultSet) {
        Product productQuery = new Product();
        try{
            productQuery.setId(Integer.parseInt(resultSet.getString("id")));
            productQuery.setDisplay_name(resultSet.getString("name"));
            productQuery.setDescription_sale(resultSet.getString("description_sale"));
            productQuery.setCategoryID(Integer.parseInt(resultSet.getString("categ_id")));
            productQuery.setList_price(Double.parseDouble(resultSet.getString("list_price")));
            productQuery.setIsActive(parseStringToBoolean(resultSet.getString("active")));
            productQuery.setIsPublished(parseStringToBoolean(resultSet.getString("is_published")));
            productQuery.setCreationDate(resultSet.getString("create_date"));
            productQuery.setSaleDelay(resultSet.getString("sale_delay"));
            productQuery.setStatus(resultSet.getString("status"));
            productQuery.setInitialQuantity(resultSet.getString("mp_qty"));
        }catch(SQLException e){
            fail("DATABASE ERROR: Not possible to parse Product");

        }
        return productQuery;

    }

    /** Method to retrieve Product information */

    public static Product getProductDetailByName(String name)  {
        Product productQuery = new Product();

        try{
            DatabaseConnection databaseConnection = new DatabaseConnection();
            Connection connection = databaseConnection.openConnection();

            String query = "SELECT * FROM product_template WHERE name = '"+name+"'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                productQuery = parseResultSetToProduct(resultSet);
            }

            resultSet.close();
            statement.close();
            databaseConnection.closeConnection();

        }catch(SQLException e){
            fail("DATABASE ERROR: Not possible to perform query");

        }
        return productQuery;
    }

    public static Product getProductDetailById(int id)  {
        Product productQuery = new Product();

        try{
            DatabaseConnection databaseConnection = new DatabaseConnection();
            Connection connection = databaseConnection.openConnection();

            String query = "SELECT * FROM product_template WHERE id = "+id;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                productQuery = parseResultSetToProduct(resultSet);
            }

            resultSet.close();
            statement.close();
            databaseConnection.closeConnection();

        }catch(SQLException e){
            fail("DATABASE ERROR: Not possible to perform query");

        }
        return productQuery;
    }

    public static List<Product> getPublishedProducts()  {
        List<Product> productList = new ArrayList<>();

        try {
            DatabaseConnection databaseConnection = new DatabaseConnection();
            Connection connection = databaseConnection.openConnection();

            String query = "SELECT * FROM product_template WHERE is_published = 't'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Product product = parseResultSetToProduct(resultSet);
                productList.add(product);
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return productList;
    }


    public static Product getProductWithLowestPrice()  {
        Product productQuery = new Product();

        try{
            DatabaseConnection databaseConnection = new DatabaseConnection();
            Connection connection = databaseConnection.openConnection();

            String query = "SELECT pt.*, pp.* from product_product pp " +
                    "JOIN product_template pt ON pp.product_tmpl_id = pt.id " +
                    "WHERE pp.active = true " +
                    "AND pp.marketplace_status = 'approved' " +
                    "AND pt.is_published = true " +
                    "AND pt.sale_ok = true " +
                    "AND pt.detailed_type  = 'product' " +
                    "AND pt.sale_delay > 0 " +
                    "AND pt.id > 122 ORDER BY pt.list_price LIMIT 1;";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                productQuery = parseResultSetToProduct(resultSet);
            }

            resultSet.close();
            statement.close();
            databaseConnection.closeConnection();

        }catch(SQLException e){
            fail("DATABASE ERROR: Not possible to perform query");

        }
        return productQuery;
    }

    public static Product getFirstProductFromMarketplace()  {
        Product productQuery = new Product();

        try{
            DatabaseConnection databaseConnection = new DatabaseConnection();
            Connection connection = databaseConnection.openConnection();

            String query = "SELECT pt.*, pp.* from product_product pp " +
                    "JOIN product_template pt ON pp.product_tmpl_id = pt.id " +
                    "WHERE pt.active = true " +
                    "AND pt.is_published = true " +
                    "AND pt.status = 'approved' " +
                    "ORDER BY pt.create_date ASC LIMIT 1";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                productQuery = parseResultSetToProduct(resultSet);
            }

            resultSet.close();
            statement.close();
            databaseConnection.closeConnection();

        }catch(SQLException e){
            fail("DATABASE ERROR: Not possible to perform query");

        }
        return productQuery;
    }

    public static List<Product> getMarketplaceProducts()  {
        List<Product> productList = new ArrayList<>();

        try {
            DatabaseConnection databaseConnection = new DatabaseConnection();
            Connection connection = databaseConnection.openConnection();

            String query = "SELECT pp.*, pt.* from product_product pp " +
                    " JOIN product_template pt ON pp.product_tmpl_id = pt.id " +
                    " WHERE pp.active = true " +
                    " AND pp.marketplace_status = 'approved' " +
                    " AND pt.is_published = true " +
                    " AND pt.sale_ok = true " +
                    " AND pt.detailed_type  = 'product' " +
                    " AND pt.sale_delay > 0 " +
                    " AND pt.id > 122 ORDER BY pt.create_date asc ;";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Product product = parseResultSetToProduct(resultSet);
                productList.add(product);
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return productList;
    }



    public static String getPublishedProductsBySeller(String sellerId)  {
        String productName = "";

        try {
            DatabaseConnection databaseConnection = new DatabaseConnection();
            Connection connection = databaseConnection.openConnection();

            String query = " SELECT name " +
                    " FROM product_template pt " +
                    " WHERE status = 'approved' " +
                    " AND is_published = 'f' " +
                    " AND marketplace_seller_id = "+sellerId;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                productName = resultSet.getString("name");
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return productName;
    }

}

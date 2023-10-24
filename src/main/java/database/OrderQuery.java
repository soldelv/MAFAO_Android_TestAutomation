package database;

import mafao.objects.Order;
import mafao.objects.OrderLines;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static apis.MafaoAPIs.getAPILastOrderBySeller;
import static constants.Constants.FULL_MOBILE_NUMBER;
import static database.UserQuery.getSellerCompanyName;
import static database.UserQuery.getUserId;
import static org.testng.AssertJUnit.fail;
import static utils.CommonMethods.parseStringToBoolean;
import static utils.CommonMethods.print;

public class OrderQuery {

    /** Method to parse the result to an OrderLine object */
    private static OrderLines parseResultSetToOrderLines(ResultSet resultSet) {
        OrderLines orderLinesQuery = new OrderLines();
        try{
            orderLinesQuery.setId(Integer.parseInt(resultSet.getString("id")));
            orderLinesQuery.setOrderId(Integer.parseInt(resultSet.getString("order_id")));
            orderLinesQuery.setProductByID(resultSet.getString("product_tmpl_id"));
            orderLinesQuery.setPriceTax(Double.parseDouble(resultSet.getString("price_tax")));
            orderLinesQuery.setPriceTotal(resultSet.getString("price_total"));
            orderLinesQuery.setPriceSubtotal(Double.parseDouble(resultSet.getString("price_subtotal")));
            orderLinesQuery.setIsDelivery(parseStringToBoolean(resultSet.getString("is_delivery")));
            orderLinesQuery.setCreate_date(resultSet.getString("create_date"));
            orderLinesQuery.setMarketplaceState(resultSet.getString("marketplace_state"));
            orderLinesQuery.setMarketplace_seller_id(resultSet.getString("marketplace_seller_id"));
            orderLinesQuery.setName(resultSet.getString("name"));
            orderLinesQuery.setPriceUnit(Double.parseDouble(resultSet.getString("price_unit")));
            orderLinesQuery.setQuantity(Integer.parseInt(resultSet.getString("product_uom")));
            String pickup_location = resultSet.getString("street")+", "+resultSet.getString("city");
            orderLinesQuery.setPickupLocation(pickup_location);
            orderLinesQuery.setCountry(resultSet.getString("country"));
            orderLinesQuery.setPickup_date(resultSet.getString("pickup_date"));

        }catch(SQLException e){
            e.printStackTrace();
            fail("DATABASE ERROR: Not possible to parse Sale Order Line");

        }
        return orderLinesQuery;

    }

    /** Method to parse the result to an Order object */
    private static Order parseResultSetToOrder(ResultSet resultSet) {
        Order orderQuery = new Order();
        try{
            int order_id = Integer.parseInt(resultSet.getString("id"));
            orderQuery.setOrderId(order_id);
            orderQuery.setAmount_total(Double.parseDouble(resultSet.getString("amount_total")));
            orderQuery.setAccess_token(resultSet.getString("access_token"));
            orderQuery.setName(resultSet.getString("product_name"));
            orderQuery.setReference(resultSet.getString("reference"));
            orderQuery.setState(resultSet.getString("state"));
            orderQuery.setDate_order(resultSet.getString("date_order"));
            orderQuery.setUser_id(Integer.parseInt(resultSet.getString("user_id")));
            orderQuery.setOrderLines(getSaleOrderLineByOrderId(order_id));

        }catch(SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            fail("DATABASE ERROR: Not possible to parse Sale Order");

        }
        return orderQuery;
    }

    /** Method to retrieve Order Line information by order_id */
    public static List<OrderLines> getSaleOrderLineByOrderId(int order_id)  {
        List<OrderLines> orderLinesQuery = new ArrayList<>();

        try {
            DatabaseConnection databaseConnection = new DatabaseConnection();
            Connection connection = databaseConnection.openConnection();

            String query = "SELECT sol.* , pl.street ,pl.city, rc.\"name\" as country, pt.description_sale, pp.product_tmpl_id " +
                    " FROM sale_order_line sol " +
                    " JOIN product_product pp ON pp.id = sol.product_id " +
                    " JOIN pickup_location pl ON pl.id = pp.pickup_location_id " +
                    " JOIN res_country rc ON rc.id = pl.country_id " +
                    " JOIN product_template pt ON pp.product_tmpl_id = pt.id " +
                    " WHERE sol.order_id = "+order_id;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                OrderLines orderLines = parseResultSetToOrderLines(resultSet);
                orderLinesQuery.add(orderLines);
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderLinesQuery;
    }

    /** Method to retrieve Order information */
    public static Order getSaleOrder()  {
        Order order = new Order();

        try {
            DatabaseConnection databaseConnection = new DatabaseConnection();
            Connection connection = databaseConnection.openConnection();

            String query = "SELECT * FROM sale_order WHERE id = 88";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                order = parseResultSetToOrder(resultSet);
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return order;
    }

    public static Order getLastSaleOrder()  {
        Order order = new Order();
        List<OrderLines> orderLinesList = getSaleOrderLineByOrderId(order.getOrderId());
        order.setOrderLines(orderLinesList);

        try {
            DatabaseConnection databaseConnection = new DatabaseConnection();
            Connection connection = databaseConnection.openConnection();

            String query ="SELECT so.*, sol.*,sol.name as product_name, pl.street, pl.city, pl.marketplace_seller_id, pl.marketplace_seller_id " +
                    " FROM sale_order_line sol " +
                    " JOIN sale_order so on so.id = sol.order_id " +
                    " JOIN res_partner rp on sol.order_partner_id = rp.id " +
                    " JOIN product_product pp on sol.product_id = pp.id "+
                    " JOIN pickup_location pl on pp.pickup_location_id = pl.id " +
                    " WHERE rp.phone_sanitized = '"+FULL_MOBILE_NUMBER+"' "+
                    " ORDER BY sol.create_date DESC LIMIT 1";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                order = parseResultSetToOrder(resultSet);
                String pickup_location = resultSet.getString("street")+", "+resultSet.getString("city");
                order.getOrderLines().get(0).setPickupLocation(pickup_location);
                int sellerId = Integer.parseInt(resultSet.getString("marketplace_seller_id"));
                order.getOrderLines().get(0).setSeller_display_name(getSellerCompanyName(sellerId));
                order.setDate_order(resultSet.getString("create_date"));
                order.getOrderLines().get(0).setCreate_date(resultSet.getString("create_date"));
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return order;
    }

    public static Order getLastReadyToPickupOrder()  {
        Order order = new Order();
        String user_id = getUserId("buyer");
        if(user_id==""){
            user_id = getUserId("seller");
        }

        try {
            DatabaseConnection databaseConnection = new DatabaseConnection();
            Connection connection = databaseConnection.openConnection();

            String query ="SELECT pl.street, pl.city, rp.commercial_company_name, pl.marketplace_seller_id,sol.create_date, " +
                    "so.*, pt.\"name\" as product_name " +
                    " FROM sale_order_line sol " +
                    " JOIN sale_order so on sol.order_id = so.id " +
                    " JOIN product_product pp on sol.product_id = pp.id " +
                    " JOIN product_template pt ON pt.id = pp.product_tmpl_id " +
                    " JOIN pickup_location pl on pp.pickup_location_id = pl.id " +
                    " JOIN res_partner rp ON rp.id = pl.marketplace_seller_id  " +
                    " WHERE sol.marketplace_state = 'ready' " +
                    " AND sol.order_partner_id = " +user_id +
                    " ORDER BY sol.pickup_date DESC";

            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.first()) {
                order = parseResultSetToOrder(resultSet);
                order.getOrderLines().get(0).setSeller_display_name(resultSet.getString("commercial_company_name"));
                order.setDate_order(resultSet.getString("create_date"));
                order.getOrderLines().get(0).setCreate_date(resultSet.getString("create_date"));
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return order;
    }

    public static Order getLastDeliveredOrder()  {
        Order order = new Order();
        String user_id = getUserId("buyer");
        if(user_id==""){
            user_id = getUserId("seller");
        }

        try {
            DatabaseConnection databaseConnection = new DatabaseConnection();
            Connection connection = databaseConnection.openConnection();

            String query ="SELECT pl.street, pl.city, rp.commercial_company_name, pl.marketplace_seller_id,sol.create_date, " +
                    "so.*, pt.\"name\" as product_name " +
                    " FROM sale_order_line sol " +
                    " JOIN sale_order so on sol.order_id = so.id " +
                    " JOIN product_product pp on sol.product_id = pp.id " +
                    " JOIN product_template pt ON pt.id = pp.product_tmpl_id " +
                    " JOIN pickup_location pl on pp.pickup_location_id = pl.id " +
                    " JOIN res_partner rp ON rp.id = pl.marketplace_seller_id  " +
                    " WHERE sol.marketplace_state = 'delivered' " +
                    " AND sol.order_partner_id = " +user_id +
                    " ORDER BY sol.pickup_date DESC";

            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.first()) {
                order = parseResultSetToOrder(resultSet);
                int sellerId = Integer.parseInt(resultSet.getString("marketplace_seller_id"));
                order.getOrderLines().get(0).setSeller_display_name(getSellerCompanyName(sellerId));
                //order.getOrderLines().get(0).setSeller_display_name(resultSet.getString("commercial_company_name"));
                order.setDate_order(resultSet.getString("create_date"));
                order.getOrderLines().get(0).setCreate_date(resultSet.getString("create_date"));
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return order;
    }

}

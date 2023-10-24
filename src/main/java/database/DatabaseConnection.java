package database;

import java.sql.*;

import static constants.Constants.*;
import static org.testng.AssertJUnit.fail;
import static utils.CommonMethods.print;

public class DatabaseConnection {
    private Connection connection;

    public Connection openConnection()  {
        try{
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            }
            print("Successful database connection");
            return connection;
        }catch(SQLException e){
            e.printStackTrace();
            fail("DATABASE CONNECTION ERROR");
        }
        return null;
    }

    public void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}

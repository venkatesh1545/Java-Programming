package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    public static void main(String[] args){
        try{
            String url = "jdbc:mysql://localhost:3306/JDBC_temp";
            String user = "#####";  //Add your user name
            String pass = "######"; // Add your password
            Connection con = DriverManager.getConnection(url, user, pass);

            if (con != null) {
                System.out.println("Successfully connected");
            }
            else{
                System.out.println("Failed to connect");
            }
            System.out.println();
        } catch (SQLException e) {
            System.out.println(e);
        } 
    }
}

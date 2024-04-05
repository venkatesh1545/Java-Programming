package Projects.Inventory_Management_system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class InventoryManagementSystem {
    static Connection con;
    public static Connection dbConnection()
    {
        try{
            String url = "jdbc:mysql://localhost:3306/IMS";
            String username = "root";
            String password = "12341234";
            con = DriverManager.getConnection(url,username,password);
            if(con!=null){
                System.out.println("Succesfully connected to database");
            }
        }
        catch(SQLException e){
            System.out.println("Error connecting to the database");
        }
        return con;
    }
    public static void main(String[] args) {
        con = dbConnection();
        GUIInitiator.initializeGUI(con);
    }
}

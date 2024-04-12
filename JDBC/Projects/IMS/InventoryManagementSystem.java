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
            String password = "732005@venkat";
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
        //new FilterFrame(con).setVisible(true); // Create an instance of FilterFrame and make it visible
    }
    
}
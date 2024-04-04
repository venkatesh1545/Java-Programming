package Projects.Inventory_Management_system;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JTable;

public class AddProductDb {
    static Connection con;
    static Statement statement;
    public static void addNewProduct(JTable table, Connection con) {
        String IDStr = JOptionPane.showInputDialog(null,"Enter ID NO:");
        String name = JOptionPane.showInputDialog(null, "Enter product name:");
        String costStr = JOptionPane.showInputDialog(null, "Enter product cost:");
        String quantityStr = JOptionPane.showInputDialog(null, "Enter product quantity:");
        // Convert cost and quantity to integers
        int ID = Integer.parseInt(IDStr);
        int cost = Integer.parseInt(costStr);
        int quantity = Integer.parseInt(quantityStr);
        
        // Insert the new product into the database
        try {
            Statement st = con.createStatement();
            String query = "INSERT INTO Product (ID, Name, Cost, Quantity) VALUES ('" + ID + "', '" + name + "', " + cost +  ", " + quantity +")";

            st.executeUpdate(query);
            st.close();
            
            // Refresh the table to display the new product
            ListALLProducts.populateTable(table);
            
            JOptionPane.showMessageDialog(null, "Product added successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error adding product: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    
}

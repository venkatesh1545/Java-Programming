import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class AddProductDb {
    public static Connection con;

    public static void addNewProduct(JTable table, Connection con) {
        String IDStr = JOptionPane.showInputDialog(null, "Enter ID NO:");
        String Products = JOptionPane.showInputDialog(null, "Enter Products:");
        String Category = JOptionPane.showInputDialog(null, "Enter product Category:");
        String Name = JOptionPane.showInputDialog(null, "Enter product Name:");
        String brand = JOptionPane.showInputDialog(null, "Enter product Brand:");
        String costStr = JOptionPane.showInputDialog(null, "Enter product cost:");
        String quantityStr = JOptionPane.showInputDialog(null, "Enter product quantity:");
    
        // Check if any field is empty
        if (IDStr == null || IDStr.trim().isEmpty() || Products == null || Products.trim().isEmpty() || Category == null || Category.trim().isEmpty() || Name == null || Name.trim().isEmpty() || brand == null || brand.trim().isEmpty() || costStr == null || costStr.trim().isEmpty() || quantityStr == null || quantityStr.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill all the fields. Null values are not accepted.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        int cost;
        int quantity;
        try {
            cost = Integer.parseInt(costStr);
            quantity = Integer.parseInt(quantityStr);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid input for cost or quantity.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        // Check if the product already exists
        if (productExists(IDStr, con)) {
            JOptionPane.showMessageDialog(null, "Product with ID " + IDStr + " already exists.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        // Insert the new product into the database
        try {
            try (Statement st = con.createStatement()) {
                String query = "INSERT INTO Product (ID, Products, Category, Name, Brand, Cost, Quantity) VALUES ('" + IDStr + "', '" + Products + "', '" + Category + "', '" + Name + "', '" + brand + "', " + cost +  ", " + quantity +")";
    
                st.executeUpdate(query);
            }
    
            // Refresh the table to display the new product
            ListALLProducts.populateTable(table);
    
            JOptionPane.showMessageDialog(null, "Product added successfully!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error adding product: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static boolean productExists(String ID, Connection con) {
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM Product WHERE ID = '" + ID + "'");
            rs.next();
            int count = rs.getInt(1);
            return count > 0;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error checking product existence: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}

package Projects.Inventory_Management_system;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JTable;

public class DeleteDb {
    public static void deleteProduct(JTable table, Connection con) {
        String productIdStr = JOptionPane.showInputDialog(null, "Enter Product ID to delete:");
        
        // Check if productIdStr is empty
        if (productIdStr == null || productIdStr.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Product ID cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int productId = Integer.parseInt(productIdStr);
        
        try {
            // Check if the product with the given ID exists
            boolean productExists = checkProductExists(con, productId);
            if (!productExists) {
                JOptionPane.showMessageDialog(null, "No product found with ID: " + productId, "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Display the confirmation dialog before deleting.
            int confirm  = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the product with ID: "+ productId + "?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if(confirm == JOptionPane.YES_OPTION){
                // Delete the product from the database
                Statement st = con.createStatement();
                String query = "DELETE FROM Product WHERE ID = " + productId;
                st.executeUpdate(query);
                st.close();
                
                // Refresh the table to reflect the changes
                ListALLProducts.populateTable(table);
                
                JOptionPane.showMessageDialog(null, "Product deleted successfully");
            } else {
                JOptionPane.showMessageDialog(null, "Product deletion canceled");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error deleting product: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid Product ID. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Method to check if a product with the given ID exists in the database
    private static boolean checkProductExists(Connection con, int productId) throws SQLException {
        Statement st = con.createStatement();
        String query = "SELECT * FROM Product WHERE ID = " + productId;
        ResultSet rs = st.executeQuery(query);
        boolean exists = rs.next(); // Check if ResultSet is not empty
        rs.close();
        st.close();
        return exists;
    }
}

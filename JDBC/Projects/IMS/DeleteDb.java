import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        
        try {
            // Check if the product with the given ID exists
            boolean productExists = checkProductExists(con, productIdStr);
            if (!productExists) {
                JOptionPane.showMessageDialog(null, "No product found with ID: " + productIdStr, "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Display the confirmation dialog before deleting.
            int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the product with ID: "+ productIdStr + "?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if(confirm == JOptionPane.YES_OPTION) {
                // Delete the product from the database
                String query = "DELETE FROM Product WHERE ID = ?";
                try (PreparedStatement pst = con.prepareStatement(query)) {
                    pst.setString(1, productIdStr);
                    int rowsAffected = pst.executeUpdate();

                    // Refresh the table to reflect the changes
                    ListALLProducts.populateTable(table);

                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "Product deleted successfully");
                    } else {
                        JOptionPane.showMessageDialog(null, "No rows deleted.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Product deletion canceled");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error deleting product: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Method to check if a product with the given ID exists in the database
    private static boolean checkProductExists(Connection con, String productIdStr) throws SQLException {
        boolean exists;
        String query = "SELECT * FROM Product WHERE ID = ?";
        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, productIdStr);
            try (ResultSet rs = pst.executeQuery()) {
                exists = rs.next(); // Check if ResultSet is not empty
            }
        }
        return exists;
    }
}

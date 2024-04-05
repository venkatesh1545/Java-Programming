package Projects.Inventory_Management_system;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class UpdateDb {
    public static void updateQuantity(Connection con) {
        String productIdStr = JOptionPane.showInputDialog(null, "Enter Product ID:");
        String newQuantityStr = JOptionPane.showInputDialog(null, "Enter New Quantity:");
        
        // Parse input strings to integers
        int productId = Integer.parseInt(productIdStr);
        int newQuantity = Integer.parseInt(newQuantityStr);
        
        try {
            Statement st = con.createStatement();
            String query = "UPDATE Product SET Quantity = " + newQuantity + " WHERE ID = " + productId;
            st.executeUpdate(query);
            st.close();
            
            JOptionPane.showMessageDialog(null, "Quantity updated successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error updating quantity: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void updateCost(Connection con) {
        String productIdStr = JOptionPane.showInputDialog(null, "Enter Product ID:");
        String newCostStr = JOptionPane.showInputDialog(null, "Enter New Cost:");
        
        // Parse input strings to integers
        int productId = Integer.parseInt(productIdStr);
        double newCost = Double.parseDouble(newCostStr);
        
        int confirmOption = JOptionPane.showConfirmDialog(null, "Do you want to update the name as well?", "Update Name", JOptionPane.YES_NO_OPTION);
        boolean updateName = (confirmOption == JOptionPane.YES_OPTION);
        
        if (updateName) {
            String newName = JOptionPane.showInputDialog(null, "Enter New Name:");
            updateProduct(con, productId, newCost, newName);
        } else {
            updateProduct(con, productId, newCost, null);
        }
    }

    private static void updateProduct(Connection con, int productId, double newCost, String newName) {
        try {
            Statement st = con.createStatement();
            StringBuilder queryBuilder = new StringBuilder("UPDATE Product SET Cost = ");
            queryBuilder.append(newCost);
            if (newName != null) {
                queryBuilder.append(", Name = '").append(newName).append("'");
            }
            queryBuilder.append(" WHERE ID = ").append(productId);
            
            String query = queryBuilder.toString();
            st.executeUpdate(query);
            st.close();
            
            String message = (newName != null) ? "Cost and name updated successfully!" : "Cost updated successfully!";
            JOptionPane.showMessageDialog(null, message);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error updating product: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

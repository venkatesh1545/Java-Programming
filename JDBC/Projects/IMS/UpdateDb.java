import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class UpdateDb {
    public static void updateQuantity(Connection con) {
        String productIdStr = JOptionPane.showInputDialog(null, "Enter Product ID:");
        
        // Check if the product ID is empty
        if (productIdStr == null || productIdStr.trim().isEmpty()) {
            // Show error message and prompt for re-update
            int option = JOptionPane.showConfirmDialog(null, "Product ID cannot be empty. Do you want to re-update?", "Error", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
            if (option == JOptionPane.YES_OPTION) {
                // Call the method recursively to re-update
                updateQuantity(con);
            }
            return; // Exit the method
        }
        
        String newQuantityStr = JOptionPane.showInputDialog(null, "Enter New Quantity:");
        
        // Check if the new quantity is empty
        if (newQuantityStr == null || newQuantityStr.trim().isEmpty()) {
            // Show error message and prompt for re-update
            int option = JOptionPane.showConfirmDialog(null, "New Quantity cannot be empty. Do you want to re-update?", "Error", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
            if (option == JOptionPane.YES_OPTION) {
                // Call the method recursively to re-update
                updateQuantity(con);
            }
            return; // Exit the method
        }
    
        // Parse input strings to integers
        int productId = Integer.parseInt(productIdStr);
        int newQuantity = Integer.parseInt(newQuantityStr);
    
        try {
            try (Statement st = con.createStatement()) {
                String query = "UPDATE Product SET Quantity = " + newQuantity + " WHERE ID = " + productId;
                int rowsAffected = st.executeUpdate(query);
                System.out.println("Rows affected: " + rowsAffected); // Debug print
    
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Quantity updated successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "No rows updated.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error updating quantity: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    

    public static void updateCost(Connection con) {
        String productIdStr = JOptionPane.showInputDialog(null, "Enter Product ID:");
        
        // Check if the product ID is empty
        if (productIdStr == null || productIdStr.trim().isEmpty()) {
            // Show error message and prompt for re-update
            int option = JOptionPane.showConfirmDialog(null, "Product ID cannot be empty. Do you want to re-update?", "Error", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
            if (option == JOptionPane.YES_OPTION) {
                // Call the method recursively to re-update
                updateCost(con);
            }
            return; // Exit the method
        }
        
        String newCostStr = JOptionPane.showInputDialog(null, "Enter New Cost:");
        
        // Check if the new cost is empty
        if (newCostStr == null || newCostStr.trim().isEmpty()) {
            // Show error message and prompt for re-update
            int option = JOptionPane.showConfirmDialog(null, "New Cost cannot be empty. Do you want to re-update?", "Error", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
            if (option == JOptionPane.YES_OPTION) {
                // Call the method recursively to re-update
                updateCost(con);
            }
            return; // Exit the method
        }
    
        // Parse input strings to integers
        int newCost = Integer.parseInt(newCostStr);
    
        try {
            try (Statement st = con.createStatement()) {
                String query = "UPDATE Product SET Cost = " + newCost + " WHERE ID = " + productIdStr;
                int rowsAffected = st.executeUpdate(query);
                System.out.println("Rows affected: " + rowsAffected); // Debug print
    
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Cost updated successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "No rows updated.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error updating cost: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public static void updateCategory(Connection con) {
        String productIdStr = JOptionPane.showInputDialog(null, "Enter Product ID:");
        
        // Check if the product ID is empty
        if (productIdStr == null || productIdStr.trim().isEmpty()) {
            // Show error message and prompt for re-update
            int option = JOptionPane.showConfirmDialog(null, "Product ID cannot be empty. Do you want to re-update?", "Error", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
            if (option == JOptionPane.YES_OPTION) {
                // Call the method recursively to re-update
                updateCategory(con);
            }
            return; // Exit the method
        }
        
        String newCategory = JOptionPane.showInputDialog(null, "Enter New Category:");
        
        // Check if the new category is empty
        if (newCategory == null || newCategory.trim().isEmpty()) {
            // Show error message and prompt for re-update
            int option = JOptionPane.showConfirmDialog(null, "New Category cannot be empty. Do you want to re-update?", "Error", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
            if (option == JOptionPane.YES_OPTION) {
                // Call the method recursively to re-update
                updateCategory(con);
            }
            return; // Exit the method
        }
    
        try {
            try (Statement st = con.createStatement()) {
                String query = "UPDATE Product SET Category = '" + newCategory + "' WHERE ID = " + productIdStr;
                int rowsAffected = st.executeUpdate(query);
                System.out.println("Rows affected: " + rowsAffected); // Debug print
    
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Category updated successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "No rows updated.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error updating category: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public static void updateName(Connection con) {
        String productIdStr = JOptionPane.showInputDialog(null, "Enter Product ID:");
        
        // Check if the product ID is empty
        if (productIdStr == null || productIdStr.trim().isEmpty()) {
            // Show error message and prompt for re-update
            int option = JOptionPane.showConfirmDialog(null, "Product ID cannot be empty. Do you want to re-update?", "Error", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
            if (option == JOptionPane.YES_OPTION) {
                // Call the method recursively to re-update
                updateName(con);
            }
            return; // Exit the method
        }
        
        String newName = JOptionPane.showInputDialog(null, "Enter New Product Name:");
        
        // Check if the new name is empty
        if (newName == null || newName.trim().isEmpty()) {
            // Show error message and prompt for re-update
            int option = JOptionPane.showConfirmDialog(null, "New Product Name cannot be empty. Do you want to re-update?", "Error", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
            if (option == JOptionPane.YES_OPTION) {
                // Call the method recursively to re-update
                updateName(con);
            }
            return; // Exit the method
        }
    
        try {
            try (Statement st = con.createStatement()) {
                String query = "UPDATE Product SET Name = '" + newName + "' WHERE ID = " + productIdStr;
                int rowsAffected = st.executeUpdate(query);
                System.out.println("Rows affected: " + rowsAffected); // Debug print
    
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Product Name updated successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "No rows updated.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error updating product name: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public static void updateProductId(Connection con) {
        String currentIdStr = JOptionPane.showInputDialog(null, "Enter Current Product ID:");
        
        // Check if the current ID is empty
        if (currentIdStr == null || currentIdStr.trim().isEmpty()) {
            // Show error message and prompt for re-update
            int option = JOptionPane.showConfirmDialog(null, "Current Product ID cannot be empty. Do you want to re-update?", "Error", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
            if (option == JOptionPane.YES_OPTION) {
                // Call the method recursively to re-update
                updateProductId(con);
            }
            return; // Exit the method
        }
        
        String newId = JOptionPane.showInputDialog(null, "Enter New Product ID:");
        
        // Check if the new ID is empty
        if (newId == null || newId.trim().isEmpty()) {
            // Show error message and prompt for re-update
            int option = JOptionPane.showConfirmDialog(null, "New Product ID cannot be empty. Do you want to re-update?", "Error", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
            if (option == JOptionPane.YES_OPTION) {
                // Call the method recursively to re-update
                updateProductId(con);
            }
            return; // Exit the method
        }
        
        try {
            //int currentId = Integer.parseInt(currentIdStr);
    
            try (Statement st = con.createStatement()) {
                //String query = "UPDATE Product SET ID = '" + newId + "' WHERE ID = " + currentIdStr;
                String query = "UPDATE Product SET ID = '" + newId + "' WHERE ID = '" + currentIdStr + "'";

                int rowsAffected = st.executeUpdate(query);
                System.out.println("Rows affected: " + rowsAffected); // Debug print
    
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Product ID updated successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "No rows updated.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid ID format. Please enter a valid integer ID.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error updating product ID: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public static void updateBrand(Connection con) {
        String productIdStr = JOptionPane.showInputDialog(null, "Enter Product ID:");
        
        // Check if the product ID is empty
        if (productIdStr == null || productIdStr.trim().isEmpty()) {
            // Show error message and prompt for re-update
            int option = JOptionPane.showConfirmDialog(null, "Product ID cannot be empty. Do you want to re-update?", "Error", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
            if (option == JOptionPane.YES_OPTION) {
                // Call the method recursively to re-update
                updateBrand(con);
            }
            return; // Exit the method
        }
        
        String newBrand = JOptionPane.showInputDialog(null, "Enter New Brand:");
        
        // Check if the new brand is empty
        if (newBrand == null || newBrand.trim().isEmpty()) {
            // Show error message and prompt for re-update
            int option = JOptionPane.showConfirmDialog(null, "New Brand cannot be empty. Do you want to re-update?", "Error", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
            if (option == JOptionPane.YES_OPTION) {
                // Call the method recursively to re-update
                updateBrand(con);
            }
            return; // Exit the method
        }
    
        try {
            try (Statement st = con.createStatement()) {
                String query = "UPDATE Product SET Brand = '" + newBrand + "' WHERE ID = " + productIdStr;
                int rowsAffected = st.executeUpdate(query);
                System.out.println("Rows affected: " + rowsAffected); // Debug print
    
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Brand updated successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "No rows updated.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error updating brand: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public static void updateProducts(Connection con) {
    String productIdStr = JOptionPane.showInputDialog(null, "Enter Product ID:");
    
    // Check if the product ID is empty
    if (productIdStr == null || productIdStr.trim().isEmpty()) {
        // Show error message and prompt for re-update
        int option = JOptionPane.showConfirmDialog(null, "Product ID cannot be empty. Do you want to re-update?", "Error", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
        if (option == JOptionPane.YES_OPTION) {
            // Call the method recursively to re-update
            updateProducts(con);
        }
        return; // Exit the method
    }
    
    String newProducts = JOptionPane.showInputDialog(null, "Enter New Products:");
    
    // Check if the new products are empty
    if (newProducts == null || newProducts.trim().isEmpty()) {
        // Show error message and prompt for re-update
        int option = JOptionPane.showConfirmDialog(null, "New Products cannot be empty. Do you want to re-update?", "Error", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
        if (option == JOptionPane.YES_OPTION) {
            // Call the method recursively to re-update
            updateProducts(con);
        }
        return; // Exit the method
    }

    try {
        try (Statement st = con.createStatement()) {
            String query = "UPDATE Product SET Products = '" + newProducts + "' WHERE ID = " + productIdStr;
            int rowsAffected = st.executeUpdate(query);
            System.out.println("Rows affected: " + rowsAffected); // Debug print

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Products updated successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "No rows updated.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error updating products: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
    }
}

}

import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ViewProductId {
    public static void viewProduct(JButton viewProductButton, JFrame frame, Connection con,JTextField productIdTextField){
        viewProductButton.addActionListener((ActionEvent e) -> {
            String productId = productIdTextField.getText();
            if(!productId.isEmpty()){
                try{
                    String query = "select * from Product where ID = '"+ productId + "' ";
                    try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(query)) {
                        
                        if (rs.next()) { // Check if the ResultSet is not empty
                            String products = rs.getString("Products");
                            String category = rs.getString("Category");
                            String name = rs.getString("Name");
                            String brand = rs.getString("Brand");
                            int cost = rs.getInt("Cost");
                            int quantity = rs.getInt("Quantity");
                            
                            // Display the product details in a message dialog
                            JOptionPane.showMessageDialog(frame,
                                    "Product ID: " + productId + "\n" +
                                    "Products: " + products + "\n" +
                                    "Category: " + category + "\n" +
                                    "Name: " + name + "\n" +
                                    "Brand: " + brand + "\n" +
                                    "Cost: " + cost + "\n" +
                                    "Quantity: " + quantity,
                                    "Product Details",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                        else{
                            JOptionPane.showMessageDialog(frame,"No product found with ID: " + productId,"Error",JOptionPane.ERROR_MESSAGE);
                        }
                        
                    }
                }
                catch(SQLException ex)
                {
                    //ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame,"Error: " + ex.getMessage(),"Database Error",JOptionPane.ERROR_MESSAGE);
                }
            }                
            else{
                // If no product ID is entered, display an error message
                JOptionPane.showMessageDialog(frame,"Please enter a product ID","Error",JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}









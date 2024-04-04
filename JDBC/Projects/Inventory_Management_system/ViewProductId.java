package Projects.Inventory_Management_system;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        viewProductButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                //JTextField productIdTextField = authorTextField; 
                String productId = productIdTextField.getText();
                if(!productId.isEmpty()){
                    try{
                        String query = "select * from Product where ID = '"+ productId + "' ";
                        Statement st = con.createStatement();
                        ResultSet rs = st.executeQuery(query);

                        if (rs.next()) { // Check if the ResultSet is not empty
                            String name = rs.getString("Name");
                            int cost = rs.getInt("Cost");
                            int quantity = rs.getInt("Quantity");

                            // Display the product details in a message dialog
                            JOptionPane.showMessageDialog(frame,
                                    "Product ID: " + productId + "\n" +
                                    "Name: " + name + "\n" +
                                    "Cost: " + cost + "\n" +
                                    "Quantity: " + quantity,
                                    "Product Details",
                                    JOptionPane.INFORMATION_MESSAGE);
                        } 
                        else{
                            JOptionPane.showMessageDialog(frame,"No product found with ID: " + productId,"Error",JOptionPane.ERROR_MESSAGE);
                        }

                        rs.close();
                        st.close();
                    }
                    catch(SQLException ex)
                    {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(frame,"Error: " + ex.getMessage(),"Database Error",JOptionPane.ERROR_MESSAGE);
                    }
                }
                else{
                    // If no product ID is entered, display an error message
                    JOptionPane.showMessageDialog(frame,"Please enter a product ID","Error",JOptionPane.ERROR_MESSAGE);
                }
                
            }
        });
    }
}
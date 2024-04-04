package Projects.Inventory_Management_system;
import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class GUIInitiator extends JFrame {
    static JTextArea textArea;
    static JScrollPane scrollPane;
    static JTextField productIdTextField;
    static JButton listAllButton, addProductButton, updateQuantityButton, updateCostButton, deleteButton,viewProductButton, exitButton,clearButton;
    static JLabel productIdLabel;
    static JTable table;
    static Connection con;
    public static void initializeGUI(Connection con){
        JFrame frame = new JFrame("Inventory Management System");
        frame.setSize(930,600);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.lightGray);

        Font font = new Font("calibri",Font.BOLD,18);

        textArea = new JTextArea();
        textArea.setFont(font);
        textArea.setEditable(false);

        productIdLabel  = new JLabel("Enter product Id: ");
        productIdLabel.setFont(font);
        productIdLabel.setBounds(220,5,150,30);
        frame.add(productIdLabel);

        productIdTextField = new JTextField();
        productIdTextField.setBounds(370, 5, 150, 25);
        frame.add(productIdTextField);

        viewProductButton = new JButton("View Product");
        viewProductButton.setBounds(530,5,150,25);
        frame.add(viewProductButton);

        listAllButton = new JButton("List All Products");
        listAllButton.setBounds(20, 45, 150, 25);
        frame.add(listAllButton);

        addProductButton = new JButton("Add Product");
        addProductButton.setBounds(180, 45, 150, 25);
        frame.add(addProductButton);

        updateQuantityButton = new JButton("Update Quantity");
        updateQuantityButton.setBounds(340, 45, 150, 25);
        frame.add(updateQuantityButton);

        updateCostButton = new JButton("Update Cost");
        updateCostButton.setBounds(500, 45, 150, 25);
        frame.add(updateCostButton);

        deleteButton = new JButton("Delete Product");
        deleteButton.setBounds(660, 45, 150, 25);
        frame.add(deleteButton);

        exitButton = new JButton("Exit");
        exitButton.setBounds(820, 45, 70, 25);
        frame.add(exitButton);

        table = new JTable();
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(100,90,700,400);
        frame.add(scrollPane);

        clearButton = new JButton("Clear");
        clearButton.setBounds(420, 500, 80, 25);
        frame.add(clearButton);

        listAllButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ListALLProducts.populateTable(table);
            }
        });
        ViewProductId.viewProduct(viewProductButton, frame, con,productIdTextField);

        addProductButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddProductDb.addNewProduct(table, con);
            }
        });

        updateQuantityButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                UpdateDb.updateQuantity(con);
            }
        });

        updateCostButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                UpdateDb.updateCost(con);
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                DeleteDb.deleteProduct(table, con);
            }
        });

        exitButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame.dispose();    //close the JFrame;
            }
        });

        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Clear.clearTable(table); // Clear the text area
            }
        });
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
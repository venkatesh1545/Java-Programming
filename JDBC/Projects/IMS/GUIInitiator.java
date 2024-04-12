import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Venkatesh
 */
public class GUIInitiator extends JFrame {
    static JTextArea textArea;
    static JScrollPane scrollPane;
    static JTextField productIdTextField;
    static JButton listAllButton, addProductButton,updateButton, deleteButton, viewProductButton, exitButton, clearButton,filterButton, generateCSVButton, viewCSVButton, applyButton; // Added viewCSVButton
    static JLabel productIdLabel;
    static JTable table;
    static Connection con;
    static JComboBox<String> productComboBox;
    static JPanel categoryPanel;

    public static void initializeGUI(Connection con) {
        setCon(con);
        //con =connection;
        JFrame frame = new JFrame("Inventory Management System");
        frame.setSize(900, 600);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.lightGray);

        Font font = new Font("calibri", Font.BOLD, 18);

        textArea = new JTextArea();
        textArea.setFont(font);
        textArea.setEditable(false);

        productIdLabel = new JLabel("Enter product Id: ");
        productIdLabel.setFont(font);
        productIdLabel.setBounds(220, 5, 150, 30);
        frame.add(productIdLabel);

        productIdTextField = new JTextField();
        productIdTextField.setBounds(370, 5, 150, 25);
        frame.add(productIdTextField);

        viewProductButton = new JButton("View Product");
        viewProductButton.setBounds(530, 5, 150, 25);
        frame.add(viewProductButton);

        listAllButton = new JButton("List All Products");
        listAllButton.setBounds(50, 45, 150, 25);
        frame.add(listAllButton);

        addProductButton = new JButton("Add Product");
        addProductButton.setBounds(210, 45, 150, 25);
        frame.add(addProductButton);

        updateButton = new JButton("Update Product");
        updateButton.setBounds(370, 45, 150, 25);
        frame.add(updateButton);

        deleteButton = new JButton("Delete Product");
        deleteButton.setBounds(530, 45, 150, 25);
        frame.add(deleteButton);

        generateCSVButton = new JButton("Generate CSV");
        generateCSVButton.setBounds(210, 500, 120, 25);
        frame.add(generateCSVButton);

        viewCSVButton = new JButton("View csv file"); // Added viewCSVButton
        viewCSVButton.setBounds(350, 500, 120, 25); // Adjusted position
        frame.add(viewCSVButton); // Added to frame

        exitButton = new JButton("Exit");
        exitButton.setBounds(690,45, 70, 25); // Adjusted position
        frame.add(exitButton);

        table = new JTable();
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(60, 90, 750, 400);
        frame.add(scrollPane);
        
        filterButton = new JButton("Filter Products");
        filterButton.setBounds(490,500,120,25);
        frame.add(filterButton);

        clearButton = new JButton("Clear");
        clearButton.setBounds(630, 500, 80, 25);
        frame.add(clearButton);

        listAllButton.addActionListener((ActionEvent e) -> {
            ListALLProducts.populateTable(table);
            GUIInitiator.enableGenerateCSVButton(); // Call the method to enable "Generate CSV" button
        });
        
        ViewProductId.viewProduct(viewProductButton, frame, con, productIdTextField);

        addProductButton.addActionListener((ActionEvent e) -> {
            AddProductDb.addNewProduct(table, con);
        });

        updateButton.addActionListener((ActionEvent e) -> {
            showUpdateOptions(); // Call method to display update options
        });

        deleteButton.addActionListener((ActionEvent e) -> {
            DeleteDb.deleteProduct(table, con);
        });

        generateCSVButton.addActionListener((ActionEvent e) -> {
            CSVGenerator.generateCSV(table);
        });

        viewCSVButton.addActionListener((ActionEvent e) -> { // Action for the viewCSVButton
            CSVViewer.viewCSV();
        });

        filterButton.addActionListener((ActionEvent e) -> {
            showFilterFrame();
        });
        

        exitButton.addActionListener((ActionEvent e) -> {
            int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                frame.dispose();
            }
        });

        clearButton.addActionListener((ActionEvent e) -> {
            Clear.clearTable(table);
        });
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    private static void showUpdateOptions() {
        JFrame updateFrame = new JFrame("Update Options");
        updateFrame.setSize(400, 300);
        updateFrame.setLayout(null);

        // Add buttons for different update options
        JButton updateCostButton = new JButton("Update Cost");
        updateCostButton.setBounds(50, 50, 150, 30);
        updateFrame.add(updateCostButton);

        JButton updateQuantityButton = new JButton("Update Quantity");
        updateQuantityButton.setBounds(200, 50, 150, 30);
        updateFrame.add(updateQuantityButton);

        JButton updateIdButton = new JButton("Update ID");
        updateIdButton.setBounds(50, 100, 150, 30);
        updateFrame.add(updateIdButton);

        JButton updateCategoryButton = new JButton("Update Category");
        updateCategoryButton.setBounds(200, 100, 150, 30);
        updateFrame.add(updateCategoryButton);

        JButton updateNameButton = new JButton("Update Name");
        updateNameButton.setBounds(50, 150, 150, 30);
        updateFrame.add(updateNameButton);

        JButton updateBrandButton = new JButton("Update Brand");
        updateBrandButton.setBounds(200, 150, 150, 30);
        updateFrame.add(updateBrandButton);

        JButton updateProductsButton = new JButton("Update Products");
        updateProductsButton.setBounds(125,200,150,30);
        updateFrame.add(updateProductsButton);

        updateNameButton.addActionListener((ActionEvent e) -> {
            UpdateDb.updateName(con);
        });

        updateProductsButton.addActionListener((ActionEvent e) -> {
            UpdateDb.updateProducts(con);
        });
        updateCategoryButton.addActionListener((ActionEvent e) -> {
            UpdateDb.updateCategory(con);
        });

        updateIdButton.addActionListener((ActionEvent e) -> {
            UpdateDb.updateProductId(con);
        });
        updateBrandButton.addActionListener((ActionEvent e) -> {
            UpdateDb.updateBrand(con);
        });

        updateQuantityButton.addActionListener((ActionEvent e) -> {
            UpdateDb.updateQuantity(con);
        });

        updateCostButton.addActionListener((ActionEvent e) -> {
            UpdateDb.updateCost(con);
        });

        updateFrame.setResizable(false);
        updateFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        updateFrame.setVisible(true);
    }
    
    private static void showFilterFrame() {
        JFrame filterFrame = new JFrame("Filter Products");
        filterFrame.setSize(450, 450);
        filterFrame.setLayout(new BorderLayout());
        filterFrame.setLocationRelativeTo(null);
    
        productComboBox = new JComboBox<>();
        categoryPanel = new JPanel();
        categoryPanel.setLayout(new GridLayout(0, 1));
        applyButton = new JButton("Apply");
    
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Select Product:"));
        topPanel.add(productComboBox);
    
        // Price range selection components
        JPanel pricePanel = new JPanel();
        pricePanel.add(new JLabel("Min Price:"));
        JTextField minPriceField = new JTextField(10);
        pricePanel.add(minPriceField);
        pricePanel.add(new JLabel("Max Price:"));
        JTextField maxPriceField = new JTextField(10);
        pricePanel.add(maxPriceField);
    
        JScrollPane categoryScrollPane = new JScrollPane(categoryPanel);
        categoryScrollPane.setBounds(30,90,300,200);
        filterFrame.add(topPanel, BorderLayout.NORTH);
        filterFrame.add(categoryScrollPane, BorderLayout.CENTER);
        filterFrame.add(pricePanel, BorderLayout.CENTER); // Add price panel to the center
        filterFrame.add(applyButton, BorderLayout.SOUTH);
    
        populateProductComboBox();
    
        productComboBox.addActionListener((e) -> {
            String selectedProduct = (String) productComboBox.getSelectedItem();
            populateCategoryCheckBoxes(selectedProduct);
        });
    
        applyButton.addActionListener((ActionEvent e) -> {
            // Get the minimum and maximum prices
            double minPrice = Double.parseDouble(minPriceField.getText());
            double maxPrice = Double.parseDouble(maxPriceField.getText());
            applyFilter(minPrice, maxPrice);
        });
    
        filterFrame.setVisible(true);
    }
    

    private static void populateProductComboBox() {
        try {
            try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery("SELECT DISTINCT Products FROM Product")) {
                while (rs.next()) {
                    productComboBox.addItem(rs.getString("Products"));
                }
            }
        } catch (SQLException ex) {
            // Handle exception
        }
    }

    private static void populateCategoryCheckBoxes(String selectedProduct) {
        try {
            categoryPanel.removeAll();
            categoryPanel.revalidate();
            categoryPanel.repaint();
            try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery("SELECT DISTINCT Category FROM Product WHERE Products = '" + selectedProduct + "'")) {
                while (rs.next()) {
                    String category = rs.getString("Category");
                    JCheckBox checkBox = new JCheckBox(category);
                    categoryPanel.add(checkBox);
                }
            }
        } catch (SQLException ex) {
            // Handle exception
        }
    }

    private static void applyFilter(double minPrice, double maxPrice) {
        String selectedProduct = (String) productComboBox.getSelectedItem();
        ArrayList<String> selectedCategories = new ArrayList<>();
        for (Component component : categoryPanel.getComponents()) {
            if (component instanceof JCheckBox checkBox) {
                if (checkBox.isSelected()) {
                    selectedCategories.add(checkBox.getText());
                }
            }
        }
        // Open the new frame to display filtered products
        showFilteredProductsFrame(selectedProduct, selectedCategories, minPrice, maxPrice);
    }
    
    private static void showFilteredProductsFrame(String selectedProduct, ArrayList<String> selectedCategories, double minPrice, double maxPrice) {
        JFrame resultFrame = new JFrame("Filtered Products");
        resultFrame.setSize(800, 600);
        resultFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        resultFrame.setLayout(new BorderLayout());
    
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Category");
        model.addColumn("Name");
        model.addColumn("Brand");
        model.addColumn("Cost");
    
        JTable resultTable = new JTable(model);
        JScrollPane resultScrollPane = new JScrollPane(resultTable);
    
        try {
            try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery("SELECT * FROM Product WHERE Products = '" + selectedProduct + "' AND Cost BETWEEN " + minPrice + " AND " + maxPrice)) {
                while (rs.next()) {
                    String category = rs.getString("Category");
                    if (selectedCategories.contains(category)) {
                        Object[] rowData = {
                            rs.getInt("ID"),
                            category,
                            rs.getString("Name"),
                            rs.getString("Brand"),
                            rs.getDouble("Cost")
                        };
                        model.addRow(rowData);
                    }
                }
            }
        } catch (SQLException ex) {
            // Handle exception
        }
    
        resultFrame.add(resultScrollPane, BorderLayout.CENTER);
    
        // Create a panel for result count label
        JPanel resultCountPanel = new JPanel(new BorderLayout());
        JLabel resultCountLabel = new JLabel("Result Count: " + model.getRowCount());
        resultCountPanel.add(resultCountLabel, BorderLayout.CENTER);
    
        resultFrame.add(resultCountPanel, BorderLayout.NORTH);
    
        resultFrame.setVisible(true);
    }
    
    
    

    
    public static void enableGenerateCSVButton() {
        generateCSVButton.setEnabled(true);
        generateCSVButton.setBackground(Color.gray);
    }
    
    public static Connection getCon() {
        return con;
    }

    public static void setCon(Connection con) {
        GUIInitiator.con = con;
    }
}

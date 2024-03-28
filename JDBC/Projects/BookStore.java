package JDBC.Projects;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class BookStore {
    static JComboBox<String> comboBox;
    static JTextArea textArea;
    static JScrollPane scrollPane;
    static JTextField authorTextField;
    static JButton enterButton, insertButton, updateButton, deleteButton;
    static Connection con;

    public static void main(String[] args){
        connectToDatabase();
        initializeGUI();
        populateTableComboBox();
    }
    public static void connectToDatabase(){
        try{
            String url = "jdbc:mysql://localhost:3306/bookStore";
            String username = "root";
            String password = "12341234";

            con = DriverManager.getConnection(url,username,password);
            if(con!=null){
                System.out.println("Successfully connected to database");
            }
        }
        catch(SQLException e){
            System.out.println("Error connecting to the database");
        }
    }
    public static void initializeGUI(){
        JFrame frame = new JFrame("Books and Authors List");
        frame.setSize(500,150);
        frame.setLayout(null);

        Font font = new Font("calibri",Font.ITALIC,18);
        comboBox = new JComboBox<>();
        comboBox.setBounds(5,5,100,30);
        comboBox.setFont(font);

        textArea = new JTextArea();
        textArea.setFont(font);
        textArea.setEditable(false);
        //JScrollPane scrollPane = new JScrollPane(textArea);
        //scrollPane.setBounds(180,50,300,300);
        
        frame.add(comboBox);
        //frame.add(scrollPane);

        authorTextField = new JTextField();
        authorTextField.setBounds(180, 370, 200, 30);
        frame.add(authorTextField);

        enterButton = new JButton("Enter");
        enterButton.setBounds(200, 200, 80, 30);
        frame.add(enterButton);
        
        insertButton = new JButton("Insert");
        insertButton.setBounds(120, 5, 80, 30);
        frame.add(insertButton);

        updateButton = new JButton("Update");
        updateButton.setBounds(210, 5, 80, 30);
        frame.add(updateButton);

        deleteButton = new JButton("Delete");
        deleteButton.setBounds(300, 5, 80, 30);
        frame.add(deleteButton);

        insertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openInsertFrame();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                openDeleteFrame();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                openUpdateFrame();
            }
        });

        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void populateTableComboBox(){
        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("Select Distinct(Author) From programming;");
            while(resultSet.next()){
                comboBox.addItem(resultSet.getString(1));
            }

            comboBox.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String selectedValue = (String) comboBox.getSelectedItem();
                    if (!selectedValue.isEmpty()) {
                        addDetailsToMessageBox(selectedValue);
                    } else {
                        JOptionPane.showMessageDialog(null, "Please enter an author name.");
                    }
                }
            });
        } catch (SQLException e) {
            System.out.println("Error populating in table names: "+e.getMessage());
        }
    }

    public static void addDetailsToMessageBox(String author) {
        try {
            Statement statement = con.createStatement();
    
            ResultSet rs = statement.executeQuery("SELECT * FROM programming WHERE author = '" + author + "'");
            @SuppressWarnings("unused")
            ResultSetMetaData metaData = rs.getMetaData();
    
            StringBuilder message = new StringBuilder();
    
            boolean firstBook = true; // Flag to determine if it's the first book
    
            // Append data rows
            while (rs.next()) {
                if (!firstBook) {
                    message.append("\n-------------------------\n"); // Add separator
                } else {
                    firstBook = false; // Set the flag to false after appending details of the first book
                }
    
                message.append("BookName: ").append(rs.getString("BookName")).append("\n");
                message.append("Author: ").append(rs.getString("Author")).append("\n");
                message.append("Price of book: ").append(rs.getString("Price")).append("\n");
                message.append("Publisher: ").append(rs.getString("Publisher")).append("\n");
                message.append("No Of Pages: ").append(rs.getString("NoOfPages")).append("\n");
                message.append("Published Date: ").append(rs.getString("publishedOn")).append("\n");
                // Append other details similarly
            }
    
            JOptionPane.showMessageDialog(null, message.toString(), "Details of " + author, JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void openInsertFrame() {
        JFrame insertFrame = new JFrame("Insert Book Details");
        insertFrame.setSize(400, 400);
        insertFrame.setLayout(null);

        JLabel nameLabel = new JLabel("Book Name:");
        nameLabel.setBounds(10, 10, 100, 30);
        insertFrame.add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(120, 10, 250, 30);
        insertFrame.add(nameField);

        JLabel authorLabel = new JLabel("Author:");
        authorLabel.setBounds(10, 50, 100, 30);
        insertFrame.add(authorLabel);

        JTextField authorField = new JTextField();
        authorField.setBounds(120, 50, 250, 30);
        insertFrame.add(authorField);

        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setBounds(10, 90, 100, 30);
        insertFrame.add(priceLabel);

        JTextField priceField = new JTextField();
        priceField.setBounds(120, 90, 250, 30);
        insertFrame.add(priceField);

        JLabel publisherLabel = new JLabel("Publisher:");
        publisherLabel.setBounds(10, 130, 100, 30);
        insertFrame.add(publisherLabel);

        JTextField publisherField = new JTextField();
        publisherField.setBounds(120, 130, 250, 30);
        insertFrame.add(publisherField);

        JLabel pagesLabel = new JLabel("No Of Pages:");
        pagesLabel.setBounds(10, 170, 100, 30);
        insertFrame.add(pagesLabel);

        JTextField pagesField = new JTextField();
        pagesField.setBounds(120, 170, 250, 30);
        insertFrame.add(pagesField);

        JLabel publishedLabel = new JLabel("Published Date:");
        publishedLabel.setBounds(10, 210, 100, 30);
        insertFrame.add(publishedLabel);

        JTextField publishedField = new JTextField();
        publishedField.setBounds(120, 210, 250, 30);
        insertFrame.add(publishedField);

        JButton saveButton = new JButton("Save");
        saveButton.setBounds(150, 250, 80, 30);
        insertFrame.add(saveButton);

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String bookName = nameField.getText();
                String author = authorField.getText();
                String price = priceField.getText();
                String publisher = publisherField.getText();
                String pages = pagesField.getText();
                String publishedDate = publishedField.getText();
                insertData(bookName, author, price, publisher, pages, publishedDate);
                insertFrame.dispose();
            }
        });

        insertFrame.setResizable(false);
        insertFrame.setVisible(true);
    }

    public static void insertData(String bookName, String author, String price, String publisher, String pages, String publishedDate) {
        try {
            Statement statement = con.createStatement();
            String query = "INSERT INTO programming (BookName, Author, Price, Publisher, NoOfPages, PublishedOn) VALUES ('" +
                    bookName + "', '" + author + "', " + price + ", '" + publisher + "', " + pages + ", '" + publishedDate + "')";
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Data inserted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error inserting data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public static void openDeleteFrame() {
        JFrame deleteFrame = new JFrame("Delete Book");
        deleteFrame.setSize(400, 300);
        deleteFrame.setLayout(null);

        JLabel authorLabel = new JLabel("Author:");
        authorLabel.setBounds(10, 50, 100, 30);
        deleteFrame.add(authorLabel);

        JTextField authorField = new JTextField();
        authorField.setBounds(120, 50, 250, 30);
        deleteFrame.add(authorField);

        JLabel BookLabel = new JLabel("Book Name:");
        BookLabel.setBounds(10, 90, 100, 30);
        deleteFrame.add(BookLabel);

        JTextField BookField = new JTextField();
        BookField.setBounds(120, 90, 250, 30);
        deleteFrame.add(BookField);

        JTextArea bookDetailsArea = new JTextArea();
        bookDetailsArea.setEditable(false);

        JButton deleteButton = new JButton("Delete");
        deleteButton.setBounds(150, 220, 100, 30);
        deleteFrame.add(deleteButton);

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String author = authorField.getText();
                String Book = BookField.getText();
                if (!author.isEmpty() && !Book.isEmpty()) {
                    String bookDetails = getBookDetails(author,Book);
                    if (!bookDetails.isEmpty()) {
                        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the following book(s)?\n" + bookDetails, "Confirmation", JOptionPane.YES_NO_OPTION);
                        if (confirm == JOptionPane.YES_OPTION) {
                            deleteData(author,Book);
                            JOptionPane.showMessageDialog(null, "Selected book(s) deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No book found for the given author.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter the author name.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                deleteFrame.dispose();
            }
        });

        deleteFrame.setResizable(false);
        deleteFrame.setVisible(true);
    }

    public static String getBookDetails(String author, String bookName) {
        StringBuilder details = new StringBuilder();
        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM programming WHERE author = '" + author + "' AND BookName = '" + bookName +"'");
            while (resultSet.next()) {
                details.append("Book Name: ").append(resultSet.getString("BookName")).append("\n");
                details.append("Author: ").append(resultSet.getString("Author")).append("\n");
                details.append("Price: ").append(resultSet.getString("Price")).append("\n");
                details.append("Publisher: ").append(resultSet.getString("Publisher")).append("\n");
                details.append("No of Pages: ").append(resultSet.getString("NoOfPages")).append("\n");
                details.append("Published Date: ").append(resultSet.getString("PublishedOn")).append("\n\n");
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return details.toString();
    }

    public static void deleteData(String author,String bookName) {
        try {
            Statement statement = con.createStatement();
            statement.executeUpdate("DELETE FROM programming WHERE author = '" + author + "' AND BookName = '" + bookName + "'");
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
    public static void openUpdateFrame() {
            JFrame updateFrame = new JFrame("Update Book");
            updateFrame.setSize(400, 400);
            updateFrame.setLayout(null);

            JLabel bookNameLabel = new JLabel("Book Name:");
            bookNameLabel.setBounds(10, 30, 100, 30);
            updateFrame.add(bookNameLabel);

            JTextField bookNameField = new JTextField();
            bookNameField.setBounds(120, 30, 250, 30);
            updateFrame.add(bookNameField);

            JLabel authorLabel = new JLabel("Author:");
            authorLabel.setBounds(10, 70, 100, 30);
            updateFrame.add(authorLabel);

            JTextField authorField = new JTextField();
            authorField.setBounds(120, 70, 250, 30);
            updateFrame.add(authorField);

            JLabel publisherLabel = new JLabel("Publisher:");
            publisherLabel.setBounds(10, 110, 100, 30);
            updateFrame.add(publisherLabel);

            JTextField publisherField = new JTextField();
            publisherField.setBounds(120, 110, 250, 30);
            updateFrame.add(publisherField);

            JLabel priceLabel = new JLabel("Price:");
            priceLabel.setBounds(10, 150, 100, 30);
            updateFrame.add(priceLabel);

            JTextField priceField = new JTextField();
            priceField.setBounds(120, 150, 250, 30);
            updateFrame.add(priceField);

            JLabel pagesLabel = new JLabel("Pages:");
            pagesLabel.setBounds(10, 190, 100, 30);
            updateFrame.add(pagesLabel);

            JTextField pagesField = new JTextField();
            pagesField.setBounds(120, 190, 250, 30);
            updateFrame.add(pagesField);

            JLabel publishDateLabel = new JLabel("Published Date:");
            publishDateLabel.setBounds(10, 230, 100, 30);
            updateFrame.add(publishDateLabel);

            JTextField publishDateField = new JTextField();
            publishDateField.setBounds(120, 230, 250, 30);
            updateFrame.add(publishDateField);

            JButton updateButton = new JButton("Update");
            updateButton.setBounds(150, 270, 100, 30);
            updateFrame.add(updateButton);

            updateButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String bookName = bookNameField.getText();
                    String author = authorField.getText();
                    String publisher = publisherField.getText();
                    String price = priceField.getText();
                    String pages = pagesField.getText();
                    if (!bookName.isEmpty() && !author.isEmpty() && !publisher.isEmpty() && !price.isEmpty() && !pages.isEmpty()) {
                        updateData(bookName, author, publisher, price, pages);
                        JOptionPane.showMessageDialog(null, "Book details updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    updateFrame.dispose();
                }
            });

            updateFrame.setResizable(false);
            updateFrame.setVisible(true);
        }

    public static void updateData(String bookName, String author, String publisher, String price, String pages) {
        try
        {
            // Create a PreparedStatement with parameters for all fields except book name
            PreparedStatement statement = con.prepareStatement("UPDATE programming SET Author = ?, Publisher = ?, Price = ?, NoOfPages = ? WHERE BookName = ?");
            
            // Set the values for the parameters
            statement.setString(1, author);
            statement.setString(2, publisher);
            statement.setString(3, price);
            statement.setString(4, pages);
            statement.setString(5, bookName);
            
            // Execute the update query
            int rowsAffected = statement.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("Updated book details: " + bookName);
            } else {
                System.out.println("No book found for update: " + bookName);
            }
        }
        catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

}

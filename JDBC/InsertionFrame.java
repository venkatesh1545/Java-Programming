package JDBC;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;

public class InsertionFrame extends JFrame {
    private Connection con;

    public InsertionFrame(Connection connection) {
        this.con = connection;
        initComponents();
    }

    private void initComponents() {
        setTitle("Insert Data");
        setSize(800, 300);
        setLayout(new FlowLayout());

        JLabel idLabel = new JLabel("ID:");
        JTextField idField = new JTextField(10);
        add(idLabel);
        add(idField);

        JLabel firstNameLabel = new JLabel("FirstName:");
        JTextField firstNameField = new JTextField(10);
        add(firstNameLabel);
        add(firstNameField);

        JLabel lastNameLabel = new JLabel("LastName:");
        JTextField lastNameField = new JTextField(10);
        add(lastNameLabel);
        add(lastNameField);

        JLabel campusLabel = new JLabel("Campus:");
        JTextField campusField = new JTextField(10);
        add(campusLabel);
        add(campusField);

        JLabel branchLabel = new JLabel("Branch:");
        JTextField branchField = new JTextField(10);
        add(branchLabel);
        add(branchField);

        JLabel phLabel = new JLabel("Phone No:");
        JTextField phField = new JTextField(10);
        add(phLabel);
        add(phField);

        JLabel cityLabel = new JLabel("City:");
        JTextField cityField = new JTextField(10);
        add(cityLabel);
        add(cityField);

        JButton insertButton = new JButton("Insert");
        insertButton.addActionListener((ActionEvent e) -> {
            String id = idField.getText();
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String campusName = campusField.getText();
            String branchName = branchField.getText();
            String phNo = phField.getText();
            String city = cityField.getText();
            insertData(id, firstName,lastName,campusName,branchName,phNo,city);
            idField.setText("");
            firstNameField.setText("");
            lastNameField.setText("");
            campusField.setText("");
            branchField.setText("");
            phField.setText("");
            cityField.setText("");
        });
        add(insertButton);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void insertData(String id, String FirstName,String LastName,String Campus, String Branch, String ph, String city) {
        try {
            Statement statement = con.createStatement();
            String query = "INSERT INTO student VALUES ('" + id + "', '" + FirstName + "', '" + LastName + "','" + Campus + "', '" + Branch + "', '" + ph + "', '" + city + "')";
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Data inserted successfully!");
        } catch (SQLException ex) {
            System.out.println("Failed to insert "+ex.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            String url = "jdbc:mysql://localhost:3306/JDBC_temp";
            String username = "root";
            String password = "12341234";

            Connection connection = DriverManager.getConnection(url, username, password);

            new InsertionFrame(connection);
        }
        catch (SQLException e) {
            System.out.println("Failed to connect: "+e.getMessage());
        }
    }
}


package JDBC;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
 
public class JDBCandGUI {
    static JComboBox<String> comboBox;
    static JTextArea textArea;
    static JScrollPane scrollPane;
    static Connection con;
    
    public static void main(String[] args) {
        connectToDatabase();
        initializeGUI();
        populateTableNames();

    }
    public static void initializeGUI(){
        JFrame frame = new JFrame("Integrating JDBC and GUI");
        frame.setSize(800,600);
        frame.setLayout(null);

        Font font = new Font("calibri",Font.BOLD,20);
        comboBox = new JComboBox<>();
        comboBox.setBounds(80,50,100,50);
        comboBox.setFont(font);

        comboBox.addActionListener((ActionEvent e) -> {
            selectTable((String) comboBox.getSelectedItem());
            //comboBox.getSelectedItem();
        });

        textArea = new JTextArea();
        textArea.setFont(font);
        scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(180,50,500,400);

        frame.add(comboBox);
        frame.add(scrollPane);

        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    public static void connectToDatabase(){
        try {
            String url = "jdbc:mysql://localhost:3306/JDBC_temp";
            String username = "root";
            String password = "12341234";

            con = DriverManager.getConnection(url,username,password);
            if(con!=null){
                System.out.println("Successfully connected to Database");
            }
            
            
        } 
        catch (SQLException e) {
            System.out.println("Error connecting to the database"+e.getMessage());
        }
    }
    public static void populateTableNames(){
        try {
            
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SHOW tables;");
            while(resultSet.next()){
                comboBox.addItem(resultSet.getString(1));
            }
        } catch (SQLException e) {
            System.out.println("Error populating in table names: "+e.getMessage());
        }
    }
    public static void selectTable(String tableName){
        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName + ";");
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            
            Font font = new Font("calibri",Font.BOLD,16);
            textArea.setFont(font);

            StringBuilder header = new StringBuilder();
            for(int i = 1;i<=columnCount; i++){
                header.append(metaData.getColumnName(i));
                if(i<columnCount){
                    header.append("\t");
                }
            }
            header.append("\n\n");

            textArea.setText(header.toString());

            while(resultSet.next()){
                StringBuilder row = new StringBuilder();
                for(int i = 1;i<=columnCount;i++){
                    row.append(resultSet.getString(i));
                    {
                        if(i < columnCount){
                            row.append("\t");
                        }
                    }
                }
                textArea.append(row.toString() + "\n");
            }
        } catch (SQLException e) {
            System.out.println("ErrorSelecting Table: "+e.getMessage());
        }
    }
}


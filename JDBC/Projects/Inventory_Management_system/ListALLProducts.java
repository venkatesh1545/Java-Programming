package Projects.Inventory_Management_system;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ListALLProducts {
    static Connection con;
    public static void populateTable(JTable table){
        try{
            con = InventoryManagementSystem.dbConnection();
            String query = "select * from Product";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            DefaultTableModel model = new DefaultTableModel();
            ResultSetMetaData metaData = (ResultSetMetaData) rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            for(int i = 1;i<=columnCount;i++)
            {
                model.addColumn(metaData.getColumnName(i));
            }
            while(rs.next()){
                Object[] rowData = new Object[columnCount];
                for(int i = 1;i<= columnCount;i++){
                    rowData[i-1] = rs.getObject(i);
                }
                model.addRow(rowData);
            }

            table.setModel(model);  //set the table model
            rs.close();
            st.close();
            con.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Error: "+e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

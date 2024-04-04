package Projects.Inventory_Management_system;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Clear {
    public static void clearTable(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Clear table data
    }
}

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Clear {
    public static void clearTable(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Clear table data
        model.setColumnCount(0); // Clear table columns
    }
}

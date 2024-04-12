import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class CSVGenerator {
    public static void generateCSV(JTable table) {
        try {
            // Prompt user for file name
            String fileName = JOptionPane.showInputDialog(null, "Enter file name (without extension) to save CSV file:", "Save CSV", JOptionPane.PLAIN_MESSAGE);
            if (fileName == null || fileName.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "File name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String filePath = fileName + ".csv";

            try (FileWriter writer = new FileWriter(filePath)) {
                writer.append("Product ID,Name,Quantity,Cost\n");

                for (int i = 0; i < table.getRowCount(); i++) {
                    for (int j = 0; j < table.getColumnCount(); j++) {
                        writer.append(table.getValueAt(i, j).toString());
                        if (j < table.getColumnCount() - 1) {
                            writer.append(",");
                        }
                    }
                    writer.append("\n");
                }
            }
            JOptionPane.showMessageDialog(null, "CSV file generated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error generating CSV file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;

public class CSVViewer {
    public static void viewCSV() {
        try {
            // Prompt user for file name
            String fileName = JOptionPane.showInputDialog(null, "Enter file name (without extension) to open CSV file:", "Open CSV", JOptionPane.PLAIN_MESSAGE);
            if (fileName == null || fileName.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "File name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String filePath = fileName + ".csv";

            File file = new File(filePath);
            if (file.exists()) {
                Desktop.getDesktop().open(file);
            } else {
                JOptionPane.showMessageDialog(null, "File does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error opening CSV file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

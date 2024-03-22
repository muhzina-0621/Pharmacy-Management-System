package pharmacy;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class View_med extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTable table;
    private JTextField medIdField; // Changed cusIdField to medIdField
    private JTextField mednameField; // Added
    private JTextField costField; // Added
    private JTextField companyField; // Added
    private Connection connection;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    View_med window = new View_med();
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public View_med() {
        getContentPane().setBackground(new Color(240, 128, 128));
        initialize();
        establishConnection();
    }

    private void initialize() {
        setTitle("Medicine Details");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 786, 669);
        getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Medicine ID");
        lblNewLabel.setFont(new Font("Times New Roman", Font.ITALIC, 14));
        lblNewLabel.setBounds(40, 350, 80, 25);
        getContentPane().add(lblNewLabel);

        medIdField = new JTextField(); // Changed cusIdField to medIdField
        medIdField.setBounds(140, 350, 140, 25); // Changed cusIdField to medIdField
        getContentPane().add(medIdField); // Changed cusIdField to medIdField
        medIdField.setColumns(10); // Changed cusIdField to medIdField

        JLabel lblNewLabel_1 = new JLabel("Medicine Name"); // Changed lblNewLabel_1 to lblNewLabel_2
        lblNewLabel_1.setFont(new Font("Times New Roman", Font.ITALIC, 14));
        lblNewLabel_1.setBounds(40, 385, 80, 25);
        getContentPane().add(lblNewLabel_1);

        mednameField = new JTextField(); // Changed cusIdField to mednameField
        mednameField.setColumns(10);
        mednameField.setBounds(140, 385, 140, 25);
        getContentPane().add(mednameField);

        JLabel lblNewLabel_2 = new JLabel("Cost"); // Changed lblNewLabel_2 to lblNewLabel_3
        lblNewLabel_2.setFont(new Font("Times New Roman", Font.ITALIC, 14));
        lblNewLabel_2.setBounds(40, 420, 120, 25);
        getContentPane().add(lblNewLabel_2);

        costField = new JTextField(); // Changed cusIdField to costField
        costField.setColumns(10);
        costField.setBounds(140, 420, 140, 25);
        getContentPane().add(costField);

        JLabel lblNewLabel_3 = new JLabel("Company"); // Changed lblNewLabel_3 to lblNewLabel_4
        lblNewLabel_3.setFont(new Font("Times New Roman", Font.ITALIC, 14));
        lblNewLabel_3.setBounds(40, 455, 80, 25);
        getContentPane().add(lblNewLabel_3);

        companyField = new JTextField(); // Changed cusIdField to companyField
        companyField.setColumns(10);
        companyField.setBounds(140, 455, 140, 25);
        getContentPane().add(companyField);

        JButton btnAddMedicines = new JButton("Add Medicines"); // Changed btnAddCustomer to btnAddMedicines
        btnAddMedicines.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addMedicines();
            }
        });
        btnAddMedicines.setFont(new Font("Cambria", Font.BOLD, 15));
        btnAddMedicines.setBounds(60, 500, 140, 42);
        getContentPane().add(btnAddMedicines);

        JButton btnDeleteMedicine = new JButton("Delete Medicine"); // Changed btnDeleteMedicines to btnDeleteMedicine
        btnDeleteMedicine.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteMedicine(); // Changed deleteCustomer to deleteMedicine
            }
        });
        btnDeleteMedicine.setFont(new Font("Cambria", Font.BOLD, 15));
        btnDeleteMedicine.setBounds(220, 500, 157, 42);
        getContentPane().add(btnDeleteMedicine);

        JButton btnShowTable = new JButton("Show Table");
        btnShowTable.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayTable();
            }
        });
        btnShowTable.setFont(new Font("Cambria", Font.BOLD, 15));
        btnShowTable.setBounds(390, 500, 140, 42);
        getContentPane().add(btnShowTable);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(60, 30, 600, 300);
        getContentPane().add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);
    }

    private void establishConnection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "hr", "9495558676Th#");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addMedicines() {
        String medid = medIdField.getText(); // Changed cusIdField to medIdField
        String medname = mednameField.getText(); // Changed cusIdField to mednameField
        String cost = costField.getText(); // Changed dateField to costField
        String company = companyField.getText(); // Changed quantityField to companyField

        try {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO Medicines VALUES (?, ?, ?, ?)");
            stmt.setString(1, medid);
            stmt.setString(2, medname);
            stmt.setString(3, cost);
            stmt.setString(4, company);

            stmt.executeUpdate();
            stmt.close();
            displayTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteMedicine() { // Changed deleteCustomer to deleteMedicine
        int selectedRowIndex = table.getSelectedRow();
        if (selectedRowIndex == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to delete.");
            return;
        }
        String medId = (String) table .getValueAt(selectedRowIndex, 0);
        try {
            Statement stmt = connection.createStatement();
            String query = "DELETE FROM Medicines WHERE medid='" + medId + "'"; // Corrected table name to "Medicines"
            stmt.executeUpdate(query);
            stmt.close();
            displayTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void displayTable() {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Medicines");
            table.setModel(buildTableModel(rs));
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();

        // Column names
        int columnCount = metaData.getColumnCount();
        String[] columnNames = new String[columnCount];
        for (int i = 1; i <= columnCount; i++) {
            columnNames[i - 1] = metaData.getColumnName(i);
        }

        // Data of the table
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        while (rs.next()) {
            Object[] rowData = new Object[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                rowData[i - 1] = rs.getObject(i);
            }
            tableModel.addRow(rowData);
        }
        return tableModel;
    }
}


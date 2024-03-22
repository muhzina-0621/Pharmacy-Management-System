package pharmacy;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Add_customer extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTable table;
    private JTextField cusIdField;
    private JTextField medIdField;
    private JTextField dateField;
    private JTextField quantityField;
    private Connection connection;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Add_customer window = new Add_customer();
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Add_customer() {
        getContentPane().setBackground(new Color(240, 128, 128));
        initialize();
        establishConnection();
    }

    private void initialize() {
        setTitle("Customer Details Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 786, 669);
        getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Customer ID");
        lblNewLabel.setFont(new Font("Times New Roman", Font.ITALIC, 14));
        lblNewLabel.setBounds(40, 350, 80, 25);
        getContentPane().add(lblNewLabel);

        cusIdField = new JTextField();
        cusIdField.setBounds(140, 350, 140, 25);
        getContentPane().add(cusIdField);
        cusIdField.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Medicine ID");
        lblNewLabel_1.setFont(new Font("Times New Roman", Font.ITALIC, 14));
        lblNewLabel_1.setBounds(40, 385, 80, 25);
        getContentPane().add(lblNewLabel_1);

        medIdField = new JTextField();
        medIdField.setColumns(10);
        medIdField.setBounds(140, 385, 140, 25);
        getContentPane().add(medIdField);

        JLabel lblNewLabel_2 = new JLabel("Date of Purchase");
        lblNewLabel_2.setFont(new Font("Times New Roman", Font.ITALIC, 14));
        lblNewLabel_2.setBounds(40, 420, 120, 25);
        getContentPane().add(lblNewLabel_2);

        dateField = new JTextField();
        dateField.setColumns(10);
        dateField.setBounds(140, 420, 140, 25);
        getContentPane().add(dateField);

        JLabel lblNewLabel_3 = new JLabel("Quantity");
        lblNewLabel_3.setFont(new Font("Times New Roman", Font.ITALIC, 14));
        lblNewLabel_3.setBounds(40, 455, 80, 25);
        getContentPane().add(lblNewLabel_3);

        quantityField = new JTextField();
        quantityField.setColumns(10);
        quantityField.setBounds(140, 455, 140, 25);
        getContentPane().add(quantityField);

        JButton btnAddCustomer = new JButton("Add Customer");
        btnAddCustomer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addCustomer();
            }
        });
        btnAddCustomer.setFont(new Font("Cambria", Font.BOLD, 15));
        btnAddCustomer.setBounds(60, 500, 140, 42);
        getContentPane().add(btnAddCustomer);

        JButton btnDeleteCustomer = new JButton("Delete Customer");
        btnDeleteCustomer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteCustomer();
            }
        });
        btnDeleteCustomer.setFont(new Font("Cambria", Font.BOLD, 15));
        btnDeleteCustomer.setBounds(220, 500, 157, 42);
        getContentPane().add(btnDeleteCustomer);

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

    private void addCustomer() {
        String cusId = cusIdField.getText();
        String medId = medIdField.getText();
        String dateOfPurchase = dateField.getText();
        String quantity = quantityField.getText();

        try {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO Customer VALUES (?, ?, ?, ?)");
            stmt.setString(1, cusId);
            stmt.setString(2, medId);
            stmt.setString(3, dateOfPurchase);
            stmt.setString(4, quantity);

            stmt.executeUpdate();
            stmt.close();
            displayTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteCustomer() {
        int selectedRowIndex = table.getSelectedRow();
        if (selectedRowIndex == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to delete.");
            return;
        }
        String cusId = (String) table.getValueAt(selectedRowIndex, 0);
        try {
            Statement stmt = connection.createStatement();
            String query = "DELETE FROM Customer WHERE cusid='" + cusId + "'";
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
            ResultSet rs = stmt.executeQuery("SELECT * FROM Customer");
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

package pharmacy;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ChangePassword extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
    private JTextField textField_1;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ChangePassword window = new ChangePassword();
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public ChangePassword() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(SystemColor.menu);
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Set Your New Password");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblNewLabel.setBounds(99, 10, 223, 31);
        getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Enter Username :");
        lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
        lblNewLabel_1.setBounds(26, 63, 134, 31);
        getContentPane().add(lblNewLabel_1);

        textField = new JTextField();
        textField.setBounds(154, 63, 223, 35);
        getContentPane().add(textField);
        textField.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("New Password :");
        lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 16));
        lblNewLabel_2.setBounds(26, 137, 178, 36);
        getContentPane().add(lblNewLabel_2);

        textField_1 = new JTextField();
        textField_1.setBounds(154, 137, 228, 35);
        getContentPane().add(textField_1);
        textField_1.setColumns(10);

        JButton btnNewButton = new JButton("Change Password");
        btnNewButton.setBackground(SystemColor.menu);
        btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
        btnNewButton.setBounds(37, 203, 178, 31);
        btnNewButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                try {
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "hr","9495558676Th#");
                    PreparedStatement st = conn.prepareStatement("UPDATE Login1 SET password=? WHERE username=?");

                    // Retrieve the values from text fields
                    String username = textField.getText();
                    String password = textField_1.getText();

                    // Set the parameters in the PreparedStatement
                    st.setString(1, password);
                    st.setString(2, username);

                    // Execute the update query
                    int rowsAffected = st.executeUpdate();

                    if (rowsAffected > 0) {
                        System.out.println("Password Change Successful");
                    } else {
                        System.out.println("Password Change not Successful !!!!!!!!!!!!!!!");
                    }
                } catch (SQLException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
        getContentPane().add(btnNewButton);

        JButton btnNewButton_1 = new JButton("Go Back");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Close the current frame
            	Dashboard d10=new Dashboard();
            	d10.setVisible(true);
            	dispose();
            }
        });
        btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
        btnNewButton_1.setBounds(258, 203, 146, 35);
        getContentPane().add(btnNewButton_1);
    }
}

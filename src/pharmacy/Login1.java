package pharmacy;

import java.sql.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Color;

public class Login1 {
    
    private JFrame frame;
    private JTextField textField;
    private JPasswordField passwordField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    // Load Oracle JDBC driver
                    Class.forName("oracle.jdbc.driver.OracleDriver");

                    Login1 window = new Login1();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public Login1() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(Color.GRAY);
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        textField = new JTextField();
        textField.setBounds(216, 92, 145, 26);
        frame.getContentPane().add(textField);
        textField.setColumns(10);
        
        JLabel lblNewLabel = new JLabel("LOGIN");
        lblNewLabel.setForeground(new Color(0, 0, 205));
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));
        lblNewLabel.setBounds(142, 10, 117, 44);
        frame.getContentPane().add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Username:");
        lblNewLabel_1.setFont(new Font("Serif", Font.BOLD, 16));
        lblNewLabel_1.setBounds(40, 89, 98, 26);
        frame.getContentPane().add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("Password:");
        lblNewLabel_2.setFont(new Font("Serif", Font.BOLD, 16));
        lblNewLabel_2.setBounds(42, 156, 76, 19);
        frame.getContentPane().add(lblNewLabel_2);
        
        JButton btnNewButton = new JButton("Login");
        btnNewButton.setBackground(new Color(240, 248, 255));
        btnNewButton.setForeground(new Color(138, 43, 226));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "hr", "9495558676Th#");
                    PreparedStatement st = conn.prepareStatement("Select username, password from Login1 where username=? and password=?");
                    String username = textField.getText();
                    char[] charArray = passwordField.getPassword();
                    String password = new String(charArray);
                    st.setString(1, username);
                    st.setString(2, password);
                    ResultSet rs = st.executeQuery();
                    if (rs.next()) {
                        System.out.println("Login Successful !!!!!!!!!!!!!!!");
                        Dashboard d1=new Dashboard();
                        d1.setVisible(true);
                    } else {
                        System.out.println("Login not Successful !!!!!!!!!!!!!!!");
                    }
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        btnNewButton.setFont(new Font("Serif", Font.BOLD, 16));
        btnNewButton.setBounds(142, 205, 98, 26);
        frame.getContentPane().add(btnNewButton);
        
        passwordField = new JPasswordField();
        passwordField.setBounds(216, 149, 145, 26);
        frame.getContentPane().add(passwordField);
    }
}

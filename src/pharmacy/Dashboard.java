package pharmacy;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Dashboard extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Dashboard frame = new Dashboard();
                    frame.setVisible(true); // Setting frame visibility
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Dashboard() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBackground(Color.ORANGE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Welcome to Apollo pharmacy");
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
        lblNewLabel.setBounds(32, 10, 383, 49);
        contentPane.add(lblNewLabel);
        
        JButton btnNewButton = new JButton("Add Customer ");
        btnNewButton.setFont(new Font("Times New Roman", Font.ITALIC, 14));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Add_customer a1 = new Add_customer();
                a1.setVisible(true); // Set visibility of Add_customer frame
                dispose(); // Close the current frame
            }
        });
        btnNewButton.setBounds(137, 55, 140, 30);
        contentPane.add(btnNewButton);
        
        JButton btnNewButton_1 = new JButton("Delete Customer");
        btnNewButton_1.setFont(new Font("Times New Roman", Font.ITALIC, 14));
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Add_customer a2 = new Add_customer();
                a2.setVisible(true); // Set visibility of Add_customer frame
                dispose(); // Close the current frame
            }
        });
        btnNewButton_1.setBounds(137, 94, 140, 30);
        contentPane.add(btnNewButton_1);
        
        JButton btnNewButton_3 = new JButton("Update Password");
        btnNewButton_3.setFont(new Font("Times New Roman", Font.ITALIC, 14));
        btnNewButton_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ChangePassword c4 = new ChangePassword();
                c4.setVisible(true); // Set visibility of ChangePassword frame
                dispose(); // Close the current frame
            }
        });
        btnNewButton_3.setBounds(137, 146, 137, 30);
        contentPane.add(btnNewButton_3);
        
        JButton btnNewButton_4 = new JButton("Exit");
        btnNewButton_4.setFont(new Font("Times New Roman", Font.ITALIC, 14));
        btnNewButton_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Add any necessary code to handle the exit action
               dispose();
            }
        });
        btnNewButton_4.setBounds(137, 201, 140, 33);
        contentPane.add(btnNewButton_4);
    }
}

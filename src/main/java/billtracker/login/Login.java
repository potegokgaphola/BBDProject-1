package billtracker.login;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import billtracker.home.Home;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField username;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel login_panel = new JPanel();
		login_panel.setBackground(Color.LIGHT_GRAY);
		login_panel.setBounds(95, 57, 329, 193);
		contentPane.add(login_panel);
		login_panel.setLayout(null);
		
		JLabel user_icon = new JLabel("");
		Image user_img = new ImageIcon(this.getClass().getResource("/user.png")).getImage();
		user_icon.setIcon(new ImageIcon(user_img));
		user_icon.setBounds(33, 56, 32, 32);
		login_panel.add(user_icon);
		
		JLabel password_icon = new JLabel("");
		Image password_img = new ImageIcon(this.getClass().getResource("/password.png")).getImage();
		password_icon.setIcon(new ImageIcon(password_img));
		password_icon.setBounds(33, 95, 32, 32);
		login_panel.add(password_icon);
		
		username = new JTextField();
		username.setBounds(123, 68, 162, 20);
		login_panel.add(username);
		username.setColumns(10);
		
		JButton login_btn = new JButton("Login");
		login_btn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				PreparedStatement st;
				ResultSet rs;
				
				//get user data
				String Username = username.getText();
				String Password = String.valueOf(password.getPassword());
				
				//sql query 
				String query = "SELECT * FROM `users` WHERE `username` = ? AND `password` = ?";
				
				try {
					st = My_CNX.getConnection().prepareStatement(query);
					st.setString(1, Username);
					st.setString(2, Password);
					rs = st.executeQuery();
					
					if (rs.next()) {
						//successfully logged in
//						JOptionPane.showMessageDialog(null, "Successfully logged in", "Login Success", 2);
						Home home = new Home();
						home.setVisible(true);
						Login.this.dispose();
					} else {
						//login error
						JOptionPane.showMessageDialog(null, "Invald username or passwod", "Login Error", 2);
					}
					
				} catch (SQLException ex) {
					// TODO Auto-generated catch block
					Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
				}
				
				
				
			}
			
		});
		login_btn.setBounds(86, 145, 89, 23);
		login_panel.add(login_btn);
		
		JButton register_btn = new JButton("Register");
		register_btn.setBounds(196, 145, 89, 23);
		register_btn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// Go to the register paged
				Register register = new Register();
				register.setVisible(true);
				Login.this.dispose();
			}
			
		});
		login_panel.add(register_btn);
		
		password = new JPasswordField();
		password.setBounds(123, 107, 162, 20);
		login_panel.add(password);
		
		JPanel label_panel = new JPanel();
		label_panel.setBackground(Color.LIGHT_GRAY);
		label_panel.setBounds(10, 11, 414, 35);
		contentPane.add(label_panel);
		label_panel.setLayout(null);
		
		JLabel label = new JLabel("Bill Tracker");
		label.setFont(new Font("Tahoma", Font.BOLD, 18));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(73, 11, 201, 24);
		label_panel.add(label);
		
		
	}
}

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
	private JTextField username_field;
	private JPasswordField password_field;
	JLabel error_lbl;

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
		user_icon.setBounds(33, 40, 32, 32);
		login_panel.add(user_icon);
		
		JLabel password_icon = new JLabel("");
		Image password_img = new ImageIcon(this.getClass().getResource("/password.png")).getImage();
		password_icon.setIcon(new ImageIcon(password_img));
		password_icon.setBounds(33, 83, 32, 32);
		login_panel.add(password_icon);
		
		username_field = new JTextField();
		username_field.setBounds(123, 52, 162, 20);
		login_panel.add(username_field);
		username_field.setColumns(10);
		
		JButton login_btn = new JButton("Login");
		login_btn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				if (checkFields()) {
					if( checkUser(username_field.getText(), String.valueOf(password_field.getPassword()))) {
						Home home = new Home();
						home.setVisible(true);
						Login.this.dispose();
					}
				} else {
					error_lbl.setText("Fill all fields");
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
		
		password_field = new JPasswordField();
		password_field.setBounds(123, 95, 162, 20);
		login_panel.add(password_field);
		
		error_lbl = new JLabel("");
		error_lbl.setFont(new Font("Tahoma", Font.PLAIN, 10));
		error_lbl.setForeground(Color.RED);
		error_lbl.setBounds(54, 126, 231, 14);
		login_panel.add(error_lbl);
		
		JPanel heading_panel = new JPanel();
		heading_panel.setBackground(Color.LIGHT_GRAY);
		heading_panel.setBounds(10, 11, 414, 35);
		contentPane.add(heading_panel);
		heading_panel.setLayout(null);
		
		JLabel heading_lbl = new JLabel("Bill Tracker");
		heading_lbl.setFont(new Font("Tahoma", Font.BOLD, 18));
		heading_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		heading_lbl.setBounds(73, 11, 201, 24);
		heading_panel.add(heading_lbl);
		
		
	}
	
	/**
	 * checks the user in the database 
	 * @param username
	 * @param password
	 * @return true if the user exist
	 */
	public boolean checkUser(String username, String password) {
		
		boolean output = false;
		String query = "SELECT * FROM `users` WHERE `username` = ? AND `password` = ?";
		
		try {
			PreparedStatement st = My_CNX.getConnection().prepareStatement(query);
			st.setString(1, username);
			st.setString(2, password);
			ResultSet rs = st.executeQuery();
			
			if (rs.next()) {
				
				output = true;
			} else {
				//login error
				error_lbl.setText("Invald username or password");
			}
			
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		return output;
	}
	
	/**
	 * checks if all the fields are filled
	 * @return true if all are filled
	 */
	public boolean checkFields() {
		boolean output = true;
		
		if(username_field.getText().trim().isEmpty() || String.valueOf(password_field.getPassword()).trim().isEmpty()) {
			output = false;
		}
		
		return output;
	}
}

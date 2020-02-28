package billtracker.home;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import billtracker.login.CurrentUser;
import billtracker.login.Login;
import billtracker.login.My_CNX;
import billtracker.login.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class AddBill extends JFrame {

	private JPanel contentPane;
	private JTextField amount_field;
	private JTextField date_field;
	private JRadioButton water_rdbtn;
	private JRadioButton electricity_rdbtn;
	private JRadioButton food_rdbtn;
	private JLabel message_lbl;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddBill frame = new AddBill();
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
	public AddBill() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final ButtonGroup bgroup = new ButtonGroup();
		
		JPanel createBill_panel = new JPanel();
		createBill_panel.setBackground(Color.LIGHT_GRAY);
		createBill_panel.setBounds(10, 11, 377, 239);
		contentPane.add(createBill_panel);
		createBill_panel.setLayout(null);
		
		JLabel bill_lbl = new JLabel("Bill");
		bill_lbl.setFont(new Font("Tahoma", Font.BOLD, 15));
		bill_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		bill_lbl.setBounds(112, 0, 116, 20);
		createBill_panel.add(bill_lbl);
		
		JLabel amount_lbl = new JLabel("Amount");
		amount_lbl.setBounds(10, 31, 116, 20);
		createBill_panel.add(amount_lbl);
		
		amount_field = new JTextField();
		amount_field.setBounds(163, 31, 204, 20);
		createBill_panel.add(amount_field);
		amount_field.setColumns(10);
		
		JLabel date_lbl = new JLabel("Date (yyyy-mm-dd)");
		date_lbl.setBounds(10, 62, 116, 20);
		createBill_panel.add(date_lbl);
		
		date_field = new JTextField();
		date_field.setBounds(163, 62, 204, 20);
		createBill_panel.add(date_field);
		date_field.setColumns(10);
		
		JLabel type_lbl = new JLabel("Type");
		type_lbl.setBounds(10, 93, 46, 27);
		createBill_panel.add(type_lbl);
		
		JButton submit_btn = new JButton("Submit");
		submit_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checkFields()) {
					
					Double amount = Double.parseDouble(amount_field.getText());
					String date = date_field.getText();
					String bill_type = null;
					
					if(water_rdbtn.isSelected()) {
						bill_type = "Water";
					} else if(food_rdbtn.isSelected()) {
						bill_type = "Food";
					} else {
						bill_type = "Electricity";
					}
					
					if (insertBill(amount, date, bill_type)) {
						amount_field.setText("");
						date_field.setText("");
						bgroup.clearSelection();;
						message_lbl.setForeground(Color.BLACK);
						message_lbl.setText("");
						message_lbl.setText("Bill created ");
					}
				}
				
			}
		});
		submit_btn.setBounds(207, 184, 123, 23);
		createBill_panel.add(submit_btn);
		
		JPanel rgroup_panel = new JPanel();
		rgroup_panel.setBounds(85, 93, 282, 27);
		createBill_panel.add(rgroup_panel);
		rgroup_panel.setLayout(null);
		
		
		water_rdbtn = new JRadioButton("water");
		water_rdbtn.setBounds(6, 7, 73, 16);
		rgroup_panel.add(water_rdbtn);
		bgroup.add(water_rdbtn);
		
		electricity_rdbtn = new JRadioButton("Electricity");
		electricity_rdbtn.setBounds(97, 7, 85, 16);
		rgroup_panel.add(electricity_rdbtn);
		bgroup.add(electricity_rdbtn);
		
		food_rdbtn = new JRadioButton("Food");
		food_rdbtn.setBounds(198, 7, 53, 16);
		rgroup_panel.add(food_rdbtn);
		bgroup.add(food_rdbtn);
		
		JButton back_btn = new JButton("Go to Home");
		back_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home home = new Home();
				home.setVisible(true);
				AddBill.this.dispose();
			}
		});
		back_btn.setBounds(37, 184, 116, 23);
		createBill_panel.add(back_btn);
		
		message_lbl = new JLabel("");
		message_lbl.setBounds(59, 148, 248, 14);
		createBill_panel.add(message_lbl);
	}
	
	/**
	 * checks if all fields are filled
	 * @return true if filled
	 */
	public boolean checkFields() {
		
		if (!amount_field.getText().isEmpty() || !date_field.getText().isEmpty() 
				|| water_rdbtn.isSelected() 
				|| food_rdbtn.isSelected() 
				|| electricity_rdbtn.isSelected()) {
			return true;
		}
		
		message_lbl.setForeground(Color.RED);
		message_lbl.setText("");
		message_lbl.setText("All fields are required");
		return false;
	}
	
	/**
	 * insert a new bill into the database
	 * @param amount
	 * @param date
	 * @param bill_type
	 * @return true if bill was inserted
	 */
	public boolean insertBill(Double amount, String date, String bill_type) {
		
		boolean output = false;
		User user = new CurrentUser();
		String query = "INSERT INTO `bills`(`bill_amount`, `date`, `bill_type`, `user_id`) VALUE(?, ?, ?, ?)";
		
		try {
			PreparedStatement statement = My_CNX.getConnection().prepareStatement(query);
			statement.setDouble(1, amount);
			statement.setString(2, date);
			statement.setString(3, bill_type);
			statement.setLong(4, user.getId());
			
			int result = statement.executeUpdate();
			if (result == 1) {
				// added a new bill
				output = true;
			}
			
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		
		return output;
	}
}

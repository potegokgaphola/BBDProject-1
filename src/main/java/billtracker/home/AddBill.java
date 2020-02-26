package billtracker.home;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import billtracker.login.Login;
import billtracker.login.My_CNX;

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

public class AddBill extends JFrame {

	private JPanel contentPane;
	private JTextField amount;
	private JTextField date;
	private JRadioButton water_rdbtn;
	private JRadioButton electricity_rdbtn;
	private JRadioButton food_rdbtn;

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
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 32, 356, 218);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel bill_lbl = new JLabel("Bill");
		bill_lbl.setFont(new Font("Tahoma", Font.BOLD, 15));
		bill_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		bill_lbl.setBounds(92, 22, 91, 27);
		panel.add(bill_lbl);
		
		JLabel amount_lbl = new JLabel("Amount");
		amount_lbl.setBounds(10, 60, 46, 14);
		panel.add(amount_lbl);
		
		amount = new JTextField();
		amount.setBounds(72, 60, 111, 20);
		panel.add(amount);
		amount.setColumns(10);
		
		JLabel date_lbl = new JLabel("Date");
		date_lbl.setBounds(10, 102, 46, 14);
		panel.add(date_lbl);
		
		date = new JTextField();
		date.setBounds(72, 99, 111, 20);
		panel.add(date);
		date.setColumns(10);
		
		JLabel type_lbl = new JLabel("Type");
		type_lbl.setBounds(10, 142, 46, 14);
		panel.add(type_lbl);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//send bill to database
				PreparedStatement st;
				int rs;
				String query = "INSERT INTO `bills`(`amount`, `date`, `bill_type`, `user_id`) VALUE(?, ?, ?, ?)";
				
				//user data
				Integer Amount = Integer.parseInt(amount.getText());
				String Date = date.getText();
				String type = null;
				
				if (water_rdbtn.isSelected()) {
					type = "Water";
				} else if(electricity_rdbtn.isSelected()) {
					type = "Electricity";
				} else if(food_rdbtn.isSelected()) {
					type = "Food";
				}
				
				try {
					st = My_CNX.getConnection().prepareStatement(query);
					st.setLong(1, Amount);
					st.setString(2, Date);
					st.setString(3, type);
					st.setLong(4, 1);
					
					rs = st.executeUpdate();
					if (rs == 1) {
						// added a new bill
						JOptionPane.showMessageDialog(null, "bill created", "New Bill Added", 2);
					} else {
						// couldn't add bill
						JOptionPane.showMessageDialog(null, "There was an error in creating the bill", "Error", 2);
					}
					
				} catch (SQLException ex) {
					// TODO Auto-generated catch block
					Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		});
		btnNewButton.setBounds(86, 184, 89, 23);
		panel.add(btnNewButton);
		
		JPanel rgroup_panel = new JPanel();
		rgroup_panel.setBounds(48, 146, 282, 27);
		panel.add(rgroup_panel);
		rgroup_panel.setLayout(null);
		
		ButtonGroup bgroup = new ButtonGroup();
		
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
	}
}

package billtracker.home;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import billtracker.login.My_CNX;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class ViewBills extends JFrame {

	private JPanel contentPane;
	private JTextField date_Field;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewBills frame = new ViewBills();
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
	public ViewBills() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JPanel electricityBill_panel = new JPanel();
		electricityBill_panel.setBackground(Color.LIGHT_GRAY);
		electricityBill_panel.setBounds(10, 96, 117, 100);
		contentPane.add(electricityBill_panel);
		electricityBill_panel.setLayout(null);
		
		JLabel electricity_lbl = new JLabel("Electricity bill");
		electricity_lbl.setFont(new Font("Tahoma", Font.ITALIC, 11));
		electricity_lbl.setForeground(Color.WHITE);
		electricity_lbl.setBounds(10, 11, 97, 14);
		electricityBill_panel.add(electricity_lbl);
		
		JLabel no_eRecord_lbl = new JLabel("No records found");
		no_eRecord_lbl.setForeground(Color.WHITE);
		no_eRecord_lbl.setFont(new Font("Tahoma", Font.ITALIC, 11));
		no_eRecord_lbl.setBounds(10, 75, 97, 14);
		no_eRecord_lbl.setVisible(false);
		electricityBill_panel.add(no_eRecord_lbl);
		
		JPanel input_panel = new JPanel();
		input_panel.setBackground(Color.LIGHT_GRAY);
		input_panel.setBounds(10, 11, 414, 74);
		contentPane.add(input_panel);
		input_panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Specify the date(yyyy-mm-dd) of the bills you want:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(10, 7, 307, 22);
		input_panel.add(lblNewLabel_2);
		
		date_Field = new JTextField();
		date_Field.setBounds(329, 11, 75, 20);
		input_panel.add(date_Field);
		date_Field.setColumns(10);
		
		JButton showBills_btn = new JButton("Show Bills");
		showBills_btn.setForeground(Color.WHITE);
		showBills_btn.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		showBills_btn.setBackground(Color.LIGHT_GRAY);
		showBills_btn.setBounds(94, 40, 223, 23);
		input_panel.add(showBills_btn);
		
		JPanel waterBill_panel = new JPanel();
		waterBill_panel.setBackground(Color.LIGHT_GRAY);
		waterBill_panel.setBounds(156, 96, 123, 100);
		contentPane.add(waterBill_panel);
		waterBill_panel.setLayout(null);
		
		JLabel waterBill_lbl = new JLabel("Water bill");
		waterBill_lbl.setForeground(Color.WHITE);
		waterBill_lbl.setFont(new Font("Tahoma", Font.ITALIC, 11));
		waterBill_lbl.setBounds(10, 11, 97, 14);
		waterBill_panel.add(waterBill_lbl);
		
		JLabel no_wRecord_lbl = new JLabel("No records found");
		no_wRecord_lbl.setForeground(Color.WHITE);
		no_wRecord_lbl.setFont(new Font("Tahoma", Font.ITALIC, 11));
		no_wRecord_lbl.setBounds(10, 75, 97, 14);
		waterBill_panel.add(no_wRecord_lbl);
		
		JPanel food_panel = new JPanel();
		food_panel.setBackground(Color.LIGHT_GRAY);
		food_panel.setBounds(307, 96, 117, 100);
		contentPane.add(food_panel);
		food_panel.setLayout(null);
		
		JLabel foodBill_lbl = new JLabel("Food bill");
		foodBill_lbl.setForeground(Color.WHITE);
		foodBill_lbl.setFont(new Font("Tahoma", Font.ITALIC, 11));
		foodBill_lbl.setBounds(10, 11, 97, 14);
		food_panel.add(foodBill_lbl);
		
		JLabel no_fRecord_lbl = new JLabel("No records found");
		no_fRecord_lbl.setForeground(Color.WHITE);
		no_fRecord_lbl.setFont(new Font("Tahoma", Font.ITALIC, 11));
		no_fRecord_lbl.setBounds(10, 75, 97, 14);
		food_panel.add(no_fRecord_lbl);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.LIGHT_GRAY);
		panel_3.setBounds(10, 215, 414, 35);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		JButton home_btn = new JButton("Go to home");
		home_btn.setBackground(Color.LIGHT_GRAY);
		home_btn.setForeground(Color.WHITE);
		home_btn.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		home_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		home_btn.setBounds(34, 11, 108, 20);
		panel_3.add(home_btn);
	}
}

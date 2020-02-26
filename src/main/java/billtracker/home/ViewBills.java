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

public class ViewBills extends JFrame {

	private JPanel contentPane;
	private JTextField bill_date;

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
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JPanel panel = new JPanel();
		panel.setVisible(false);
		panel.setBounds(85, 92, 206, 113);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Bill");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 186, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Type");
		lblNewLabel_1.setBounds(10, 33, 46, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Amount");
		lblNewLabel_2.setBounds(10, 58, 46, 14);
		panel.add(lblNewLabel_2);
		
		final JLabel amount_view = new JLabel("");
		amount_view.setBounds(96, 58, 100, 14);
		panel.add(amount_view);
		
		final JLabel bill_type = new JLabel("");
		bill_type.setBounds(96, 33, 100, 14);
		panel.add(bill_type);
		
		JPanel input_panel = new JPanel();
		input_panel.setBounds(45, 40, 336, 28);
		contentPane.add(input_panel);
		input_panel.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Show electricity bill from: ");
		lblNewLabel_3.setBounds(21, 11, 101, 14);
		input_panel.add(lblNewLabel_3);
		
		bill_date = new JTextField();
		bill_date.setBounds(142, 8, 86, 20);
		input_panel.add(bill_date);
		bill_date.setColumns(10);
		
		JButton btnNewButton = new JButton("show");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PreparedStatement st;
				ResultSet rs;
				String query = "SELECT * FROM `bills` WHERE `date` = ?";
				
				try {
					String date = bill_date.getText();
							
					st = My_CNX.getConnection().prepareStatement(query);
					st.setString(1, date);
					rs = st.executeQuery();
					
					if (rs.next()) {
						String type = rs.getString("bill_type");
						String amount = rs.getString("amount");
						amount_view.setText(amount);
						bill_type.setText(type);
					}
					
				} catch (SQLException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
				
				panel.setVisible(true);
			}
		});
		btnNewButton.setBounds(255, 7, 72, 18);
		input_panel.add(btnNewButton);
	}

}

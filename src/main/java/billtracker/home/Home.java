package billtracker.home;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JInternalFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Home extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
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
	public Home() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(23, 57, 101, 182);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton addBill_btn = new JButton("add Bill");
		addBill_btn.setFont(new Font("Tahoma", Font.ITALIC, 11));
		addBill_btn.setForeground(Color.WHITE);
		addBill_btn.setBounds(10, 49, 84, 23);
		panel.add(addBill_btn);
		
		JButton viewBills_btn = new JButton("view Bill");
		viewBills_btn.setForeground(Color.WHITE);
		viewBills_btn.setFont(new Font("Tahoma", Font.ITALIC, 11));
		viewBills_btn.setBounds(10, 71, 84, 23);
		panel.add(viewBills_btn);
		viewBills_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewBills veiwBill = new ViewBills();
				veiwBill.setVisible(true);
				Home.this.dispose();
			}
		});
		addBill_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddBill addBill = new AddBill();
				addBill.setVisible(true);
				Home.this.dispose();
			}
		});
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(23, 21, 401, 25);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel welcome_lbl = new JLabel("Welcome, User");
		welcome_lbl.setHorizontalAlignment(SwingConstants.TRAILING);
		welcome_lbl.setForeground(Color.WHITE);
		welcome_lbl.setFont(new Font("Tahoma", Font.ITALIC, 11));
		welcome_lbl.setBounds(257, 11, 134, 14);
		panel_1.add(welcome_lbl);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.LIGHT_GRAY);
		panel_2.setBounds(134, 57, 290, 182);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnNewButton = new JButton("show graph ");
		btnNewButton.setBounds(78, 137, 127, 23);
		panel_2.add(btnNewButton);
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setFont(new Font("Tahoma", Font.ITALIC, 11));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BillGraph billGraph = new BillGraph("Water Graph", "Progess for water");
				billGraph.pack();
				billGraph.setVisible(true);
			}
		});
	}
}

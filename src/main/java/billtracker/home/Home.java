package billtracker.home;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		panel.setBounds(161, 61, 159, 137);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton viewbills_btn = new JButton("View Bills");
		viewbills_btn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				ViewBills viewbills = new ViewBills();
				viewbills.setVisible(true);
				Home.this.dispose();
			}
			
		});
		viewbills_btn.setBounds(25, 28, 89, 23);
		panel.add(viewbills_btn);
		
		JButton viewGraph_btn = new JButton("View Graph");
		viewGraph_btn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				BillsGraph billsgraph = new BillsGraph();
				billsgraph.setVisible(true);
				Home.this.dispose();
			}
			
		});
		viewGraph_btn.setBounds(25, 62, 89, 23);
		panel.add(viewGraph_btn);
		
		JButton addBill_btn = new JButton("Add Bill");
		addBill_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddBill addbill = new AddBill();
				addbill.setVisible(true);
				Home.this.dispose();
			}
		});
		addBill_btn.setBounds(25, 103, 89, 23);
		panel.add(addBill_btn);
	}

}

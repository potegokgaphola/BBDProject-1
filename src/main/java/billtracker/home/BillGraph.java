package billtracker.home;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import org.jfree.chart.JFreeChart;
//import org.jfree.chart.ChartFactor
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import billtracker.login.My_CNX;

public class BillGraph extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BillGraph frame = new BillGraph("" ,"" , "", "", "");
					frame.pack();
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
	public BillGraph(String applicationTitle, String chartTitle,String bill_type,String startDate,String endDate) {
		super(applicationTitle);
		JFreeChart lineChart = ChartFactory.createLineChart(chartTitle, 
				"Date", "Bill Amount",
				createDataset(startDate, endDate, bill_type), 
				PlotOrientation.VERTICAL,
				true, true, false);
		ChartPanel chartPanel = new ChartPanel(lineChart);
		chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
		setContentPane( chartPanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private DefaultCategoryDataset createDataset(String startDate, String endDate, String bill_type) {
	      DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
	      
		  PreparedStatement st;
		  ResultSet rs;
		  String query  = "SELECT `bill_amount`, `date` FROM `bills` WHERE `date` BETWEEN ? AND ? AND `bill_type`=? ORDER BY `date` ASC";
		  
		  try {
			st = My_CNX.getConnection().prepareStatement(query);
			st.setString(1, startDate);
			st.setString(2, endDate);
			st.setString(3, bill_type);
			
			rs = st.executeQuery();
			while (rs.next()) {
				
				dataset.addValue( Double.parseDouble(rs.getString("bill_amount")) 
						, bill_type.toLowerCase() 
						, rs.getString("date"));
			}
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		  
	    return dataset;
	}

}

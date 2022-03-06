package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.Button;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import dblogic.dblogic;
import java.io.FileWriter; 

public class SalesReport extends dblogic{
	
	//static variables
	static String[][] arr_for_sales = new String[10000][4];

	JFrame SalesReport;
	private JPanel main_panel;
	private JLabel txtDate;
	private JLabel lblHome;
	private JLabel lblDate;
	private JLabel lblArrow1;
	private JLabel lblSalesReport;
	private JLabel lblSortBy;
	private JComboBox<String> comboSort;
	private Date date;
	private JTableHeader header1;
	private JTable inventoryTable;
	private JScrollPane scrollPane1;
	private Button btnExport;
	private Button btnLoad;
	private DefaultTableCellRenderer centerRenderer;

	private void initialize() {
		
	
		
		
		SalesReport = new JFrame("Retail POS System");
		SalesReport.setBounds(100, 100, 1200, 650);
		SalesReport.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SalesReport.setVisible(true);
		SalesReport.setResizable(false);
     
		main_panel = new JPanel();
		main_panel.setBackground(new Color(246,244,230));
		main_panel.setBounds(0, 0, 730, 401);
		main_panel.setLayout(null);
		SalesReport.getContentPane().add(main_panel);
		
		lblHome = new JLabel("Home");
        lblHome.addMouseListener(new MouseAdapter() {
        	public void mouseEntered(MouseEvent e) {
        		lblHome.setForeground(new Color(74,83,68));
        	}
        	public void mouseExited(MouseEvent e) {
        		lblHome.setForeground(new Color (229, 149, 96));
        	}
        	public void mouseClicked(MouseEvent e) {
        		SalesReport.dispose();
        		SelectTransaction.main(null);
        	}
        });
        lblHome.setHorizontalAlignment(SwingConstants.LEFT);
        lblHome.setForeground(new Color(229, 149, 96));
        lblHome.setFont(new Font("Tahoma", Font.PLAIN, 30));
        lblHome.setBounds(21, 12, 85, 34);
        main_panel.add(lblHome);
		
        lblArrow1 = new JLabel(">");
        lblArrow1.setHorizontalAlignment(SwingConstants.LEFT);
        lblArrow1.setForeground(new Color(229, 149, 96));
        lblArrow1.setFont(new Font("Tahoma", Font.PLAIN, 30));
        lblArrow1.setBounds(105, 11, 25, 34);
        main_panel.add(lblArrow1);
        
        lblSalesReport = new JLabel("Sales Transaction Report");
        lblSalesReport.setHorizontalAlignment(SwingConstants.LEFT);
        lblSalesReport.setForeground(new Color(130,166,145));
        lblSalesReport.setFont(new Font("Tahoma", Font.PLAIN, 30));
        lblSalesReport.setBounds(133, 13, 343, 34);
        main_panel.add(lblSalesReport);
		
        lblDate = new JLabel("Date:");
        lblDate.setForeground(new Color(229, 149, 96));
        lblDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblDate.setBounds(975, 22, 42, 24);
        main_panel.add(lblDate);
        
        txtDate = new JLabel();
        txtDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txtDate.setForeground(new Color(74,83,68));
        txtDate.setHorizontalAlignment(SwingConstants.CENTER);
        txtDate.setBounds(1014, 22, 125, 24);
        main_panel.add(txtDate);
     
        date = new Date();
		txtDate.setText(new SimpleDateFormat("d MMMM yyyy").format(date));

		//INVENTORY TABLE
        scrollPane1 = new JScrollPane();
        scrollPane1.setBorder(new LineBorder(new Color(51, 79, 33), 2, true));
        scrollPane1.setBackground(new Color(197, 224, 180));
        scrollPane1.setBounds(42, 112, 1108, 410);
        main_panel.add(scrollPane1);
        
        inventoryTable = new JTable();
        inventoryTable.setEnabled(false);
        scrollPane1.setViewportView(inventoryTable);

        header1 = inventoryTable.getTableHeader();
        header1.setFont(new Font("Tahoma", Font.BOLD, 12));
        inventoryTable.setModel(new DefaultTableModel (
                arr_for_sales,
                new String [] {
                   "Order Date", "Order ID", "Amount", "Order Status"
                }
        ));
        inventoryTable.setAutoCreateRowSorter(false);
        inventoryTable.setFont(new Font("Tahoma", Font.PLAIN, 16));
        inventoryTable.setSelectionBackground(new java.awt.Color(153, 153, 255));
        scrollPane1.setViewportView(inventoryTable);
        
        centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        inventoryTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        inventoryTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        inventoryTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        inventoryTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        //--
        
   
        btnExport = new Button("Export txt File");
        btnExport.addMouseListener(new MouseAdapter() {
        	public void mouseEntered(MouseEvent e) {
        		btnExport.setBackground(new Color (229,149,96));
        	}
        	public void mouseExited(MouseEvent e) {
        		btnExport.setBackground(new Color(186, 186, 186));
        	}
        	public void mouseClicked(MouseEvent arg0) {
        		try {
	        		FileWriter fileSales = new FileWriter("sales.txt");
	        		int j = 0;
	        		fileSales.write("Order date  Order ID \tAmount  \tStatus\n");
	        		while(true) {
	        			if(arr_for_sales[j][0] == null) {
	        				break;
	        			}
	        			else {
	        				fileSales.write(arr_for_sales[j][0]+"\t"+arr_for_sales[j][1]+"\t"+arr_for_sales[j][2]+"\t"+arr_for_sales[j][3]+"\n");
		        			j++;
	        			
	        			}
	        			
	        		}
	        		fileSales.close();
        		}
        		catch(Exception ex31) {
        			System.out.println(ex31+"ex31");
        		}
        		

        		JOptionPane.showMessageDialog(SalesReport, "Sales Report Exported Successfully!");
        		
        		
        		
        		SalesReport.dispose();
        		SelectTransaction.main(null);
        	}
        });
        btnExport.setForeground(Color.WHITE);
        btnExport.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnExport.setBackground(new Color(186, 186, 186));
        btnExport.setBounds(1033, 82, 114, 24);
        main_panel.add(btnExport);
        
        btnLoad = new Button("LOAD REPORT");
        btnLoad.addMouseListener(new MouseAdapter() {
        	public void mouseEntered(MouseEvent e) {
        		btnLoad.setBackground(new Color (229, 149, 96));
        	}
        	public void mouseExited(MouseEvent e) {
        		btnLoad.setBackground(new Color(130,166,145));
        	}
        	public void mouseClicked(MouseEvent e) {
        		
        		//getting the SALES data
        		try {
        			int ctr = 0;
        			ps = con.prepareStatement("Select * FROM SALES");
        			rs = ps.executeQuery();
        			while(rs.next()) {
        				arr_for_sales[ctr][0] = rs.getString("Sale_Date");
        				arr_for_sales[ctr][1] = rs.getString("Ord_No");
        				arr_for_sales[ctr][2] = rs.getString("Sale_GrandTotal");
        				arr_for_sales[ctr][3] = rs.getString("Sale_State");
        				ctr++;
        			}
        		}
        		catch(Exception ex30) {
        			System.out.println(ex30+"ex30");
        		}
     			
        		SalesReport.dispose();
        		SalesReport sr = new SalesReport();
     			
    			sr.SalesReport.setVisible(true);
        		
        		JOptionPane.showMessageDialog(SalesReport, "Sales Report Loaded Successfully!");
        	}
        });
        btnLoad.setForeground(Color.WHITE);
        btnLoad.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnLoad.setBackground(new Color(130, 166, 145));
        btnLoad.setBounds(537, 542, 143, 34);
        main_panel.add(btnLoad);   

	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SalesReport window = new SalesReport();
					window.SalesReport.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public SalesReport() {
		initialize();
	}
}
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
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import dblogic.dblogic;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;   


public class OrderSummary extends dblogic{
	//static initialization
	
	static float total_php, change_amount = 0;
	
	//for ORDER and SALES insertion
	//Ord no, Sale_Date, Sale_State, Sale_grandTotal=total_php, Sale_Tendered, Sale_Change, Emp_ID
	static String ord_no_present;
	static String ord_date, Sale_State;
	static int emp_ID;
	static float Sale_grandTotal, sale_Tendered, Sale_Change;
	
	

	

	JFrame OrderSummary;
	private JPanel main_panel;
	private JLabel txtDate;
	private JLabel lblHome;
	private JLabel lblDate;
	private JLabel lblArrow1;
	private JLabel lblCreate;
	private JLabel lblOrderNo;
	private JLabel lblTenderedAmt;
	private JLabel lblChange;
	private JLabel lblGrandTotal;
	private JLabel lblArrow2;
	private JLabel lblOrderSummary;
	private JLabel txtOrderNo;
	private JTextField txtTenderedAmt;
	private JTextField txtChange;
	private JTextField txtGrandTotal;
	private Date date;
	private JScrollPane scrollPane1;
	private JTableHeader header1;
	private JTable cartTable;
	private Button btnProceed;
	private Button btnCancel;
	private DefaultTableCellRenderer centerRenderer;
	
	

	private void initialize() {
		
		try {
			
			 String[][] arr_for_sumTable = new String[10000][4];
			
			 
			 CreateOrder co = new CreateOrder();
			 
			 
			 //lumalabas kasi yung createOrder hehe
		    co.CreateOrder.setVisible(false);
			 
			 //gets table from CreateOrder
			 arr_for_sumTable = co.getCart();
			 total_php = co.getTotal();
			 
			 
				// get order no
 			try {
 				int highest = -99999;
 				int ord_no;
 				ps = con.prepareStatement("select * from ORDER");
 				rs = ps.executeQuery();
 				while(rs.next()) {
 			
 					ord_no = rs.getInt("Ord_No");
 					if(ord_no>highest) {
 						highest = ord_no;
 					}
 					
 				
 				}
 				ord_no_present = String.valueOf(highest+1);
 			}
 			catch(Exception ex5) {
 				System.out.println(ex5+"ex5");
 			}
			 
			 
			 
			
			
			
			OrderSummary = new JFrame("Retail POS System");
			OrderSummary.setBounds(100, 100, 1200, 650);
			OrderSummary.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			OrderSummary.setVisible(true);
			OrderSummary.setResizable(false);
	     
			main_panel = new JPanel();
			main_panel.setBackground(new Color(246,244,230));
			main_panel.setBounds(0, 0, 730, 401);
			main_panel.setLayout(null);
			OrderSummary.getContentPane().add(main_panel);
			
			lblHome = new JLabel("Home");
	        lblHome.addMouseListener(new MouseAdapter() {
	        	public void mouseEntered(MouseEvent e) {
	        		lblHome.setForeground(new Color(74,83,68));
	        	}
	        	public void mouseExited(MouseEvent e) {
	        		lblHome.setForeground(new Color (229, 149, 96));
	        	}
	        	public void mouseClicked(MouseEvent e) {
	            	OrderSummary.dispose();
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
	        
	        lblCreate = new JLabel("Create Order");
	        lblCreate.addMouseListener(new MouseAdapter() {
	        	public void mouseEntered(MouseEvent e) {
	        		lblCreate.setForeground(new Color(74,83,68));
	        	}
	        	public void mouseExited(MouseEvent e) {
	        		lblCreate.setForeground(new Color (229, 149, 96));
	        	}
	        	public void mouseClicked(MouseEvent e) {
	            	OrderSummary.dispose();
	    			CreateOrder.main(null);
	        	}
	        });
	        lblCreate.setHorizontalAlignment(SwingConstants.LEFT);
	        lblCreate.setForeground(new Color(229, 149, 96));
	        lblCreate.setFont(new Font("Tahoma", Font.PLAIN, 30));
	        lblCreate.setBounds(133, 13, 177, 34);
	        main_panel.add(lblCreate);
	        
	        lblArrow2 = new JLabel(">");
	        lblArrow2.setHorizontalAlignment(SwingConstants.LEFT);
	        lblArrow2.setForeground(new Color(229, 149, 96));
	        lblArrow2.setFont(new Font("Tahoma", Font.PLAIN, 30));
	        lblArrow2.setBounds(312, 12, 25, 34);
	        main_panel.add(lblArrow2);
			
	        lblOrderSummary = new JLabel("Order Summary");
	        lblOrderSummary.setHorizontalAlignment(SwingConstants.LEFT);
	        lblOrderSummary.setForeground(new Color(130,166,145));
	        lblOrderSummary.setFont(new Font("Tahoma", Font.PLAIN, 30));
	        lblOrderSummary.setBounds(338, 13, 222, 34);
	        main_panel.add(lblOrderSummary);
	        
	        lblDate = new JLabel("Date:");
	        lblDate.setForeground(new Color(229, 149, 96));
	        lblDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
	        lblDate.setBounds(982, 33, 42, 24);
	        main_panel.add(lblDate);
	
	        txtDate = new JLabel();
	        txtDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
	        txtDate.setForeground(new Color(74,83,68));
	        txtDate.setHorizontalAlignment(SwingConstants.CENTER);
	        txtDate.setBounds(1021, 33, 125, 24);
	        main_panel.add(txtDate);
	        
	        date = new Date();
			txtDate.setText(new SimpleDateFormat("d MMMM yyyy").format(date));
	        
	        lblOrderNo = new JLabel("Order Number:");
	        lblOrderNo.setForeground(new Color(130, 166, 145));
	        lblOrderNo.setFont(new Font("Tahoma", Font.PLAIN, 20));
	        lblOrderNo.setBounds(44, 57, 156, 34);
	        main_panel.add(lblOrderNo);
	        
	        txtOrderNo = new JLabel();
	        txtOrderNo.setFont(new Font("Tahoma", Font.BOLD, 11));
	        txtOrderNo.setText(ord_no_present);
	        txtOrderNo.setHorizontalAlignment(SwingConstants.CENTER);
	        txtOrderNo.setBounds(182, 62, 202, 24);
	        main_panel.add(txtOrderNo);
	        
	        //ORDER SUMMARY TABLE
	        scrollPane1 = new JScrollPane();
	        scrollPane1.setBorder(new LineBorder(new Color(51, 79, 33), 2, true));
	        scrollPane1.setBackground(new Color(197, 224, 180));
	        scrollPane1.setBounds(44, 95, 1102, 322);
	        main_panel.add(scrollPane1);
	        
	        cartTable = new JTable();
	        cartTable.setEnabled(false);
	        scrollPane1.setViewportView(cartTable);
	
	        header1 = cartTable.getTableHeader();
	        header1.setFont(new Font("Tahoma", Font.BOLD, 12));
	        cartTable.setModel(new DefaultTableModel (
	                arr_for_sumTable,
	                new String [] {
	                    "Item ID", "Item Name", "Unit Price", "Qty"
	                }
	        ));
	        cartTable.setAutoCreateRowSorter(false);
	        cartTable.setFont(new Font("Tahoma", Font.PLAIN, 16));
	        cartTable.setSelectionBackground(new java.awt.Color(153, 153, 255));
	        scrollPane1.setViewportView(cartTable);
	        
	        centerRenderer = new DefaultTableCellRenderer();
	        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
	        cartTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
	        cartTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
	        cartTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
	        //--
	        
	        lblTenderedAmt = new JLabel("Tendered Amount:");
	        lblTenderedAmt.setHorizontalAlignment(SwingConstants.LEFT);
	        lblTenderedAmt.setForeground(new Color(229, 149, 96));
	        lblTenderedAmt.setFont(new Font("Tahoma", Font.PLAIN, 16));
	        lblTenderedAmt.setBounds(44, 450, 135, 24);
	        main_panel.add(lblTenderedAmt);
	        
	        txtTenderedAmt = new JTextField();
	        txtTenderedAmt.setHorizontalAlignment(SwingConstants.CENTER);
	        txtTenderedAmt.setBounds(189, 450, 202, 24);
	        main_panel.add(txtTenderedAmt);
	        txtTenderedAmt.setColumns(10);
	        
	        lblChange = new JLabel("Change Amount:");
	        lblChange.setHorizontalAlignment(SwingConstants.LEFT);
	        lblChange.setForeground(new Color(229, 149, 96));
	        lblChange.setFont(new Font("Tahoma", Font.PLAIN, 16));
	        lblChange.setBounds(44, 498, 135, 24);
	        main_panel.add(lblChange);
	        
	        txtChange = new JTextField();
	        txtChange.setHorizontalAlignment(SwingConstants.CENTER);
	        txtChange.setColumns(10);
	        txtChange.setEditable(false);
	        txtChange.setBounds(189, 498, 202, 24);
	        main_panel.add(txtChange);
	        
	        lblGrandTotal = new JLabel("GRAND TOTAL:");
	        lblGrandTotal.setHorizontalAlignment(SwingConstants.LEFT);
	        lblGrandTotal.setForeground(new Color(229, 149, 96));
	        lblGrandTotal.setFont(new Font("Tahoma", Font.BOLD, 20));
	        lblGrandTotal.setBounds(818, 438, 156, 35);
	        main_panel.add(lblGrandTotal);
	        
	        txtGrandTotal = new JTextField();
	        txtGrandTotal.setText(total_php+" PHP");
	        txtGrandTotal.setFont(new Font("Tahoma", Font.BOLD, 15));
	        txtGrandTotal.setEditable(false);
	        txtGrandTotal.setHorizontalAlignment(SwingConstants.RIGHT);
	        txtGrandTotal.setColumns(10);
	        txtGrandTotal.setBounds(984, 438, 165, 35);
	        main_panel.add(txtGrandTotal);
	
	        btnCancel = new Button("CANCEL");
	        btnCancel.addMouseListener(new MouseAdapter() {
	        	public void mouseEntered(MouseEvent e) {
	        		btnCancel.setBackground(new Color (229,149,96));
	        	}
	        	public void mouseExited(MouseEvent e) {
	        		btnCancel.setBackground(new Color(130, 166, 145));
	        	}
	        	public void mouseClicked(MouseEvent e) {
	        		
	        		int a = JOptionPane.showConfirmDialog(null, "Are you sure you do not wish to proceed?", "Select action", JOptionPane.YES_NO_OPTION);
	        		
	            	if (a == JOptionPane.YES_OPTION) {
	            		OrderSummary.dispose();
	            		CreateOrder.main(null);
	            	}
	        	}
	        });
	        btnCancel.setForeground(Color.WHITE);
	        btnCancel.setFont(new Font("Tahoma", Font.BOLD, 20));
	        btnCancel.setBackground(new Color(130, 166, 145));
	        btnCancel.setBounds(874, 549, 125, 34);
	        main_panel.add(btnCancel);
	        
	        btnProceed = new Button("PLACE ORDER");
	        btnProceed.addMouseListener(new MouseAdapter() {
	        	public void mouseEntered(MouseEvent e) {
	        		btnProceed.setBackground(new Color (229,149,96));
	        	}
	        	public void mouseExited(MouseEvent e) {
	        		btnProceed.setBackground(new Color(130, 166, 145));
	        	}
	        	public void mouseClicked(MouseEvent e) {
	        		
	        		
	        		//Getting Tendered amount and calculating the change
	        		sale_Tendered = Float.parseFloat(txtTenderedAmt.getText());
	        		change_amount =  sale_Tendered - total_php;
	        	
	        		if(change_amount<0) {
	        			System.out.println("Invalid transaction. Insufficient payment");
	        			txtTenderedAmt.setText("");
	        			change_amount = 0;
	        		}
	        		else {
	        			
	        			txtChange.setText(change_amount+" PHP");
	        			
	        			//Insert to ORDER Table
	        			try {
	        				 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
	        				  LocalDateTime now = LocalDateTime.now();  
	        				  
	        				  
	        			       Date date2= new Date();
	        			       String string_date = new String(dtf.format(now));
	        			       int emp_id = Integer.parseInt(LoggedUser.substring(6,7));
	        			       emp_ID = emp_id;
	        				  System.out.println(string_date);
	        				  ord_date = string_date;
		        			 ps = con.prepareStatement("insert into ORDER (Ord_Date, Emp_Id) "+"VALUES ("+"'"+string_date+"'"+","+"'"+emp_id+"'"+")");
		        			 ps.executeUpdate();
	        			}
		        		catch(Exception ex4) {
		        			System.out.println(ex4+"ex4");
		        		}
	        			
	        		
	        			
	        			//Try ko lang i print yung order table
	        			try {
	        				ps = con.prepareStatement("select * from ORDER");
	        				rs = ps.executeQuery();
	        				System.out.println("ORDER TABLE");
	        				while(rs.next()) {
	        					System.out.println("Ord no: "+rs.getString("Ord_No"));
	        					System.out.println("Ord date: "+rs.getString("Ord_Date"));
	        					System.out.println("Emp ID: "+rs.getString("Emp_ID"));
	        				}
	        			}
	        			catch(Exception ex5) {
	        				System.out.println(ex5+"ex5");
	        			}
	        			
	        			//insert to SALES table
	        			/**/
	        			//Ord no, Sale_Date, Sale_State, Sale_grandTotal=total_php, Sale_Tendered, Sale_Change, Emp_ID
	        			try {
	        				Sale_State = "COMPLETED";
	        				ps = con.prepareStatement("insert into SALES (Ord_No, Sale_Date, Sale_State, Sale_GrandTotal, Sale_Tendered, Sale_Change, Emp_ID) "+
	        			"VALUES ("+"'"+Integer.parseInt(ord_no_present)+"'"+","+"'"+ord_date+"'"+","+"'"+Sale_State+"'"+","+"'"+total_php+"'"+","+"'"+sale_Tendered+
	        			"'"+","+"'"+change_amount+"'"+","+"'"+emp_ID+"'"+")");
	        				ps.executeUpdate();
	        			}
	        			catch(Exception ex6) {
	        				System.out.println(ex6+"ex6");
	        			}
	        			
	        			//Print ko lang SALES table;
	        			/*try {
	        				ps = con.prepareStatement("select * from SALES");
	        				rs = ps.executeQuery();
	        				System.out.println("SALESTABLE");
	        				while(rs.next()) {
	        					System.out.println("Ord no: "+rs.getString("Ord_No"));
	        					System.out.println("Sale date: "+rs.getString("Sale_Date"));
	        					System.out.println("Sale State: "+rs.getString("Sale_State"));
	        					System.out.println("total: "+rs.getString("Sale_GrandTotal"));
	        					System.out.println("tendered: "+rs.getString("Sale_Tendered"));
	        					System.out.println("change: "+rs.getString("Sale_Change"));
	        					System.out.println("Emp ID: "+rs.getString("Emp_ID"));
	        				}
	        			}
	        			catch(Exception ex7) {
	        				System.out.println(ex7+"ex7");
	        			}*/
	        			
	        			
	        			
	        			
	        			
	        			
	        			CreateOrder clear = new CreateOrder();
	        			clear.clearTable(true);
	        			clear.finalizeCart(Integer.parseInt(ord_no_present));
	        			clear.CreateOrder.setVisible(false);
		        		
	        			
	        			
	        			
	        		}
	        		
	        	
	        		
	        		
	        		
	        		OrderSummary.dispose();
	        		JOptionPane.showMessageDialog(null, "Order completed!");
	        		SelectTransaction.main(null);
	        	}
	        });        
	        btnProceed.setFont(new Font("Tahoma", Font.BOLD, 13));
	        btnProceed.setBounds(1021, 547, 125, 34);
	        btnProceed.setBackground(new Color(130,166,145));
	        btnProceed.setForeground(new Color(255, 255, 255));
	        main_panel.add(btnProceed);
		}
		catch(Exception ex11) {
			System.out.println(ex11+"ex11");
		}

	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderSummary window = new OrderSummary();
					window.OrderSummary.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public OrderSummary() {
		initialize();
	}
}
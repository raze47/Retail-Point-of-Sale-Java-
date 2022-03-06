package gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JTextArea;
import dblogic.dblogic;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;   

public class RefundOrder extends dblogic {

	//static variables
	static String[][] arr_for_refund = new String[10000][4];
	static int ord_no = 0;
	static float grandTotal = 0;
	static int[] originalStk = new int[10000];
	
	JFrame RefundOrder;
	private JPanel main_panel;
	private JPanel otherPanel;
	private JLabel txtDate;
	private JLabel lblHome;
	private JLabel lblDate;
	private JLabel lblArrow1;
	private JLabel lblRefundOrder;
	private JLabel lblOrderNo;
	private JLabel lblGrandTotal;
	private JLabel lblReason;
	private JLabel lblOthers;
	private JTextField txtGrandTotal;
	private JTextField txtOrderNo;
	private JTextArea textArea;
	private JScrollPane scrollPane1;
	private JScrollPane scrollPane2;
	private JComboBox <String> comboReason;
	private Date date;
	private JTableHeader header1;
	private JTable cartTable;
	private Button btnCancel;
	private Button btnSearch;
	private Button btnRefund;
	private DefaultTableCellRenderer centerRenderer;
	
	private void initialize() {
			
		try {
			RefundOrder = new JFrame("Retail POS System");
			RefundOrder.setBounds(100, 100, 1200, 650);
			RefundOrder.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			RefundOrder.setVisible(true);
			RefundOrder.setResizable(false);
	     
			main_panel = new JPanel();
			main_panel.setBackground(new Color(246,244,230));
			main_panel.setBounds(0, 0, 730, 401);
			main_panel.setLayout(null);
			RefundOrder.getContentPane().add(main_panel);
			
			lblHome = new JLabel("Home");
	        lblHome.addMouseListener(new MouseAdapter() {
	        	public void mouseEntered(MouseEvent e) {
	        		lblHome.setForeground(new Color(74,83,68));
	        	}
	        	public void mouseExited(MouseEvent e) {
	        		lblHome.setForeground(new Color (229, 149, 96));
	        	}
	        	public void mouseClicked(MouseEvent e) {
	        		RefundOrder.dispose();
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
	        
	        lblRefundOrder = new JLabel("Refund Order");
	        lblRefundOrder.setHorizontalAlignment(SwingConstants.LEFT);
	        lblRefundOrder.setForeground(new Color(130,166,145));
	        lblRefundOrder.setFont(new Font("Tahoma", Font.PLAIN, 30));
	        lblRefundOrder.setBounds(133, 13, 192, 34);
	        main_panel.add(lblRefundOrder);
	        
	        lblDate = new JLabel("Date:");
	        lblDate.setForeground(new Color(229, 149, 96));
	        lblDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
	        lblDate.setBounds(978, 22, 42, 24);
	        main_panel.add(lblDate);
	        
	        txtDate = new JLabel();
	        txtDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
	        txtDate.setForeground(new Color(74,83,68));
	        txtDate.setHorizontalAlignment(SwingConstants.CENTER);
	        txtDate.setBounds(1017, 22, 125, 24);
	        main_panel.add(txtDate);
	     
	        date = new Date();
			txtDate.setText(new SimpleDateFormat("d MMMM yyyy").format(date));
	        
	        lblOrderNo = new JLabel("Order Number:");
	        lblOrderNo.setForeground(new Color(130, 166, 145));
	        lblOrderNo.setFont(new Font("Tahoma", Font.PLAIN, 20));
	        lblOrderNo.setBounds(54, 61, 140, 34);
	        main_panel.add(lblOrderNo);
	        
	        txtOrderNo = new JTextField();
	        txtOrderNo.setHorizontalAlignment(SwingConstants.CENTER);
	        txtOrderNo.setBounds(192, 66, 202, 24);
	        main_panel.add(txtOrderNo);
	        
	        btnSearch = new Button("SEARCH");
	        btnSearch.addMouseListener(new MouseAdapter() {
	        	public void mouseEntered(MouseEvent e) {
	        		btnSearch.setBackground(new Color (229,149,96));
	        	}
	        	public void mouseExited(MouseEvent e) {
	        		btnSearch.setBackground(new Color(130, 166, 145));
	        	} 
	        	public void mouseClicked(MouseEvent arg0) {
	        		int tableCtr = 0;
	        		grandTotal = 0;
	        		ord_no = Integer.parseInt(txtOrderNo.getText());
	        		
	        		for(int k = 0; k<10000; k++) {
            			arr_for_refund[k][0] = null;
            			arr_for_refund[k][1] = null;
            			arr_for_refund[k][2] = null;
            			arr_for_refund[k][3] = null;
            		}
					
					
	        		
	        		try {
	        			ps = con.prepareStatement("Select * FROM CART WHERE Ord_No=?");
	        			ps.setInt(1, ord_no);
	        			rs = ps.executeQuery();
	        			System.out.println("Loob ng try");
	        			while(rs.next()) {
	        				//Nested para makuha yung itemID
	        				System.out.println("loob ng rs.next()");
	        				System.out.println(rs.getString("Ord_No"));
	        				try {
	        					
	        					//clear table
	        				
	        					
	        					
	        					ResultSet rs2;
	        					ps = con.prepareStatement("Select * FROM PRODUCT WHERE Prod_No=?");
	        					ps.setInt(1, Integer.parseInt(rs.getString("Prod_No")));
	        					rs2 = ps.executeQuery();
	        					
	        					//assigning values to table
	        					while(rs2.next()) {
	        			
	        						
	        						
	        						arr_for_refund[tableCtr][0] = rs2.getString("Prod_No");
	        						arr_for_refund[tableCtr][1] = rs2.getString("Prod_Name");
	        						arr_for_refund[tableCtr][2] = rs2.getString("Prod_OrgPrc");
	        						arr_for_refund[tableCtr][3] = rs.getString("Prod_Qty");
	        						
	        						
	        						if(Integer.parseInt(arr_for_refund[tableCtr][3]) == 0) {
	        							arr_for_refund[tableCtr][0] = null;
		        						arr_for_refund[tableCtr][1] = null;
		        						arr_for_refund[tableCtr][2] = null;
		        						arr_for_refund[tableCtr][3] = null;
	        						}
	        						
	        						
	        						tableCtr++;
	        						grandTotal += Float.parseFloat(rs2.getString("Prod_OrgPrc")) * Float.parseFloat(rs.getString("Prod_Qty"));;
	        						System.out.println("table ctr: "+tableCtr);
	        						System.out.println("grand total: "+grandTotal);
	        					}
	        					
	        					
	        				}
	        			
	        				catch(Exception ex21) {
	        					System.out.println(ex21+"ex21");
	        				}
	        				
	        			}
	        			tableCtr = 0;
	        			RefundOrder.dispose();
	        			
	        			RefundOrder window1 = new RefundOrder();
	        			window1.RefundOrder.setVisible(true);
	        		
	        		}
	        		catch(Exception ex20) {
	        			System.out.println(ex20+"ex20");
	        		}
	        	
	        		
	        			
	        	}
	        });
	        btnSearch.setForeground(Color.WHITE);
	        btnSearch.setFont(new Font("Tahoma", Font.BOLD, 15));
	        btnSearch.setBackground(new Color(130, 166, 145));
	        btnSearch.setBounds(400, 64, 98, 24);
	        main_panel.add(btnSearch);
	        
	        //CART TABLE
	        scrollPane1 = new JScrollPane();
	        scrollPane1.setBorder(new LineBorder(new Color(51, 79, 33), 2, true));
	        scrollPane1.setBackground(new Color(197, 224, 180));
	        scrollPane1.setBounds(54, 95, 804, 401);
	        main_panel.add(scrollPane1);
	        
	        cartTable = new JTable();
	        cartTable.setEnabled(false);
	        scrollPane1.setViewportView(cartTable);
	
	        header1 = cartTable.getTableHeader();
	        header1.setFont(new Font("Tahoma", Font.BOLD, 12));
	        cartTable.setModel(new DefaultTableModel (
	                arr_for_refund,
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
	
	        lblGrandTotal = new JLabel("GRAND TOTAL:");
	        lblGrandTotal.setHorizontalAlignment(SwingConstants.LEFT);
	        lblGrandTotal.setForeground(new Color(229, 149, 96));
	        lblGrandTotal.setFont(new Font("Tahoma", Font.BOLD, 20));
	        lblGrandTotal.setBounds(51, 508, 165, 39);
	        main_panel.add(lblGrandTotal);
	        
	        txtGrandTotal = new JTextField();
	        txtGrandTotal.setText(String.valueOf(grandTotal)+" PHP");
	        txtGrandTotal.setFont(new Font("Tahoma", Font.BOLD, 15));
	        txtGrandTotal.setEditable(false);
	        txtGrandTotal.setHorizontalAlignment(SwingConstants.RIGHT);
	        txtGrandTotal.setColumns(10);
	        txtGrandTotal.setBounds(219, 508, 165, 35);
	        main_panel.add(txtGrandTotal);
	        
	        lblReason = new JLabel("Reason:");
	        lblReason.setForeground(new Color(130, 166, 145));
	        lblReason.setFont(new Font("Tahoma", Font.PLAIN, 16));
	        lblReason.setBounds(880, 96, 65, 24);
	        main_panel.add(lblReason);
	        
	        comboReason = new JComboBox<String>();
	        comboReason.setBounds(940, 95, 202, 26);
	        comboReason.addItem("");
	        comboReason.addItem("Malfunction");
	        comboReason.addItem("Does not work as intended");
	        comboReason.addItem("Physical damage");
	        comboReason.addItem("Expired Item");
	        comboReason.setSelectedIndex(0);
	        main_panel.add(comboReason);
	
	        lblOthers = new JLabel("Others:");
	        lblOthers.setForeground(new Color(130, 166, 145));
	        lblOthers.setFont(new Font("Tahoma", Font.PLAIN, 16));
	        lblOthers.setBounds(880, 132, 65, 24);
	        main_panel.add(lblOthers);
	 
	        otherPanel = new JPanel();
	        otherPanel.setBounds(880, 156, 262, 160);
	        otherPanel.setLayout(new BorderLayout(0, 0));
	        main_panel.add(otherPanel);
	
			textArea = new JTextArea("  ");
			textArea.setFont(new Font("Tahoma", Font.PLAIN, 12));
			textArea.setLineWrap(true);
			textArea.setWrapStyleWord(true);
	
			scrollPane2 = new JScrollPane(textArea);
			scrollPane2 = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			otherPanel.add(scrollPane2, BorderLayout.CENTER);
	        
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
	                	RefundOrder.dispose();
	                	SelectTransaction.main(null);
	                }	
	        	}
	        });
	        btnCancel.setForeground(Color.WHITE);
	        btnCancel.setFont(new Font("Tahoma", Font.BOLD, 13));
	        btnCancel.setBackground(new Color(130, 166, 145));
	        btnCancel.setBounds(870, 547, 125, 34);
	        main_panel.add(btnCancel);
	        
	        btnRefund = new Button("REFUND ORDER");
	        btnRefund.addMouseListener(new MouseAdapter() {
	        	public void mouseEntered(MouseEvent e) {
	        		btnRefund.setBackground(new Color (229,149,96));
	        	}
	        	public void mouseExited(MouseEvent e) {
	        		btnRefund.setBackground(new Color(130, 166, 145));
	        	}
	        	public void mouseClicked(MouseEvent e) {
	
	        		int a = JOptionPane.showConfirmDialog(null, "<html><br>Are you sure you wish to refund:</br> <br>Order "+ord_no+"</br><br> Grand Total: PHP "+grandTotal+"</br>", "Select action", JOptionPane.YES_NO_OPTION);
	        		
	        		if (a == JOptionPane.YES_OPTION) {
	        			JOptionPane.showMessageDialog(main_panel, "Refund successful!");
	        			
	        			
	        			//updating SALES status
            	  		try {

            	     	  			
	            			ps = con.prepareStatement("Update SALES set Sale_State=? where Ord_No=?");
	            			ps.setString(1, "REFUNDED"); 
	            	  		ps.setInt(2, ord_no);
	            	  		ps.executeUpdate();
	            	  		System.out.println("SALES UPDATED");
	            	  		
	            	  	
	            	  		
	            	  		//update product table
	            	  	/*	ps = con.prepareStatement("Select * from CART where Ord_No=?");
	            	  		ps.setInt(1, ord_no);
	            	  		rs = ps.executeQuery();
	            	  		while(rs.next()) {
	            	  			try {
	            	  				     	  				
	            	  				ps = con.prepareStatement("Update PRODUCT set Inv_Stk=?+Inv_Stk where Prod_No=?");
	            	  				ps.setInt(1, Integer.parseInt(rs.getString("Prod_Qty").trim()));
	            	  				ps.setInt(2, Integer.parseInt(rs.getString("Prod_No").trim()));
	            	  				ps.executeUpdate();
	            	  				System.out.println("Restocked product table, Prod_Qty: "+Integer.parseInt(rs.getString("Prod_Qty").trim())+" and no: "+Integer.parseInt(rs.getString("Prod_No").trim()));
	            	  			
	            	  			}
	            	  			catch(Exception ex25) {
	            	  				System.out.println(ex25+"ex25");
	            	  			}
	            	  		}*/
	            		}
	            		catch(Exception ex24) {
	            			System.out.println(ex24+"ex24");
	            		}
            	  		
            	  		
            	  		//Inserting values to REFUND table
            	  		try {
            	  		   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
	        			   LocalDateTime now = LocalDateTime.now();  
            	  		   int emp_id = Integer.parseInt(LoggedUser.substring(6,7));
        			       int emp_ID = emp_id;
        			       String string_date = new String(dtf.format(now));
        			       
            	  			ps = con.prepareStatement("Insert INTO REFUND (Emp_ID, Ord_No, Ref_Date)"+
            	  					" VALUES ("+"'"+emp_ID+"'"+","+"'"+ord_no+"'"+","+"'"+string_date+"')");
            	  			ps.executeUpdate();
            	  		}
            	  		catch(Exception exRefund) {
            	  			System.out.println(exRefund+"exRefund");
            	  		}
            	  		
            	  		
            	  		
            	  		
            	  		
            	  		          	  		
	        		        			
	        			try {
	            			//clearing cart
	            	  		ps = con.prepareStatement("Update CART set Prod_Qty=?, Prod_Tot=? where Ord_No=?");
	            	  		ps.setInt(1, 0); //placeholder lang to avoid foreign key error
	            	  		ps.setFloat(2, 0);

	            	  		ps.setInt(3, ord_no);
	            	  		ps.executeUpdate();
	            	  		
	            	  	
	            	  		
	            		}
	            		catch(Exception ex23) {
	            			System.out.println(ex23+"ex23");
	            		}
	            		
	            		
	        			
	        			
	        			
	        			
	        			RefundOrder.dispose();
	        			SelectTransaction.main(null);
	        		}
	        		else {
	        			JOptionPane.showMessageDialog(main_panel, "Refund cancelled!");
	        			RefundOrder.dispose();
	        			SelectTransaction.main(null);
	        		}	
	        	}
	        });        
	        btnRefund.setFont(new Font("Tahoma", Font.BOLD, 13));
	        btnRefund.setBounds(1017, 545, 125, 34);
	        btnRefund.setBackground(new Color(130,166,145));
	        btnRefund.setForeground(new Color(255, 255, 255));
	        main_panel.add(btnRefund);
		}
		catch(Exception ex24) {
			System.out.println(ex24+"ex24");
		}
    
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RefundOrder window = new RefundOrder();
					window.RefundOrder.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public RefundOrder() {
		initialize();
	}
}
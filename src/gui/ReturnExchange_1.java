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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import dblogic.dblogic;

public class ReturnExchange_1 extends dblogic {
	
	//static variables
	static int ord_no;
	static String[][] arr_for_retExchange = new String[10000][4];
	static float grandTotal = 0;
	
	JFrame ReturnExchange_1;
	private JPanel main_panel;
	private JLabel txtDate;
	private JLabel lblHome;
	private JLabel lblDate;
	private JLabel lblArrow1;
	private JLabel lblReturnEx;
	private JLabel lblOrderNo;
	private Date date;
	private JTable cartTable;
	private JTableHeader header1;
	private JScrollPane scrollPane1;
	private JTextField txtOrderNo;
	private Button btnSearch;
	private Button btnProceed;
	private Button btnCancel;
	private DefaultTableCellRenderer centerRenderer;
	
	private void initialize() {
	
		try {
			ReturnExchange_1 = new JFrame("Retail POS System");
			ReturnExchange_1.setBounds(100, 100, 1200, 650);
			ReturnExchange_1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			ReturnExchange_1.setVisible(true);
			ReturnExchange_1.setResizable(false);
	     
			main_panel = new JPanel();
			main_panel.setBackground(new Color(246,244,230));
			main_panel.setBounds(0, 0, 730, 401);
			main_panel.setLayout(null);
			ReturnExchange_1.getContentPane().add(main_panel);
			
			lblHome = new JLabel("Home");
	        lblHome.addMouseListener(new MouseAdapter() {
	        	public void mouseEntered(MouseEvent e) {
	        		lblHome.setForeground(new Color(74,83,68));
	        	}
	        	public void mouseExited(MouseEvent e) {
	        		lblHome.setForeground(new Color (229, 149, 96));
	        	}
	        	public void mouseClicked(MouseEvent e) {
	        		ReturnExchange_1.dispose();
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
			
	        lblReturnEx = new JLabel("Return & Exchange Order");
	        lblReturnEx.setHorizontalAlignment(SwingConstants.LEFT);
	        lblReturnEx.setForeground(new Color(130,166,145));
	        lblReturnEx.setFont(new Font("Tahoma", Font.PLAIN, 30));
	        lblReturnEx.setBounds(133, 13, 356, 34);
	        main_panel.add(lblReturnEx);
	        
	        lblDate = new JLabel("Date:");
	        lblDate.setForeground(new Color(229, 149, 96));
	        lblDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
	        lblDate.setBounds(976, 38, 42, 24);
	        main_panel.add(lblDate);
	        
	        txtDate = new JLabel();
	        txtDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
	        txtDate.setForeground(new Color(74,83,68));
	        txtDate.setHorizontalAlignment(SwingConstants.CENTER);
	        txtDate.setBounds(1015, 38, 125, 24);
	        main_panel.add(txtDate);
	     
	        date = new Date();
			txtDate.setText(new SimpleDateFormat("d MMMM yyyy").format(date));
	
			lblOrderNo = new JLabel("Order Number:");
	        lblOrderNo.setForeground(new Color(130, 166, 145));
	        lblOrderNo.setFont(new Font("Tahoma", Font.PLAIN, 20));
	        lblOrderNo.setBounds(44, 82, 140, 24);
	        main_panel.add(lblOrderNo);
	   
	        txtOrderNo = new JTextField();
	        txtOrderNo.setHorizontalAlignment(SwingConstants.CENTER);
	        txtOrderNo.setBounds(183, 86, 202, 24);
	        main_panel.add(txtOrderNo);
	        
	        btnSearch = new Button("SEARCH");
	        btnSearch.addMouseListener(new MouseAdapter() {
	        	public void mouseEntered(MouseEvent e) {
	        		btnSearch.setBackground(new Color (229,149,96));
	        	}
	        	public void mouseExited(MouseEvent e) {
	        		btnSearch.setBackground(new Color(130, 166, 145));
	        	} 
	        	public void mouseClicked(MouseEvent e) {
	        		int tableCtr = 0;
	        		grandTotal = 0;
	        		ord_no = Integer.parseInt(txtOrderNo.getText());
	        		
	        		for(int k = 0; k<10000; k++) {
            			arr_for_retExchange[k][0] = null;
            			arr_for_retExchange[k][1] = null;
            			arr_for_retExchange[k][2] = null;
            			arr_for_retExchange[k][3] = null;
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
	        						System.out.println(rs2.getString("Prod_No"));
	        						System.out.println(rs2.getString("Prod_Name"));
	        						System.out.println(rs2.getString("Prod_OrgPrc"));
	        						System.out.println(rs.getString("Prod_Qty"));
	        						
	        						
	        						arr_for_retExchange[tableCtr][0] = rs2.getString("Prod_No");
	        						arr_for_retExchange[tableCtr][1] = rs2.getString("Prod_Name");
	        						arr_for_retExchange[tableCtr][2] = rs2.getString("Prod_OrgPrc");
	        						arr_for_retExchange[tableCtr][3] = rs.getString("Prod_Qty");
	        						
	        						
	        						if(Integer.parseInt(arr_for_retExchange[tableCtr][3])==0) {
	        							arr_for_retExchange[tableCtr][0] = null;
		        						arr_for_retExchange[tableCtr][1] = null;
		        						arr_for_retExchange[tableCtr][2] = null;
		        						arr_for_retExchange[tableCtr][3] = null;
	        						}
	        						
	        						
	        						
	        						
	        						tableCtr++;
	        						grandTotal += Float.parseFloat(rs2.getString("Prod_OrgPrc")) * Float.parseFloat(rs.getString("Prod_Qty"));;
	        						System.out.println("table ctr: "+tableCtr);
	        					}
	        					
	        					
	        				}
	        			
	        				catch(Exception ex21) {
	        					System.out.println(ex21+"ex21");
	        				}
	        				
	        			}
	        			tableCtr = 0;
	        			ReturnExchange_1.dispose();
	        			
	        			ReturnExchange_1 window1 = new ReturnExchange_1();
	        			window1.ReturnExchange_1.setVisible(true);
	        		
	        		}
	        		catch(Exception ex20) {
	        			System.out.println(ex20+"ex20");
	        		}
	        	
	        		
	        		
	        		
	        		
	        	}
	        });
	        btnSearch.setForeground(Color.WHITE);
	        btnSearch.setFont(new Font("Tahoma", Font.BOLD, 15));
	        btnSearch.setBackground(new Color(130, 166, 145));
	        btnSearch.setBounds(391, 86, 98, 24);
	        main_panel.add(btnSearch);
	        
	        //ORDER TABLE
	        scrollPane1 = new JScrollPane();
	        scrollPane1.setBorder(new LineBorder(new Color(51, 79, 33), 2, true));
	        scrollPane1.setBackground(new Color(197, 224, 180));
	        scrollPane1.setBounds(44, 119, 1096, 414);
	        main_panel.add(scrollPane1);
	        
	        cartTable = new JTable();
	        cartTable.setEnabled(false);
	        scrollPane1.setViewportView(cartTable);
	
	        header1 = cartTable.getTableHeader();
	        header1.setFont(new Font("Tahoma", Font.BOLD, 12));
	        cartTable.setModel(new DefaultTableModel (
	               arr_for_retExchange,
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
	        
	        btnCancel = new Button("CANCEL");
	        btnCancel.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        	}
	        });
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
	            		ReturnExchange_1.dispose();
	            		SelectTransaction.main(null);
	            	}
	        	}
	        });
	        btnCancel.setForeground(Color.WHITE);
	        btnCancel.setFont(new Font("Tahoma", Font.BOLD, 20));
	        btnCancel.setBackground(new Color(130, 166, 145));
	        btnCancel.setBounds(435, 550, 125, 34);
	        main_panel.add(btnCancel);
	        
	        btnProceed = new Button("PROCEED");
	        btnProceed.addMouseListener(new MouseAdapter() {
	        	public void mouseEntered(MouseEvent e) {
	        		btnProceed.setBackground(new Color (229,149,96));
	        	}
	        	public void mouseExited(MouseEvent e) {
	        		btnProceed.setBackground(new Color(130, 166, 145));
	        	}
	        	public void mouseClicked(MouseEvent e) {
	
	        		int a = JOptionPane.showConfirmDialog(null, "<html><br>Are you sure you wish to return and exchange:</br> <br>Order "+ord_no+" </br><br> Grand Total: PHP "+grandTotal+"</br>", "Select action", JOptionPane.YES_NO_OPTION);
	        		
	        		
	        		
	        		
	            	if (a == JOptionPane.YES_OPTION) {
	            		
	            		
	            		try {

    	     	  			
	            			ps = con.prepareStatement("Update SALES set Sale_State=? where Ord_No=?");
	            			ps.setString(1, "EXCHANGED"); 
	            	  		ps.setInt(2, ord_no);
	            	  		ps.executeUpdate();
	            	  		System.out.println("SALES UPDATED");
	            	  		
	            	  		//update product table
	            	  		ps = con.prepareStatement("Select * from CART where Ord_No=?");
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
	            	  		}
	            		}
	            		catch(Exception ex24) {
	            			System.out.println(ex24+"ex24");
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
	        			
	        			
	        			//Inserting values to EXCHANGE table
            	  		try {
            	  		   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
	        			   LocalDateTime now = LocalDateTime.now();  
            	  		   int emp_id = Integer.parseInt(LoggedUser.substring(6,7));
        			       int emp_ID = emp_id;
        			       String string_date = new String(dtf.format(now));
        			       
            	  			ps = con.prepareStatement("Insert INTO RETURN_EXCHANGE (Ord_No, Emp_ID, Exc_Date)"+
            	  					" VALUES ("+"'"+ord_no+"'"+","+"'"+emp_ID+"'"+","+"'"+string_date+"')");
            	  			ps.executeUpdate();
            	  		}
            	  		catch(Exception exExchange) {
            	  			System.out.println(exExchange+"exExchange");
            	  		}
            	  		
	            		
	            		
	        			
	            		
	            		
	        	  		ReturnExchange_1.dispose();
            			CreateOrder.main(null);
	            		
	            	}
	        		
	        	}
	        });        
	        btnProceed.setFont(new Font("Tahoma", Font.BOLD, 20));
	        btnProceed.setBounds(635, 550, 125, 34);
	        btnProceed.setBackground(new Color(130,166,145));
	        btnProceed.setForeground(new Color(255, 255, 255));
	        main_panel.add(btnProceed);
		}
		catch(Exception ex17) {
			System.out.println(ex17+"ex17");
		}
	        
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReturnExchange_1 window = new ReturnExchange_1();
					window.ReturnExchange_1.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public ReturnExchange_1() {
		initialize();
	}
	
	/*public void findOrder() {
		int tableCtr = 0;
		System.out.println("FindOrder");

		try {
			ps = con.prepareStatement("Select * FROM CART WHERE Ord_No=?");
			ps.setInt(1, ord_no);
			rs = ps.executeQuery();
			while(rs.next()) {
				System.out.println("Wala: "+rs.getString("Wala"));
				System.out.println("Cust_No: "+rs.getString("Cust_No"));
				System.out.println("Prod_No: "+rs.getString("Prod_No"));
				System.out.println("Prod_Qty: "+rs.getString("Prod_Qty"));
				System.out.println("Prod_tot: "+rs.getString("Prod_Tot"));
				System.out.println("Prod_OrgPrc: "+rs.getString("Prod_OrgPrc"));
				System.out.println("Ord_No: "+rs.getString("Ord_No"));
			}
		}
		catch(Exception ex19) {
		
		}
		
		
	}*/
	
}
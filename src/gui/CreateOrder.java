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

import dblogic.dblogic;

import java.awt.Button;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileWriter;  // Import the File class
import java.sql.ResultSet;

public class CreateOrder extends dblogic {
	
    static String[][] arr_for_shopTable = new String[10000][4];
    static int cust_No = 0;
    static boolean transactionCheck = false;
    static int transactionFreq = 0;
    static int ctr1 = 0; //for shopTable
	static float total_php = 0; //for totalPHP
	static boolean isRetExchange = false;
	
	//for backup
	static int retrieveStocks = 0;
	static int retrieveStocks_final = 0;
	static int retrieveItem = 0;
	static int retrieveItem_final = 0;
	static int retrieveCustNo = 0;
	static int[] retrieveStocks_arr = new int[10000];
	static int[] retrieveItem_arr = new int[10000];
	static int[] inputItem_arr = new int[10000];
	static int retrieveCtr = 0;
	
	//for finalize cart
	
	static int final_cust_no, final_prod_no, final_prod_qty;
	static float final_prod_tot, final_prod_orgprc;
	
	
	
    
	JFrame CreateOrder;
	private JPanel main_panel;
	private JLabel txtDate;
	private JLabel lblHome;
	private JLabel lblDate;
	private JLabel lblArrow1;
	private JLabel lblCreate;
	private JLabel lblCart;
	private JLabel lblItemName;
	private JLabel lblQuantity;
	private JLabel lblGrandTotal;
	private JTextField txtItemName;
	private JTextField txtQty;
	private JTextField txtGrandTotal;
	private Date date;
	private JTable productTable;
	private JTable cartTable;
	private JScrollPane scrollPane1;
	private JScrollPane scrollPane2;
	private JTableHeader header1;
	private JTableHeader header2;
	private Button btnAdd;
	private Button btnProceed;
	private Button btnCancel;
	private DefaultTableCellRenderer centerRenderer;
	
	private void initialize() {
		
	
		
		
		//For shop_cart

			
		String sql = "Select * FROM PRODUCT";
		String[][] arr_for_table = new String[10000][4];
		

			try 
			{
			DecimalFormat df = new DecimalFormat(".00");
			int ctr = 0;
			
			st = con.createStatement();
			rs = st.executeQuery(sql);
			
			while(rs.next()) {
				arr_for_table[ctr][0] = "PROD"+String.format("%04d", Integer.parseInt(new String(rs.getString("Prod_No"))));
				arr_for_table[ctr][1] = rs.getString("Prod_Name");
				arr_for_table[ctr][2] = rs.getString("Inv_Stk");
				arr_for_table[ctr][3] = df.format(Float.parseFloat(new String((rs.getString("Prod_OrgPrc")))));
				ctr++;
			 }
			
		CreateOrder = new JFrame("Retail POS System");
		CreateOrder.setBounds(100, 100, 1200, 650);
		CreateOrder.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		CreateOrder.setVisible(true);
		CreateOrder.setResizable(false);
     
		main_panel = new JPanel();
		main_panel.setBackground(new Color(246,244,230));
		main_panel.setBounds(0, 0, 730, 401);
		main_panel.setLayout(null);
		CreateOrder.getContentPane().add(main_panel);
		
		lblHome = new JLabel("Home");
        lblHome.addMouseListener(new MouseAdapter() {
        	public void mouseEntered(MouseEvent e) {
        		lblHome.setForeground(new Color(74,83,68));
        	}
        	public void mouseExited(MouseEvent e) {
        		lblHome.setForeground(new Color (229, 149, 96));
        	}
        	public void mouseClicked(MouseEvent e) {
            	CreateOrder.dispose();
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
        lblCreate.setHorizontalAlignment(SwingConstants.LEFT);
        lblCreate.setForeground(new Color(130,166,145));
        lblCreate.setFont(new Font("Tahoma", Font.PLAIN, 30));
        lblCreate.setBounds(133, 13, 177, 34);
        main_panel.add(lblCreate);

        txtDate = new JLabel();
        txtDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txtDate.setForeground(new Color(74,83,68));
        txtDate.setHorizontalAlignment(SwingConstants.CENTER);
        txtDate.setBounds(1037, 22, 125, 24);
        main_panel.add(txtDate);
     
        date = new Date();
		txtDate.setText(new SimpleDateFormat("d MMMM yyyy").format(date));
  
        lblDate = new JLabel("Date:");
        lblDate.setForeground(new Color(229, 149, 96));
        lblDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblDate.setBounds(998, 22, 42, 24);
        main_panel.add(lblDate);
        
        //PRODUCTS TABLE
        scrollPane1 = new JScrollPane();
		scrollPane1.setBorder(new LineBorder(new Color(51, 79, 33), 2, true));
		scrollPane1.setBackground(new Color(197, 224, 180));
		scrollPane1.setBounds(23, 71, 443, 520);
		main_panel.add(scrollPane1);
		
        productTable = new JTable();
        productTable.setEnabled(false);
		scrollPane1.setViewportView(productTable);
		
		header1 = productTable.getTableHeader();
		header1.setFont(new Font("Tahoma", Font.BOLD, 12));
        productTable.setModel(new DefaultTableModel (
        		arr_for_table,
                new String [] {
                    "Item ID", "Item Name", "Stocks", "Unit Price"
                }
        ));
        productTable.setAutoCreateRowSorter(false);
        productTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
        productTable.setSelectionBackground(new java.awt.Color(153, 153, 255));
        scrollPane1.setViewportView(productTable);
        
        centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        productTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        productTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        productTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        //--
        
        //CART TABLE
        lblCart = new JLabel("Shopping Cart");
        lblCart.setHorizontalAlignment(SwingConstants.CENTER);
        lblCart.setForeground(new Color(130, 166, 145));
        lblCart.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblCart.setBounds(762, 57, 156, 34);
        main_panel.add(lblCart);
        
        scrollPane2 = new JScrollPane();
        scrollPane2.setBorder(new LineBorder(new Color(51, 79, 33), 2, true));
        scrollPane2.setBackground(new Color(197, 224, 180));
        scrollPane2.setBounds(497, 97, 665, 267);
        main_panel.add(scrollPane2);
        
        cartTable = new JTable();
        cartTable.setEnabled(false);
        scrollPane2.setViewportView(cartTable);

        header2 = cartTable.getTableHeader();
        header2.setFont(new Font("Tahoma", Font.BOLD, 12));
        cartTable.setModel(new DefaultTableModel (
                arr_for_shopTable,
                new String [] {
                    "Item ID", "Item Name", "Unit Price", "Qty"
                }
            		));
        cartTable.setAutoCreateRowSorter(false);
        cartTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
        cartTable.setSelectionBackground(new java.awt.Color(153, 153, 255));
        scrollPane2.setViewportView(cartTable);
        
        centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        cartTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        cartTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        cartTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        //--
        
        lblItemName = new JLabel("Item ID:");
        lblItemName.setHorizontalAlignment(SwingConstants.LEFT);
        lblItemName.setForeground(new Color(229, 149, 96));
        lblItemName.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblItemName.setBounds(497, 391, 74, 24);
        main_panel.add(lblItemName);
        
        txtItemName = new JTextField();
        txtItemName.setHorizontalAlignment(SwingConstants.CENTER);
        txtItemName.setBounds(589, 391, 188, 24);
        main_panel.add(txtItemName);
        txtItemName.setColumns(10);
        
        lblQuantity = new JLabel("Quantity:");
        lblQuantity.setHorizontalAlignment(SwingConstants.LEFT);
        lblQuantity.setForeground(new Color(229, 149, 96));
        lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblQuantity.setBounds(497, 426, 74, 24);
        main_panel.add(lblQuantity);

        txtQty = new JTextField();
        txtQty.setHorizontalAlignment(SwingConstants.CENTER);
        txtQty.setColumns(10);
        txtQty.setBounds(589, 428, 188, 24);
        main_panel.add(txtQty);
        
        btnAdd = new Button("ADD");
        btnAdd.addMouseListener(new MouseAdapter() {
        	public void mouseEntered(MouseEvent e) {
        		btnAdd.setBackground(new Color (229,149,96));
        	}
        	public void mouseExited(MouseEvent e) {
        		btnAdd.setBackground(new Color(130, 166, 145));
        	}
        	public void mouseClicked(MouseEvent e) {
        		String itemID, itemQuantity;
        		itemID      = txtItemName.getText();
        		itemID   = itemID.replaceAll("\\D", "");
        		itemQuantity  = txtQty.getText();
        		//Main query nest
        		try {
        			int int_itemID = Integer.parseInt(itemID);
        			int int_itemQuantity = Integer.parseInt(itemQuantity);
        			int updateStocks = 0;
        			float prod_price;
        			int ctr = 0;
        	
        			System.out.printf("PROD");
        			System.out.println(itemID);
        			System.out.println(int_itemQuantity);
        			
        			
        			//Fetching from PRODUCT table
        			String sql_sel_prod = "Select * FROM PRODUCT WHERE Prod_No = ?";
        			
        			ps = con.prepareStatement(sql_sel_prod);
        			ps.setInt(1, int_itemID);
        			rs = ps.executeQuery();
        			while(rs.next()) {
        				System.out.println(rs.getString("Prod_Name"));
        				if(Integer.parseInt(rs.getString("Inv_Stk")) > int_itemQuantity) {
        					System.out.println("Nice, pasok");
        					retrieveStocks = Integer.parseInt(rs.getString("Inv_Stk"));
        					retrieveItem = Integer.parseInt(rs.getString("Prod_No"));
        					updateStocks = Integer.parseInt(rs.getString("Inv_Stk")) - int_itemQuantity;
        					prod_price = Float.parseFloat(rs.getString("Prod_OrgPrc"));
        					float prod_total = (float)int_itemQuantity * prod_price;
      
        					//Update PRODUCTS stock
        					try {
        						ps = con.prepareStatement("update PRODUCT set Inv_Stk=? where Prod_No=?");
        						ps.setInt(1, updateStocks);
        						ps.setInt(2, int_itemID);
        						ps.executeUpdate();
        						
        					
            					
            					//present CART table
            					try {
            						//But first get the cust_no
            						int highest = -99999;
            						ps = con.prepareStatement("select * from CART");
            						rs = ps.executeQuery();
            						while(rs.next()) {
            							if(Integer.parseInt(rs.getString("Cust_No")) > highest) {
            								highest = Integer.parseInt(rs.getString("Cust_No"));
            							}
            						
            						}
            						//update cart table
            						try {
            							//create a static variable to track session transaction
            							
      									retrieveStocks_final = retrieveStocks;
    									retrieveItem_final = retrieveItem;
    									//Testing lang for array
    									retrieveStocks_arr[retrieveCtr] = retrieveStocks_final;
    									retrieveItem_arr[retrieveCtr] = retrieveItem_final;
            							inputItem_arr[retrieveCtr] = int_itemQuantity;
            							
            							
            							transactionCheck = true;
            							transactionFreq++;
            							if(transactionCheck) {
            								if(transactionFreq == 1) {
            									/*
  
            									*/
            									highest++; //for unique cust_no
            							
            									retrieveCustNo = highest;
            									
            								
            								}
            								//kulang ka kasi sa queries LOL
            								ps = con.prepareStatement("insert into CART (Cust_No, Prod_No, Prod_Qty, Prod_Tot, Prod_OrgPrc) "+"VALUES ("+"'"+highest+"'"+", "+"'"+int_itemID+"'"+", "+"'"+int_itemQuantity+"'"+","+"'"+prod_total+"'"+","+"'"+prod_price+"'"+")");     
            								ps.executeUpdate();
            								System.out.println("highest: "+highest);
            								System.out.println("Inserting cart successful");
            								
            								
            								//Present yung cart sa right
            								try {
            									
            									ps = con.prepareStatement("select * from PRODUCT where Prod_No=?");
            									ps.setInt(1, int_itemID);
            									rs = ps.executeQuery();
            									while(rs.next()) {
            										arr_for_shopTable[ctr1][0] = "PROD"+String.format("%04d", Integer.parseInt(new String(rs.getString("Prod_No"))));
            										arr_for_shopTable[ctr1][1] = rs.getString("Prod_Name");
            										arr_for_shopTable[ctr1][2] = df.format(Float.parseFloat(new String((rs.getString("Prod_OrgPrc")))));
            										arr_for_shopTable[ctr1][3] = String.valueOf(int_itemQuantity);
            										ctr1++;
            									}
            									
            	        						
            	            					
            	            					//updating php total
            									total_php = 0;
            	            					for(int j = 0; j<10000; j++) {
            	            						//quantity * org_price sa cart
            	            					
            	            						if(arr_for_shopTable[j][2]!=null) {
            	            							total_php += Float.parseFloat(arr_for_shopTable[j][2]) * Float.parseFloat(arr_for_shopTable[j][3]);
            	            						}
            	            						else {
            	            							break;
            	            						}
            	            							
            	            					
            	            					}
            	            					System.out.println("total: "+total_php);
            	            					
            	            					CreateOrder.dispose();
            	            					CreateOrder window1 = new CreateOrder();
            	            					window1.CreateOrder.setVisible(true);
            	            				

            	            					
            									
            								}
            								catch(Exception ex10) {
            									System.out.println(ex10+"ex10");
            								}
            								
          
            							}
            							
            						}
            						catch(Exception ex8) {
            							System.out.println(ex8+"ex8");
            						}
            						
            						
            					}
            					catch(Exception ex7) {
            						System.out.println(ex7+"ex7");
            					}
        					}
        					
        					catch(Exception ex6) {
        						System.out.println(ex6+"ex6");
        					}
        		
        				}
        				else {
        					
        				}
        			}
        	
    		  		
        			
        			retrieveCtr++;
        			
    			    JOptionPane.showMessageDialog(null, "Item added!");
    			    
    			    
        		}
        		catch(Exception ex3) {
        			System.out.println(ex3);
        		}
        	}
        });
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnAdd.setBackground(new Color(130, 166, 145));
        btnAdd.setBounds(677, 458, 100, 28);
        main_panel.add(btnAdd);
        
        lblGrandTotal = new JLabel("GRAND TOTAL:");
        lblGrandTotal.setHorizontalAlignment(SwingConstants.LEFT);
        lblGrandTotal.setForeground(new Color(229, 149, 96));
        lblGrandTotal.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblGrandTotal.setBounds(872, 391, 135, 24);
        main_panel.add(lblGrandTotal);
        
        txtGrandTotal = new JTextField();
        txtGrandTotal.setText(total_php+"PHP");
        txtGrandTotal.setFont(new Font("Tahoma", Font.BOLD, 15));
        txtGrandTotal.setEditable(false);
        txtGrandTotal.setHorizontalAlignment(SwingConstants.RIGHT);
        txtGrandTotal.setColumns(10);
        txtGrandTotal.setBounds(1006, 391, 156, 24);
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
            		transactionCheck = false;
            		transactionFreq = 0;
            		
            		//return all values to product mga ninakaw pati
            		
            		//Update product back
            		
            		for(int i = 0; i<10000; i++) {
            			if(retrieveStocks_arr[i]!=0) {
            				try {
                    			ps = con.prepareStatement("update PRODUCT set Inv_Stk=?+Inv_Stk where Prod_No=?");
        						ps.setInt(1, inputItem_arr[i]);
        						ps.setInt(2, retrieveItem_arr[i]);
        						ps.executeUpdate();
        						
                    		}
                			catch(Exception ex13) {
                    			System.out.println(ex13+"ex13");
                    		}
            			}
            			else {
            				break;
            			}
            			
           
            	
            		
            		
            		
            		
            		//Remove CART entry
            		try {
            			ps = con.prepareStatement("DELETE FROM CART WHERE Cust_No=?");
            			ps.setInt(1, retrieveCustNo);
            			ps.executeUpdate();
            		}
            		catch(Exception ex14) {
            			System.out.println(ex14+"ex14");
            		}
            		//Clear arr_for_shopTable entries
            		for(int k = 0; k<10000; k++) {
            			arr_for_shopTable[k][0] = null;
            			arr_for_shopTable[k][1] = null;
            			arr_for_shopTable[k][2] = null;
            			arr_for_shopTable[k][3] = null;
            		}
            		//clear total_php, ctr1
            		total_php = 0;
            		ctr1 = 0;
            		
            		System.out.println("-----");
            		//Print every retrieval records for testing
            		System.out.println("Deleted Customer Number: "+retrieveCustNo);
            		for(int j = 0; j<10000; j++) {
            		
            			if(retrieveStocks_arr[j] == 0)
            				break;
                		System.out.println(j+" Previous stock: "+retrieveStocks_arr[j]);
                		System.out.println("Item no: "+retrieveItem_arr[j]);
      		
            		}
            	
            		
            		CreateOrder.dispose();
            		SelectTransaction.main(null);
            	}
            }
        }});
        btnCancel.setForeground(Color.WHITE);
        btnCancel.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnCancel.setBackground(new Color(130, 166, 145));
        btnCancel.setBounds(1037, 509, 125, 34);
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
        		
        		int a = JOptionPane.showConfirmDialog(null, "Are you sure you wish to proceed?", "Select action", JOptionPane.YES_NO_OPTION);
        		
            	if (a == JOptionPane.YES_OPTION) {
            		CreateOrder.dispose();
            		OrderSummary.main(null);
            	}
        	}
        });        
        btnProceed.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnProceed.setBounds(1037, 562, 125, 34);
        btnProceed.setBackground(new Color(130,166,145));
        btnProceed.setForeground(new Color(255, 255, 255));
        main_panel.add(btnProceed);
        
	}
		catch(Exception ex5) {
			System.out.println(ex5);
		}
	}
	
		public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				try {
					CreateOrder window = new CreateOrder();
					window.CreateOrder.setVisible(true);
				} 
				
				
				catch (Exception e) {
					e.printStackTrace();
					}
				}
			});
	}

		
		
		
	public void setRetExchange(boolean retExchange, int currentCustNo) {
		isRetExchange = retExchange;
		retrieveCustNo = currentCustNo;
	}
		
		
	public CreateOrder() {
		initialize();
	}

	
	public String[][] getCart() {
		return arr_for_shopTable;
	}
	
	public float getTotal() {
		return total_php;
	}
	
	public void clearTable(boolean proceed) {
		
			//clear by default?
		if(proceed) {
			for(int k = 0; k<10000; k++) {
				arr_for_shopTable[k][0] = null;
				arr_for_shopTable[k][1] = null;
				arr_for_shopTable[k][2] = null;
				arr_for_shopTable[k][3] = null;
				inputItem_arr[k] = 0;

			}
		
		}
		transactionFreq = 0;
		retrieveCtr = 0;
		total_php = 0;
		ctr1 = 0;
		
	}
	
	public void finalizeCart(int ord_no) {
		//kasama na rin rito receipt
		//update cart with Ord_No
	    String[] itemNames = new String[10000];
		ResultSet rs2;
	
		
		
		
		
		
		
		
		
		try {
			ps = con.prepareStatement("UPDATE CART set Ord_No=? where Cust_No=?");
			ps.setInt(1, ord_no);
			ps.setInt(2, retrieveCustNo);
			ps.executeUpdate();
			
			
			try {
				int i = 0;
				System.out.println("Before cart query");
				ps = con.prepareStatement("Select * from CART where Ord_No=?");
				ps.setInt(1, ord_no);
				rs = ps.executeQuery();
				System.out.println("After cart query");
				while(rs.next()) {
					System.out.println("Before product query");
					ps = con.prepareStatement("Select * FROM PRODUCT where Prod_No=?");
					ps.setInt(1, Integer.parseInt(rs.getString("Prod_No")));
					rs2 = ps.executeQuery();
					System.out.println("After product query");
					while(rs2.next()) {
						itemNames[i] = rs2.getString("Prod_Name");
						System.out.println("Prod name from PRODUCT: "+rs2.getString("Prod_Name"));
						System.out.println("Prod name from method: "+itemNames[i]);
						i++;
				
					}
				}
			}
			catch(Exception getName) {
				System.out.println(getName+"getName");
			}
			
			
			
			
			
			
			
			
			//try ko lang i print for testing
			try {
				int ctr = 0;
				FileWriter receipt = new FileWriter("receipt.txt");
				ps = con.prepareStatement("Select * FROM CART where Ord_No=?");
				ps.setInt(1, ord_no);
				rs = ps.executeQuery();
				
				while(rs.next()) {
			
					if(ctr==0) {
						receipt.write("Customer number: "+rs.getString("Cust_No")+" | Order number: "+ord_no+"\n");
					}
					
					receipt.write("Prod no: "+rs.getString("Prod_No")+" Prod name: "+itemNames[ctr]+" Prod Qty: "+rs.getString("Prod_Qty")+" Prod Total: "+rs.getString("Prod_Tot")+"\n");
				
					ctr++;
			
				}
				receipt.close();
			}
			catch(Exception ex19) {
				System.out.println(ex19+"ex19");
			}
			
			
			
		}
		catch(Exception ex18) {
			System.out.println(ex18+"ex18");
		}
	}
	
	

}
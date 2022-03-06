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
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import dblogic.dblogic;

public class UpdateStocks extends dblogic {

	JFrame UpdateStocks;
	private JPanel main_panel;
	private JLabel txtDate;
	private JLabel lblHome;
	private JLabel lblDate;
	private JLabel lblArrow1;
	private JLabel lblManageInventory;
	private JLabel lblArrow2;
	private JLabel lblUpdate;
	private JLabel lblSpecify;
	private JLabel lblUnit;
	private JLabel lblQuantity;
	private Date date;
	private JTextField txtSpecify;
	private JTextField txtQuantity;
	private JTextField txtUnitPrice;
	private JTableHeader header1;
	private JTable inventoryTable;
	private JScrollPane scrollPane1;
	private Button btnDone;
	private Button btnCancel;
	private Button btnUpdate;
	private DefaultTableCellRenderer centerRenderer;
	
	private void initialize() {

		try {
			
			String sql = "Select * FROM PRODUCT";
			String[][] arr_for_table = new String[10000][4];


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
			
			
			
			UpdateStocks = new JFrame("Retail POS System");
			UpdateStocks.setBounds(100, 100, 1200, 650);
			UpdateStocks.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			UpdateStocks.setVisible(true);
			UpdateStocks.setResizable(false);
	     
			main_panel = new JPanel();
			main_panel.setBackground(new Color(246,244,230));
			main_panel.setBounds(0, 0, 730, 401);
			main_panel.setLayout(null);
			UpdateStocks.getContentPane().add(main_panel);
			
			lblHome = new JLabel("Home");
	        lblHome.addMouseListener(new MouseAdapter() {
	        	public void mouseEntered(MouseEvent e) {
	        		lblHome.setForeground(new Color(74,83,68));
	        	}
	        	public void mouseExited(MouseEvent e) {
	        		lblHome.setForeground(new Color (229, 149, 96));
	        	}
	        	public void mouseClicked(MouseEvent e) {
	        		UpdateStocks.dispose();
	        		ManageInventory.main(null);
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
	        
	        lblManageInventory = new JLabel("Manage Inventory");
	        lblManageInventory.addMouseListener(new MouseAdapter() {
	        	public void mouseEntered(MouseEvent e) {
	        		lblManageInventory.setForeground(new Color (74,83,68));
	        	}
	        	public void mouseExited(MouseEvent e) {
	        		lblManageInventory.setForeground(new Color(229, 149, 96));
	        	}
	        	public void mouseClicked(MouseEvent e) {
	        		UpdateStocks.dispose();
	        		ManageInventory.main(null);
	        	}
	        });
	        lblManageInventory.setHorizontalAlignment(SwingConstants.LEFT);
	        lblManageInventory.setForeground(new Color(229, 149, 96));
	        lblManageInventory.setFont(new Font("Tahoma", Font.PLAIN, 30));
	        lblManageInventory.setBounds(133, 13, 249, 34);
	        main_panel.add(lblManageInventory);
			
	        lblArrow2 = new JLabel(">");
	        lblArrow2.setHorizontalAlignment(SwingConstants.LEFT);
	        lblArrow2.setForeground(new Color(229, 149, 96));
	        lblArrow2.setFont(new Font("Tahoma", Font.PLAIN, 30));
	        lblArrow2.setBounds(382, 12, 25, 34);
	        main_panel.add(lblArrow2);
	        
	        lblUpdate = new JLabel("Update Stocks");
	        lblUpdate.setHorizontalAlignment(SwingConstants.LEFT);
	        lblUpdate.setForeground(new Color(130, 166, 145));
	        lblUpdate.setFont(new Font("Tahoma", Font.PLAIN, 30));
	        lblUpdate.setBounds(408, 12, 195, 34);
	        main_panel.add(lblUpdate);
	        
	        lblDate = new JLabel("Date:");
	        lblDate.setForeground(new Color(229, 149, 96));
	        lblDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
	        lblDate.setBounds(986, 22, 42, 24);
	        main_panel.add(lblDate);
	        
	        txtDate = new JLabel();
	        txtDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
	        txtDate.setForeground(new Color(74,83,68));
	        txtDate.setHorizontalAlignment(SwingConstants.CENTER);
	        txtDate.setBounds(1025, 22, 125, 24);
	        main_panel.add(txtDate);
	     
	        date = new Date();
			txtDate.setText(new SimpleDateFormat("d MMMM yyyy").format(date));
	
			//INVENTORY TABLE
	        scrollPane1 = new JScrollPane();
	        scrollPane1.setBorder(new LineBorder(new Color(51, 79, 33), 2, true));
	        scrollPane1.setBackground(new Color(197, 224, 180));
	        scrollPane1.setBounds(42, 69, 1108, 339);
	        main_panel.add(scrollPane1);
	        
	        inventoryTable = new JTable();
	        inventoryTable.setEnabled(false);
	        scrollPane1.setViewportView(inventoryTable);
	
	        header1 = inventoryTable.getTableHeader();
	        header1.setFont(new Font("Tahoma", Font.BOLD, 12));
	        inventoryTable.setModel(new DefaultTableModel (
	        		arr_for_table,
	                new String [] {
	                    "Item ID", "Item Name", "Category", "Stocks", "Unit Price"
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
	        inventoryTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
	        //--
	        
	        lblSpecify = new JLabel("Specify Item ID:");
	        lblSpecify.setHorizontalAlignment(SwingConstants.LEFT);
	        lblSpecify.setForeground(new Color(229, 149, 96));
	        lblSpecify.setFont(new Font("Tahoma", Font.PLAIN, 16));
	        lblSpecify.setBounds(78, 427, 116, 24);
	        main_panel.add(lblSpecify);
	        
	        txtSpecify = new JTextField();
	        txtSpecify.setHorizontalAlignment(SwingConstants.CENTER);
	        txtSpecify.setBounds(200, 427, 291, 24);
	        main_panel.add(txtSpecify);
	        txtSpecify.setColumns(10);
	        
	        lblQuantity = new JLabel("Quantity:");
	        lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 16));
	        lblQuantity.setBounds(78, 462, 75, 23);
	        lblQuantity.setForeground(new Color (229, 149, 96));
	        lblQuantity.setBackground(new Color(246,244,230));
	        lblQuantity.setEnabled(true);
	        main_panel.add(lblQuantity);
	        
	        txtQuantity = new JTextField();
	        txtQuantity.setHorizontalAlignment(SwingConstants.CENTER);
	        txtQuantity.setColumns(10);
	        txtQuantity.setBounds(200, 462, 291, 24);
	        main_panel.add(txtQuantity);
	        
	        lblUnit = new JLabel("Unit Price:");
	        lblUnit.setHorizontalAlignment(SwingConstants.LEFT);
	        lblUnit.setForeground(new Color(229, 149, 96));
	        lblUnit.setBackground(new Color(246,244,230));
	        lblUnit.setFont(new Font("Tahoma", Font.PLAIN, 16));
	        lblUnit.setBounds(78, 495, 85, 24);
	        lblUnit.setEnabled(true);
	        main_panel.add(lblUnit);
	        
	        txtUnitPrice = new JTextField();
	        txtUnitPrice.setHorizontalAlignment(SwingConstants.CENTER);
	        txtUnitPrice.setColumns(10);
	        txtUnitPrice.setBounds(200, 497, 291, 24);
	        main_panel.add(txtUnitPrice);

	        btnUpdate = new Button("UPDATE");
	        btnUpdate.addMouseListener(new MouseAdapter() {
	        	public void mouseEntered(MouseEvent e) {
	        		btnUpdate.setBackground(new Color (229,149,96));
	        	}
	        	public void mouseExited(MouseEvent e) {
	        		btnUpdate.setBackground(new Color(130, 166, 145));
	        	}
	        	public void mouseClicked(MouseEvent e) {
	        		
	       		    String itemSpecify, itemQuantity, itemUnitPrice;
	        		itemSpecify   = txtSpecify.getText();
	        		itemSpecify   = itemSpecify.replaceAll("\\D", "");
	        		itemQuantity  = txtQuantity.getText();
		        	itemUnitPrice = txtUnitPrice.getText();
	        		
		        	try {
	        			int int_itemSpecify = Integer.parseInt(itemSpecify);
	        			int int_itemQuantity = Integer.parseInt(itemQuantity);
	        			float int_unitPrice = Float.parseFloat(itemUnitPrice);
	        			System.out.println(itemSpecify);
	        			System.out.println(int_itemQuantity);
	        			System.out.println(int_unitPrice);
	        			//Insert new values
	        			
	        			ps = con.prepareStatement("update PRODUCT set Prod_OrgPrc=?, Inv_Stk=? where Prod_No=?"); //This is where the bug lies
	        			ps.setFloat(1, int_unitPrice);
	        			ps.setInt(2, int_itemQuantity);	 
	        			ps.setInt(3, int_itemSpecify);
        			    ps.executeUpdate();
        			    JOptionPane.showMessageDialog(null, "Item updated!");
        		  		UpdateStocks.dispose();
    	        		ManageInventory.main(null);
	        			
	        		}
	        		catch(Exception ex3) {
	        			System.out.println(ex3);
	        		}
	        	}
	        });
	        btnUpdate.setForeground(Color.WHITE);
	        btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 14));
	        btnUpdate.setBackground(new Color(130, 166, 145));
	        btnUpdate.setBounds(394, 525, 97, 28);
	        main_panel.add(btnUpdate);
	        
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
	                	UpdateStocks.dispose();
		        		ManageInventory.main(null);
	                }	
	        	}
	        });
	        btnCancel.setForeground(Color.WHITE);
	        btnCancel.setFont(new Font("Tahoma", Font.BOLD, 20));
	        btnCancel.setBackground(new Color(130, 166, 145));
	        btnCancel.setBounds(868, 554, 125, 34);
	        main_panel.add(btnCancel);
	        
	        btnDone = new Button("DONE");
	        btnDone.addMouseListener(new MouseAdapter() {
	        	public void mouseEntered(MouseEvent e) {
	        		btnDone.setBackground(new Color (229,149,96));
	        	}
	        	public void mouseExited(MouseEvent e) {
	        		btnDone.setBackground(new Color(130, 166, 145));
	        	}
	        	public void mouseClicked(MouseEvent e) {
	        		JOptionPane.showMessageDialog(null, "Stocks are now up-to-date!");
	        		UpdateStocks.dispose();
	        		ManageInventory.main(null);
	        	}
	        });
	        btnDone.setForeground(Color.WHITE);
	        btnDone.setFont(new Font("Tahoma", Font.BOLD, 20));
	        btnDone.setBackground(new Color(130, 166, 145));
	        btnDone.setBounds(1025, 554, 125, 34);
	        main_panel.add(btnDone);
		}
		catch(Exception ex5) {
			System.out.println(ex5);
		}
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateStocks window = new UpdateStocks();
					window.UpdateStocks.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public UpdateStocks() {
		initialize();
	}
}
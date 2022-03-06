package gui;
import dblogic.dblogic;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
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


public class ManageInventory extends dblogic{

	JFrame ManageInventory;
	private JPanel main_panel;
	private JLabel txtDate;
	private JLabel lblHome;
	private JLabel lblDate;
	private JLabel lblArrow1;
	private JLabel lblManageInventory;
	private Date date;
	private JTable inventoryTable;
	private JTableHeader header1;
	private JScrollPane scrollPane1;
	private Button btnUpdate;
	private Button btnRemoveItem;
	private Button btnAddItem;
	private DefaultTableCellRenderer centerRenderer;
	
	private void initialize() {
		try {
			//Database initialization
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
	
			
			
			
			
			ManageInventory = new JFrame("Retail POS System");
			ManageInventory.setBounds(100, 100, 1200, 650);
			ManageInventory.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			ManageInventory.setVisible(true);
			ManageInventory.setResizable(false);
	     
			main_panel = new JPanel();
			main_panel.setBackground(new Color(246,244,230));
			main_panel.setBounds(0, 0, 730, 401);
			main_panel.setLayout(null);
			ManageInventory.getContentPane().add(main_panel);
			
			lblHome = new JLabel("Home");
	        lblHome.addMouseListener(new MouseAdapter() {
	        	public void mouseEntered(MouseEvent e) {
	        		lblHome.setForeground(new Color(74,83,68));
	        	}
	        	public void mouseExited(MouseEvent e) {
	        		lblHome.setForeground(new Color (229, 149, 96));
	        	}
	        	public void mouseClicked(MouseEvent e) {
	        		ManageInventory.dispose();
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
	
	        lblManageInventory = new JLabel("Manage Inventory");
	        lblManageInventory.setHorizontalAlignment(SwingConstants.LEFT);
	        lblManageInventory.setForeground(new Color(130,166,145));
	        lblManageInventory.setFont(new Font("Tahoma", Font.PLAIN, 30));
	        lblManageInventory.setBounds(133, 13, 249, 34);
	        main_panel.add(lblManageInventory);
	        
	        lblDate = new JLabel("Date:");
	        lblDate.setForeground(new Color(229, 149, 96));
	        lblDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
	        lblDate.setBounds(988, 22, 42, 24);
	        main_panel.add(lblDate);
	        
	        txtDate = new JLabel();
	        txtDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
	        txtDate.setForeground(new Color(74,83,68));
	        txtDate.setHorizontalAlignment(SwingConstants.CENTER);
	        txtDate.setBounds(1027, 22, 125, 24);
	        main_panel.add(txtDate);
	     
	        date = new Date();
			txtDate.setText(new SimpleDateFormat("d MMMM yyyy").format(date));
	        
			//INVENTORY TABLE
	        scrollPane1 = new JScrollPane();
	        scrollPane1.setBorder(new LineBorder(new Color(51, 79, 33), 2, true));
	        scrollPane1.setBackground(new Color(197, 224, 180));
	        scrollPane1.setBounds(42, 71, 1103, 442);
	        main_panel.add(scrollPane1);
	        
	        inventoryTable = new JTable();
	        inventoryTable.setEnabled(false);
	        scrollPane1.setViewportView(inventoryTable);
	
	        header1 = inventoryTable.getTableHeader();
	        header1.setFont(new Font("Tahoma", Font.BOLD, 12));
	        inventoryTable.setModel(new DefaultTableModel (
	        		arr_for_table,
	                new String [] {
	                    "Item ID", "Item Name", "Stocks", "Unit Price"
	                }
	        ));
	        inventoryTable.setAutoCreateRowSorter(false);
	        inventoryTable.setFont(new Font("Tahoma", Font.PLAIN, 16));
	        inventoryTable.setSelectionBackground(new java.awt.Color(153, 153, 255));
	        scrollPane1.setViewportView(inventoryTable);
	        
	        centerRenderer = new DefaultTableCellRenderer();
	        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
	        inventoryTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
	        inventoryTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
	        inventoryTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
	        //--
	        
	        btnAddItem = new Button("ADD ITEM");
	        btnAddItem.addMouseListener(new MouseAdapter() {
	        	public void mouseEntered(MouseEvent e) {
	        		btnAddItem.setBackground(new Color (229,149,96));
	        	}
	        	public void mouseExited(MouseEvent e) {
	        		btnAddItem.setBackground(new Color(130, 166, 145));
	        	}
	        	public void mouseClicked(MouseEvent arg0) {
	        		ManageInventory.dispose();
	        		AddItem.main(null);
	        	}
	        });
	        btnAddItem.setForeground(Color.WHITE);
	        btnAddItem.setFont(new Font("Tahoma", Font.BOLD, 16));
	        btnAddItem.setBackground(new Color(130, 166, 145));
	        btnAddItem.setBounds(358, 544, 130, 34);
	        main_panel.add(btnAddItem); 
	        btnUpdate = new Button("UPDATE STOCKS");
	        btnUpdate.addMouseListener(new MouseAdapter() {
	        	public void mouseEntered(MouseEvent e) {
	        		btnUpdate.setBackground(new Color (229,149,96));
	        	}
	        	public void mouseExited(MouseEvent e) {
	        		btnUpdate.setBackground(new Color(130, 166, 145));
	        	}
	        	public void mouseClicked(MouseEvent arg0) {
	        		ManageInventory.dispose();
	        		UpdateStocks.main(null);
	        	}
	        });
	        btnUpdate.setForeground(Color.WHITE);
	        btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 14));
	        btnUpdate.setBackground(new Color(130, 166, 145));
	        btnUpdate.setBounds(530, 544, 130, 34);
	        main_panel.add(btnUpdate);
	        
	        btnRemoveItem = new Button("REMOVE ITEM");
	        btnRemoveItem.addMouseListener(new MouseAdapter() {
	        	public void mouseEntered(MouseEvent e) {
	        		btnRemoveItem.setBackground(new Color (229,149,96));
	        	}
	        	public void mouseExited(MouseEvent e) {
	        		btnRemoveItem.setBackground(new Color(130, 166, 145));
	        	}
	        	public void mouseClicked(MouseEvent arg0) {
	        		ManageInventory.dispose();
	        		RemoveItem.main(null);
	        	}
	        });        
	        btnRemoveItem.setFont(new Font("Tahoma", Font.BOLD, 16));
	        btnRemoveItem.setBounds(701, 544, 130, 34);
	        btnRemoveItem.setBackground(new Color(130,166,145));
	        btnRemoveItem.setForeground(new Color(255, 255, 255));
	        main_panel.add(btnRemoveItem);
		}
		catch(Exception ex2) {
			System.out.println(ex2);
		}
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageInventory window = new ManageInventory();
					window.ManageInventory.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public ManageInventory() {
		initialize();
	}
}
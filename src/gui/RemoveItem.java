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
import java.sql.DriverManager;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import dblogic.dblogic;

public class RemoveItem extends dblogic{

	JFrame RemoveItem;
	private JPanel main_panel;
	private JLabel txtDate;
	private JLabel lblHome;
	private JLabel lblDate;
	private JLabel lblArrow1;
	private JLabel lblManageInventory;
	private JLabel txtOrder;
	private JLabel lblArrow2;
	private JLabel lblRemove;
	private JLabel lblSelect;
	private Date date;
	private JTableHeader header1;
	private JTable inventoryTable;
	private JScrollPane scrollPane1;
	private Button btnRemove;
	private Button btnCancel;
	private DefaultTableCellRenderer centerRenderer;
	

	private void initialize() throws SQLException {
		
		String[][] arr_for_table = new String[10000][4];
		String sql = "Select * FROM PRODUCT";
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
	
	
		RemoveItem = new JFrame("Retail POS System");
		RemoveItem.setBounds(100, 100, 1200, 650);
		RemoveItem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		RemoveItem.setVisible(true);
		RemoveItem.setResizable(false);
     
		main_panel = new JPanel();
		main_panel.setBackground(new Color(246,244,230));
		main_panel.setBounds(0, 0, 730, 401);
		main_panel.setLayout(null);
		RemoveItem.getContentPane().add(main_panel);
		
		lblHome = new JLabel("Home");
        lblHome.addMouseListener(new MouseAdapter() {
        	public void mouseEntered(MouseEvent e) {
        		lblHome.setForeground(new Color(74,83,68));
        	}
        	public void mouseExited(MouseEvent e) {
        		lblHome.setForeground(new Color (229, 149, 96));
        	}
        	public void mouseClicked(MouseEvent e) {
        		RemoveItem.dispose();
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
        		RemoveItem.dispose();
        		ManageInventory.main(null);
        	}
        });
        lblManageInventory.setHorizontalAlignment(SwingConstants.LEFT);
        lblManageInventory.setForeground(new Color(229, 149, 96));
        lblManageInventory.setFont(new Font("Tahoma", Font.PLAIN, 30));
        lblManageInventory.setBounds(133, 13, 249, 34);
        main_panel.add(lblManageInventory);

        lblDate = new JLabel("Date:");
        lblDate.setForeground(new Color(229, 149, 96));
        lblDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblDate.setBounds(989, 22, 42, 24);
        main_panel.add(lblDate);
        
        txtDate = new JLabel();
        txtDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txtDate.setForeground(new Color(74,83,68));
        txtDate.setHorizontalAlignment(SwingConstants.CENTER);
        txtDate.setBounds(1028, 22, 125, 24);
        main_panel.add(txtDate);
        
        date = new Date();
		txtDate.setText(new SimpleDateFormat("d MMMM yyyy").format(date));

        lblArrow2 = new JLabel(">");
        lblArrow2.setHorizontalAlignment(SwingConstants.LEFT);
        lblArrow2.setForeground(new Color(229, 149, 96));
        lblArrow2.setFont(new Font("Tahoma", Font.PLAIN, 30));
        lblArrow2.setBounds(382, 12, 25, 34);
        main_panel.add(lblArrow2);
        
        lblRemove = new JLabel("Remove Item");
        lblRemove.setHorizontalAlignment(SwingConstants.LEFT);
        lblRemove.setForeground(new Color(130, 166, 145));
        lblRemove.setFont(new Font("Tahoma", Font.PLAIN, 30));
        lblRemove.setBounds(408, 12, 195, 34);
        main_panel.add(lblRemove);

        lblSelect = new JLabel("Select one item row only to remove  from inventory.");
        lblSelect.setForeground(new Color(130, 166, 145));
        lblSelect.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblSelect.setBounds(44, 85, 436, 24);
        main_panel.add(lblSelect);

        //INVENTORY TABLE
        scrollPane1 = new JScrollPane();
        scrollPane1.setBorder(new LineBorder(new Color(51, 79, 33), 2, true));
        scrollPane1.setBackground(new Color(197, 224, 180));
        scrollPane1.setBounds(42, 109, 1111, 409);
        main_panel.add(scrollPane1);
        
        inventoryTable = new JTable();
        inventoryTable.setEnabled(true);
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
        
        txtOrder = new JLabel("");
        txtOrder.setHorizontalAlignment(SwingConstants.RIGHT);
        txtOrder.setForeground(new Color(130, 166, 145));
        txtOrder.setFont(new Font("Tahoma", Font.PLAIN, 20));
        txtOrder.setBounds(786, 199, 165, 34);
        main_panel.add(txtOrder);
        
        btnCancel = new Button("CANCEL");
        btnCancel.addMouseListener(new MouseAdapter() {
        	public void mouseEntered(MouseEvent e) {
        		btnCancel.setBackground(new Color (229,149,96));
        	}
        	public void mouseExited(MouseEvent e) {
        		btnCancel.setBackground(new Color(130, 166, 145));
        	}
        	public void mouseClicked(MouseEvent e) {
        		RemoveItem.dispose();
        		ManageInventory.main(null);
        	}
        });
        btnCancel.setForeground(Color.WHITE);
        btnCancel.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnCancel.setBackground(new Color(130, 166, 145));
        btnCancel.setBounds(460, 549, 125, 34);
        main_panel.add(btnCancel);
        
        btnRemove = new Button("REMOVE ITEM");
        btnRemove.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	
        	try {
        		int selectedIndex = inventoryTable.getSelectedRow();
        		String id = inventoryTable.getModel().getValueAt(selectedIndex, 0).toString();
        		id   = id.replaceAll("\\D", "");
    			int int_id = Integer.parseInt(id);
        		int dialogresult = JOptionPane.showConfirmDialog(null, "Do you want to delete the record?", "Warning",JOptionPane.YES_NO_OPTION);
        		
        		if (dialogresult == JOptionPane.YES_OPTION) {
        			
        			DefaultTableModel table = (DefaultTableModel)inventoryTable.getModel();
                	int row = inventoryTable.getSelectedRow();

               		  if (row < 0) {
               		        JOptionPane.showMessageDialog(null, "Please select one row."); 
               		  }      
               		  else {
               		     table.removeRow(row);
               		  }
        			
        				String driver = "net.ucanaccess.jdbc.UcanaccessDriver",
        						db = "jdbc:ucanaccess://C:\\Users\\asus\\Desktop\\THIRD YEAR\\APPDEV\\RetailPOS_System2\\database\\adet_database_try_ver_5.accdb";
						
        				Class.forName(driver);
        				con =  DriverManager.getConnection(db);
        				ps = con.prepareStatement("DELETE from PRODUCT where Prod_No = ?");
        				ps.setInt(1, int_id);
        				ps.executeUpdate();
        				JOptionPane.showMessageDialog(null, "Record removed.");
        		  }
        		else {
        			JOptionPane.showMessageDialog(main_panel, "No record removed.");
        		}
			}
			
			catch (Exception e1) {
				e1.printStackTrace();
			}
        }
        });
        btnRemove.addMouseListener(new MouseAdapter() {
        	public void mouseEntered(MouseEvent e) {
        		btnRemove.setBackground(new Color (229,149,96));
        	}
        	public void mouseExited(MouseEvent e) {
        		btnRemove.setBackground(new Color(130, 166, 145));
        	}
        });
        btnRemove.setForeground(Color.WHITE);
        btnRemove.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnRemove.setBackground(new Color(130, 166, 145));
        btnRemove.setBounds(617, 549, 125, 34);
        main_panel.add(btnRemove);

	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RemoveItem window = new RemoveItem();
					window.RemoveItem.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
		});
	}


	public RemoveItem() throws SQLException {
		initialize();
	}
}
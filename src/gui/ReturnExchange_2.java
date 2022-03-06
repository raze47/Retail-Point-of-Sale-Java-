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

public class ReturnExchange_2 {

	JFrame ReturnExchange_2;
	private JPanel main_panel;
	private JLabel txtDate;
	private JLabel lblHome;
	private JLabel lblDate;
	private JLabel lblArrow1;
	private JLabel lblReturnEx;
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
	private JTableHeader header1;
	private JTableHeader header2;
	private JScrollPane scrollPane1;
	private JScrollPane scrollPane2;
	private Button btnAdd;
	private Button btnProceed;
	private Button btnBack;
	private DefaultTableCellRenderer centerRenderer;
	
	private void initialize() {
		
		ReturnExchange_2 = new JFrame("Retail POS System");
		ReturnExchange_2.setBounds(100, 100, 1200, 650);
		ReturnExchange_2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ReturnExchange_2.setVisible(true);
		ReturnExchange_2.setResizable(false);
     
		main_panel = new JPanel();
		main_panel.setBackground(new Color(246,244,230));
		main_panel.setBounds(0, 0, 730, 401);
		main_panel.setLayout(null);
		ReturnExchange_2.getContentPane().add(main_panel);
		
		lblHome = new JLabel("Home");
        lblHome.addMouseListener(new MouseAdapter() {
        	public void mouseEntered(MouseEvent e) {
        		lblHome.setForeground(new Color(74,83,68));
        	}
        	public void mouseExited(MouseEvent e) {
        		lblHome.setForeground(new Color (229, 149, 96));
        	}
        	public void mouseClicked(MouseEvent e) {
        		ReturnExchange_2.dispose();
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
        lblReturnEx.setBounds(133, 13, 354, 34);
        main_panel.add(lblReturnEx);
        
        lblDate = new JLabel("Date:");
        lblDate.setForeground(new Color(229, 149, 96));
        lblDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblDate.setBounds(990, 22, 42, 24);
        main_panel.add(lblDate);
        
        txtDate = new JLabel();
        txtDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txtDate.setForeground(new Color(74,83,68));
        txtDate.setHorizontalAlignment(SwingConstants.CENTER);
        txtDate.setBounds(1029, 22, 125, 24);
        main_panel.add(txtDate);
     
        date = new Date();
		txtDate.setText(new SimpleDateFormat("d MMMM yyyy").format(date));

        //PRODUCT TABLE
        scrollPane1 = new JScrollPane();
		scrollPane1.setBorder(new LineBorder(new Color(51, 79, 33), 2, true));
		scrollPane1.setBackground(new Color(197, 224, 180));
		scrollPane1.setBounds(34, 71, 428, 508);
		main_panel.add(scrollPane1);
		
        productTable = new JTable();
        productTable.setEnabled(false);
		scrollPane1.setViewportView(productTable);
		
		header1 = productTable.getTableHeader();
		header1.setFont(new Font("Tahoma", Font.BOLD, 12));
        productTable.setModel(new DefaultTableModel (
                new Object [][] { 
                	
                },
                new String [] {
                    "Item Name", "Stocks", "Unit Price"
                }
            		));
        productTable.setAutoCreateRowSorter(false);
        productTable.setFont(new Font("Tahoma", Font.PLAIN, 16));
        productTable.setSelectionBackground(new java.awt.Color(153, 153, 255));
        scrollPane1.setViewportView(productTable);
        
        centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        productTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        productTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        productTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        //--

        //CART TABLE
        lblCart = new JLabel("Shopping Cart");
        lblCart.setHorizontalAlignment(SwingConstants.CENTER);
        lblCart.setForeground(new Color(130, 166, 145));
        lblCart.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblCart.setBounds(740, 57, 156, 34);
        main_panel.add(lblCart);
        
        scrollPane2 = new JScrollPane();
        scrollPane2.setBorder(new LineBorder(new Color(51, 79, 33), 2, true));
        scrollPane2.setBackground(new Color(197, 224, 180));
        scrollPane2.setBounds(484, 95, 670, 309);
        main_panel.add(scrollPane2);
        
        cartTable = new JTable();
        cartTable.setEnabled(false);
        scrollPane2.setViewportView(cartTable);

        header2 = cartTable.getTableHeader();
        header2.setFont(new Font("Tahoma", Font.BOLD, 12));
        cartTable.setModel(new DefaultTableModel (
                new Object [][] { 
                	
                },
                new String [] {
                		 "Item ID", "Item Name", "Unit Price", "Qty"
                }
            		));
        cartTable.setAutoCreateRowSorter(false);
        cartTable.setFont(new Font("Tahoma", Font.PLAIN, 16));
        cartTable.setSelectionBackground(new java.awt.Color(153, 153, 255));
        scrollPane2.setViewportView(cartTable);
        
        centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        cartTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        cartTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        cartTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        //--
        
        lblItemName = new JLabel("Item Name:");
        lblItemName.setHorizontalAlignment(SwingConstants.LEFT);
        lblItemName.setForeground(new Color(229, 149, 96));
        lblItemName.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblItemName.setBounds(484, 410, 111, 24);
        main_panel.add(lblItemName);
        
        txtItemName = new JTextField();
        txtItemName.setHorizontalAlignment(SwingConstants.CENTER);
        txtItemName.setBounds(589, 410, 202, 24);
        main_panel.add(txtItemName);
        txtItemName.setColumns(10);
        
        lblQuantity = new JLabel("Quantity:");
        lblQuantity.setHorizontalAlignment(SwingConstants.LEFT);
        lblQuantity.setForeground(new Color(229, 149, 96));
        lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblQuantity.setBounds(484, 445, 74, 24);
        main_panel.add(lblQuantity);

        txtQty = new JTextField();
        txtQty.setHorizontalAlignment(SwingConstants.CENTER);
        txtQty.setColumns(10);
        txtQty.setBounds(589, 445, 202, 24);
        main_panel.add(txtQty);
        
        btnAdd = new Button("ADD");
        btnAdd.addMouseListener(new MouseAdapter() {
        	public void mouseEntered(MouseEvent e) {
        		btnAdd.setBackground(new Color (229,149,96));
        	}
        	public void mouseExited(MouseEvent e) {
        		btnAdd.setBackground(new Color(130, 166, 145));
        	}
        	@Override
        	public void mouseClicked(MouseEvent e) {
            	JOptionPane.showMessageDialog(null, "Item added!");
        	}
        });
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnAdd.setBackground(new Color(130, 166, 145));
        btnAdd.setBounds(664, 476, 125, 28);
        main_panel.add(btnAdd);
        
        lblGrandTotal = new JLabel("GRAND TOTAL:");
        lblGrandTotal.setHorizontalAlignment(SwingConstants.LEFT);
        lblGrandTotal.setForeground(new Color(229, 149, 96));
        lblGrandTotal.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblGrandTotal.setBounds(823, 410, 156, 39);
        main_panel.add(lblGrandTotal);
        
        txtGrandTotal = new JTextField();
        txtGrandTotal.setText("PHP");
        txtGrandTotal.setFont(new Font("Tahoma", Font.BOLD, 15));
        txtGrandTotal.setEditable(false);
        txtGrandTotal.setHorizontalAlignment(SwingConstants.RIGHT);
        txtGrandTotal.setColumns(10);
        txtGrandTotal.setBounds(989, 410, 165, 35);
        main_panel.add(txtGrandTotal);
        
        btnBack = new Button("BACK");
        btnBack.addMouseListener(new MouseAdapter() {
        	public void mouseEntered(MouseEvent e) {
        		btnBack.setBackground(new Color (229,149,96));
        	}
        	public void mouseExited(MouseEvent e) {
        		btnBack.setBackground(new Color(130, 166, 145));
        	}
        	public void mouseClicked(MouseEvent e) {
            	int a = JOptionPane.showConfirmDialog(null, "Are you sure you do not wish to proceed?", "Select action", JOptionPane.YES_NO_OPTION);
            		
                if (a == JOptionPane.YES_OPTION) {
                	ReturnExchange_2.dispose();
                	ReturnExchange_1.main(null);
                }	
        	}
        });
        btnBack.setForeground(Color.WHITE);
        btnBack.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnBack.setBackground(new Color(130, 166, 145));
        btnBack.setBounds(1029, 505, 125, 34);
        main_panel.add(btnBack);
        
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
            		ReturnExchange_2.dispose();
            		ReturnExchange_3.main(null);
            	}
        	}
        });        
        btnProceed.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnProceed.setBounds(1029, 545, 125, 34);
        btnProceed.setBackground(new Color(130,166,145));
        btnProceed.setForeground(new Color(255, 255, 255));
        main_panel.add(btnProceed);
        
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReturnExchange_2 window = new ReturnExchange_2();
					window.ReturnExchange_2.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public ReturnExchange_2() {
		initialize();
	}
}
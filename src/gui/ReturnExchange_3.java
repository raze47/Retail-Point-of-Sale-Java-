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

public class ReturnExchange_3 {

	JFrame ReturnExchange_3;
	private JPanel main_panel;
	private JLabel txtDate;
	private JLabel lblHome;
	private JLabel lblDate;
	private JLabel lblArrow1;
	private JLabel lblReturnEx;
	private JLabel lblCart;
	private JLabel lblTotalPayment;
	private JLabel txtTotalPayment;
	private JLabel lblGrandTotal;
	private JLabel lblBalance;
	private JLabel txtBalance;
	private JLabel lblTenderedAmt;
	private JLabel lblChangeAmount;
	private JTextField txtTenderedAmt;
	private JTextField txtChange;
	private JTextField txtGrandTotal;
	private Date date;	
	private JScrollPane scrollPane1;
	private JTableHeader header1;
	private JTable cartTable;
	private Button btnCancel;
	private Button btnPlaceOrder;
	private DefaultTableCellRenderer centerRenderer;

	private void initialize() {
		
		ReturnExchange_3 = new JFrame("Retail POS System");
		ReturnExchange_3.setBounds(100, 100, 1200, 650);
		ReturnExchange_3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ReturnExchange_3.setVisible(true);
		ReturnExchange_3.setResizable(false);
     
		main_panel = new JPanel();
		main_panel.setBackground(new Color(246,244,230));
		main_panel.setBounds(0, 0, 730, 401);
		main_panel.setLayout(null);
		ReturnExchange_3.getContentPane().add(main_panel);
		
		lblHome = new JLabel("Home");
        lblHome.addMouseListener(new MouseAdapter() {
        	public void mouseEntered(MouseEvent e) {
        		lblHome.setForeground(new Color(74,83,68));
        	}
        	public void mouseExited(MouseEvent e) {
        		lblHome.setForeground(new Color (229, 149, 96));
        	}
        	public void mouseClicked(MouseEvent e) {
        		ReturnExchange_3.dispose();
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
        lblDate.setBounds(985, 12, 42, 24);
        main_panel.add(lblDate);
        
        txtDate = new JLabel();
        txtDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txtDate.setForeground(new Color(74,83,68));
        txtDate.setHorizontalAlignment(SwingConstants.CENTER);
        txtDate.setBounds(1024, 12, 125, 24);
        main_panel.add(txtDate);
     
        date = new Date();
		txtDate.setText(new SimpleDateFormat("d MMMM yyyy").format(date));

        lblCart = new JLabel("Shopping Cart");
        lblCart.setHorizontalAlignment(SwingConstants.LEFT);
        lblCart.setForeground(new Color(130, 166, 145));
        lblCart.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblCart.setBounds(33, 74, 136, 34);
        main_panel.add(lblCart);
        
        //CART TABLE
        scrollPane1 = new JScrollPane();
        scrollPane1.setBorder(new LineBorder(new Color(51, 79, 33), 2, true));
        scrollPane1.setBackground(new Color(197, 224, 180));
        scrollPane1.setBounds(31, 107, 788, 366);
        main_panel.add(scrollPane1);
        
        cartTable = new JTable();
        cartTable.setEnabled(false);
        scrollPane1.setViewportView(cartTable);

        header1 = cartTable.getTableHeader();
        header1.setFont(new Font("Tahoma", Font.BOLD, 12));
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
        lblGrandTotal.setBounds(33, 484, 156, 35);
        main_panel.add(lblGrandTotal);
        
        txtGrandTotal = new JTextField();
        txtGrandTotal.setText("PHP");
        txtGrandTotal.setFont(new Font("Tahoma", Font.BOLD, 15));
        txtGrandTotal.setEditable(false);
        txtGrandTotal.setHorizontalAlignment(SwingConstants.RIGHT);
        txtGrandTotal.setColumns(10);
        txtGrandTotal.setBounds(199, 484, 165, 35);
        main_panel.add(txtGrandTotal);

        lblTotalPayment = new JLabel("Total Payment:");
        lblTotalPayment.setHorizontalAlignment(SwingConstants.LEFT);
        lblTotalPayment.setForeground(new Color(229, 149, 96));
        lblTotalPayment.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblTotalPayment.setBounds(839, 120, 118, 24);
        main_panel.add(lblTotalPayment);
        
        txtTotalPayment = new JLabel();
        txtTotalPayment.setText("PHP");
        txtTotalPayment.setFont(new Font("Tahoma", Font.BOLD, 15));
        txtTotalPayment.setHorizontalAlignment(SwingConstants.RIGHT);
        txtTotalPayment.setBounds(1007, 120, 142, 24);
        main_panel.add(txtTotalPayment);
        
        lblBalance = new JLabel("Balance:");
        lblBalance.setHorizontalAlignment(SwingConstants.LEFT);
        lblBalance.setForeground(new Color(229, 149, 96));
        lblBalance.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblBalance.setBounds(842, 155, 118, 24);
        main_panel.add(lblBalance);
        
        txtBalance = new JLabel();
        txtBalance.setFont(new Font("Tahoma", Font.BOLD, 15));
        txtBalance.setText("PHP");
        txtBalance.setHorizontalAlignment(SwingConstants.RIGHT);
        txtBalance.setBounds(1007, 155, 142, 24);
        main_panel.add(txtBalance);
        
        lblTenderedAmt = new JLabel("Tendered Amount:");
        lblTenderedAmt.setHorizontalAlignment(SwingConstants.LEFT);
        lblTenderedAmt.setForeground(new Color(229, 149, 96));
        lblTenderedAmt.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblTenderedAmt.setBounds(842, 216, 142, 24);
        main_panel.add(lblTenderedAmt);
        
        txtTenderedAmt = new JTextField();
        txtTenderedAmt.setFont(new Font("Tahoma", Font.BOLD, 15));
        txtTenderedAmt.setHorizontalAlignment(SwingConstants.RIGHT);
        txtTenderedAmt.setColumns(10);
        txtTenderedAmt.setBounds(1007, 216, 142, 24);
        main_panel.add(txtTenderedAmt);
        
        lblChangeAmount = new JLabel("Change Amount:");
        lblChangeAmount.setHorizontalAlignment(SwingConstants.LEFT);
        lblChangeAmount.setForeground(new Color(229, 149, 96));
        lblChangeAmount.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblChangeAmount.setBounds(842, 251, 142, 24);
        main_panel.add(lblChangeAmount);
        
        txtChange = new JTextField();
        txtChange.setText("PHP");
        txtChange.setFont(new Font("Tahoma", Font.BOLD, 15));
        txtChange.setEditable(false);
        txtChange.setHorizontalAlignment(SwingConstants.RIGHT);
        txtChange.setColumns(10);
        txtChange.setBounds(1007, 251, 142, 24);
        main_panel.add(txtChange);
        
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
                	ReturnExchange_3.dispose();
                	ReturnExchange_2.main(null);
                }	
        	}
        });
        btnCancel.setForeground(Color.WHITE);
        btnCancel.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnCancel.setBackground(new Color(130, 166, 145));
        btnCancel.setBounds(877, 545, 125, 34);
        main_panel.add(btnCancel);

        btnPlaceOrder = new Button("PLACE ORDER");
        btnPlaceOrder.addMouseListener(new MouseAdapter() {
        	public void mouseEntered(MouseEvent e) {
        		btnPlaceOrder.setBackground(new Color (229,149,96));
        	}
        	public void mouseExited(MouseEvent e) {
        		btnPlaceOrder.setBackground(new Color(130, 166, 145));
        	}
        	public void mouseClicked(MouseEvent e) {
        		JOptionPane.showMessageDialog(null, "Order has been returned and exchanged successfully!");
        		ReturnExchange_3.dispose();
        		SelectTransaction.main(null);
        	}
        });        
        btnPlaceOrder.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnPlaceOrder.setBounds(1027, 545, 125, 34);
        btnPlaceOrder.setBackground(new Color(130,166,145));
        btnPlaceOrder.setForeground(new Color(255, 255, 255));
        main_panel.add(btnPlaceOrder);

	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReturnExchange_3 window = new ReturnExchange_3();
					window.ReturnExchange_3.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public ReturnExchange_3() {
		initialize();
	}
}
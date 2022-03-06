package gui;
import dblogic.dblogic;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Button;
import javax.swing.JComboBox;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SelectTransaction extends dblogic{

	JFrame SelectTransaction;
	private JPanel main_panel;
	private JLabel txtDate;
	private JComboBox <String> comboSelectTrans;
	private Button btnProceed;
	private JLabel lblSelectTransaction;
	private JLabel txtEmpNo;
	private JLabel lblDate;
	private JLabel lblEmpNo;
	private Date date;
	private Button btnLogout;
	
	private void initialize() {
		
		//setBounds(x, y, width, height)
		
		SelectTransaction = new JFrame("Retail POS System");
		SelectTransaction.setBounds(100, 100, 730, 401);
		SelectTransaction.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SelectTransaction.setVisible(true);
		SelectTransaction.setResizable(false);
     
		main_panel = new JPanel();
		main_panel.setBackground(new Color(246,244,230));
		main_panel.setBounds(0, 0, 730, 401);
		main_panel.setLayout(null);
		SelectTransaction.getContentPane().add(main_panel);
        
        txtDate = new JLabel();
        txtDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txtDate.setForeground(new Color(74,83,68));
        txtDate.setHorizontalAlignment(SwingConstants.CENTER);
        txtDate.setBounds(559, 23, 125, 24);
        main_panel.add(txtDate);
     
        date = new Date();
		txtDate.setText(new SimpleDateFormat("d MMMM yyyy").format(date));
		
        comboSelectTrans = new JComboBox <String>();
        comboSelectTrans.setBounds(205, 162, 304, 34);
        comboSelectTrans.addItem("");
        comboSelectTrans.addItem("Create Order");        
        comboSelectTrans.addItem("Return & Exchange Order");
        comboSelectTrans.addItem("Refund Order");
        comboSelectTrans.addItem("Manage Inventory");
        comboSelectTrans.addItem("Sales Transaction");
        comboSelectTrans.setSelectedIndex(0);
        main_panel.add(comboSelectTrans);

        btnProceed = new Button("PROCEED");
        btnProceed.addMouseListener(new MouseAdapter() {
        	public void mouseEntered(MouseEvent e) {
        		btnProceed.setBackground(new Color (229,149,96));
        	}
        	public void mouseExited(MouseEvent e) {
        		btnProceed.setBackground(new Color(130, 166, 145));
        	}
        	public void mouseClicked(MouseEvent e) {
        		
        		if (comboSelectTrans.getSelectedItem().equals("Create Order")) {
                	SelectTransaction.dispose();
        			CreateOrder.main(null);
                }
        		
        		if (comboSelectTrans.getSelectedItem().equals("Return & Exchange Order")) {
                	SelectTransaction.dispose();
        			ReturnExchange_1.main(null);
                }
        		
        		if (comboSelectTrans.getSelectedItem().equals("Refund Order")) {
                	SelectTransaction.dispose();
        			RefundOrder.main(null);
                }
        		
        		if (comboSelectTrans.getSelectedItem().equals("Manage Inventory")) {
                	SelectTransaction.dispose();
                	ManageInventory.main(null);
                }
        		if (comboSelectTrans.getSelectedItem().equals("Sales Transaction")) {
                	SelectTransaction.dispose();
                	SalesReport.main(null);
                }
			}
        });  
        btnProceed.setFont(new Font("Calibri", Font.BOLD, 20));
        btnProceed.setBounds(296, 214, 125, 34);
        btnProceed.setBackground(new Color(130,166,145));
        btnProceed.setForeground(new Color(255, 255, 255));
        main_panel.add(btnProceed);
        
        lblSelectTransaction = new JLabel("Select Transaction");
        lblSelectTransaction.setHorizontalAlignment(SwingConstants.CENTER);
        lblSelectTransaction.setForeground(new Color(229, 149, 96));
        lblSelectTransaction.setFont(new Font("Tahoma", Font.BOLD, 35));
        lblSelectTransaction.setBounds(176, 108, 363, 34);
        main_panel.add(lblSelectTransaction);
        
        lblDate = new JLabel("Date:");
        lblDate.setForeground(new Color(229, 149, 96));
        lblDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblDate.setBounds(520, 23, 42, 24);
        main_panel.add(lblDate);
        
        lblEmpNo = new JLabel("Employee Number");
        lblEmpNo.setHorizontalAlignment(SwingConstants.LEFT);
        lblEmpNo.setForeground(new Color(229, 149, 96));
        lblEmpNo.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblEmpNo.setBounds(23, 319, 131, 24);
        main_panel.add(lblEmpNo);
        
        txtEmpNo = new JLabel();
        txtEmpNo.setText("####");
        txtEmpNo.setHorizontalAlignment(SwingConstants.RIGHT);
        txtEmpNo.setBounds(164, 320, 108, 24);
        main_panel.add(txtEmpNo);
        
        btnLogout = new Button("LOGOUT");
        btnLogout.addMouseListener(new MouseAdapter() {
        	public void mouseEntered(MouseEvent e) {
        		btnLogout.setBackground(new Color (229,149,96));
        	}
        	public void mouseExited(MouseEvent e) {
        		btnLogout.setBackground(new Color(186, 186, 186));
        	}
        	public void mouseClicked(MouseEvent e) {
        		LoggedUser = "";
        		
        		SelectTransaction.dispose();
        		Login.main(null);
        	}
        });
        btnLogout.setForeground(Color.WHITE);
        btnLogout.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnLogout.setBackground(new Color(186, 186, 186));
        btnLogout.setBounds(601, 319, 93, 24);
        main_panel.add(btnLogout);
        
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectTransaction window = new SelectTransaction();
					window.SelectTransaction.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public SelectTransaction() {
		initialize();
	}
}
package gui;
import dblogic.dblogic;

import java.awt.Color;
//import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Button;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login extends dblogic {

	public JFrame Login;
	private JPanel main_panel;
	private JLabel lblEmployeeNo;
	private JLabel lblPassword;
	private JLabel lblAuthorizedLogin;
	private JLabel Image;
	private JTextField txtEmployeeNo;
	private JPasswordField passfieldEmp;
	private Button btnLogin;
	
	
	private void initialize() {
		
		Login = new JFrame("Retail POS System");
		Login.setBounds(100, 100, 536, 332);
		Login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Login.setVisible(true);
		Login.setResizable(false);
     
		main_panel = new JPanel();
		main_panel.setBackground(new Color(246,244,230));
		main_panel.setBounds(0, 0, 750,700);
		main_panel.setLayout(null);
		Login.getContentPane().add(main_panel);
        
		Image = new JLabel("");
        Image.setIcon(new ImageIcon(Login.class.getResource("/images/auth-user.png")));
        Image.setBounds(225, 28, 65, 65);
        main_panel.add(Image);
        
        lblAuthorizedLogin = new JLabel("Authorized Login");
        lblAuthorizedLogin.setHorizontalAlignment(SwingConstants.CENTER);
        lblAuthorizedLogin.setForeground(new Color(229, 149, 96));
        lblAuthorizedLogin.setFont(new Font("Tahoma", Font.BOLD, 25));
        lblAuthorizedLogin.setBounds(143, 104, 233, 34);
        main_panel.add(lblAuthorizedLogin);
        
        lblEmployeeNo = new JLabel("Employee Number:");
        lblEmployeeNo.setForeground(new Color(229, 149, 96));
        lblEmployeeNo.setFont(new Font("Calibri", Font.PLAIN, 16));
        lblEmployeeNo.setBounds(79, 149, 136, 24);
        main_panel.add(lblEmployeeNo);
        
        txtEmployeeNo = new JTextField();
        txtEmployeeNo.setHorizontalAlignment(SwingConstants.CENTER);
        txtEmployeeNo.setBounds(225, 149, 206, 24);
        main_panel.add(txtEmployeeNo);
        
        lblPassword = new JLabel("Password:");
        lblPassword.setForeground(new Color(229, 149, 96));
        lblPassword.setFont(new Font("Calibri", Font.PLAIN, 16));
        lblPassword.setBounds(79, 184, 73, 24);
        main_panel.add(lblPassword);
        
        passfieldEmp = new JPasswordField();
        passfieldEmp.setHorizontalAlignment(SwingConstants.CENTER);
        passfieldEmp.setBounds(225, 184, 206, 24);
        main_panel.add(passfieldEmp);
        
        btnLogin = new Button("LOGIN");
        btnLogin.addMouseListener(new MouseAdapter() {
        	public void mouseEntered(MouseEvent e) {
        		btnLogin.setBackground(new Color (229,149,96));
        	}
        	public void mouseExited(MouseEvent e) {
        		btnLogin.setBackground(new Color(130, 166, 145));
        	}
        	public void mouseClicked(MouseEvent e) {
        		
        		//Initialize database
        		try {
        			boolean logged_in = false;
        			String emp_no = txtEmployeeNo.getText(), emp_pw = new String(passfieldEmp.getPassword());
        			
        			
        			String sql = "Select * FROM EMPLOYEE";
        			
        			st = con.createStatement();
        			rs = st.executeQuery(sql);
        			
        			//Authentication
        			while(rs.next()) {
        				if(emp_no.equals(rs.getString("Emp_No"))) {
        					if(emp_pw.equals(rs.getString("Emp_PW"))) {
        						System.out.println("Welcome "+rs.getString("emp_FName"));
        						LoggedUser = emp_no;
        						System.out.println(emp_no);
        						logged_in = true;
        						JOptionPane.showMessageDialog(Login, "Welcome " + rs.getString("emp_FName") + "!");
                				Login.dispose();
                				SelectTransaction.main(null);    				
        					}
        				}
        			}
        			//If failed to log in
        			if(logged_in == false) {
        				JOptionPane.showMessageDialog(main_panel, "Incorrect employee number or password! Try again.", "Invalid Entry", JOptionPane.ERROR_MESSAGE);
        				System.out.println("Invalid entry. Try again");
        				txtEmployeeNo.setText(""); passfieldEmp.setText("");
        			}
        		}
        		
        		catch(Exception ex1) {
        			System.out.println(ex1);
        		}
			}
		});
        btnLogin.setFont(new Font("Calibri", Font.BOLD, 20));
        btnLogin.setBounds(201, 233, 125, 34);
        btnLogin.setBackground(new Color(130,166,145));
        btnLogin.setForeground(new Color(255, 255, 255));
        main_panel.add(btnLogin);
        
	}
	
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.Login.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/


	public Login() {
		initialize();
	}
}
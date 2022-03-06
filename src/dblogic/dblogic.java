package dblogic;
import java.sql.*;

public class dblogic {
	public Connection con;
	public Statement st;
	public ResultSet rs;
	public PreparedStatement ps;
	
	
	//session tracker
	public static String LoggedUser;

	
	public dblogic() {
		connect();
	}
	public void connect() {
		try {
			String driver = "net.ucanaccess.jdbc.UcanaccessDriver",
					db = "jdbc:ucanaccess://C:\\Users\\RV\\eclipse-workspace\\RetailPOS_SystemPinakaUpdated\\database\\Retail_POS_-_Database.accdb",
					sql = "Select * FROM EMPLOYEE";
			//Guys baguhin niyo nalang yung db variable XDDD
			//db = "jdbc:ucanaccess://C:\\Users\\RV\\eclipse-workspace\\RetailPOS_System2\\database\\Retail_POS_-_Database.accdb"
			Class.forName(driver);
			con =  DriverManager.getConnection(db);
			st = con.createStatement();
			rs = st.executeQuery(sql);
			
			//Testing
			
			
			
		}
		
		catch(Exception e){
			System.out.println(e);
		}
	}
	
	public static void main(String args[]) {
		dblogic dbl = new dblogic();
		dbl.connect();
	}
}

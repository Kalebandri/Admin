import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class connect {
	Connection con;
	Statement st = null;
	ResultSet rs;
	
	
	public void connect(){
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/","root","");
			st = con.createStatement();
			
			st.executeQuery("USE db_Uts");
			

		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public ResultSet readTable(String query){
		
		connect();
		try {
			
			rs = st.executeQuery(query);
			return rs;
			
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		
	}
	
	public void updateTable(String query){
		connect();
		try {
			
			st.executeUpdate(query);
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
}

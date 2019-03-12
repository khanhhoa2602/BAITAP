package Baitap;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
public class ketnoisql  {
		public static void ketnoi(ArrayList<String> listurl) throws SQLException  {
		Connection con=DriverManager.getConnection(  
		"jdbc:mysql://localhost:3306/demo","root","");  
		Statement stmt=(Statement)con.createStatement();  
		ResultSet rs=stmt.executeQuery("SELECT url FROM web"); 
		while(rs.next()) {
			System.out.println(rs.getString(1));
			listurl.add(rs.getString(1));
			}
		con.close();
	}
	
}

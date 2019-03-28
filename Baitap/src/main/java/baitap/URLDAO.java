package baitap;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.LinkedList;
public class URLDAO {
	URL1 u=new URL1();
    static LinkedList<String> listurl = new LinkedList<String>();
    public LinkedList<String> getListurl() {
		 return listurl;
	 }
	public URLDAO() {
	}
	 public synchronized void hien() throws SQLException, MalformedURLException {
	                	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/baitap", "root", "");
	            		Statement stmt = (Statement) con.createStatement();
	            		ResultSet rs = stmt.executeQuery("SELECT * FROM url");
	            		while (rs.next()) {
	            			u.setProtocol(rs.getString(2));
	            			u.setHost(rs.getString(3));
	            			u.setFilename(rs.getString(4));
	            			if (rs.getString(4) == null) {
	            				u.setFilename("");
	            			}
	            			URL url = new URL(u.getProtocol(), u.getHost(), u.getFilename());
	            			listurl.add(url.toString());
	            		}  
	            		Iterator<String> iterator = listurl.iterator();
	            	    while (iterator.hasNext()) {
	            	        System.out.println(iterator.next());
	            	    }
	            	  		con.close();
	            		
	            }
	 
}
	          



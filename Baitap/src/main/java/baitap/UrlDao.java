package baitap;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import java.sql.Statement;
public class UrlDao {
	public static final Logger LOG = Logger.getLogger(UrlDao.class);
	Connectmysql consql;
	public UrlDao(Connectmysql consql) {
		super();
		this.consql = consql;
	}
	//get url from database
	public ArrayList<UrlObject> geturlfromdata() {
		ArrayList<UrlObject> arr=new ArrayList<UrlObject>();
		try {
			Connection con=consql.getConnection();
			
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery("SELECT * FROM inputurl");
			while(rs.next()){
		    	UrlObject url = new UrlObject();
		    	url.setUrl(rs.getString(1));
		    	arr.add(url);
		    	LOG.debug("get url from database:"+url.getUrl());
		    }
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr;
	}
//save output url checked to database
	public void saveUrl(UrlObject u) throws InterruptedException {
		try {
Connection con=consql.getConnection();
			Statement stmt = (Statement) con.createStatement();
			String sql = "REPLACE  into outputurl values( '" + u.getUrl() + "','" + u.getTime() + "','" + u.getTimeResponse().toString() + "','" + String.valueOf(u.getResponseCode()) +"','"+ String.valueOf(u.getMess()) +"')";
			 stmt.executeUpdate(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
		
	}
}

package thutap;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;
import java.util.Scanner; 
public class ketnoi {
	public ketnoi() {
	}
	public static Scanner nhap=new Scanner(System.in);
	static String so;
	static String url = null;
	//kết nối
	public static void danhsachurl() throws SQLException {
		Connection con=DriverManager.getConnection(  
		"jdbc:mysql://localhost:3306/demo","root","");  
		Statement stmt=(Statement)con.createStatement();  
		//--danh sách url
		ResultSet rs=stmt.executeQuery("SELECT * FROM web"); 
		while(rs.next())  
		System.out.println(rs.getInt(1)+"   "+rs.getString(2));
		con.close();
	}
	public static void chonurl(int n,int timeout) throws SQLException, IOException, IOException {
		int dem=0;
		Connection con=DriverManager.getConnection(  
				"jdbc:mysql://localhost:3306/demo","root","");  
		Statement stmt=(Statement)con.createStatement(); 
		//--chon url
		System.out.println("\nnhập stt của url cần request:");
		so=nhap.nextLine();
		ResultSet rs=stmt.executeQuery("SELECT url from web where id="+so);
	    while(rs.next()) 
	    url=rs.getString(1);
	    //http request
		HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
		int responseCode = connection.getResponseCode();
		//--ping n lan,moi lan trong timeout(milis)
		for(int i=0;i<n;i++) {
			if((pingURL(url,timeout))!=1) {
				dem=dem+0;
				
			}
			else {
				dem=dem+1;
			}
			if(dem==3) {
				System.out.println("\nERRO MESS: - loi");
				break;	
			}		
		}
		con.close();
	}
	//--ping
	 private static int pingURL(String url, int timeout) {
		 int pingerro=0;
	        try {
	     
	            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
	            connection.setConnectTimeout(timeout);
	            connection.setReadTimeout(timeout);
	            connection.setRequestMethod("HEAD");
	            int responseCode = connection.getResponseCode();
	            if(200 <= responseCode && responseCode <= 399) {
	            	pingerro=pingerro+0;
	            	System.out.println("\nping to URL: "+url+"- STATUS CODE: "+responseCode+" - truy cap binh thuong");
	            }
	            if( responseCode > 399) {
	            	pingerro=pingerro+1;
	            	System.out.println("\nping to URL: "+url+"- STATUS CODE: "+responseCode+" - khong kha dung");
	            }
	        } catch (IOException exception) {
	    
	        }
	        return pingerro;
	    }

}

package baitap;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.LinkedList;
import org.apache.log4j.Logger;
public class URL1 {
		private static final Logger log = Logger.getLogger(URL1.class);
	//thuoc tinh cua url
	private static String protocol;
	private static String hostmachince;
	private static String filename;
    static LinkedList<String> listurl = new LinkedList<String>();
	public URL1() {
	}
	//hien url tu csdl
	 public synchronized  void hien() throws SQLException, MalformedURLException {
	                	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/baitap", "root", "");
	            		Statement stmt = (Statement) con.createStatement();
	            		ResultSet rs = stmt.executeQuery("SELECT * FROM url");
	            		while (rs.next()) {
	            			protocol = rs.getString(2);
	            			hostmachince = rs.getString(3);
	            			filename = rs.getString(4);
	            			if (rs.getString(4) == null) {
	            				filename = "";
	            			}
	            			URL url = new URL(protocol, hostmachince, filename);
	            			listurl.add(url.toString());
	            		}  
	            		Iterator<String> iterator = listurl.iterator();
	            	    while (iterator.hasNext()) {
	            	        System.out.println(iterator.next());
	            	    }
	            	  		con.close();
	            		
	            }
	 public LinkedList<String> getListurl() {
		 return listurl;
	 }
	 //request url
	   public synchronized void request(String url) {
	            		int statuscode;
	            		int error = 0;
	            		int pingok = 0;	
            		HttpURLConnection connection;
	            		while (true) {
	            			try {
	              				connection = (HttpURLConnection) new URL(url).openConnection();
	            				statuscode = connection.getResponseCode();
	            				if (200 <= statuscode && statuscode <= 399) {
	            					pingok = pingok + 1;
	            					error = error + 0;
	            					System.out
	            							.println("Ping to url: " + url + "--statuscode: " + statuscode + "--truy cap binh thuong");
	            					Thread.sleep(3000);// ping ok-thời gian nghỉ=3s
	            				}
	            				if (400 <= statuscode && statuscode <= 599) {
	            					pingok = pingok + 0;
	            					error = error + 1;
	            					System.out.println("Ping to url: " + url + "--statuscode: " + statuscode + "--khong kha dung");
	            					Thread.sleep(5000);// ping lỗi thời gian nghỉ=5s
	            				}
	            				if (error == 3) {
	            					log.error("url " + url + " -loi");// nếu 3 lần error thì hiển thị ra màn hình và lưu vào file cấu
	            														// hình
	            					break;
	            				}
	            				if (pingok == 3) {
	            					System.out.println("Ping to url: " + url + "--success!");
	            					break;
	            				}
	            			} catch (MalformedURLException e) {
	            				// TODO Auto-generated catch block
	            				e.printStackTrace();
	            			} catch (IOException e) {
	            				// TODO Auto-generated catch block
	            				e.printStackTrace();
	            			} catch (InterruptedException e) {
	            				// TODO Auto-generated catch block
	            				e.printStackTrace();
	            			}
	            
	            		}
	                }
	    
	    public static void main(String arg[]) throws InterruptedException {
	        final URL1 u=new URL1();
	        Thread t1 = new Thread() {
	            public void run() {
	                try {
						u.hien();
					} catch (MalformedURLException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            }
	        };
	        t1.start();
	        LinkedList<String> listurl=u.getListurl();
	        t1.join();
	  
	        Iterator<String> iterator = listurl.iterator();
    	    while (iterator.hasNext()) {
    	    	String url=iterator.next();
    	      Thread t2 = new Thread() {
  	            public void run() {
  	                u.request(url);
  	               
  	            }
  	        };
  	        t2.start();
    	    }
	        
	    }
}
	          



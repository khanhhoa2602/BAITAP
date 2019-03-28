package baitap;
import java.util.Iterator;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.LinkedList;

public class main{
	
	public static void main(String[] args) throws MalformedURLException, SQLException, InterruptedException{
	//khởi tạo đối tượng u từ class URLDAO
	URLDAO u=new URLDAO();
	//Thread t1 hiện url từ csdl
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
	    	//Thread t2 request url
	    	 Thread t2 = new Thread() {
	  	            public void run() {
	  	     //khởi tạo đối tượng request từ class Request
	  	             Request request=new Request(url);
	  	  	    	 request.requesturl(url);  
	  	            }
	  	        };
	  	        t2.start();
	    	
           }
       };
	 
	}
	

    

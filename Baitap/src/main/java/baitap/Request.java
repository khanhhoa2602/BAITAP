package baitap;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.log4j.Logger;
public class Request {
	private static final Logger log = Logger.getLogger(Request.class);
	private String url;
	int statuscode;
	public Request(String url) {
		this.url = url;
	}
	  public synchronized void requesturl(String url) {
			int error=0;
			int pingok=0;
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
	
}

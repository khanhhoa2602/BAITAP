package Baitap;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class main extends Thread{
	static URL url;
	static int i,n;
	static ArrayList<String> listurl = new ArrayList<String>();
	//cấu hình số luồng 
	public static void MyThreadPool(ArrayList<String> list) {
	ExecutorService executor = Executors.newFixedThreadPool(list.size());
	for(i=0;i<list.size();i++) {
		{
			executor.submit(new request(list.get(i).toString()));
		}
	}
	}
	public static void main(String[] args) throws SQLException {
		ketnoisql.ketnoi(listurl);
		MyThreadPool(listurl);

	}

    
}

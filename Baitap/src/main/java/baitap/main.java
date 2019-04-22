package baitap;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
public class main {
	public static final Logger LOG = Logger.getLogger(main.class);
	public static void main(String[] args) throws InterruptedException {
		Myproperties myProperties = new Myproperties();
		myProperties.writeProperties();
		int numThread = Integer.parseInt(myProperties.getProperty("threadPoolSize"));
		int time = Integer.parseInt(myProperties.getProperty("timeOut"));
		String url, user, password;
		url = myProperties.getProperty("url");
		user = myProperties.getProperty("user");
		password = myProperties.getProperty("pass");
	    String numberrequest=myProperties.getProperty("numberrequest");
	    
		UrlObject u=new UrlObject();
		Connectmysql consql = new Connectmysql(url, user, password);
		UrlDao urldao = new UrlDao(consql);
		ArrayList<UrlObject> arr = new ArrayList<UrlObject>();
	   arr = urldao.geturlfromdata();
	   
		ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(100);
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(numThread, numThread, time, TimeUnit.SECONDS, queue);
		int i =0 ;
		while(i < Integer.parseInt(numberrequest)) {
			i++;
			for(UrlObject urlobject : arr)
			{
				threadPoolExecutor.execute(new threadpool(urlobject, urldao));
				
			}
			try {
				Thread.sleep(10000);
			} catch(Exception e){
			}
		}
		System.out.println("done! ");
		

	}
}
package baitap;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import org.apache.log4j.Logger;
public class httpconnect {
	private static final Logger LOG = Logger.getLogger(httpconnect.class);
	public void connect(UrlObject u)
	{
		try
		{
			long t1, t2;
			String url = u.getUrl();
			URL obj = new URL(url);
			t1 = System.currentTimeMillis();
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			u.setResponseCode(con.getResponseCode());
			t2 = System.currentTimeMillis();
			Date date = new Date(t1);
			u.setTime(date.toString());
			u.setTimeResponse(t2-t1);
			
		}
		catch(Exception e)
		{
	
		}
	}
	
}
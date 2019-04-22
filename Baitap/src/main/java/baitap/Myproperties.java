package baitap;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import org.apache.log4j.Logger;
public class Myproperties {
	public static final Logger LOG = Logger.getLogger(Myproperties.class);
	public static void writeProperties() {
		Properties properties = new Properties();
		try {
		FileOutputStream fileOutputStream = new FileOutputStream("config.properties");
		properties.put("user", "root");//user to connect mysql
		properties.put("pass", "");//pass of user to connect mysql
		properties.put("numberrequest", "4");//number of second request an url
		properties.put("threadPoolSize", "4"); // number of Thread in thread Pool
		properties.put("timeOut", "20");  // number of second a Thread live
		properties.put("url", "jdbc:mysql://localhost:3306/requesturl");//url connect to mysql
		properties.store(fileOutputStream, "");
		LOG.info("save properties");
		fileOutputStream.close();
		} catch (Exception e) {
			LOG.error(e);
		}
	}
	public void writeProperty(String key, String value) {
		Properties properties = new Properties();
		try {
			FileInputStream fileInputStream = new FileInputStream("config.properties");
			FileOutputStream fileOutputStream = new FileOutputStream("config.properties");
			properties.load(fileInputStream);
			properties.put(key, value);
			properties.store(fileOutputStream, "");
			fileOutputStream.close();
		}catch(Exception e ) {
		}
	}
	public String getProperty(String key) {
		String value = "";
		Properties properties = new Properties();
		try {
			FileInputStream fileInputStream = new FileInputStream("config.properties");
			properties.load(fileInputStream);
			value = properties.getProperty(key);
			fileInputStream.close();
			LOG.info("get property : " + key);
		}catch (Exception e) {
		}
		return value;
	}
	
	
}

package baitap;
import org.apache.log4j.Logger;
public class threadpool implements Runnable {
	public static final Logger LOG = Logger.getLogger(threadpool.class);
	UrlObject url;
UrlDao urldao;
	public void run() {
		try {
			httpconnect httpConnection = new httpconnect();
			httpConnection.connect(url);
			//if ReponseCode>399 => error
			if(url.getResponseCode()>399) {
				url.setError(url.getError()+1);
			}
			//if error=3 =>save warning mess to database
			if(url.getError()==3) {
				
				url.setMess("ERROR:"+url.getResponseCode());
				LOG.error("Can not connect to url-"+url.getUrl());
			}
			LOG.debug("httpconnect"+url.getTime()+","+url.getTimeResponse()+","+url.getResponseCode());
			urldao.saveUrl(url);
			LOG.debug("Save data");
		} catch (Exception e) {
			LOG.error(e);
			}
	}
	public threadpool(UrlObject url, UrlDao urldao)
	{
		this.url = url;
		this.urldao = urldao;
	
	}
}


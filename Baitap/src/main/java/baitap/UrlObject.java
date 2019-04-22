package baitap;

public class UrlObject {
	private String url;
	private String time;
	private Long timeResponse;
	private int responseCode;
	private int error;
	private String mess;
	public UrlObject() {
		super();
		
	}
	public UrlObject(String url, String time, Long timeResponse, int responseCode,int error,String mess) {
		super();
		this.url = url;
		this.time = time;
		this.timeResponse = timeResponse;
		this.responseCode = responseCode;
		this.error=error;
		this.mess=mess;
	}

	public String getMess() {
		return mess;
	}
	public void setMess(String mess) {
		this.mess = mess;
	}
	public int getError() {
		return error;
	}
	public void setError(int error) {
		this.error = error;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Long getTimeResponse() {
		return timeResponse;
	}
	public void setTimeResponse(Long timeResponse) {
		this.timeResponse = timeResponse;
	}
	public int getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
	

}

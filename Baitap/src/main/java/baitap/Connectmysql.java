package baitap;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class Connectmysql {
	public static final Logger LOG = Logger.getLogger(Connectmysql.class);
	static String Url;
	static String user;
	static String pass;
public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
public Connectmysql(String url, String user, String pass) {
		super();
		Url = url;
		this.user = user;
		this.pass = pass;
	}

private static Connection con;
public static Connection getConnection() {
	con=null;
	try {	
		con=(Connection)DriverManager.getConnection(Url,user,pass);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return con;
	
}
}

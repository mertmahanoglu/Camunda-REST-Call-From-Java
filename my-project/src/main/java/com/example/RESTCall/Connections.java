package com.example.RESTCall;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.example.workflow.CamundaLogger;
import com.example.workflow.CamundaLogger.LogTypes;

public class Connections {
	
	URL _url;
    StringBuilder _sb;
	HttpURLConnection _conn;
	static CamundaLogger _logger;
	
	public  Connections() {
		try {
			set_url(new URL("https://localhost:7040/api/"));
			_sb = new StringBuilder();
			_logger = new CamundaLogger();
		} catch (MalformedURLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public URL setConnection(String apiURL) throws Exception {
		try {
			_logger.SetupLogger();
			_logger.Log("Setting connection has started", LogTypes.INFO);
			_sb.append(get_url()).append(apiURL);
			_url = new URL(_sb.toString());
			_conn = (HttpURLConnection) _url.openConnection();
			_conn.setRequestMethod("GET");
			_conn.setRequestProperty("Accept", "application/json"); 
			_logger.Log("Connection has been set", LogTypes.INFO);
			_logger.CloseLogger();
			return _url;
		} catch (Exception e) {
			_logger.Log("setConnectionException : Error occured while setting connection. Error Details :\n " + e.getMessage(), LogTypes.SEVERE);
			throw new Exception(e.getMessage());
		}
		finally {
			  _sb.setLength(0);
			  _conn.disconnect();
		}
	}
	
	
	
	
	
	
	public HttpURLConnection get_conn() {
		return _conn;
	}

	public void set_conn(HttpURLConnection _conn) {
		this._conn = _conn;
	}

	public URL get_url() {
		return _url;
	}
	public void set_url(URL _url) {
		this._url = _url;
	}
	
}

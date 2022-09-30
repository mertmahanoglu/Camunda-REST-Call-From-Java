package com.example.workflow;

import java.io.IOException;
import java.util.Formatter;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class CamundaLogger {

	static private FileHandler _handler;

	// TXT formatında log
	static private SimpleFormatter _simpleFormatter;
	
	//HTML Formatında log
	static private Formatter _htmlFormatter;
	
	static private Logger _logger;
	
	

	
	public enum LogTypes{
		WARN,
		INFO,
		SEVERE,
		FINEST
	}
	
	public void SetupLogger() {
		
		try {

			//Handler setup
			_handler = new FileHandler("CamundaLog.log",true);
			 _simpleFormatter = new SimpleFormatter();
			//Logger setup
			  _logger = Logger.getLogger("com.example.workflow");
			  _logger.setLevel(Level.INFO);
			  
			// Formatter setup
			  _handler.setFormatter(_simpleFormatter);
			  _logger.addHandler(_handler);	  
		} catch (SecurityException | IOException e) {
			_logger.severe("Error occured while creating log files : " + e.getMessage());

		}
		

	}
	 
	public void Log(String message, LogTypes levelType) {	
		  switch(levelType) {
		  case WARN:
			  _logger.warning(message);
			  break;
		  case INFO:
			  _logger.info(message);
			  break;
		  case SEVERE:
			  _logger.severe(message);
			  break;
		  case FINEST:
			  _logger.finest(message);
		  }
	}
	
	public void CloseLogger() {
		_handler.close();
	}
}

package com.example.RESTCall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

import com.example.workflow.CamundaLogger;
import com.example.workflow.UserController;
import com.example.workflow.CamundaLogger.LogTypes;
import com.example.workflow.UserInfo.UserProfile;

import camundajar.impl.com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.logging.LogLevel;
import org.apache.http.client.HttpClient;


public class APIController {
	
		Connections _connection;
		static URL _url;
		HttpURLConnection _conn;
		static CamundaLogger _logger;

		public  APIController() {
				_connection = new Connections();
				_logger = new CamundaLogger();
		}
	
	
	
		public void GetTasks() {
			try {
				_url = _connection.setConnection("Task/GetTasks");  
			    InputStreamReader in = new InputStreamReader(_connection.get_conn().getInputStream());
	            BufferedReader br = new BufferedReader(in);
	            String output;
	
	            
	            while ((output = br.readLine()) != null) {
	                System.out.println(output);
	            }
	          
			} catch (Exception e) {
			     System.err.println(e.getMessage());
			}
	
		}
	
	
		public void GetHistory() {
			try {
				_url = _connection.setConnection("History/GetHistory");   
			    InputStreamReader in = new InputStreamReader(_connection.get_conn().getInputStream());
	            BufferedReader br = new BufferedReader(in);
	            String output;
	            while ((output = br.readLine()) != null) {
	                System.out.println(output);
	            }
	          
			} catch (Exception e) {
			     System.err.println(e.getMessage());
			}
			
		}

		public void CreateUser() throws IOException {
			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			try {
				_logger.SetupLogger();
				_logger.Log("User creating has been started", LogTypes.INFO);
				
				_url = _connection.setConnection("User/CreateUserFromJSON");  		   
				
				
				
				UserController controller = new UserController();
				UserProfile userProfile = controller.UserProfile();
				
				Gson gson = new Gson();
				String json = gson.toJson(userProfile);

				HttpPost request = new HttpPost(_url.toString());
			    StringEntity params = new StringEntity(json);
			    request.addHeader("Content-Type", "application/json");
			    request.setEntity(params);
			    HttpResponse response = httpClient.execute(request);
			    
			    /*String uri = String.format(_url.toString());
	            HttpClient client = HttpClient.newBuilder().build();
	            HttpRequest request = HttpRequest.newBuilder()
	                    .uri(URI.create(uri))
	                    .header("Content-Type", "application/json")
	                    .POST(BodyPublishers.ofString(json))
	                    .build();

	            HttpResponse<?> response = client.send(request, BodyHandlers.discarding());*/
			    
			    System.out.println(response);
			    
			    _logger.Log("HTTP Post Request sent", LogTypes.INFO);
				_logger.CloseLogger();
			    
			} catch (Exception e) {
				 _logger.Log("An error occured while calling api with POST request. Details : \n " + e.getMessage(), LogTypes.SEVERE);
			}
			
		}
	
	}

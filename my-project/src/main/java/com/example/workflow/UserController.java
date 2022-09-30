package com.example.workflow;


import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.history.HistoricProcessInstance;
import org.camunda.bpm.engine.impl.pvm.runtime.ExecutionImpl;
import org.camunda.bpm.engine.runtime.ProcessInstance;

import com.example.RESTCall.APIController;
import com.example.workflow.CamundaLogger.LogTypes;
import com.example.workflow.UserInfo.CredentialInformation;
import com.example.workflow.UserInfo.ProfileInformation;

import com.example.workflow.UserInfo.UserProfile;

public class UserController implements JavaDelegate {
	
	static 	CamundaLogger _logger = new CamundaLogger();
	static ProcessEngine _processEngine;
	static RuntimeService _runtimeService;
	static IdentityService _identityService;
	static ExecutionImpl _execution; 
	
	
    static String name;
    static String id;
    static String lastname;
    static String email;
    static String password;
    static String isValidated;

	public UserController() {
		_processEngine = ProcessEngines.getDefaultProcessEngine();
		_runtimeService = _processEngine.getRuntimeService();
		_identityService= _processEngine.getIdentityService();
		_execution = new ExecutionImpl();
		
	}
	
   public void execute(DelegateExecution execution) throws Exception {
	   
	   try {
		   _logger.SetupLogger();
		   _logger.Log("Requesting form values from Camunda Service ...", LogTypes.INFO);
		     name = (String) execution.getVariable("name");
		     id = (String) execution.getVariable("user_id");
		     lastname = (String) execution.getVariable("lastname");
		     email = (String) execution.getVariable("email");
		     password = (String) execution.getVariable("password");
		     
		     //execution.setVariable("isValidated", false);
		     
		     
		     _logger.Log("Form values received from Camunda Service", LogTypes.INFO);
		     _logger.Log("Controller is starting to create user", LogTypes.INFO);
		     APIController controller = new APIController();
		     _logger.Log("Controller is started to create user", LogTypes.INFO);
		     controller.CreateUser();
		     _logger.Log("User created with id " + id, LogTypes.INFO);
		 	_logger.CloseLogger();
			} catch (Exception e) {
				_logger.Log("CreateUserException: Error occured while creating user with id " + id, LogTypes.SEVERE);
			}
	
   }
	

	
	public ProfileInformation ProfileInfo() {
		ProfileInformation profileInfo = new ProfileInformation();
		try {
			_logger.SetupLogger();

			 _logger.Log("Setting user informations with id " + id, LogTypes.INFO);
			profileInfo.setId(id);
			profileInfo.setEmail(email);
			profileInfo.setLastName(lastname);
			profileInfo.setFirstName(name);
			 _logger.Log("User informations has been set", LogTypes.INFO);
				_logger.CloseLogger();
			
		} catch (Exception e) {
			_logger.Log("ProfileInfoException: Error occured while setting user info with " + id, LogTypes.SEVERE);
		}
		
		return profileInfo;
		
	}
	
	public CredentialInformation CredentialInfo() {
		
		CredentialInformation credentialInfo = new CredentialInformation();
		try {
			_logger.SetupLogger();

			 _logger.Log("Setting user credentials with id " + id, LogTypes.INFO);
				credentialInfo.setPassword(password);
				credentialInfo.setAuthenticatedUserPassword(password);
			 _logger.Log("User credentials has been set", LogTypes.INFO);
				_logger.CloseLogger();
			
		} catch (Exception e) {
			_logger.Log("ProfileInfoException: Error occured while setting user credentials with " + id, LogTypes.SEVERE);
		}

		return credentialInfo;
	}
	
	public UserProfile UserProfile() 
	{
		UserProfile userProfile = new UserProfile();
		userProfile.setProfile(ProfileInfo());
		userProfile.setCredentials(CredentialInfo());
		
		return userProfile;
	}
	
	
}

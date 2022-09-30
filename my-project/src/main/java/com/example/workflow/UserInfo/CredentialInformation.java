package com.example.workflow.UserInfo;

public class CredentialInformation {
	  public String password;
      public String authenticatedUserPassword;
      
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAuthenticatedUserPassword() {
		return authenticatedUserPassword;
	}
	public void setAuthenticatedUserPassword(String authenticatedUserPassword) {
		this.authenticatedUserPassword = authenticatedUserPassword;
	}
}
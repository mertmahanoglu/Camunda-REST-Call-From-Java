package com.example.workflow.UserInfo;

public class UserProfile {
	 public ProfileInformation profile;
     public CredentialInformation credentials;
     
	public ProfileInformation getProfile() {
		return profile;
	}
	public void setProfile(ProfileInformation profile) {
		this.profile = profile;
	}
	public CredentialInformation getCredentials() {
		return credentials;
	}
	public void setCredentials(CredentialInformation credentials) {
		this.credentials = credentials;
	}
     
     
}

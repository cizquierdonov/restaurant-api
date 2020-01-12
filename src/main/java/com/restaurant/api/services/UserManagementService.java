package com.restaurant.api.services;

public interface UserManagementService {
	
	public boolean validateUser(String username, String password) throws Exception;
	
}

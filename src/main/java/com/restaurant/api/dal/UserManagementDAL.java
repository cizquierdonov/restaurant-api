package com.restaurant.api.dal;

import com.restaurant.api.domain.entities.login.User;

public interface UserManagementDAL {

	public User findByUsername(String username) throws Exception;

}

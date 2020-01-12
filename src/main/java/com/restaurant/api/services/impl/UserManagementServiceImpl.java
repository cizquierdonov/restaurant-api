package com.restaurant.api.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.api.dal.UserManagementDAL;
import com.restaurant.api.domain.constant.Constant;
import com.restaurant.api.domain.entities.login.User;
import com.restaurant.api.services.UserManagementService;
import com.restaurant.api.utils.StringFormatUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author NB-CIZQUIERDO
 *
 */
@Slf4j
@Service
public class UserManagementServiceImpl implements UserManagementService {	
	
	@Autowired
	private UserManagementDAL userDAL;

	@Override
	public boolean validateUser(String username, String password) throws Exception {
		
		boolean validUser = false;
		User user = userDAL.findByUsername(username);
		
		String hashStr = StringFormatUtils.getStringHash(password, Constant.HASH_ALGORITHM);
		
		if (hashStr.equals(user.getPassword())) {
			log.info("[" + username + "] is a valid user for Restaurant API");
			validUser = true;
		} else {
			log.info("[" + username + "] is not a valid user for Restaurant API");
		}
		
		return validUser;
	}	

}

package com.restaurant.api.dal.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.restaurant.api.dal.UserManagementDAL;
import com.restaurant.api.domain.constant.Constant;
import com.restaurant.api.domain.entities.login.User;
import com.restaurant.api.utils.CSVFileReaderWriter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class UserManagementDALFileImpl implements UserManagementDAL {
	
	@Override
	public User findByUsername(String username) throws IOException {
		
		User user = null;
		CSVFileReaderWriter instance = CSVFileReaderWriter.getInstance();
		List<String> lines = instance.getAllFileLines(Constant.USERS_FILENAME);
		log.info("'" + Constant.USERS_FILENAME + "' file readed and returned to find authenticated user");
		
		for (String line : lines) {
			
			String[] params = line.split(Constant.CSV_FILE_SEPARATOR);
			
			if ( (params.length == 3) && (params[1].equals(username)) ) {
				user = new User();
				user.setName(params[0]);
				user.setUsername(params[1]);
				user.setPassword(params[2]);
				break;
			}
			
		}	
				
		return user;
	}

}

package com.restaurant.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.api.domain.constant.Constant;
import com.restaurant.api.domain.entities.ResponseStatus;
import com.restaurant.api.domain.entities.login.LoginRequest;
import com.restaurant.api.domain.entities.login.LoginResponse;
import com.restaurant.api.domain.entities.login.User;
import com.restaurant.api.services.UserManagementService;
import com.restaurant.api.utils.JWTUtils;
import com.restaurant.api.utils.StringFormatUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/restaurant/api/v1/authentication/")
public class AuthenticationController {
	
	@Autowired
	private UserManagementService UserManagementService;
      
	@PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE }, value="/loginUser")
	public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest loginRequest) {
    	
		User user = loginRequest.getUser();		
		LoginResponse loginResponse = new LoginResponse();
		loginResponse.setUser(user);
		HttpStatus httpStatus = null;		
		ResponseStatus status = new ResponseStatus();
		status.setTimestamp(StringFormatUtils.getTodayISODateFormat());
		
		try {
			if (UserManagementService.validateUser(user.getUsername(), user.getPassword())) {
				
		    	String token = JWTUtils.getToken(user.getUsername());
		        user.setToken(token);
		        httpStatus = HttpStatus.ACCEPTED;
		        status.setMessage(Constant.LOGIN_SUCCESSFUL_MSG);
		        log.info("[Username: " + user.getUsername() + "] - " + Constant.LOGIN_SUCCESSFUL_MSG);
		        
			} else {
				
				user.setToken(Constant.EMPTY);
				httpStatus = HttpStatus.UNAUTHORIZED;
				status.setMessage(Constant.LOGIN_ERROR_MSG);
				log.info("[Username: " + user.getUsername() + "] - " + Constant.LOGIN_ERROR_MSG);
				
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			status.setMessage(e.getMessage());
		}
		
		user.setPassword(Constant.EMPTY);
		status.setCode(httpStatus.value());
		status.setType(httpStatus.name());
		loginResponse.setStatus(status);		
        
    	return new ResponseEntity<>(loginResponse, httpStatus);
        
    }
}

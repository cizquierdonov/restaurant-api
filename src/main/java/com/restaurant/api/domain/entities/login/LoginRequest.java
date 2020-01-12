package com.restaurant.api.domain.entities.login;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginRequest implements Serializable {

	private static final long serialVersionUID = 1255098413787521502L;
	
	private User user;

}

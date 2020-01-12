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
public class User implements Serializable {

	private static final long serialVersionUID = 1021820436765846233L;
	
	private String name;
	private String username;
	private String password;
	private String token;

}

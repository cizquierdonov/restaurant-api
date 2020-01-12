package com.restaurant.api.domain.entities.login;

import java.io.Serializable;

import com.restaurant.api.domain.entities.ResponseStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author NB-CIZQUIERDO
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6491689236310288433L;
	private User user;
	private ResponseStatus status;
}

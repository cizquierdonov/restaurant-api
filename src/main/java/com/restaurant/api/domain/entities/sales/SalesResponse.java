package com.restaurant.api.domain.entities.sales;

import java.io.Serializable;
import java.util.List;

import com.restaurant.api.domain.entities.ResponseStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SalesResponse implements Serializable {

	private static final long serialVersionUID = -8948880281449065896L;
	
	private List<Sale> sales;
	private ResponseStatus status;

}

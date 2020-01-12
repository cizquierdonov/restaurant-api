package com.restaurant.api.domain.entities.sales;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SalesSearchRequest implements Serializable {
	
	private static final long serialVersionUID = -5539319499775765866L;
	
	private String date;
	
}

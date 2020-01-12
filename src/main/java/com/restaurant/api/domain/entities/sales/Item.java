package com.restaurant.api.domain.entities.sales;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Item implements Serializable {

	private static final long serialVersionUID = -3395415251890793197L;
	
	private String code;
	private String brand;
	private String name;
	private String description;
	private float price;
	private int quantity;	

}

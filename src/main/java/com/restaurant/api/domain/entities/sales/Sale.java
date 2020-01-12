package com.restaurant.api.domain.entities.sales;

import java.io.Serializable;
import java.util.List;

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
public class Sale implements Serializable {

	private static final long serialVersionUID = 3315645639259843926L;

	private int id;
	private String date;
	private float totalAmount;
	private String currency;
	private String clientId;
	private List<Payment> payments;
	private List<Item> items;

}

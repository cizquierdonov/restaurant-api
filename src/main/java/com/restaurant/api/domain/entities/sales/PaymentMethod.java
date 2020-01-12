package com.restaurant.api.domain.entities.sales;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum PaymentMethod implements Serializable {
	
	CREDIT("CREDIT"), DEBIT("DEBIT"), CASH("CASH"), PAYCHECK("PAYCHECK");

	private String type;
	
	
}

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
public class Payment implements Serializable {

	private static final long serialVersionUID = 974304047776944397L;
	
	private PaymentMethod paymentMethod;
	private float amount;

}

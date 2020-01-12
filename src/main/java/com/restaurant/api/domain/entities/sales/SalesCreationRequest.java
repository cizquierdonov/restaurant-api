package com.restaurant.api.domain.entities.sales;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SalesCreationRequest implements Serializable {

	private static final long serialVersionUID = 7398748926446971997L;
	
	private List<Sale> sales;

}

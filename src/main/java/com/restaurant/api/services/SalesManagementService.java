package com.restaurant.api.services;

import java.util.Date;
import java.util.List;

import com.restaurant.api.domain.entities.sales.Sale;

public interface SalesManagementService {
	
	public List<Sale> getSalesByDate(Date date) throws Exception;
	
	public void createSale(Sale sale) throws Exception;
	
}

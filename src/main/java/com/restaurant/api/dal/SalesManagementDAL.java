package com.restaurant.api.dal;

import java.util.Date;
import java.util.List;

import com.restaurant.api.domain.entities.sales.Sale;

public interface SalesManagementDAL {
	
	public List<Sale> findByDate(Date date) throws Exception;
	
	public void insert(Sale sale) throws Exception;
	
}

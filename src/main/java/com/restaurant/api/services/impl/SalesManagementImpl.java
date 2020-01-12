package com.restaurant.api.services.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.api.dal.SalesManagementDAL;
import com.restaurant.api.domain.entities.sales.Sale;
import com.restaurant.api.jms.Receiver;
import com.restaurant.api.jms.Sender;
import com.restaurant.api.services.SalesManagementService;

@Service
public class SalesManagementImpl implements SalesManagementService {
	
	@Autowired
	private SalesManagementDAL salesDAL;
	
	@Autowired
	private Sender sender;

	@Autowired
	private Receiver receiver;

	@Override
	public List<Sale> getSalesByDate(Date date) throws Exception {
		
		sender.send(new SimpleDateFormat("dd-MM-yyyy").format(date));
		List<Sale> sales = salesDAL.findByDate(date);
		
		if (sales == null) {
			sales = new ArrayList<>();
		}
		
		return sales;
	}

	@Override
	public void createSale(Sale sale) throws Exception {		
		salesDAL.insert(sale);
	}


}


package com.restaurant.api.jms;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;

import com.restaurant.api.domain.constant.Constant;
import com.restaurant.api.domain.entities.sales.Item;
import com.restaurant.api.domain.entities.sales.Payment;
import com.restaurant.api.domain.entities.sales.Sale;
import com.restaurant.api.services.SalesManagementService;
import com.restaurant.api.utils.CSVFileReaderWriter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Receiver {
	
	@Autowired
	private SalesManagementService salesService;
	
	private CSVFileReaderWriter instance;

	@JmsListener(destination = "${jms.queue}")
	public void receive(TextMessage message) {
		
		try {
			String messageText = message.getText();
			//log.info("##### Received message={}", messageText);
			message.acknowledge();
			
			if (message != null) {
				
				List<Sale> sales = salesService.getSalesByDate(new SimpleDateFormat("dd-MM-yyyy").parse(messageText));
				List<String> lines = new ArrayList<>();
				for (Sale sale : sales) {
					lines.add(sale.toString());
					for (Item item : sale.getItems()) {
						lines.add(item.toString());
					}
					for (Payment payment : sale.getPayments()) {
						lines.add(payment.toString());
					}
				}
				
				String filename = String.format(Constant.SALES_JMS_QUERY_FILENAME, messageText);
				instance = CSVFileReaderWriter.getInstance();
				instance.insertLinesIfFileNotExists(lines, filename);					
				
			}
		
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

}

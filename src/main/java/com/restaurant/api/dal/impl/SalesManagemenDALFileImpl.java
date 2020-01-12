package com.restaurant.api.dal.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.restaurant.api.dal.SalesManagementDAL;
import com.restaurant.api.domain.constant.Constant;
import com.restaurant.api.domain.entities.sales.Item;
import com.restaurant.api.domain.entities.sales.Payment;
import com.restaurant.api.domain.entities.sales.PaymentMethod;
import com.restaurant.api.domain.entities.sales.Sale;
import com.restaurant.api.utils.CSVFileReaderWriter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class SalesManagemenDALFileImpl implements SalesManagementDAL {
	
	private String salesFilepath = "";	
	private String salesFullFilenamePath;	
	
	
	public SalesManagemenDALFileImpl() {
		salesFullFilenamePath = String.format("%s%s", salesFilepath, Constant.SALES_FILENAME);
	}

	@Override
	public List<Sale> findByDate(Date date) throws IOException {
		List<Sale> sales = new ArrayList<>();
		CSVFileReaderWriter instance = CSVFileReaderWriter.getInstance();
		List<String> lines = instance.getAllFileLines(salesFullFilenamePath);
		
		Iterator<String> it = lines.iterator();
		
		while (it.hasNext()) {
			String line = it.next();
			
			String[] saleInfo = line.split(Constant.CSV_FILE_SEPARATOR);
			
			if (saleInfo.length == 7) {
				Sale sale = new Sale();
				sale.setId(Integer.parseInt(saleInfo[0]));
				sale.setDate(saleInfo[1]);
				sale.setTotalAmount(Float.parseFloat(saleInfo[2]));
				sale.setCurrency(saleInfo[3]);
				sale.setClientId(saleInfo[4]);
				
				List<Item> items = new ArrayList<>();
				
				for (int i = 0; i < Integer.parseInt(saleInfo[5]); i++) {
					line = it.next();
					String[] itemInfo = line.split(Constant.CSV_FILE_SEPARATOR);
					
					if (itemInfo.length == 6) {
						Item item = new Item();
						item.setCode(itemInfo[0]);
						item.setBrand(itemInfo[1]);
						item.setName(itemInfo[2]);
						item.setDescription(itemInfo[3]);
						item.setPrice(Float.parseFloat(itemInfo[4]));
						item.setQuantity(Integer.parseInt(itemInfo[5]));
						items.add(item);
					}
				}
				
				sale.setItems(items);
				
				List<Payment> payments = new ArrayList<>();
				
				for (int i = 0; i < Integer.parseInt(saleInfo[6]); i++) {
					line = it.next();
					String[] paymentInfo = line.split(Constant.CSV_FILE_SEPARATOR);
					
					if (paymentInfo.length == 2) {
						Payment payment = new Payment();
						payment.setPaymentMethod(PaymentMethod.valueOf(paymentInfo[0]));
						payment.setAmount(Float.parseFloat(paymentInfo[1]));
						payments.add(payment);
					}
				}
				
				sale.setPayments(payments);
				
				if (sale.getDate().equals(new SimpleDateFormat("dd-MM-yyyy").format(date))) {
					sales.add(sale);
				}
			}			
			
		}
		
		return sales;
	}

	@Override
	public void insert(Sale sale) throws IOException {
		List<String> lines = new ArrayList<>();
		CSVFileReaderWriter instance = CSVFileReaderWriter.getInstance();
		String sp = Constant.CSV_FILE_SEPARATOR;
		
		String saleStr = String.format("%s%s%s%s%s%s%s%s%s%s%s%s%s", sale.getId(), sp, sale.getDate(), sp, sale.getTotalAmount(), sp,
				sale.getCurrency(), sp, sale.getClientId(), sp, sale.getItems().size(), sp, sale.getPayments().size());
		lines.add(saleStr);
		
		for (Item item : sale.getItems()) {
			String itemStr = String.format("%s%s%s%s%s%s%s%s%s%s%s", item.getCode(), sp, item.getBrand(), sp, item.getName(), sp,
					item.getDescription(), sp, item.getPrice(), sp, item.getQuantity());
			lines.add(itemStr);
		}
		
		for (Payment payment : sale.getPayments()) {
			String paymentStr = String.format("%s%s%s", payment.getPaymentMethod().getType(), sp, payment.getAmount());
			lines.add(paymentStr);
		}
		
		instance.insertLines(lines, salesFullFilenamePath);
	}

}

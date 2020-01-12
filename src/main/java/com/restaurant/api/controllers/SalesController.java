package com.restaurant.api.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.api.domain.constant.Constant;
import com.restaurant.api.domain.entities.ResponseStatus;
import com.restaurant.api.domain.entities.sales.Sale;
import com.restaurant.api.domain.entities.sales.SalesCreationRequest;
import com.restaurant.api.domain.entities.sales.SalesResponse;
import com.restaurant.api.domain.entities.sales.SalesSearchRequest;
import com.restaurant.api.services.SalesManagementService;
import com.restaurant.api.utils.JWTUtils;
import com.restaurant.api.utils.StringFormatUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/restaurant/api/v1/sales/")
public class SalesController {
	
	@Autowired
	private SalesManagementService salesService;
	
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE }, value = "/{date}")
    public ResponseEntity<SalesResponse> getSalesByDate(HttpServletRequest httpServletRequest,
    		@PathVariable("date") SalesSearchRequest salesSearchRequest) {
		
		HttpStatus httpStatus = null;
		ResponseStatus status = new ResponseStatus();
		status.setTimestamp(StringFormatUtils.getTodayISODateFormat());
		
		JWTUtils.validateToken(httpServletRequest);
		
		List<Sale> sales = new ArrayList<>();
		
		try {
			Date salesDate = new SimpleDateFormat(Constant.DD_MM_YYYY_DATE_MASK).parse(salesSearchRequest.getDate());
			httpStatus = HttpStatus.OK;
			
			sales = salesService.getSalesByDate(salesDate);
			httpStatus = HttpStatus.OK;
			
			if (sales.size() > 0) {
				status.setMessage("The search got " + sales.size() + " results");
			} else {
				status.setMessage("No results found for specified date");
			}
			
		} catch (ParseException e) {
			log.error(Constant.INVALID_DATE_SALES_ERROR_MSG, e);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			status.setMessage(Constant.INVALID_DATE_SALES_ERROR_MSG);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			status.setMessage(e.getMessage());
		}
		
		SalesResponse salesResponse = new SalesResponse();
		salesResponse.setSales(sales);
		
		status.setCode(httpStatus.value());
		status.setType(httpStatus.name());
		
		salesResponse.setStatus(status);
       
       return new ResponseEntity<>(salesResponse, httpStatus);
    }
	
	@PutMapping(produces = { MediaType.APPLICATION_JSON_VALUE }, value = "/create")
	public ResponseEntity<SalesResponse> createSale(HttpServletRequest httpServletRequest,
			@RequestBody SalesCreationRequest salesCreationRequest) {
		
		HttpStatus httpStatus = null;
		ResponseStatus status = new ResponseStatus();
		status.setTimestamp(StringFormatUtils.getTodayISODateFormat());
		
		JWTUtils.validateToken(httpServletRequest);
		
		List<Sale> sales = new ArrayList<>();
		
		try {
			salesService.createSale(salesCreationRequest.getSales().get(0));
			httpStatus = HttpStatus.OK;
			status.setMessage("User created successfully!");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			status.setMessage(e.getMessage());
		}		
		
		SalesResponse salesResponse = new SalesResponse();
		salesResponse.setSales(sales);
		
		status.setCode(httpStatus.value());
		status.setType(httpStatus.name());
		salesResponse.setStatus(status);
		
		return new ResponseEntity<>(salesResponse, httpStatus);
	}

}

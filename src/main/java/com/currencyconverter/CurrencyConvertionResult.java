package com.currencyconverter;

import java.math.BigDecimal;

public class CurrencyConvertionResult {
	private String from;

	private String to;

	private BigDecimal result;
	
	private String error;
	
	public CurrencyConvertionResult(String from, String to, BigDecimal result) {
		this.from = from;
		this.to = to;
		this.result = result;		
	}
	
	public CurrencyConvertionResult(String error) {
		if (error == null || error.isEmpty()) {
			throw new IllegalArgumentException("Error cannot be null or empty string.");
		}
		
		this.error = error;
	}

	public Boolean isValid() {
		return error == null || error.isEmpty();
	}	

	public String getFrom() {
		return from;
	}
	
	public String getTo() {
		return to;
	}
	
	public BigDecimal getResult() {
		return result;
	}
	
	public String getError() {
		return error;
	}
}

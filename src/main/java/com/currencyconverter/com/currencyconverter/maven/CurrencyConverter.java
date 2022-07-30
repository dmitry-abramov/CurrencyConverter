package com.currencyconverter.com.currencyconverter.maven;

import java.math.BigDecimal;
import java.util.HashMap;

public class CurrencyConverter {
	private HashMap<String, BigDecimal> rates = new HashMap<String, BigDecimal>(){{
	    put("USDEUR", new BigDecimal("0.98"));
	    put("EURUSD", new BigDecimal("1.02"));
	}};
	
	/**
     * Converts value from <code>fromCurrency<code> currency to <code>toCurrency<code>.
     *
     * @param value value to convert
     * @param fromCurrency original currency
     * @param toCurrency currency to convert
	 * @throws CurrencyConverterException 
     */
	public BigDecimal convert(BigDecimal value, String fromCurrency, String toCurrency) 
			throws CurrencyConverterException {
		var rateKey = (fromCurrency + toCurrency).toUpperCase();
		
		if (!rates.containsKey(rateKey)) {
			throw new CurrencyConverterException("There is no rate for pair " + fromCurrency + " - " + toCurrency);
		}
		
		var rate = rates.get(rateKey);		
		return value.multiply(rate);
	}
}

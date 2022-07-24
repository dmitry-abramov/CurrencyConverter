package com.currencyconverter.com.currencyconverter.maven;

import java.util.HashMap;

public class CurrencyConverter {
	private HashMap<String, Double> rates = new HashMap<String, Double>(){{
	    put("USDEUR", 0.98);
	    put("EURUSD", 1.02);
	}};
	
	/**
     * Converts value from <code>fromCurrency<code> currency to <code>toCurrency<code>.
     *
     * @param value value to convert
     * @param fromCurrency original currency
     * @param toCurrency currency to convert
	 * @throws CurrencyConverterException 
     */
	public double convert(double value, String fromCurrency, String toCurrency) 
			throws CurrencyConverterException {
		var rateKey = (fromCurrency + toCurrency).toUpperCase();
		
		if (!rates.containsKey(rateKey)) {
			throw new CurrencyConverterException("There is no rate for pair " + fromCurrency + " - " + toCurrency);
		}
		
		var rate = rates.get(rateKey);		
		return value * rate;
	}
}

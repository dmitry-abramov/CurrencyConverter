package com.currencyconverter;

import java.math.BigDecimal;
import java.util.HashMap;

public class CurrencyConverter {
	private RateProvider rateProvider;
	
	public CurrencyConverter(RateProvider rateProvider) {
		if (rateProvider == null) {
			throw new IllegalArgumentException("rateProvider parameter shouldn't be null");
		}
		
		this.rateProvider = rateProvider;
	}
	
	/**
     * Converts value from <code>fromCurrency<code> currency to <code>toCurrency<code>.
     *
     * @param value value to convert
     * @param fromCurrency original currency
     * @param toCurrency currency to convert
	 * @throws CurrencyConvertionException 
     */
	public BigDecimal convert(BigDecimal value, String fromCurrency, String toCurrency) 
			throws CurrencyConvertionException {
		var rate = this.rateProvider.getRate(fromCurrency, toCurrency);		
		return value.multiply(rate);
	}
}

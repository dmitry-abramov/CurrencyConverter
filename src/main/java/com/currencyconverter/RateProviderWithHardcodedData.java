package com.currencyconverter;

import java.math.BigDecimal;
import java.util.HashMap;

public class RateProviderWithHardcodedData implements RateProvider {
	private HashMap<String, BigDecimal> rates = new HashMap<String, BigDecimal>(){{
	    put("USDEUR", new BigDecimal("0.98"));
	    put("EURUSD", new BigDecimal("1.02"));
	}};

	public BigDecimal getRate(String fromCurrency, String toCurrency) throws CurrencyConvertionException {
        var rateKey = (fromCurrency + toCurrency).toUpperCase();
		
		if (!rates.containsKey(rateKey)) {
			throw new CurrencyConvertionException("There is no rate for pair " + fromCurrency + " - " + toCurrency);
		}
		
		return rates.get(rateKey);
	}
}

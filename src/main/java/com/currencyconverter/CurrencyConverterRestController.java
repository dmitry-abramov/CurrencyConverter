package com.currencyconverter;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyConverterRestController {
	
	@GetMapping("/convert")
	public CurrencyConvertionResult convert(
			@RequestParam(value = "from", defaultValue = "USD") String fromCurrency,
			@RequestParam(value = "to", defaultValue = "EUR") String toCurrency,
			@RequestParam(value = "value", defaultValue = "1.00") BigDecimal value) {		
		try {
			RateProvider rateProvider = new CsvFileRateProvider();
			CurrencyConverter converter = new CurrencyConverter(rateProvider);
			
			BigDecimal convertedValue = converter.convert(value, fromCurrency, toCurrency);
			
			CurrencyConvertionResult result = 
					new CurrencyConvertionResult(fromCurrency, toCurrency, convertedValue);
			
			return result;
		} catch (CurrencyConvertionRuntimeException e) {
			CurrencyConvertionResult result = new CurrencyConvertionResult(e.getMessage());
			
			return result;
		}
	}
}

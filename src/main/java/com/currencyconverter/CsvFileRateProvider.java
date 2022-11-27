package com.currencyconverter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Scanner;

public class CsvFileRateProvider implements RateProvider {

	public BigDecimal getRate(String fromCurrency, String toCurrency) throws CurrencyConvertionRuntimeException {
		try {
			InputStream file = CsvFileRateProvider.class.getResourceAsStream("/rates.txt");
			if (file == null) {
				throw new CurrencyConvertionRuntimeException("File with rates is not found.");
			}
			
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				// from, to, rate
				// USD,EUR,0.90
				String row = scanner.nextLine();
				String[] parts = row.split(",");
				String from = parts[0];
				String to = parts[1];
				String rate = parts[2];
								
				if (from.equalsIgnoreCase(fromCurrency) && to.equalsIgnoreCase(toCurrency)) {
					scanner.close();
					return new BigDecimal(rate);
				}
			}
			
			scanner.close();
			throw new CurrencyConvertionRuntimeException("There is no rate for pair " + fromCurrency + " - " + toCurrency);		
		} catch (NumberFormatException e) {
			throw new CurrencyConvertionRuntimeException("Error during read file with rates: " + e.getMessage(), e);
		}
	}
}

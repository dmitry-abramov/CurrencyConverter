package com.currencyconverter.com.currencyconverter.maven;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Scanner;

public class CsvFileRateProvider implements RateProvider {

	public BigDecimal getRate(String fromCurrency, String toCurrency) throws CurrencyConvertionException {
		try {
			File file = new File("C:\\Users\\Dmitry\\CurrencyConverter-workspace\\com.currencyconverter.maven\\src\\main\\java\\com\\currencyconverter\\com\\currencyconverter\\maven\\rates.txt");
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
			throw new CurrencyConvertionException("There is no rate for pair " + fromCurrency + " - " + toCurrency);
		} catch (FileNotFoundException e) {
			throw new CurrencyConvertionException("File with rates is not found.", e);
		} catch (NumberFormatException e) {
			throw new CurrencyConvertionException("Error during read file with rates: " + e.getMessage(), e);
		}
	}
}

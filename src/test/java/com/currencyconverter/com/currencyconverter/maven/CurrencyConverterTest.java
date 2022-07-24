package com.currencyconverter.com.currencyconverter.maven;

import static org.junit.Assert.*;
import org.junit.*;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CurrencyConverterTest {

	@Test
	public void convert_zeroUsdToEur_returnsZero() throws CurrencyConverterException {
		CurrencyConverter converter = new CurrencyConverter();

		var actualResult = converter.convert(0, "USD", "EUR");

		assertEquals(0, actualResult, 0.001);
	}

	@ParameterizedTest
	@CsvSource({ "0, 0", "1, 0.98", "2, 1.96" })
	public void convert_usdToEur_returnsConvertedValue(double value, double expectedResullt)
			throws CurrencyConverterException {
		CurrencyConverter converter = new CurrencyConverter();

		var actualResult = converter.convert(value, "USD", "EUR");

		assertEquals(expectedResullt, actualResult, 0.001);
	}

	@ParameterizedTest
	@CsvSource({ "0, 0", "1, 1.02", "2, 2.04" })
	public void convert_eurToUsd_returnsConvertedValue(double value, double expectedResullt)
			throws CurrencyConverterException {
		CurrencyConverter converter = new CurrencyConverter();

		var actualResult = converter.convert(value, "EUR", "USD");

		assertEquals(expectedResullt, actualResult, 0.001);
	}

	@ParameterizedTest
	@CsvSource({ "USD,RUB" })
	public void convert_notExitingPair_throwsException(String fromCurrency, String toCurrency)
			throws CurrencyConverterException {
		CurrencyConverter converter = new CurrencyConverter();

		var actualException = Assertions.assertThrows(CurrencyConverterException.class, () -> {
			converter.convert(0, fromCurrency, toCurrency);
		});

		assertEquals("There is no rate for pair " + fromCurrency + " - " + toCurrency,
				actualException.getMessage());
	}

	@ParameterizedTest
	@CsvSource({ "1,usd,eur,0.98","1,EuR,uSd,1.02" })
	public void convert_currencyInDifferentCases_converteSuccessfully(double value, String fromCurrency,
			String toCurrency, double expectedValue) throws CurrencyConverterException {
		CurrencyConverter converter = new CurrencyConverter();

		var actualValue = converter.convert(value, fromCurrency, toCurrency);

		assertEquals(expectedValue, actualValue, 0.001);
	}
}

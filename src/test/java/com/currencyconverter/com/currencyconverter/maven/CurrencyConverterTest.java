package com.currencyconverter.com.currencyconverter.maven;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.*;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CurrencyConverterTest {

	@Test
	public void convert_zeroUsdToEur_returnsZero() throws CurrencyConverterException {
		CurrencyConverter converter = new CurrencyConverter();

		var actualResult = converter.convert(BigDecimal.ZERO, "USD", "EUR");

		assertBigDecimalEquals(BigDecimal.ZERO, actualResult);
	}

	@ParameterizedTest
	@CsvSource({ "0, 0", "1, 0.98", "2, 1.96" })
	public void convert_usdToEur_returnsConvertedValue(BigDecimal value, BigDecimal expectedResullt)
			throws CurrencyConverterException {
		CurrencyConverter converter = new CurrencyConverter();

		var actualResult = converter.convert(value, "USD", "EUR");

		assertBigDecimalEquals(expectedResullt, actualResult);
	}

	@ParameterizedTest
	@CsvSource({ "0, 0", "1, 1.02", "2, 2.04" })
	public void convert_eurToUsd_returnsConvertedValue(BigDecimal value, BigDecimal expectedResullt)
			throws CurrencyConverterException {
		CurrencyConverter converter = new CurrencyConverter();

		var actualResult = converter.convert(value, "EUR", "USD");

		assertBigDecimalEquals(expectedResullt, actualResult);
	}	

	@ParameterizedTest
	@CsvSource({ "USD,RUB" })
	public void convert_notExitingPair_throwsException(String fromCurrency, String toCurrency)
			throws CurrencyConverterException {
		CurrencyConverter converter = new CurrencyConverter();

		var actualException = Assertions.assertThrows(CurrencyConverterException.class, () -> {
			converter.convert(BigDecimal.ZERO, fromCurrency, toCurrency);
		});

		assertEquals("There is no rate for pair " + fromCurrency + " - " + toCurrency,
				actualException.getMessage());
	}

	@ParameterizedTest
	@CsvSource({ "1,usd,eur,0.98","1,EuR,uSd,1.02" })
	public void convert_currencyInDifferentCases_converteSuccessfully(BigDecimal value, String fromCurrency,
			String toCurrency, BigDecimal expectedValue) throws CurrencyConverterException {
		CurrencyConverter converter = new CurrencyConverter();

		var actualValue = converter.convert(value, fromCurrency, toCurrency);

		assertBigDecimalEquals(expectedValue, actualValue);
	}
	
	private static void assertBigDecimalEquals(BigDecimal expectedResullt, BigDecimal actualResult) {
		var isEquals = expectedResullt.compareTo(actualResult);
		assertEquals(0, isEquals);
	}
}

package com.currencyconverter;

public class CurrencyConvertionException extends Exception {
	public CurrencyConvertionException() {
		super();
	}
	
	public CurrencyConvertionException(String message) {
		super(message);
	}
	
	public CurrencyConvertionException(String message, Throwable cause) {
		super(message, cause);
	}
}

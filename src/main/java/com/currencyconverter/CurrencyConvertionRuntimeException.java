package com.currencyconverter;

public class CurrencyConvertionRuntimeException extends RuntimeException {
	public CurrencyConvertionRuntimeException() {
		super();
	}
	
	public CurrencyConvertionRuntimeException(String message) {
		super(message);
	}
	
	public CurrencyConvertionRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}
}

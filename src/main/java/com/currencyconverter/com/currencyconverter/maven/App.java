package com.currencyconverter.com.currencyconverter.maven;

import java.math.BigDecimal;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class App {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Options options = new Options();
		var valueOption = Option.builder()
				.option("v")
				.argName("v")
				.desc("value to convert from one curreny to another")
				.hasArg()
				.required()
				.build();
		
		var fromCurrencyOption = Option.builder()
				.option("f")
				.argName("f")
				.desc("source currency to convert")
				.hasArg()
				.required()
				.build();
		
		var toCurrencyOption = Option.builder()
				.option("t")
				.argName("t")
				.desc("destination currency to convert")
				.hasArg()
				.required()
				.build();
		
		options.addOption(valueOption);
		options.addOption(fromCurrencyOption);		
		options.addOption(toCurrencyOption);
		
		CommandLineParser parser = new DefaultParser();
		try {
			CommandLine cmd = parser.parse(options, args);
			var value = new BigDecimal(cmd.getParsedOptionValue("v").toString());
			var fromCurrency = cmd.getParsedOptionValue("f").toString();
			var toCurrency = cmd.getParsedOptionValue("t").toString();				
			var currencyConverter = new CurrencyConverter();
			
			var convertedValue = currencyConverter.convert(value, fromCurrency, toCurrency);
			System.out.println(value + fromCurrency + " = " + convertedValue + toCurrency);			
		} catch (ParseException e) {
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp("currencyConverter", options);
		} catch (CurrencyConverterException e) {
			System.out.println(e.getMessage());
		}		
	}
}

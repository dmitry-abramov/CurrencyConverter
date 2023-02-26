package com.currencyconverter;

import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;

import org.jsoup.Jsoup;

public class GoogleRateProvider implements RateProvider {

	@Override
	public BigDecimal getRate(String fromCurrency, String toCurrency) throws CurrencyConvertionRuntimeException {
		try {
			var request = HttpRequest.newBuilder()
					  .uri(new URI("https://www.google.com/search?q=" + fromCurrency +"+to+" + toCurrency + "&oq=eur+to+usd"))
					  .header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/110.0.0.0 Safari/537.36")
					  .GET()
					  .build();
			
			var client = HttpClient.newHttpClient();
			var response = client.send(request, BodyHandlers.ofString());
			var responseString = response.body();
			
			var doc = Jsoup.parse(responseString);
			var element = doc.select("div[data-exchange-rate]").first();
			var rate = element.attr("data-exchange-rate");			
			
			return new BigDecimal(rate);
		} catch (Exception e) {
			throw new CurrencyConvertionRuntimeException("Can't get rate from Google. " + e.getMessage(), e);
		}	
	}

}

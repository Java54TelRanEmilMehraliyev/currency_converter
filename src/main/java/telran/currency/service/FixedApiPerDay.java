package telran.currency.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.json.JSONObject;

public class FixedApiPerDay extends AbstractCurrencyConvertor {
	   protected String uriString = "http://data.fixer.io/api/latest?access_key=49c071d0e1955e57831f9a34432b69c3";
	   private long lastUpdateTimestamp = 0;
	   //DONE additional encapsulation fields
	   
	   public FixedApiPerDay() {
		   rates = getRates();
	   }
	protected HashMap<String, Double> getRates() {
		// DONE Auto-generated method stub
		HttpClient httpClient = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder(URI.create(uriString))
				.build();
		try {
			HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
			JSONObject jsonObject = new JSONObject(response.body());
		    JSONObject jsonRates = jsonObject.getJSONObject("rates");
		    lastUpdateTimestamp = jsonObject.getLong("timestamp");
		    
		    HashMap<String, Double> ratesMap = new HashMap<>();
		    for(String key : jsonRates.keySet()) {
		    	ratesMap.put(key, jsonRates.getDouble(key));
		    }
		    return ratesMap;
		}catch (Exception e) {
	        throw new RuntimeException("Error fetching rates: " + e.getMessage(), e);
	    }
	}
	@Override
	public List<String> strongestCurrencies(int amount) {
		refresh();
		return super.strongestCurrencies(amount);
	}
	@Override
	public List<String> weakestCurrencies(int amount) {
		refresh();
		return super.weakestCurrencies(amount);
	}
	@Override
	public double convert(String codeFrom, String codeTo, double amount) {
		refresh();
		return super.convert(codeFrom, codeTo, amount);
	}
	private void refresh() {
		// DONE 
		//checks whether refresh is needed 
		//if so it calls getRates method for updating "rates" map
		//one per day
		long currentTimestamp = System.currentTimeMillis() / 1000l;
		if (currentTimestamp - lastUpdateTimestamp >= 86400) {
			rates = getRates();
		}
	}
	@Override
	public HashSet<String> getAllCodes() {
		
		return new HashSet<>(rates.keySet());
	}
	}

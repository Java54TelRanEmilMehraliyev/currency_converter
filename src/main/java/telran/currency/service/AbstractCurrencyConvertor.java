package telran.currency.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class AbstractCurrencyConvertor implements CurrencyConvertor {
	protected Map<String, Double> rates; // key - currency ISO code;
											// value - amount of code's units in 1 eur;

	@Override
	public List<String> strongestCurrencies(int amount) {

		return rates.entrySet().stream().sorted(Map.Entry.<String, Double>comparingByValue()).limit(amount)
				.map(Map.Entry::getKey).collect(Collectors.toList());
	}

	@Override
	public List<String> weakestCurrencies(int amount) {

		return rates.entrySet().stream().sorted(Map.Entry.<String, Double>comparingByValue().reversed()).limit(amount)
				.map(Map.Entry::getKey).collect(Collectors.toList());
	}

	@Override
	public double convert(String codeFrom, String codeTo, double amount) {
		double rateFrom = rates.getOrDefault(codeFrom, 1.0);
		double rateTo = rates.getOrDefault(codeTo, 1.0);
		return amount * (rateTo / rateFrom);
	}

}

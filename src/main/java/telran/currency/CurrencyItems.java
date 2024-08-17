package telran.currency;

import java.util.HashSet;
import java.util.List;

import telran.currency.service.CurrencyConvertor;
import terlan.view.Item;

public class CurrencyItems {
	private static CurrencyConvertor currencyConvertor;

	public static List<Item> getItems(CurrencyConvertor currencyConvertor) {
		
        //DONE
		return List.of(
		        Item.of("Strongest Currencies", io -> {
		            int amount = io.readInt("Enter amount of currencies", "Invalid input");
		            List<String> strongest = currencyConvertor.strongestCurrencies(amount);
		            io.writeLine("Strongest currencies: " + strongest);
		        }),
		        Item.of("Weakest Currencies", io -> {
		            int amount = io.readInt("Enter amount of currencies", "Invalid input");
		            List<String> weakest = currencyConvertor.weakestCurrencies(amount);
		            io.writeLine("Weakest currencies: " + weakest);
		        }),
		        Item.of("Convert Currency", io -> {
		        	String fromCurrency = io.readString("Enter the source currency code (e.g., USD)");
	                String toCurrency = io.readString("Enter the target currency code (e.g., EUR)");
	                double amount = io.readDouble("Enter the amount to convert", "Invalid input");

	                double result = currencyConvertor.convert(fromCurrency, toCurrency, amount);
	                io.writeLine(String.format("Converted amount: %.2f %s", result, toCurrency));
		        }),
		        Item.of("Get all available codes", io -> {
		        	HashSet<String> codes = currencyConvertor.getAllCodes();
		        	io.writeLine("Available currency codes: " + codes);
		        }),
		        Item.ofExit()
		    );
		}
}

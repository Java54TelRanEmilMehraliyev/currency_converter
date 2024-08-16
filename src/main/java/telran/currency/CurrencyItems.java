package telran.currency;

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
		        Item.ofExit()
		    );
		}
}

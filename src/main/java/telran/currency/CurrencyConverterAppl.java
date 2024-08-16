package telran.currency;
import telran.currency.service.*;
import terlan.view.Menu;
import terlan.view.SystemInputOutput;
import terlan.view.Item;
public class CurrencyConverterAppl {

	public static void main(String[] args) {
        CurrencyConvertor converter = new FixedApiPerDay();
        SystemInputOutput io = new SystemInputOutput();
        Menu menu = new Menu("Currency Converter", CurrencyItems.getItems(converter).toArray(new Item[0]));
        menu.perform(io);
    }
}

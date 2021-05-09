package erp.client.javafx.component.textfield.currency;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import erp.client.javafx.component.textfield.number.DoubleField;
import javafx.geometry.Pos;
import javafx.scene.control.TextFormatter;
import javafx.util.StringConverter;

public class CurrencyField extends DoubleField{

	private Locale locale;
	private NumberFormat currencyFormat;
	CurrencyConverter converter = new CurrencyConverter();
	
	public CurrencyField(String name, Locale locale, boolean isMandatoryField) {
		super(name, isMandatoryField);
		this.locale = locale;
		this.currencyFormat = NumberFormat.getCurrencyInstance(this.locale);
		this.converter = new CurrencyConverter();
		this.setAlignment(Pos.CENTER_RIGHT);
		this.setTextFormatter(new TextFormatter<>(converter));
		this.setOnAction(e -> {
			super.validateField();
		});
	}
	
	public Double getCashAmount() {
		Double value = converter.fromString(getText().trim());
		return value == null ? 0.0 : value;
	}
	
	public BigDecimal getCashAmountBigDecimal() {
		return new BigDecimal(getCashAmount());
	}
	
	public void setCashAmount(Double amount) {
		if(amount == null) {
			amount = 0.0;
		}
		setText(currencyFormat.format(amount));
	}
	
	class CurrencyConverter extends StringConverter<Double> {

		@Override
		public String toString(Double object) {
			return object != null ? currencyFormat.format(object) : "";
		}

		@Override
		public Double fromString(String string) {
			String symbol = currencyFormat.getCurrency().getSymbol();
			if(string != null && !string.isEmpty() && !string.equals(symbol)) {
				if(!string.contains(symbol)) {
					string = symbol + string;
				}
				try {
					Number number = currencyFormat.parse(string);
					return number.doubleValue();
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			return null;
		}
		
	}

	public Locale getLocale() {
		return locale;
	}
	
}

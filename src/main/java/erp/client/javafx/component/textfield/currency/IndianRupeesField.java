package erp.client.javafx.component.textfield.currency;

import java.util.Locale;

public class IndianRupeesField extends CurrencyField {

	public IndianRupeesField(String name, boolean isMandatoryField) {
		super(name, new Locale("en", "IN"), isMandatoryField);
	}

}

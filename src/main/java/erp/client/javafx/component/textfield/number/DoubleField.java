package erp.client.javafx.component.textfield.number;

import erp.client.javafx.component.textfield.CTextField;

public class DoubleField extends CTextField{

	public DoubleField(String name, boolean isMandatoryField) {
		super(name, isMandatoryField, -1);
	}

	@Override
	public void replaceText(int start, int end, String text) {
		if(isValidNumber(text)) {
			super.replaceText(start, end, text);
		}
	}
	
	private boolean isValidNumber(String text) {
		try {
			if(text.isEmpty() || text.equals("."))
				return true;
			Double.parseDouble(text);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}

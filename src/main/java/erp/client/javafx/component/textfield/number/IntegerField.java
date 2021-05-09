package erp.client.javafx.component.textfield.number;

import erp.client.javafx.component.textfield.CTextField;

public class IntegerField extends CTextField{

	public IntegerField(String name, boolean isMandatoryField, int characterLimit_setMinusOneForNoLimit) {
		super(name, isMandatoryField, characterLimit_setMinusOneForNoLimit);
	}
	
	@Override
	public void replaceText(int start, int end, String text) {
		if(isValidNumber(text)) {
			super.replaceText(start, end, text);
		}
	}

	private boolean isValidNumber(String text) {
		try {
			if(text.isEmpty())
				return true;
			Integer.parseInt(text);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}

package erp.client.javafx.component.textfield;

import erp.client.javafx.component.FormField;
import erp.client.javafx.utility.PopupUtility;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.EventHandler;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class CTextField extends TextField implements FormField{

	private Label label;
	private boolean isMandatoryField;
	private String name;
	private final IntegerProperty maxLength;
	
	public CTextField(String name, boolean isMandatoryField, int characterLimit_setMinusOneForNoLimit){
		this.isMandatoryField = isMandatoryField;
		this.name = name;
		label = new Label(isMandatoryField ? name + " *" : name);
		this.maxLength = new SimpleIntegerProperty(characterLimit_setMinusOneForNoLimit);
		
		this.setOnAction(e -> {
			validateField();
		});
		
		this.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode() == KeyCode.ESCAPE) {
					clear();
				}
			}
		});
	}

    @Override
	public void replaceText(int start, int end, String text) {
		if(this.maxLength.getValue() <= 0) {
			//Default behavior, in case of no max length
			super.replaceText(start, end, text);
		}else {
			//Get the text in the text field, before the user enters something
			String currentText = this.getText() == null ? "" : this.getText();
			
			//Compute the text that should normally be in the text field now
			String finalText = currentText.substring(0, start) + text + currentText.substring(end);
			
			//If the max length is not exceeded
			int numberOfExceedingCharacters = finalText.length() - this.maxLength.getValue();
			if(numberOfExceedingCharacters <= 0) {
				//Normal behavior
				super.replaceText(start, end, text);
			}else {
				//Otherwise, cut the text that was going to be inserted
				String cutInsertedText = text.substring(0, text.length() - numberOfExceedingCharacters);
				//And replace this text
				super.replaceText(start, end, cutInsertedText);
				PopupUtility.showMessage(AlertType.ERROR, "Maximum allowed size of " + name + " is " + maxLength.getValue() +".");
			}
		}
	}
	
	public Label getLabel() {
		return label;
	}

	public boolean isMandatoryField() {
		return isMandatoryField;
	}
	
	public boolean validateField() {
		if(isMandatoryField) {
			String text = getText().trim();
			if(text.isEmpty()) {
				requestFocus();
				PopupUtility.showMessage(AlertType.ERROR, "Please enter " + name);
				return false;
			}
		}
		return true;
	}

	@Override
	public void clearField() {
		clear();
	}
}

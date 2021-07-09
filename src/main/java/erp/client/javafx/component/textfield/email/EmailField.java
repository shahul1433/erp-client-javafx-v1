package erp.client.javafx.component.textfield.email;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import erp.client.javafx.component.FormField;
import erp.client.javafx.utility.PopupUtility;
import erp.client.javafx.utility.StringUtils;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class EmailField extends TextField implements FormField{

	private Label label;
	private final IntegerProperty maxLength;
	private final boolean isMandatoryField;
	
	public EmailField(boolean isMandatoryField, int characterLimit_setMinusOneForNoLimit) {
		this.getStylesheets().add(FormField.class.getResource("style.css").toExternalForm());
		this.isMandatoryField = isMandatoryField;
		this.label = new Label("Email"+(isMandatoryField ? " *": ""));
		this.maxLength = new SimpleIntegerProperty(characterLimit_setMinusOneForNoLimit);
		
		this.setOnKeyPressed(e -> {
			if(e.getCode() == KeyCode.ESCAPE) {
				clear();
				getStyleClass().removeAll("validation-error");
			}
		});
		this.getStylesheets().add(EmailField.class.getResource("style.css").toExternalForm());
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
				PopupUtility.showMessage(AlertType.ERROR, "Maximum allowed size of Email is " + maxLength.getValue() +".");
			}
		}
		if(checkField() || !StringUtils.hasText(getText().trim())) {
			getStyleClass().removeAll("validation-error");
		}else {
			getStyleClass().add("validation-error");
		}
	}
	
	public Label getLabel() {
		return label;
	}

	public boolean isMandatoryField() {
		return isMandatoryField;
	}
	
	@Override
	public boolean validateField() {
		if(isMandatoryField || StringUtils.hasText(getText().trim())) {
			if(checkField())
				return true;
			else {
				PopupUtility.showMessage(AlertType.ERROR, "Please enter valid Email");
				requestFocus();
				return false;
			}
		}
		return true;
	}

	private boolean checkField() {
		Pattern p = Pattern.compile("^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		Matcher m = p.matcher(getText());
		if(m.find() && m.group().equals(getText())) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public void clearField() {
		super.clear();
		getStyleClass().removeAll("validation-error");
	}

	@Override
	public void setReadOnly(boolean isReadOnly) {
		setEditable(!isReadOnly);
		if (isReadOnly) {
			this.getStyleClass().add("read-only");
		} else {
			this.getStyleClass().removeAll("read-only");
		}
	}
}

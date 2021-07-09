package erp.client.javafx.component.textfield.phone;

import erp.client.javafx.component.FormField;
import erp.client.javafx.utility.PopupUtility;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class PhoneField extends TextField implements FormField{

	private Label label;
	private boolean isMandatoryField;
	private final String name;
	private final IntegerProperty maxLength;
	private CountryListCombobox countryListCombobox;
	private final Country defaultCountry;
	private GridPane gridPane = new GridPane();
	
	public PhoneField(String name, boolean isMandatoryField, Country defaultCountry) {
		this.getStylesheets().add(PhoneField.class.getResource("style.css").toExternalForm());
		this.getStylesheets().add(FormField.class.getResource("style.css").toExternalForm());
		this.defaultCountry = defaultCountry;
		this.isMandatoryField = isMandatoryField;
		this.name = name;
		this.label = new Label(isMandatoryField ? name + " *" : name);
		this.maxLength = new SimpleIntegerProperty(10);
		this.countryListCombobox = new CountryListCombobox();
		this.countryListCombobox.selectCountry(defaultCountry);
		
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		
		ColumnConstraints empty = new ColumnConstraints();
		ColumnConstraints textfieldColumn = new ColumnConstraints(100, 150, Double.MAX_VALUE);
		textfieldColumn.setHgrow(Priority.ALWAYS);
		
		gridPane.getColumnConstraints().add(0, empty);
		gridPane.getColumnConstraints().add(1, textfieldColumn);
		
		GridPane.setConstraints(countryListCombobox, 0, 0);
		GridPane.setConstraints(this, 1, 0);
		
		gridPane.getChildren().addAll(countryListCombobox, this);
		
		bind();
		registerListeners();
	}
	
	private void registerListeners() {
		countryListCombobox.valueProperty().addListener(new ChangeListener<Country>() {
			@Override
			public void changed(ObservableValue<? extends Country> observable, Country oldValue, Country newValue) {
				clear();
			}
		});
	}
	
	private void bind() {
		maxLength.bind(countryListCombobox.getMaxLength());
	}
	
	@Override
	public void replaceText(int start, int end, String text) {
		if(isValidNumber(text)) {
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
					PopupUtility.showMessage(AlertType.ERROR, "Maximum allowed size of " + name + " is " + maxLength.getValue() +" character(s).");
				}
				
				if(!getText().isEmpty() && (getText().trim().length() != countryListCombobox.getSelectedCountry().getLength())) {
					getStyleClass().add("validation-error");
				}else {
					getStyleClass().removeAll("validation-error");
				}
			}
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
	
	@Override
	public boolean validateField() {
		if(isMandatoryField) {
			if(getText().trim().isEmpty()) {
				PopupUtility.showMessage(AlertType.ERROR, "Please enter "+ name);
				requestFocus();
				return false;
			}else {
				Country selectedCountry = countryListCombobox.getSelectedCountry();
				if(selectedCountry == null) {
					PopupUtility.showMessage(AlertType.ERROR, "Please select Country/Region");
					countryListCombobox.requestFocus();
					return false;
				}
				if(getText().trim().length() != selectedCountry.getLength()) {
					PopupUtility.showMessage(AlertType.ERROR, "Invalid " + name);
					requestFocus();
					return false;
				}
			}
		}
		return true;
	}

	public Label getLabel() {
		return label;
	}

	public CountryListCombobox getCountryListCombobox() {
		return countryListCombobox;
	}

	public GridPane getHBoxComboPhoneField() {
		return gridPane;
	}
	
	public String getPhoneNo() {
		StringBuilder sb = new StringBuilder();
		Country selectedCountry = countryListCombobox.getSelectedCountry();
		if(selectedCountry == null)
			return null;
		if(getText().trim().length() != selectedCountry.getLength())
			return null;
		sb.append(selectedCountry.getExtension()).append("-").append(getText().trim());
		return sb.toString();
	}
	
	public void setPhoneNo(String phoneNo) {
		if(phoneNo == null || phoneNo.isEmpty()) {
			countryListCombobox.selectCountry(null);
			setText(null);
			return;
		}
		//format : +XXX - XXXXXXXXXX, so extension and phone no is separated by '-'
		if(phoneNo.contains("-")) {
			String[] splitedPhoneNo = phoneNo.split("-");
			String extension = splitedPhoneNo[0];
			String number = splitedPhoneNo[1];
			
			for(Country c : Country.values()) {
				if(c.getExtension().equals(extension)) {
					countryListCombobox.selectCountry(c);
					setText(number);
					return;
				}
			}
			countryListCombobox.selectCountry(null);
			setText(number);
		}else {
			countryListCombobox.selectCountry(null);
			setText(phoneNo);
		}
	}
	
	public void setFieldEditable(boolean flag) {
		this.setEditable(flag);
		countryListCombobox.setEditable(flag);
	}

	@Override
	public void clearField() {
		super.clear();
		countryListCombobox.selectCountry(defaultCountry);
		getStyleClass().removeAll("validation-error");
	}

	@Override
	public void setReadOnly(boolean isReadOnly) {
		setEditable(!isReadOnly);
		countryListCombobox.setEditable(isReadOnly);
		if (isReadOnly) {
			this.getStyleClass().add("read-only");
		} else {
			this.getStyleClass().removeAll("read-only");
		}
	}
}

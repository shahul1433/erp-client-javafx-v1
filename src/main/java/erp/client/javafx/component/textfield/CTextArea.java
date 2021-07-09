package erp.client.javafx.component.textfield;

import java.math.BigDecimal;

import erp.client.javafx.component.FormField;
import erp.client.javafx.utility.PopupUtility;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.EventHandler;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;

public class CTextArea extends TextArea implements FormField{

	private Label label;
	private boolean isMandatoryField;
	private String name;
	private final IntegerProperty maxLength;
	private ProgressBar sizeBar;
	private VBox textAreaWithProgressBar;
	
	private static final String RED_BAR    = "red-bar";
	private static final String YELLOW_BAR = "yellow-bar";
	private static final String ORANGE_BAR = "orange-bar";
	private static final String GREEN_BAR  = "green-bar";
	private static final String[] barColorStyleClasses = { RED_BAR, ORANGE_BAR, YELLOW_BAR, GREEN_BAR };

	public CTextArea(String name, boolean isMandatoryField, int characterLimit_setMinusOneForNoLimit) {
		this.getStylesheets().add(FormField.class.getResource("style.css").toExternalForm());
		this.getStylesheets().add(CTextArea.class.getResource("style.css").toExternalForm());
		this.name = name;
		this.isMandatoryField = isMandatoryField;
		this.maxLength = new SimpleIntegerProperty(characterLimit_setMinusOneForNoLimit);
		this.label = new Label(isMandatoryField ? name + " *" : name);
		sizeBar = new ProgressBar(0);
		sizeBar.setPrefWidth(this.getPrefWidth());
		sizeBar.getStylesheets().add(getClass().getResource("progressBar.css").toExternalForm());
		textAreaWithProgressBar = new VBox(0);
		textAreaWithProgressBar.getChildren().addAll(this, sizeBar);

		this.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode() == KeyCode.ESCAPE) {
					clear();
					sizeBar.setProgress(0);
				}
			}
		});

		sizeBar.prefWidthProperty().bind(this.widthProperty());
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
				PopupUtility.showMessage(AlertType.WARNING, "Maximum allowed size of " + name + " is " + maxLength.getValue() +".");
			}
		}
		calculateProgress();
	}
	
	public void setString(String text) {
		setText(text);
		calculateProgress();
	}
	
	private void calculateProgress() {
		BigDecimal maxlength = new BigDecimal(maxLength.get());
		BigDecimal length = new BigDecimal(getText().length());
		BigDecimal divide = length.divide(maxlength);
		double progress = divide.doubleValue();
		sizeBar.setProgress(progress);
		
		if(progress < 0.2) {
			setBarStyleClass(GREEN_BAR);
		}else if(progress < 0.4) {
			setBarStyleClass(YELLOW_BAR);
		}else if(progress < 0.8) {
			setBarStyleClass(ORANGE_BAR);
		}else {
			setBarStyleClass(RED_BAR);
		}
	}
	
	private void setBarStyleClass(String barStyleClass) {
		sizeBar.getStyleClass().removeAll(barColorStyleClasses);
		sizeBar.getStyleClass().add(barStyleClass);
	}
	
	public VBox getTextAreaWithProgressBar() {
		return textAreaWithProgressBar;
	}

	public Label getLabel() {
		return label;
	}

	public ProgressBar getSizeBar() {
		return sizeBar;
	}

	public boolean isMandatoryField() {
		return isMandatoryField;
	}

	@Override
	public boolean validateField() {
		if(isMandatoryField) {
			String text = getText().trim();
			if(text.isEmpty()) {
				requestFocus();
				PopupUtility.showMessage(AlertType.WARNING, "Please enter " + name);
				return false;
			}
		}
		return true;
	}

	@Override
	public void clearField() {
		clear();
		sizeBar.setProgress(0);
	}

	@Override
	public void setReadOnly(boolean isReadOnly) {
		setEditable(!isReadOnly);
		if (isReadOnly) {
			this.getStyleClass().add("read-only-area");
		} else {
			this.getStyleClass().removeAll("read-only-area");
		}
	}

}

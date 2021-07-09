package erp.client.javafx.component.textfield;

import erp.client.javafx.component.FormField;
import erp.client.javafx.utility.PopupUtility;
import javafx.event.EventHandler;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class CPasswordField extends PasswordField implements FormField{

	private Label label;
	private String name;
	
	public CPasswordField(String name) {
		this.getStylesheets().add(FormField.class.getResource("style.css").toExternalForm());
		this.name = name;
		this.label = new Label(name + " *");
		
		this.setOnAction(e -> {
			validateField();
		});

		this.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode() == KeyCode.ESCAPE) {
					setText("");
				}
			}
		});
	}

	public Label getLabel() {
		return label;
	}
	
	@Override
	public boolean validateField() {
		String text = getText().trim();
		if(text.isEmpty()) {
			requestFocus();
			PopupUtility.showMessage(AlertType.ERROR, "Please enter " + name);
			return false;
		}
		return true;
	}

	@Override
	public void clearField() {
		clear();
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

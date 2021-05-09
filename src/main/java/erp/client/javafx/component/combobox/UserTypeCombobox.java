package erp.client.javafx.component.combobox;

import erp.client.javafx.component.FormField;
import erp.client.javafx.entity.UserType;
import erp.client.javafx.utility.PopupUtility;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.stage.Stage;

public class UserTypeCombobox extends ComboBox<UserType> implements FormField{

	private Label label;
	private String name;
	private boolean isMandatoryField;
	private Stage stage;
	
	public UserTypeCombobox(String name, boolean isMandatoryField, Stage parent) {
		this.stage = parent;
		this.name = name;
		this.label = new Label(isMandatoryField ? name +" *" : name);
		this.isMandatoryField = isMandatoryField;
		
		populateData();
		setCellFactory(ut -> createUserTypeCell());
		setButtonCell(createUserTypeCell());
	}
	
	private void populateData() {
		for(UserType type : UserType.values()) {
			if(type != UserType.ALL) {
				getItems().add(type);
			}
		}
	}
	
	private ListCell<UserType> createUserTypeCell() {
		return new ListCell<UserType>() {
			@Override
			protected void updateItem(UserType item, boolean empty) {
				super.updateItem(item, empty);
				if(item == null || empty)
					setGraphic(null);
				else
					setText(item.getUserType());
			}
		};
	}

	public Label getLabel() {
		return label;
	}
	
	public UserType getSelectedUserType() {
		return getSelectionModel().getSelectedItem();
	}
	
	public void setSelectedUserType(UserType type) {
		getSelectionModel().select(type);
	}

	@Override
	public boolean validateField() {
		if(isMandatoryField) {
			if(getSelectedUserType() == null) {
				PopupUtility.showErrorMessage(stage, "Please select " + name);
				this.requestFocus();
				return false;
			}
		}
		return true;
	}

	@Override
	public void clearField() {
		getSelectionModel().select(null);
	}

}

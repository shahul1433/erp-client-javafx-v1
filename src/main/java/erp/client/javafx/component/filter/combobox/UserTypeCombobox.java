package erp.client.javafx.component.filter.combobox;

import erp.client.javafx.component.filter.FilterField;
import erp.client.javafx.entity.UserType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;

public class UserTypeCombobox extends ComboBox<UserType> implements FilterField{

	private Label label;
	
	public UserTypeCombobox() {
		
		label = new Label("User Type");
		
		for(UserType ut : UserType.values()) {
			getItems().add(ut);
		}
		
		setCellFactory(ut -> createUserTypeCell());
		setButtonCell(createUserTypeCell());
		
		getSelectionModel().selectFirst();
	}
	
	private ListCell<UserType> createUserTypeCell() {
		return new ListCell<UserType>() {
			@Override
			protected void updateItem(UserType item, boolean empty) {
				super.updateItem(item, empty);
				if(item == null || empty)
					setGraphic(null);
				else {
					setText(item.getUserType());
				}
			}
		};
	}

	public Label getLabel() {
		return label;
	}

	@Override
	public boolean isValidFilterField() throws Exception {
		return (getSelectionModel().getSelectedItem() != UserType.ALL);
	}

	@Override
	public void clearSearch() {
		getSelectionModel().selectFirst();
	}

	public UserType getSelectedUserType() {
		return getSelectionModel().getSelectedItem();
	}
}

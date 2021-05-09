package erp.client.javafx.user;

import erp.client.javafx.entity.TUserRole;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

import java.util.HashSet;
import java.util.Set;

public class UserRoleListView extends ListView<UserRole>{
	
	public UserRoleListView() {
		getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		setCellFactory(param -> createUserRoleListCell());
	}
	
	private ListCell<UserRole> createUserRoleListCell() {
		return new ListCell<UserRole>() {
			@Override
			protected void updateItem(UserRole item, boolean empty) {
				super.updateItem(item, empty);
				if(item == null || empty)
					setText(null);
				else
					setText(item.getRole());
			}
		};
	}
	
	public void addUserRole(UserRole role) {
		getItems().add(role);
	}
	
	public void removeUserRole(UserRole role) {
		getItems().remove(role);
	}
	
	public HashSet<UserRole> getRoles() {
		return new HashSet<>(getItems());
	}
	
	public void setRoles(Set<TUserRole> roles) {
		getItems().clear();
		for(TUserRole role : roles) {
			getItems().add(new UserRole(role));
		}
	}
	
}

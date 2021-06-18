package erp.client.javafx.component.combobox;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;

public class PageNoCombobox extends ComboBox<Integer>{

	EventHandler<ActionEvent> filter;
	
	public PageNoCombobox() {
		filter = disableDefaultActionEvent();
		addPageNo(1);
		getSelectionModel().selectFirst();
	}
	
	public void addPageNo(Integer pageNo) {
		addEventFilter(ActionEvent.ACTION, filter);
		getItems().add(pageNo);
		removeEventFilter(ActionEvent.ACTION, filter);
	}
	
	public void setPageNoItems(ObservableList<Integer> items) {
		addEventFilter(ActionEvent.ACTION, filter);
		setItems(items);
		removeEventFilter(ActionEvent.ACTION, filter);
	}

	public Integer getPageNo() {
		return getSelectionModel().getSelectedItem() - 1;
	}

	private EventHandler<ActionEvent> disableDefaultActionEvent() {
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				event.consume();
			}
		};
	}
}

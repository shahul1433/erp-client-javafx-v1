package erp.client.javafx.component.filter.combobox;

import java.util.Comparator;

import com.fasterxml.jackson.core.type.TypeReference;

import erp.client.javafx.component.filter.FilterField;
import erp.client.javafx.config.ConfigurationManager;
import erp.client.javafx.config.Constants;
import erp.client.javafx.entity.GstStateCodeList;
import erp.client.javafx.entity.TGstStateCode;
import erp.client.javafx.exception.FormValidationException;
import erp.client.javafx.http.HttpModule;
import erp.client.javafx.http.ResponseEntity;
import erp.client.javafx.utility.PopupUtility;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;

public class GSTStateCodeCombobox extends ComboBox<TGstStateCode> implements FilterField{

	private Label label;
	
	public GSTStateCodeCombobox() {
		label = new Label("GST State Code");
		
		this.setCellFactory(cf -> createGstStateCodeCell());
		this.setButtonCell(createGstStateCodeCell());
		
		GetAllGstStateCodeTask task = new GetAllGstStateCodeTask();
		task.setOnFailed(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent workerStateEvent) {
				PopupUtility.showMessage(Alert.AlertType.ERROR, workerStateEvent.getSource().getException().getMessage());
			}
		});
		task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent workerStateEvent) {
				GstStateCodeList gstStateCodeList = task.getValue();
				gstStateCodeList.getGstStateList().forEach(gst -> {getItems().add(gst);});
				getItems().sort(new Comparator<TGstStateCode>() {
					@Override
					public int compare(TGstStateCode o1, TGstStateCode o2) {
						return o1.getCode().compareTo(o2.getCode());
					}
				});
			}
		});
		new Thread(task).start();
	}
	
	private ListCell<TGstStateCode> createGstStateCodeCell() {
		return new ListCell<TGstStateCode>() {
			@Override
			protected void updateItem(TGstStateCode item, boolean empty) {
				super.updateItem(item, empty);
				if(item == null || empty)
					setGraphic(null);
				else
					setText(item.getCode() + " - " + item.getState());
			}
		};
	}
	
	@Override
	public boolean isValidFilterField() throws Exception {
		return getSelectionModel().getSelectedItem() != null;
	}

	@Override
	public void clearSearch() {
		getSelectionModel().clearSelection();
	}

	public Label getLabel() {
		return label;
	}

	public TGstStateCode getSelectedGstStateCode() {
		return getSelectionModel().getSelectedItem();
	}
	
	class GetAllGstStateCodeTask extends Task<GstStateCodeList> {

		@Override
		protected GstStateCodeList call() throws Exception {

			String getAllGstStateCodeListUrl = ConfigurationManager.getConfiguration().getServer().getServerUrl() + Constants.GST.GET_ALL_STATE_CODE_URL;
			ResponseEntity<GstStateCodeList> responseEntity = HttpModule.getRequest(getAllGstStateCodeListUrl, new TypeReference<GstStateCodeList>() {});
			if(responseEntity == null){
				throw new FormValidationException(Alert.AlertType.ERROR, "Something went wrong while getting GST State code list\nPlease find log for more info");
			}
			return responseEntity.getEntity();
		}
		
	}

}

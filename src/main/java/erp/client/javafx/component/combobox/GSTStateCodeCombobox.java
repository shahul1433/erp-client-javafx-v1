package erp.client.javafx.component.combobox;

import java.util.Comparator;

import com.fasterxml.jackson.core.type.TypeReference;

import erp.client.javafx.component.FormField;
import erp.client.javafx.config.ConfigurationManager;
import erp.client.javafx.config.Constants;
import erp.client.javafx.gst.GstStateCodeList;
import erp.client.javafx.exception.FormValidationException;
import erp.client.javafx.gst.GstStateCodeDTO;
import erp.client.javafx.http.HttpModule;
import erp.client.javafx.http.ResponseEntity;
import erp.client.javafx.utility.PopupUtility;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;

public class GSTStateCodeCombobox extends ComboBox<GstStateCodeDTO> implements FormField{

	private Label label;
	private String name;
	private boolean isMandatoryField;
	private GetAllGstStateCodeService service;
	
	public GSTStateCodeCombobox(String name, boolean isMandatoryField) {
		this.label = new Label(isMandatoryField ? name + " *" : name);
		this.name = name;
		this.isMandatoryField = isMandatoryField;
		
		this.setCellFactory(sf -> createGstStateCodeCell());
		this.setButtonCell(createGstStateCodeCell());

		service = new GetAllGstStateCodeService();
		service.setOnFailed(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent workerStateEvent) {
				PopupUtility.showMessage(Alert.AlertType.ERROR, workerStateEvent.getSource().getException().getMessage());
			}
		});
		service.setOnSucceeded(new GstStateCodeSucceedHandler());
		service.start();
	}
	
	private ListCell<GstStateCodeDTO> createGstStateCodeCell() {
		return new ListCell<GstStateCodeDTO>() {
			@Override
			protected void updateItem(GstStateCodeDTO item, boolean empty) {
				super.updateItem(item, empty);
				if(item == null || empty)
					setGraphic(null);
				else
					setText(item.getCode() + " - " + item.getState());
			}
		};
	}

	public GstStateCodeDTO getSelectedGstStateCode() {
		return getSelectionModel().getSelectedItem();
	}
	
	public void setSelectedGstStateCode(GstStateCodeDTO gstStateCode) {
		service.restart();
		service.setOnSucceeded(new GstStateCodeSucceedHandler(){
			@Override
			public void handle(WorkerStateEvent workerStateEvent) {
				super.handle(workerStateEvent);
				getSelectionModel().select(gstStateCode);
			}
		});
	}
	
	public Label getLabel() {
		return label;
	}

	@Override
	public boolean validateField() {
		if(isMandatoryField) {
			if(getSelectedGstStateCode() == null) {
				PopupUtility.showMessage(AlertType.WARNING, "Please select " + name);
				this.requestFocus();
				return false;
			}
		}
		return true;
	}

	@Override
	public void clearField() {
		setSelectedGstStateCode(null);
	}

	@Override
	public void setReadOnly(boolean isReadOnly) {
		setEditable(!isReadOnly);
	}

	class GstStateCodeSucceedHandler implements EventHandler<WorkerStateEvent> {

		@Override
		public void handle(WorkerStateEvent workerStateEvent) {
			GstStateCodeList gstStateCodeList = service.getValue();
			gstStateCodeList.getGstStateList().forEach(gst -> {getItems().add(gst);});
			getItems().sort(new Comparator<GstStateCodeDTO>() {
				@Override
				public int compare(GstStateCodeDTO o1, GstStateCodeDTO o2) {
					return o1.getCode().compareTo(o2.getCode());
				}
			});
		}
	}

	class GetAllGstStateCodeService extends Service<GstStateCodeList> {

		@Override
		protected Task<GstStateCodeList> createTask() {
			return new GetAllGstStateCodeTask();
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
}

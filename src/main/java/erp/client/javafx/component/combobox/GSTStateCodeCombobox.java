package erp.client.javafx.component.combobox;

import java.util.Comparator;

import com.fasterxml.jackson.core.type.TypeReference;

import erp.client.javafx.component.FormField;
import erp.client.javafx.config.ConfigurationManager;
import erp.client.javafx.config.Constants;
import erp.client.javafx.entity.GstStateCodeList;
import erp.client.javafx.entity.TGstStateCode;
import erp.client.javafx.http.HttpModule;
import erp.client.javafx.http.ResponseEntity;
import erp.client.javafx.utility.PopupUtility;
import javafx.concurrent.Task;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;

public class GSTStateCodeCombobox extends ComboBox<TGstStateCode> implements FormField{

	private Label label;
	private String name;
	private boolean isMandatoryField;
	
	public GSTStateCodeCombobox(String name, boolean isMandatoryField) {
		this.label = new Label(isMandatoryField ? name + " *" : name);
		this.name = name;
		this.isMandatoryField = isMandatoryField;
		
		this.setCellFactory(sf -> createGstStateCodeCell());
		this.setButtonCell(createGstStateCodeCell());
		
		GetAllGstStateCodeTask task = new GetAllGstStateCodeTask();
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

	public TGstStateCode getSelectedGstStateCode() {
		return getSelectionModel().getSelectedItem();
	}
	
	public void setSelectedGstStateCode(TGstStateCode gstStateCode) {
		getSelectionModel().select(gstStateCode);
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

	class GetAllGstStateCodeTask extends Task<Void> {

		@Override
		protected Void call() throws Exception {

			String getAllGstStateCodeListUrl = ConfigurationManager.getConfiguration().getServer().getServerUrl() + Constants.GST.GET_ALL_STATE_CODE_URL;
			ResponseEntity<GstStateCodeList> responseEntity = HttpModule.getRequest(getAllGstStateCodeListUrl, new TypeReference<GstStateCodeList>() {});
			if(responseEntity == null)
				return null;
			
			responseEntity.getEntity().getGstStateList().forEach(gst -> {getItems().add(gst);});
			getItems().sort(new Comparator<TGstStateCode>() {
				@Override
				public int compare(TGstStateCode o1, TGstStateCode o2) {
					return o1.getCode().compareTo(o2.getCode());
				}
			});
			
			return null;
		}
		
	}

	@Override
	public void clearField() {
		setSelectedGstStateCode(null);
	}
}

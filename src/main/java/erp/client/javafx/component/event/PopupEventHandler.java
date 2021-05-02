package erp.client.javafx.component.event;

import erp.client.javafx.exception.FormValidationException;
import erp.client.javafx.utility.PopupUtility;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class PopupEventHandler implements EventHandler<PopupEvent>{

	private Stage stage;
	
	public PopupEventHandler(Stage stage) {
		this.stage = stage;
	}
	
	@Override
	public void handle(PopupEvent event) {
		Alert alert = null;
		if(event.getEventType() == PopupEvent.SHOW_POPUP_MESSAGE){
			alert = PopupUtility.showMessage(stage, event.getAlertType(), event.getMessage());
		}else if(event.getEventType() == PopupEvent.SHOW_POPUP_EXCEPTION){
			Exception exception = event.getException();
			if(exception instanceof FormValidationException) {
				alert = PopupUtility.showMessage(stage, event.getAlertType(), exception.getMessage());
			}else{
				StringBuilder error = new StringBuilder(exception.getMessage());
				if(exception.getCause() != null) {
					error.append(",Cause: ").append(exception.getCause());
				}
				alert = PopupUtility.showMessage(stage, event.getAlertType(), error.toString());
			}
		}
		alert.showAndWait();
	}

}

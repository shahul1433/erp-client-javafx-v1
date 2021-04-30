package erp.client.javafx.component.event;

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
		Alert alert = PopupUtility.showMessage(stage, event.getAlertType(), event.getMessage());
		alert.showAndWait();
	}

}

package erp.client.javafx.utility;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PopupUtility {

	public static Alert showErrorMessage(Stage parent, String msg) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.initModality(Modality.APPLICATION_MODAL);
		alert.initOwner(parent);
		alert.getDialogPane().setContentText(msg);
		alert.getDialogPane().setHeaderText(null);
		alert.showAndWait();
//		alert.showAndWait()
//			.filter(response -> response == ButtonType.OK)
//			.ifPresent(response -> System.out.println("The alert was approved"));
		return alert;
	}
	
	public static Alert showMessage(Stage parent,AlertType alertType, String msg) {
		Alert alert = new Alert(alertType);
		alert.initModality(Modality.APPLICATION_MODAL);
		alert.initOwner(parent);
		alert.getDialogPane().setContentText(msg);
		alert.getDialogPane().setHeaderText(null);
		return alert;
	}
	
	public static void showMessage(AlertType alertType, String msg) {
		Alert alert = new Alert(alertType);
		Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
		alertStage.getIcons().add(new Image(PopupUtility.class.getResourceAsStream("/image/dolphin.png")));
		alert.initModality(Modality.APPLICATION_MODAL);
		alert.getDialogPane().setContentText(msg);
		alert.getDialogPane().setHeaderText(null);
		alert.showAndWait();
	}

}

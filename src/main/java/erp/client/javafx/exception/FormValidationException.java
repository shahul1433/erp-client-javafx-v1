package erp.client.javafx.exception;

import javafx.scene.control.Alert;

public class FormValidationException extends Exception{

    private Alert.AlertType alertType;

    public FormValidationException(Alert.AlertType alertType, String message) {
        super(message);
        this.alertType = alertType;
    }

    public Alert.AlertType getAlertType() {
        return alertType;
    }

}

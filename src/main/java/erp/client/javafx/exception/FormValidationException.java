package erp.client.javafx.exception;

import erp.client.javafx.component.event.PopupEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;

public class FormValidationException extends Exception{

    public FormValidationException(String message) {
        super(message);
    }
}

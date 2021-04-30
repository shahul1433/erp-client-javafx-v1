package erp.client.javafx.layout;

import erp.client.javafx.component.event.PopupEvent;
import erp.client.javafx.exception.FormValidationException;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;

public abstract class AbstractBorderPane extends BorderPane implements BasePane {

    public AbstractBorderPane() {
        init();
        designGUI();
        registerListeners();
    }

    protected void handleException(Exception e) {
        if(e instanceof FormValidationException) {
            this.fireEvent(new PopupEvent(PopupEvent.SHOW_POPUP, Alert.AlertType.ERROR,e.getMessage()));
        } else {
            var exception = new StringBuffer();
            if(e.getMessage() != null && !e.getMessage().isBlank()) {
                exception.append(e.getClass().getName()).append(": ").append(e.getMessage());
            }
            if(e.getCause() != null) {
                exception.append(", ").append("Cause: ").append(e.getCause());
            }
            this.fireEvent(new PopupEvent(PopupEvent.SHOW_POPUP, Alert.AlertType.ERROR,exception.toString()));
        }
    }
}

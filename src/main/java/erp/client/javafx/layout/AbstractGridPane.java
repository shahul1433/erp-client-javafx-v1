package erp.client.javafx.layout;

import erp.client.javafx.component.event.PopupEvent;
import erp.client.javafx.exception.ExceptionHandler;
import erp.client.javafx.exception.FormValidationException;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;

public abstract class AbstractGridPane extends GridPane implements BasePane {

    public AbstractGridPane() {
        init();
        designGUI();
        registerListeners();
    }

    protected void handleException(Exception e) {
        new ExceptionHandler(e, this);
    }
}

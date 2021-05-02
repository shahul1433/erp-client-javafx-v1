package erp.client.javafx.layout;

import erp.client.javafx.component.event.PopupEvent;
import erp.client.javafx.exception.ExceptionHandler;
import erp.client.javafx.exception.FormValidationException;
import javafx.scene.control.Alert;
import javafx.scene.layout.HBox;

public abstract class AbstractHBoxPane extends HBox implements BasePane {

    public AbstractHBoxPane() {
        init();
        designGUI();
        registerListeners();
    }

    protected void handleException(Exception e) {
        new ExceptionHandler(e, this);
    }
}
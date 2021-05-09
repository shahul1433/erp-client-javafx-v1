package erp.client.javafx.layout;

import erp.client.javafx.exception.ExceptionHandler;
import javafx.scene.layout.VBox;

public abstract class AbstractVBoxPane extends VBox implements BasePane {

    public AbstractVBoxPane() {
        init();
        designGUI();
        registerListeners();
    }

    protected void handleException(Exception e) {
        new ExceptionHandler(e, this);
    }
}

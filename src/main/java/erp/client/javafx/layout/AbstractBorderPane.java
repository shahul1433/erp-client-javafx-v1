package erp.client.javafx.layout;

import erp.client.javafx.exception.ExceptionHandler;
import javafx.scene.layout.BorderPane;

public abstract class AbstractBorderPane extends BorderPane implements BasePane {

    public AbstractBorderPane() {
        init();
        designGUI();
        registerListeners();
    }

    protected void handleException(Exception e) {
        new ExceptionHandler(e, this);
    }
}

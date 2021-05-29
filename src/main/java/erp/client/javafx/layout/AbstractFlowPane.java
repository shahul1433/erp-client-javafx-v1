package erp.client.javafx.layout;

import erp.client.javafx.exception.ExceptionHandler;
import javafx.scene.layout.FlowPane;

public abstract class AbstractFlowPane extends FlowPane implements BasePane {

    public AbstractFlowPane() {
        init();
        designGUI();
        registerListeners();
    }

    protected void handleException(Exception e) {
        new ExceptionHandler(e, this);
    }
}

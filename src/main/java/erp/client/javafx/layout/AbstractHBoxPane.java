package erp.client.javafx.layout;

import erp.client.javafx.container.Arguments;
import erp.client.javafx.exception.ExceptionHandler;
import javafx.scene.layout.HBox;

public abstract class AbstractHBoxPane extends HBox implements BasePane {

    protected Arguments arguments;

    public AbstractHBoxPane(Arguments arguments) {
        this.arguments = arguments;
        init();
        designGUI();
        registerListeners();
    }

    protected void handleException(Exception e) {
        new ExceptionHandler(e, this);
    }
}

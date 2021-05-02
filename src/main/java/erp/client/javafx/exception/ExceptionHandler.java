package erp.client.javafx.exception;

import javafx.scene.Node;

public class ExceptionHandler extends ExceptionBase{

    private Exception exception;

    public ExceptionHandler(Exception exception, Node node) {
        super(node);
        this.exception = exception;
        handleException(exception);
    }

}

package erp.client.javafx.exception;

import erp.client.javafx.container.status.StatusBar;
import erp.client.javafx.container.status.StatusBarStatus;
import javafx.scene.Node;

/**
 * Use this exception handler to set status bar as well.
 */
public class WorkerStateEventStatusBarExceptionHandler extends WorkerStateEventExceptionHandler{

    private StatusBar statusBar;

    public WorkerStateEventStatusBarExceptionHandler(Node node, StatusBar statusBar) {
        super(node);
        this.statusBar = statusBar;
    }

    @Override
    protected void handleException(Exception exception) {
        statusBar.setStatus(StatusBarStatus.ERROR);
        super.handleException(exception);
    }
}

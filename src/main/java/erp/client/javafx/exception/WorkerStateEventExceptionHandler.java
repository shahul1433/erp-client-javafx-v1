package erp.client.javafx.exception;

import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;

public class WorkerStateEventExceptionHandler extends ExceptionBase implements EventHandler<WorkerStateEvent> {

    public WorkerStateEventExceptionHandler(Node node) {
        super(node);
    }

    @Override
    public void handle(WorkerStateEvent event) {
        if(event.getEventType() == WorkerStateEvent.WORKER_STATE_FAILED) {
            handleException((Exception) event.getSource().getException());
        }
    }
}

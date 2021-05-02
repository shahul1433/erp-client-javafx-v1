package erp.client.javafx.exception;

import erp.client.javafx.component.event.PopupEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import org.apache.http.conn.HttpHostConnectException;

class ExceptionBase {

    private Node node;

    public ExceptionBase(Node node) {
        this.node = node;
    }

    protected void handleException(Exception exception) {
        if(exception instanceof FormValidationException) {
            FormValidationException ex = (FormValidationException) exception;
            node.fireEvent(new PopupEvent(ex.getAlertType(), ex));
        }else if(exception instanceof HttpHostConnectException) {
            node.fireEvent(new PopupEvent<HttpHostConnectException>(Alert.AlertType.ERROR, "Connection error, please check connectivity to the server"));
        }else {
            node.fireEvent(new PopupEvent(Alert.AlertType.ERROR, (Exception) exception));
        }
    }

    public Node getNode() {
        return node;
    }

}

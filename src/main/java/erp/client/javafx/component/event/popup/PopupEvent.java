package erp.client.javafx.component.event.popup;

import javafx.event.Event;
import javafx.event.EventType;
import javafx.scene.control.Alert.AlertType;

public class PopupEvent<T extends Exception> extends Event {

    private static final long serialVersionUID = 1L;

    public static final EventType<PopupEvent> ANY = new EventType<>("ANY");
    public static final EventType<PopupEvent> SHOW_POPUP_MESSAGE = new EventType<>(ANY, "SHOW_POPUP_MESSAGE");
    public static final EventType<PopupEvent> SHOW_POPUP_EXCEPTION = new EventType<>(ANY, "SHOW_POPUP_EXCEPTION");

    private AlertType alertType;
    private String message;
    private T exception;

    public PopupEvent(AlertType alertType, String message) {
        super(SHOW_POPUP_MESSAGE);
        this.alertType = alertType;
        this.message = message;
    }

    public PopupEvent(AlertType alertType, T exception) {
        super(SHOW_POPUP_EXCEPTION);
        this.alertType = alertType;
        this.exception = exception;
    }

    public AlertType getAlertType() {
        return alertType;
    }

    public String getMessage() {
        return message;
    }

    public T getException() { return exception;}
}

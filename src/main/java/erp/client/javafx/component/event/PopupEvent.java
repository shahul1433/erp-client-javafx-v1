package erp.client.javafx.component.event;

import javafx.event.Event;
import javafx.event.EventType;
import javafx.scene.control.Alert.AlertType;

public class PopupEvent extends Event {

	private static final long serialVersionUID = 1L;

	public static final EventType<PopupEvent> SHOW_POPUP = new EventType<>("SHOW_POPUP");
	
	private AlertType alertType;
	private String message;
	
	public PopupEvent(EventType<? extends Event> eventType, AlertType alertType, String message) {
		super(eventType);
		this.alertType = alertType;
		this.message = message;
	}

	public AlertType getAlertType() {
		return alertType;
	}

	public String getMessage() {
		return message;
	}

}

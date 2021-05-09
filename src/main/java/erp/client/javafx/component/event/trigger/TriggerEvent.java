package erp.client.javafx.component.event.trigger;

import javafx.event.Event;
import javafx.event.EventType;

public class TriggerEvent extends Event {

    public static final EventType<TriggerEvent> REFRESH = new EventType<>("REFRESH");

    public TriggerEvent(EventType<? extends Event> eventType) {
        super(eventType);
    }
}

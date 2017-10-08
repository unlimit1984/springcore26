package ua.epam.spring.core.loggers;

import ua.epam.spring.core.Event;
import ua.epam.spring.core.EventLogger;

/**
 * Created by Vladimir_Vysokomorny on 03-Oct-17.
 */
public class ConsoleEventLogger implements EventLogger {

    public void logEvent(Event event) {
        System.out.println(event.getMsg());
    }
}

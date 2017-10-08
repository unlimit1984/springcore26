package ua.epam.spring.core.loggers;

import ua.epam.spring.core.Event;
import ua.epam.spring.core.EventLogger;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Vladimir on 08.10.2017.
 */
public class CombinedEventLogger implements EventLogger{
    private Collection<EventLogger> loggers = new ArrayList<>();

    public CombinedEventLogger(Collection<EventLogger> loggers) {
        this.loggers = loggers;
    }

    @Override
    public void logEvent(Event event) {
        for (EventLogger logger:loggers) {
            logger.logEvent(event);
        }
    }
}

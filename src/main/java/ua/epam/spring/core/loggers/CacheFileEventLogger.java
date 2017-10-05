package ua.epam.spring.core.loggers;

import ua.epam.spring.core.Event;

import java.util.List;

/**
 * Created by Vladimir on 06.10.2017.
 */
public class CacheFileEventLogger extends FileEventLogger {
    private int cacheSize;
    private List<Event> cache;

    public CacheFileEventLogger(String fileName, Integer cacheSize) {
        super(fileName);
        this.cacheSize = cacheSize;
    }

    @Override
    public void logEvent(Event event) {
        cache.add(event);
        if (cache.size() == cacheSize) {
            writeEventsFromCache();
            cache.clear();
        }
    }

    private void writeEventsFromCache() {
        cache.forEach(super::logEvent);
    }

    public void cleanUp() {
        if (!cache.isEmpty()) {
            writeEventsFromCache();
        }
    }
}

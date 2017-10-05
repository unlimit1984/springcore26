package ua.epam.spring.core.loggers;

import org.apache.commons.io.FileUtils;
import ua.epam.spring.core.Event;
import ua.epam.spring.core.EventLogger;

import java.io.File;
import java.io.IOException;

/**
 * Created by Vladimir on 06.10.2017.
 */
public class FileEventLogger implements EventLogger {
    private String fileName;
    private File file;

    public FileEventLogger(String fileName) {
        this.fileName = fileName;
    }

    public void init() throws IOException {
        this.file = new File(fileName);
        if (!file.canWrite()) {
            throw new IOException("Can't write here: " + fileName);
        } ;
    }

    @Override
    public void logEvent(Event event) {
        try {
            FileUtils.writeStringToFile(new File(fileName), event.getMsg(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

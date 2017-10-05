package ua.epam.spring.core;

import ua.epam.spring.core.beans.Client;
import ua.epam.spring.core.loggers.ConsoleEventLogger;

/**
 * Created by Vladimir_Vysokomorny on 03-Oct-17.
 */
public class App {

    private Client client;
    private EventLogger eventLogger;

    public App(Client client, EventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }

    public void logEvent(String msg){
        String message = msg.replaceAll(client.getId(), client.getFullName());
        eventLogger.logEvent(message);
    }

    public static void main(String[] args){

        App app = new App(new Client("1", "John Smith"), new ConsoleEventLogger());
        app.logEvent("Some event from user 1");
    }

}

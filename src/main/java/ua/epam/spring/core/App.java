package ua.epam.spring.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.epam.spring.core.beans.Client;
import ua.epam.spring.core.loggers.ConsoleEventLogger;

import java.util.Date;

/**
 * Created by Vladimir_Vysokomorny on 03-Oct-17.
 */
public class App {

    private static ConfigurableApplicationContext ctx;

    private Client client;
    private EventLogger eventLogger;

    public App(Client client, EventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }

    public void logEvent(String msg){
        String message = msg.replaceAll(client.getId(), client.getFullName());
        Event event = (Event)ctx.getBean("event");
        event.setMsg(message);
        eventLogger.logEvent(event);
    }

    public static void main(String[] args){

        //ctx = new ClassPathXmlApplicationContext("spring.xml");
        ctx = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App)ctx.getBean("app");

        app.logEvent("Some event for 1");
        app.logEvent("Some event for 2");

        ctx.close();
    }

}

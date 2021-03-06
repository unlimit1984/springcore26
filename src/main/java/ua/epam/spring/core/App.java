package ua.epam.spring.core;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.epam.spring.core.aop.StatisticsAspect;
import ua.epam.spring.core.beans.Client;

import java.util.Map;

/**
 * Created by Vladimir_Vysokomorny on 03-Oct-17.
 */
public class App {

    private static ConfigurableApplicationContext ctx;
    //private static AnnotationConfigApplicationContext ctx;

    private Client client;
    private EventLogger defaultLogger;
    private Map<EventType, EventLogger> loggers;

    private StatisticsAspect statisticsAspect;

    public void setStatisticsAspect(StatisticsAspect statisticsAspect) {
        this.statisticsAspect = statisticsAspect;
    }

    public App() {
    }

    public App(Client client, EventLogger defaultLogger, Map<EventType, EventLogger> loggers) {
        this.client = client;
        this.defaultLogger = defaultLogger;
        this.loggers = loggers;
    }

    public void logEvent(String msg, EventType eventType) {
        EventLogger logger = loggers.get(eventType);

        String message = msg.replaceAll(client.getId(), client.getFullName());

        Event event = (Event) ctx.getBean("event");
        event.setMsg(message);

        if (logger == null) {
            logger = defaultLogger;
        }
        logger.logEvent(event);
    }

    public void printStatistics(){
        statisticsAspect.printStatistics();
    }

    public static void main(String[] args) {

        ctx = new ClassPathXmlApplicationContext("spring.xml");
//        ctx = new AnnotationConfigApplicationContext();
//        ctx.register(AppConfig.class);
//        ctx.refresh();

        App app = (App) ctx.getBean("app");

        app.logEvent("Some event for 1", EventType.ERROR);
        app.logEvent("Some event for 2", null);
        app.logEvent("Some event for 3", EventType.INFO);

        app.printStatistics();

        ctx.close();
    }

}

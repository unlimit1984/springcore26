package ua.epam.spring.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import ua.epam.spring.core.App;
import ua.epam.spring.core.Event;
import ua.epam.spring.core.EventLogger;
import ua.epam.spring.core.EventType;
import ua.epam.spring.core.beans.Client;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vladimir on 08.10.2017.
 */
@Configuration
@Import(LoggerConfig.class)
@PropertySource("classpath:client.properties")
public class AppConfig {

//    @Bean
//    public PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
//        PropertyPlaceholderConfigurer props = new PropertyPlaceholderConfigurer();
//        props.setLocation(new ClassPathResource("classpath:client.properties"));
//        props.setIgnoreResourceNotFound(true);
//        props.setSystemPropertiesMode(PropertyPlaceholderConfigurer.SYSTEM_PROPERTIES_MODE_OVERRIDE);
//
//        return props;
//    }

    @Autowired
    private LoggerConfig loggerConfig;

    @Autowired
    private Environment env;

    @Bean
    public Client client() {
        Client client = new Client(env.getProperty("id"), env.getProperty("name"));
        client.setGreeting(env.getProperty("greeting"));
        return client;

    }

    @Bean(name = "app")
    public App app() {
        Map<EventType, EventLogger> loggers = new HashMap<>();
        loggers.put(EventType.ERROR, loggerConfig.combinedEventLogger());
        loggers.put(EventType.INFO, loggerConfig.consoleEventLogger());
        App app = new App(client(), loggerConfig.cacheFileEventLogger(), loggers);
        return app;
    }

    @Bean
    @Scope("prototype")
    public Event event() {
        return new Event(new Date(), dateFormat());
    }

    @Bean
    public DateFormat dateFormat() {
        return DateFormat.getDateTimeInstance();
    }
}

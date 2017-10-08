package ua.epam.spring.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.epam.spring.core.loggers.CacheFileEventLogger;
import ua.epam.spring.core.loggers.CombinedEventLogger;
import ua.epam.spring.core.loggers.ConsoleEventLogger;
import ua.epam.spring.core.loggers.FileEventLogger;

import java.util.Arrays;

/**
 * Created by Vladimir on 08.10.2017.
 */
@Configuration
public class LoggerConfig {

    @Bean
    public ConsoleEventLogger consoleEventLogger() {
        return new ConsoleEventLogger();
    }

    @Bean(initMethod = "init")
    public FileEventLogger fileEventLogger() {
        return new FileEventLogger("logs/log.txt");
    }

    @Bean(destroyMethod = "cleanUp")
    public CacheFileEventLogger cacheFileEventLogger() {
        return new CacheFileEventLogger("logs/log.txt", 10);
    }

    @Bean
    public CombinedEventLogger combinedEventLogger() {
        return new CombinedEventLogger(Arrays.asList(fileEventLogger(), consoleEventLogger()));
    }

}

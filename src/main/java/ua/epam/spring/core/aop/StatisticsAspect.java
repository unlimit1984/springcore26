package ua.epam.spring.core.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vladimir on 23.10.2017.
 */
@Aspect
//@Component
public class StatisticsAspect {

    private Map<Class<?>, Integer> counter = new HashMap<>();

    @Pointcut("execution(* *.logEvent(..))")
    private void allLogEventMethods() {
    }

    @Pointcut("allLogEventMethods() within(*.*Console*Logger)")
    private void consoleLoggerMethods() {
    }

    @AfterReturning("allLogEventMethods()")
    public void count(JoinPoint jp) {
        Class<?> clazz = jp.getTarget().getClass();

        if (!counter.containsKey(clazz)) {
            counter.put(clazz, 0);
        }
        counter.put(clazz, counter.get(clazz) + 1);
    }

//    @Around("consoleLoggerMethods()")
//    public void aroundLogEvent(ProceedingJoinPoint jp) {
//        //getting count
//        if (count < MAX_ALLOWED) {
//            jp.proceed();
//        } else {
//            //
//        }
//    }


    @Around("consoleLoggerMethods() && args(evt)")
    public void aroundLogEvent(ProceedingJoinPoint jp, Object evt) {
        int i = 0;
        //getting count
//        if (count < MAX_ALLOWED) {
//            jp.proceed(new Object[]{evt});
//        } else {
//            otherLogger.logEvent(evt)
//        }
    }
}

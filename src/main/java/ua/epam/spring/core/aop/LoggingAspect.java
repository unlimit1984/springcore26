package ua.epam.spring.core.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Created by Vladimir on 23.10.2017.
 */
@Aspect //mandatory annotation
//@Component //по идее нужно добавлять, если используем аннотации, но и без него работает
public class LoggingAspect {

    @Pointcut("execution(* *.logEvent(..))")
    private void allLogEventMethods() {
    }


    @Pointcut("allLogEventMethods() && within(*.*File*Logger)")
    private void logEventInsideFileLoggers() {
    }


    //@Before("allLogEventMethods")
    //or
    @Before("execution(* *.logEvent(..))")
    public void logBefore(JoinPoint joinPoint) {
//        LOG.info("BEFORE : " +
        System.out.println("BEFORE : " +
                joinPoint.getTarget().getClass().getSimpleName() + " " +
                joinPoint.getSignature().getName());
    }

    @AfterReturning(pointcut = "allLogEventMethods()", returning = "retVal")
    public void logAfter(Object retVal) {
//        LOG.info("Returned value: " + retVal);
        System.out.println("Returned value: " + retVal);
    }

    @AfterThrowing(pointcut = "allLogEventMethods()", throwing = "ex")
    public void logAfterThrow(Throwable ex) {
//        LOG.warn("Thrown: " + ex);
        System.out.println("Thrown: " + ex);
    }
}

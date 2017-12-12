package tool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;

import java.util.Arrays;

public class LoggingAspect {
    private static Logger LOGGER = LogManager.getLogger();

    public Object logging(ProceedingJoinPoint joinPoint)
            throws Throwable {
        LOGGER.info("Starting method {} of object {}" +
                        " with arguments {}", joinPoint.getSignature(),
                joinPoint.getTarget(),
                Arrays.toString(joinPoint.getArgs()));

        Object result = joinPoint.proceed();

        LOGGER.info("The result of the method {} is {}",
                joinPoint.getSignature(), result);
        return result;
    }
}
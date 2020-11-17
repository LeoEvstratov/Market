package com.evstratov.market.aop;

import com.evstratov.market.MarketApplication;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionLogger {
    private final Logger logger = LoggerFactory.getLogger(MarketApplication.class);

    @AfterThrowing(pointcut = "execution(* com.evstratov.market..*(..))", throwing = "ex")
    public void exceptionLog(Throwable ex) {
        logger.error(ex.getClass().getSimpleName() + " EXCEPTION FOR LOGGING IN DB");
    }
}

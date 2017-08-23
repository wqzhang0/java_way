package com.wqzhang;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by com.wqzhang on 2017/7/6.
 */
@Component
@Aspect
public class LogAspect {

    public LogAspect() {
        System.out.print("LogAspect is scanning");
    }

    @Pointcut("execution(* com.com.wqzhang..*.bookFlight(..))") //2
    private void logPointCut() {
        System.out.println("LogAspect :> logPointCut start");
    }

    @AfterReturning(pointcut = "logPointCut()", returning = "retVal") //3
    public void logBookingStatus(boolean retVal) {  //4
        if (retVal) {
            System.out.println("booking flight succeeded!");
        } else {
            System.out.println("booking flight failed!");
        }
    }
}

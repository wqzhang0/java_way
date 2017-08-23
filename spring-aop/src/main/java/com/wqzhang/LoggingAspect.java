package com.wqzhang;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by com.wqzhang on 2017/7/6.
 */

@Component
@Aspect
public class LoggingAspect {


    /**
     * 切面的前置方法 即方法执行前拦截到的方法 记录并输出
     * 在目标方法执行之前的通知
     *
     * @param joinPoint
     */
    @Before("execution(* com.com.wqzhang.*.*(..))")
    public void beforeMethod(JoinPoint joinPoint) {
        System.out.println("======================方法开始======================");
        Object object = joinPoint.getSignature();
        String methodName = joinPoint.getSignature().getName();
        List<Object> list = Arrays.asList(joinPoint.getArgs());
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String rightnow = sdf.format(date);
        System.out.println(rightnow + "执行了【" + methodName + "方法开始执行......】");
        System.out.println("******参数" + list + "******");
    }

    @AfterReturning(value = "execution(* com.com.wqzhang.*.*(..))", returning = "result")
    public void afterMethod(JoinPoint joinPoint, Object result) {
        Object object = joinPoint.getSignature();
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String rightnow = sdf.format(date);
        System.out.println(rightnow + "执行了【" + object + "方法正常执行结束......】" + "【返回结果:" + result + "】");
        System.out.println("√√√√√√√√√√√√√√√√√√√√√√方法结束√√√√√√√√√√√√√√√√√√√√√√");
    }
}

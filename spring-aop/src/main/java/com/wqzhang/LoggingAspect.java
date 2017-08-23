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
     * �����ǰ�÷��� ������ִ��ǰ���ص��ķ��� ��¼�����
     * ��Ŀ�귽��ִ��֮ǰ��֪ͨ
     *
     * @param joinPoint
     */
    @Before("execution(* com.com.wqzhang.*.*(..))")
    public void beforeMethod(JoinPoint joinPoint) {
        System.out.println("======================������ʼ======================");
        Object object = joinPoint.getSignature();
        String methodName = joinPoint.getSignature().getName();
        List<Object> list = Arrays.asList(joinPoint.getArgs());
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String rightnow = sdf.format(date);
        System.out.println(rightnow + "ִ���ˡ�" + methodName + "������ʼִ��......��");
        System.out.println("******����" + list + "******");
    }

    @AfterReturning(value = "execution(* com.com.wqzhang.*.*(..))", returning = "result")
    public void afterMethod(JoinPoint joinPoint, Object result) {
        Object object = joinPoint.getSignature();
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String rightnow = sdf.format(date);
        System.out.println(rightnow + "ִ���ˡ�" + object + "��������ִ�н���......��" + "�����ؽ��:" + result + "��");
        System.out.println("�̷̡̡̡̡̡̡̡̡̡̡̡̡̡̡̡̡̡̡̡̡̡��������̡̡̡̡̡̡̡̡̡̡̡̡̡̡̡̡̡̡̡̡̡�");
    }
}

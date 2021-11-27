package com.sme.todo.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Aspect
@Component
@Slf4j
public class AopLoggingHandler {

    private ObjectMapper mapper;

    @PostConstruct
    private void postContruct() {
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.registerModule(new Jdk8Module());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    @Around("execution(* com.sme.todo.*.*.*(..))")
    public Object logger(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().toString();

        Object[] array = joinPoint.getArgs();
        log.info(className + "." + methodName + "() :: " + mapper.writeValueAsString(array));

        Object object = joinPoint.proceed();
        log.info(className + "." + methodName + "() :: " + mapper.writeValueAsString(object));

        return object;
    }
}

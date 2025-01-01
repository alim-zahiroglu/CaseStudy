package com.uydev.aspect;

import com.uydev.service.impl.LogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    private final LogService logService;

    public LoggingAspect(LogService logService) {
        this.logService = logService;
    }

    @Pointcut("execution(* com.uydev.controller.ModelController.*(..))")
    public void modelControllerMethods() {
    }

    @Pointcut("execution(* com.uydev.controller.PartController.*(..))")
    public void partControllerMethods() {
    }


    @Before("modelControllerMethods()")
    public void logModelBefore(JoinPoint joinPoint) {
        logBefore(joinPoint, "Model");
    }

    @AfterReturning(pointcut = "modelControllerMethods()", returning = "result")
    public void logModelAfter(JoinPoint joinPoint, Object result) {
        logAfter(joinPoint, "Model", result);
    }

    @Before("partControllerMethods()")
    public void logPartBefore(JoinPoint joinPoint) {
        logBefore(joinPoint, "Part");
    }

    @AfterReturning(pointcut = "partControllerMethods()", returning = "result")
    public void logPartAfter(JoinPoint joinPoint, Object result) {
        logAfter(joinPoint, "Part", result);
    }



    private void logBefore(JoinPoint joinPoint,String entity){
        String operation = entity.equals("Model") ? getModelOperationName(joinPoint) : getPartOperationName(joinPoint);
        Long entityId = getEntityId(joinPoint);
        Object[] args = joinPoint.getArgs();

        logService.log(String.format("Started operation: %s with parameters: %s",
                operation, Arrays.toString(args)), entity, entityId);

    }


    private void logAfter(JoinPoint joinPoint, String entity, Object result){
        String operation = entity.equals("Model") ? getModelOperationName(joinPoint) : getPartOperationName(joinPoint);
        Long entityId = getEntityId(joinPoint);
        Object[] args = joinPoint.getArgs();


        logService.log(String.format("Completed operation: %s with parameters: %s, Result: %s",
                operation, Arrays.toString(args), result), entity, entityId, true);

    }


    private String getModelOperationName(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        return switch (methodName) {
            case "findAllModels" -> "LIST_ALL";
            case "listModelByProjectId" -> "LIST_BY_PROJECT";
            case "listModelByProjectIdAndMonth" -> "LIST_BY_PROJECT_AND_MONTH";
            case "addModel" -> "ADD";
            case "updateModel" -> "UPDATE";
            case "deleteModel" -> "DELETE";
            default -> "UNKNOWN_OPERATION";
        };
    }


    private Long getEntityId(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof Long) {
                return (Long) arg;
            }
        }
        return null;
    }

    private String getPartOperationName(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        return switch (methodName) {
            case "findAllParts" -> "LIST_ALL";
            case "findAllPartsByModelId" -> "LIST_BY_MODEL";
            case "addPart" -> "ADD";
            case "updatePart" -> "UPDATE";
            case "deletePart" -> "DELETE";
            default -> "UNKNOWN_OPERATION";
        };
    }
}

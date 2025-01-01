package com.uydev.exception;

public class MonthlyTargetNotFoundException extends RuntimeException{
    public MonthlyTargetNotFoundException(String projectName){
        super("Monthly Target Not found for project name: '" + projectName + "'");
    }
    public MonthlyTargetNotFoundException(Long projectId){
        super("Monthly Target Not found for project id: '" + projectId + "'");
    }
    public MonthlyTargetNotFoundException(){
        super("Related monthly target Not found");
    }
}

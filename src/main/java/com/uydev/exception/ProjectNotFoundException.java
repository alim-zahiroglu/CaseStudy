package com.uydev.exception;

public class ProjectNotFoundException extends RuntimeException{
    public ProjectNotFoundException(String projectName){
        super("Project Not found with name: '" + projectName + "'");
    }
    public ProjectNotFoundException(Long projectId){
        super("Project Not found with id: '" + projectId + "'");
    }
    public ProjectNotFoundException(){
        super("Related Project Not found");
    }
}

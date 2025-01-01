package com.uydev.exception;

public class DuplicateKeyException extends RuntimeException {

    public DuplicateKeyException(String message){
        super(message);
    }
    public DuplicateKeyException(){
        super("Entity already exist");
    }
}

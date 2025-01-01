package com.uydev.exception;

public class ValueNotAcceptableException extends RuntimeException{
    public ValueNotAcceptableException(String rejectedFor, Object rejectedValue, String acceptable){
        super("The value: '" + rejectedValue + "'" + "Not acceptable for: '" + rejectedFor + "', " + acceptable);
    }
    public ValueNotAcceptableException(Object rejectedValue, String acceptable){
        super("The value: '" + rejectedValue + "'" + "Not acceptable," + acceptable);
    }
    public ValueNotAcceptableException(Object rejectedValue){
        super("The value: '" + rejectedValue + "'" + "Not acceptable");
    }
    public ValueNotAcceptableException(){
        super("Value Not acceptable");
    }
    public ValueNotAcceptableException(String message){
        super(message);
    }

}

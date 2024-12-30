package com.uydev.controller;
import com.uydev.dto.ResponseWrapper;
import org.springframework.stereotype.Component;

@Component
public class BaseResponseOk {
    protected <T> ResponseWrapper<T> ok(T data, String message) {
        return ResponseWrapper.ok(data,message);
    }
    protected <T> ResponseWrapper<T> ok(T data) {
        return ResponseWrapper.ok(data,"The operation is successful ");
    }
}

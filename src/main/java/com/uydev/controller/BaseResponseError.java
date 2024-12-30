package com.uydev.controller;

import com.uydev.dto.ResponseWrapper;

public class BaseResponseError {
    protected <T> ResponseWrapper<T> error(T data, String error){
        return ResponseWrapper.error(data,error);
    }
}

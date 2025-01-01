package com.uydev.exception;

import com.uydev.controller.BaseResponseError;
import com.uydev.dto.ResponseWrapper;
import jakarta.validation.constraints.Null;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends BaseResponseError {

    @ExceptionHandler(value = UserNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseWrapper<Null> handleRuntimeException(UserNotFoundException ex){
        return error(null,ex.getMessage());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseWrapper<Map<String,List<String>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        Map<String,List<String>> errorMaps = new HashMap<>();
        List<ObjectError> errors = ex.getBindingResult().getAllErrors();
        for (ObjectError objectError:errors){
            String fieldName = ((FieldError)objectError).getField();
            if (errorMaps.containsKey(fieldName)){
                errorMaps.put(fieldName,addMapValue(errorMaps.get(fieldName),objectError.getDefaultMessage()));
            }else {
                errorMaps.put(fieldName,addMapValue(new ArrayList<>(),objectError.getDefaultMessage()));
            }
        }
        return error(errorMaps, "Please check the value you entered");
    }

    private List<String> addMapValue(List<String> list, String value) {
        list.add(value);
        return list;
    }

    @ExceptionHandler(value = DuplicateKeyException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseWrapper<Null> handleDuplicateKeyException(DuplicateKeyException ex){
        return error(null,ex.getMessage());
    }

    @ExceptionHandler(value = ProjectNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseWrapper<Null> handleProjectNotFoundException(ProjectNotFoundException ex){
        return error(null,ex.getMessage());
    }

    @ExceptionHandler(value = ModelNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseWrapper<Null> handleModelNotFoundException(ModelNotFoundException ex){
        return error(null,ex.getMessage());
    }

    @ExceptionHandler(value = MonthlyTargetNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseWrapper<Null> handleMonthlyTargetNotFoundException(MonthlyTargetNotFoundException ex){
        return error(null,ex.getMessage());
    }

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseWrapper<Null> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex){
        return error(null,ex.getMessage());
    }
    @ExceptionHandler(value = ValueNotAcceptableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseWrapper<Null> handleValueNotAcceptableException(ValueNotAcceptableException ex){
        return error(null,ex.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseWrapper<Null> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return error(null, "You should provide a valid request body or valid parameters");
    }
}

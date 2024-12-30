package com.uydev.dto;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class ResponseWrapper<T> {
    private LocalDateTime timestamp;
    private boolean success;
    private String message;
    private T data;

    // for success response
    public static <T> ResponseWrapper<T> ok(T data, String message) {
        return ResponseWrapper.<T>builder()
                .timestamp(LocalDateTime.now())
                .success(true)
                .message(message)
                .data(data)
                .build();
    }

    // for error response
    public static <T> ResponseWrapper<T> error(T data, String error){
        return ResponseWrapper.<T>builder()
                .timestamp(LocalDateTime.now())
                .success(false)
                .message(error)
                .data(data)
                .build();
    }
}

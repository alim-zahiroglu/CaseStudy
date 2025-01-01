package com.uydev.controller;

import com.uydev.dto.ResponseWrapper;
import com.uydev.entity.LogEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/api/v1/logging")
public interface LoggingController {

    @GetMapping("/list/model")
    ResponseWrapper<List<LogEntity>> findAllLogsInModelController();

    @GetMapping("/list/model/success")
    ResponseWrapper<List<LogEntity>> findAllSuccessLogsInModelController();

    @GetMapping("/list/part")
    ResponseWrapper<List<LogEntity>> findAllLogsInPartController();

    @GetMapping("/list/part/success")
    ResponseWrapper<List<LogEntity>> findAllSuccessLogsInPartController();



}

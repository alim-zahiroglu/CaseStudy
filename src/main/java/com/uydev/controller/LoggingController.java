package com.uydev.controller;

import com.uydev.dto.ResponseWrapper;
import com.uydev.entity.LogEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/api/v1/logging")
@Tag(name = "Logging API", description = "Operations related to Model and prt operations logging")
public interface LoggingController {

    @GetMapping("/list/model")
    @Operation(summary = "Get all logs in model controller")
    ResponseWrapper<List<LogEntity>> findAllLogsInModelController();

    @GetMapping("/list/model/success")
    @Operation(summary = "Get all success logs in model controller")
    ResponseWrapper<List<LogEntity>> findAllSuccessLogsInModelController();

    @GetMapping("/list/part")
    @Operation(summary = "Get all logs in part controller")
    ResponseWrapper<List<LogEntity>> findAllLogsInPartController();

    @GetMapping("/list/part/success")
    @Operation(summary = "Get all success logs in part controller")
    ResponseWrapper<List<LogEntity>> findAllSuccessLogsInPartController();



}

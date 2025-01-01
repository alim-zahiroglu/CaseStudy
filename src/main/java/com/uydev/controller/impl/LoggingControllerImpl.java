package com.uydev.controller.impl;

import com.uydev.controller.BaseResponseOk;
import com.uydev.controller.LoggingController;
import com.uydev.dto.ResponseWrapper;
import com.uydev.entity.LogEntity;
import com.uydev.service.impl.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LoggingControllerImpl extends BaseResponseOk implements LoggingController {
    private final LogService logService;

    @Override
    public ResponseWrapper<List<LogEntity>> findAllLogsInPartController() {
        return ok(logService.findAllLogsInPartService());
    }

    @Override
    public ResponseWrapper<List<LogEntity>> findAllLogsInModelController() {
        return ok(logService.findAllLogsInModelService());
    }

    @Override
    public ResponseWrapper<List<LogEntity>> findAllSuccessLogsInModelController() {
        return ok(logService.findAllSuccessLogsInModelService());
    }

    @Override
    public ResponseWrapper<List<LogEntity>> findAllSuccessLogsInPartController() {
        return ok(logService.findAllSuccessLogsInPartService());
    }
}

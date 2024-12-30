package com.uydev.controller.impl;

import com.uydev.controller.BaseResponseOk;
import com.uydev.controller.ModelController;
import com.uydev.dto.ModelDto;
import com.uydev.dto.ResponseWrapper;
import com.uydev.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ModelControllerImpl extends BaseResponseOk implements ModelController {
    private final ModelService modelService;
    @Override
    public ResponseWrapper<List<ModelDto>> findAllModels() {
        return ok(modelService.findAllModels(), "All models are retrieved");
    }
}

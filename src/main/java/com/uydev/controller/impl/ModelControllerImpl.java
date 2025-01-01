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
        return ok(modelService.findAllModels());
    }

    @Override
    public ResponseWrapper<List<ModelDto>> listModelByProjectId(Long projectId) {
        return ok(modelService.findAllModelsByProjectId(projectId), "All models are retrieved for project id: "+ projectId);
    }

    @Override
    public ResponseWrapper<ModelDto> addModel(ModelDto newModel, Long projectId) {
        return ok(modelService.addModel(newModel,projectId), "Model added successfully");
    }

    @Override
    public ResponseWrapper<ModelDto> updateModel(ModelDto newModel, Long modelId) {
        return ok(modelService.updateModel(newModel, modelId), "Model updated successfully");
    }

    @Override
    public ResponseWrapper<ModelDto> deleteModel(Long modelId) {
        return ok(modelService.deleteModel(modelId));
    }
}

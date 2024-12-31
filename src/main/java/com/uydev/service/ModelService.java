package com.uydev.service;

import com.uydev.dto.ModelDto;

import java.util.List;

public interface ModelService {
    List<ModelDto> findAllModels();

    ModelDto addModel(ModelDto newModel, Long projectId);

    Integer findAvailablePercentage(Long projectId);

    List<ModelDto> findAllModelsByProjectId(Long projectId);

    void deleteModelByProjectId(Long projectId);
}

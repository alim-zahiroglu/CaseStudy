package com.uydev.service;

import com.uydev.dto.ModelDto;
import com.uydev.entity.Model;

import java.util.List;

public interface ModelService {
    List<ModelDto> findAllModels();

    ModelDto addModel(ModelDto newModel, Long projectId);

    Integer findAvailablePercentage(Long projectId);

    List<ModelDto> findAllModelsByProjectId(Long projectId);

    void deleteModelByProjectId(Long projectId);

    ModelDto updateModel(ModelDto newModel, Long modelId);

    ModelDto deleteModel(Long modelId);

    Integer findCurrentPercentage(Model model);

    int calculateModelTotal(Model model,Integer percentage);
}

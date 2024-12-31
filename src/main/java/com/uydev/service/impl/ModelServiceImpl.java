package com.uydev.service.impl;

import com.uydev.dto.ModelDto;
import com.uydev.entity.Model;
import com.uydev.enums.ConfigType;
import com.uydev.mapper.MapperUtil;
import com.uydev.repository.ModelRepository;
import com.uydev.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModelServiceImpl implements ModelService {
    private final ModelRepository repository;
    private final MapperUtil mapper;
    @Override
    public List<ModelDto> findAllModels() {
        List<Model> models = repository.findAllByProject_IsDeleted(false);
        return models.stream()
                .map(model -> {
                    ModelDto modelDto = mapper.convert(model, new ModelDto());
                    modelDto.setCurrentPercentage(findCurrentPercentage(model));
                    return modelDto;
                })
                .toList();
    }

    private Integer findCurrentPercentage(Model model) {
        ConfigType configType = model.getProject().getConfigType();

        return switch (configType) {
            case WEEKLY -> model.getWeeklyPercentage();
            case MONTHLY -> model.getMonthlyPercentage();
            default -> model.getFixedPercentage();
        };

    }
}

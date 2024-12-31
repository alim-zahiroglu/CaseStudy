package com.uydev.service.impl;

import com.uydev.dto.ModelDto;
import com.uydev.entity.Model;
import com.uydev.entity.Project;
import com.uydev.enums.ConfigType;
import com.uydev.exception.DuplicateKeyException;
import com.uydev.exception.ValueNotAcceptableException;
import com.uydev.mapper.MapperUtil;
import com.uydev.repository.ModelRepository;
import com.uydev.service.ModelService;
import com.uydev.service.MonthlyTargetService;
import com.uydev.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModelServiceImpl implements ModelService {
    private final ModelRepository repository;
    private final ProjectService projectService;
    private final MonthlyTargetService monthlyTargetService;
    private final MapperUtil mapper;
    @Override
    public List<ModelDto> findAllModels() {
        List<Model> models = repository.findAllByIsDeleted(false);
        return models.stream()
                .map(model -> {
                    ModelDto modelDto = mapper.convert(model, new ModelDto());
                    modelDto.setCurrentPercentage(findCurrentPercentage(model));
                    modelDto.setModelTotal(calculateModelTotal(model,modelDto.getCurrentPercentage()));
                    return modelDto;
                })
                .toList();
    }

    @Override
    public ModelDto addModel(ModelDto newModel, Long projectId) {
        if (repository.existsByNameAndIsDeleted(newModel.getName(),false)){

            throw new DuplicateKeyException("Model with name: '" + newModel.getName() + "' is already exist");
        }
        Project project = projectService.findById(projectId);
        Integer availability =  findAvailablePercentage(projectId);
        if (availability >= newModel.getCurrentPercentage()){
            Model model = mapper.convert(newModel, new Model());
            model.setProject(project);
            setModelPercentage(model, newModel.getCurrentPercentage());
            Model savedModel = repository.save(model);
            int modelTotal = calculateModelTotal(model, newModel.getCurrentPercentage());
            ModelDto response = mapper.convert(savedModel, new ModelDto());
            response.setModelTotal(modelTotal);
            response.setCurrentPercentage(newModel.getCurrentPercentage());
            return response;
        }

        throw new ValueNotAcceptableException("CurrentPercentage",newModel.getCurrentPercentage(),"It should not grater then: " + availability);

    }

    @Override
    public Integer findAvailablePercentage(Long projectId) {
        List<Model> models = repository.findAllByProjectIdAndIsDeleted(projectId,false,false);
        Integer occupied =  models.stream()
                .filter(model -> {
                    if (model.getProject().getConfigType() == ConfigType.FIXED){
                        return model.isActive();
                    }
                    return true;
                })
                .map(this::findCurrentPercentage)
                .reduce(Integer::sum).orElse(0);
        return 100-occupied;
    }

    private Model setModelPercentage(Model model,Integer percentage) {
         switch (model.getProject().getConfigType()){
            case WEEKLY -> model.setWeeklyPercentage(percentage);
            case MONTHLY -> model.setMonthlyPercentage(percentage);
            default -> model.setFixedPercentage(percentage);
        }
        return model;
    }


    private Integer findCurrentPercentage(Model model) {
        ConfigType configType = model.getProject().getConfigType();

        return switch (configType) {
            case WEEKLY -> model.getWeeklyPercentage();
            case MONTHLY -> model.getMonthlyPercentage();
            default -> model.getFixedPercentage();
        };

    }

    private int calculateModelTotal(Model model,Integer percentage){
        int target = monthlyTargetService.getCurrentMonthlyTargetByProjectId(model.getProject().getId()).getTarget();
        return (target*percentage)/100;
        }
}

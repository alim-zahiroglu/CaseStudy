package com.uydev.service.impl;

import com.uydev.dto.ModelDto;
import com.uydev.entity.Model;
import com.uydev.entity.Project;
import com.uydev.enums.ConfigType;
import com.uydev.exception.DuplicateKeyException;
import com.uydev.exception.ModelNotFoundException;
import com.uydev.exception.ValueNotAcceptableException;
import com.uydev.mapper.MapperUtil;
import com.uydev.repository.ModelRepository;
import com.uydev.service.ModelService;
import com.uydev.service.MonthlyTargetService;
import com.uydev.service.ProjectService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ModelServiceImpl implements ModelService {
    private final ModelRepository repository;
    private final ProjectService projectService;
    private final MonthlyTargetService monthlyTargetService;
    private final MapperUtil mapper;

    public ModelServiceImpl(ModelRepository repository, @Lazy ProjectService projectService, MonthlyTargetService monthlyTargetService, MapperUtil mapper) {
        this.repository = repository;
        this.projectService = projectService;
        this.monthlyTargetService = monthlyTargetService;
        this.mapper = mapper;
    }

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
    public List<ModelDto> findAllModelsByProjectId(Long projectId) {
        Project project = projectService.findById(projectId);
        return findAllModels().stream()
                .filter(model-> Objects.equals(model.getProjectId(), project.getId()))
                .toList();
    }

    @Override
    public ModelDto addModel(ModelDto newModel, Long projectId) {
        checkModelExists(newModel.getName(),projectId);
        Project project = projectService.findById(projectId);
        Integer availability =  findAvailablePercentage(projectId);
        if (availability >= newModel.getCurrentPercentage()){
            Model model = mapper.convert(newModel, new Model());
            model.setProject(project);

            // set model is active if project is not fixed
            if (project.getConfigType() != ConfigType.FIXED){
                model.setActive(true);
            }
            setModelPercentage(model, newModel.getCurrentPercentage());
            Model savedModel = repository.save(model);

            return preparedResponse(savedModel,newModel.getCurrentPercentage());
        }

        throw new ValueNotAcceptableException("CurrentPercentage",newModel.getCurrentPercentage(),"It should not grater then: " + availability);

    }

    @Override
    public ModelDto updateModel(ModelDto newModel, Long modelId) {
        Model oldModel = findById(modelId);
        checkUpdatedNameIsUnique(newModel.getName(),oldModel.getProject().getId(),modelId);
        Integer oldPercentage = findCurrentPercentage(oldModel);
        Integer availability =  findAvailablePercentage(oldModel.getProject().getId());

        // check if new model is change to active or inactive and availability is enough
        if (oldModel.getProject().getConfigType() != ConfigType.FIXED){
            checkAvailability(availability + oldPercentage, newModel.getCurrentPercentage());
            Model model = mapper.convert(newModel, new Model());
            model.setActive(true);
            return updateModel(model,oldModel.getProject(),modelId,newModel.getCurrentPercentage());
        } else{
            if (!oldModel.isActive()){
                if (newModel.isActive()){
                    checkAvailability(availability, newModel.getCurrentPercentage());
                }
                else {
                    checkAvailability(availability + oldPercentage, newModel.getCurrentPercentage());
                }

            }else{
                checkAvailability(availability + oldPercentage, newModel.getCurrentPercentage());
            }
        }

        Model model = mapper.convert(newModel, new Model());
        model.setActive(true);
        return updateModel(model,oldModel.getProject(),modelId,newModel.getCurrentPercentage());
    }

    @Override
    public ModelDto deleteModel(Long modelId) {
        Model model = findById(modelId);
        model.setIsDeleted(true);
        repository.save(model);
        Integer percentage = findCurrentPercentage(model);
        return preparedResponse(model,percentage);

    }

    private void checkUpdatedNameIsUnique(String modelName, Long projectId, Long modelId){
        if (repository.existsByNameAndProjectIdAndIdNotAndIsDeleted(modelName,projectId,modelId,false)){
            throw new DuplicateKeyException("Model with name: '" + modelName + "' is already exist");
        }
    }

    private ModelDto updateModel(Model model,Project project, Long oldModelId, Integer percentage){
        model.setProject(project);
        setModelPercentage(model, percentage);
        model.setId(oldModelId);
        Model savedModel = repository.save(model);
        return preparedResponse(savedModel,percentage);

    }

    private void checkAvailability(Integer availability, Integer percentage){
        if (availability < percentage){
            throw new ValueNotAcceptableException("CurrentPercentage",percentage,"It should not grater then: " + availability);
        }
    }

    private ModelDto preparedResponse(Model model,Integer percentage){
        int modelTotal = calculateModelTotal(model, percentage);
        ModelDto response = mapper.convert(model, new ModelDto());
        response.setModelTotal(modelTotal);
        response.setCurrentPercentage(percentage);
        return response;
    }

    private Model findById(Long modelId) {
        Model model = repository.findById(modelId).orElseThrow(()-> new ModelNotFoundException("Model Not found with id: '" + modelId + "'"));
        if (model.getIsDeleted()){
            throw new ModelNotFoundException("Model Not found with id: '" + modelId + "'");
        }
        return model;
    }

    private void checkModelExists(String modelName, Long projectId){
        if (repository.existsByNameAndProjectIdAndIsDeleted(modelName,projectId, false)){

            throw new DuplicateKeyException("Model with name: '" + modelName + "' is already exist");
        }
    }

    @Override
    public void deleteModelByProjectId(Long projectId) {
        List<Model> models = repository.findAllByProjectIdAndModelIsDeleted(projectId,false);
        models.forEach(model -> {
            model.setIsDeleted(true);
            repository.save(model);
        });
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

        Integer currentPercentage = switch (configType) {
            case WEEKLY -> model.getWeeklyPercentage();
            case MONTHLY -> model.getMonthlyPercentage();
            default -> model.getFixedPercentage();
        };
        if (currentPercentage == null) return 0;

        return currentPercentage;
    }

    private int calculateModelTotal(Model model,Integer percentage){
        int target = monthlyTargetService.getCurrentMonthlyTargetByProjectId(model.getProject().getId()).getTarget();
        return (target*percentage)/100;
        }
}

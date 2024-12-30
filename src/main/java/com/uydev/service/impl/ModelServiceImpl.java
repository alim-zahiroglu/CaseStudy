package com.uydev.service.impl;

import com.uydev.dto.ModelDto;
import com.uydev.entity.Model;
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
                .map(model -> mapper.convert(model, new ModelDto()))
                .toList();
    }
}

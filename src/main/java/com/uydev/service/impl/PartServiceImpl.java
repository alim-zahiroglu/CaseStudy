package com.uydev.service.impl;

import com.uydev.dto.PartDto;
import com.uydev.entity.Model;
import com.uydev.entity.Part;
import com.uydev.exception.DuplicateKeyException;
import com.uydev.mapper.MapperUtil;
import com.uydev.repository.PartRepository;
import com.uydev.service.ModelService;
import com.uydev.service.MonthlyTargetService;
import com.uydev.service.PartService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PartServiceImpl implements PartService {
    private final PartRepository repository;
    private final MapperUtil mapper;
    private final ModelService modelService;
    private final MonthlyTargetService monthlyTargetService;

    @Override
    public List<PartDto> findAllParts() {
        List<Part> parts = getAllParts();
        return parts.stream()
                .map(part -> {
                    PartDto partDto = mapper.convert(part, new PartDto());
                    int totalQuantity = calculateQuantityInTotal(part);
                    partDto.setQuantityInTotal(totalQuantity);
                    return partDto;
                })
                .toList();
    }


    @Override
    public List<PartDto> findAllPartsByModelId(Long modelId) {
        List<Part> parts = getAllParts();
        return parts.stream()
                .filter(part -> Objects.equals(part.getModel().getId(), modelId))
                .map(part -> {
                    PartDto partDto = mapper.convert(part, new PartDto());
                    int totalQuantity = calculateQuantityInTotal(part);
                    partDto.setQuantityInTotal(totalQuantity);
                    return partDto;
                })
                .toList();
    }

    @Override
    public PartDto addPart(PartDto newPart, Long modelId) {
        Model model = modelService.findById(modelId);
        checkPartExists(newPart.getName(),modelId);
        Part part = mapper.convert(newPart, new Part());
        part.setModel(model);
        Part savedPart = repository.save(part);
        return prepareResponse(savedPart);
    }

    private void checkPartExists(String name, Long modelId) {
        repository.findByNameAndModelIdAndIsDeleted(name, modelId,false)
                .ifPresent(part -> {
                    throw new DuplicateKeyException("Part with name: '" + name + "' already exists in model id: " + modelId);
                });
    }

    private PartDto prepareResponse(Part savedPart) {
        PartDto partDto = mapper.convert(savedPart, new PartDto());
        int totalQuantity = calculateQuantityInTotal(savedPart);
        partDto.setQuantityInTotal(totalQuantity);
        return partDto;
    }

    private List<Part> getAllParts() {
        return repository.findAllByIsDeleted(false);
    }
    private int calculateQuantityInTotal(Part part) {
        Model model = part.getModel();
        Integer currentPercentage = modelService.findCurrentPercentage(model);
        int modelTotal = modelService.calculateModelTotal(model,currentPercentage);
        return modelTotal * part.getQuantityPerModel();
    }

}

package com.uydev.service;

import com.uydev.dto.PartDto;

import java.util.List;

public interface PartService {
    List<PartDto> findAllParts();

    List<PartDto> findAllPartsByModelId(Long modelId);

    PartDto addPart(PartDto newPart, Long modelId);

    PartDto updatePart(PartDto newPart, Long partId);

    PartDto deletePart(Long partId);

    void deletePartByModelId(Long modelId);
}

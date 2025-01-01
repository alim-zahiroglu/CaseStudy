package com.uydev.service;

import com.uydev.dto.PartDto;

import java.util.List;

public interface PartService {
    List<PartDto> findAllParts();

    List<PartDto> findAllPartsByModelId(Long modelId);
}

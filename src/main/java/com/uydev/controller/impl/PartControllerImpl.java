package com.uydev.controller.impl;

import com.uydev.controller.BaseResponseOk;
import com.uydev.controller.PartController;
import com.uydev.dto.PartDto;
import com.uydev.dto.ResponseWrapper;
import com.uydev.service.PartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequiredArgsConstructor
public class PartControllerImpl extends BaseResponseOk implements PartController {
    private final PartService partService;

    @Override
    public ResponseWrapper<List<PartDto>> findAllParts() {
        return ok(partService.findAllParts(),"All parts are retrieved");
    }

    @Override
    public ResponseWrapper<List<PartDto>> findAllPartsByModelId(Long modelId) {
        return ok(partService.findAllPartsByModelId(modelId),"All parts are retrieved by model id: "+modelId);
    }

    @Override
    public ResponseWrapper<PartDto> addPart(PartDto newPart) {
        return null;
    }

    @Override
    public ResponseWrapper<PartDto> updatePart(PartDto newPart, Long partId) {
        return null;
    }

    @Override
    public ResponseWrapper<PartDto> deletePart(Long partId) {
        return null;
    }
}

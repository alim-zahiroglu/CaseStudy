package com.uydev.controller;

import com.uydev.dto.ModelDto;
import com.uydev.dto.ResponseWrapper;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/model")
public interface ModelController {
    @GetMapping("/list")
    ResponseWrapper<List<ModelDto>> findAllModels();

    @GetMapping("/list/{projectId}")
    ResponseWrapper<List<ModelDto>> listModelByProjectId(@PathVariable Long projectId);

    @PostMapping("/add/{projectId}")
    ResponseWrapper<ModelDto> addModel(@Valid @RequestBody ModelDto newModel, @PathVariable Long projectId);


    @PutMapping("/update/{modelId}")
    ResponseWrapper<ModelDto> updateModel(@Valid @RequestBody ModelDto newModel, @PathVariable Long modelId);
}
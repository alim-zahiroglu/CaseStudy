package com.uydev.controller;

import com.uydev.dto.ModelDto;
import com.uydev.dto.ResponseWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/model")
@Tag(name = "Model API", description = "Operations related to models")
public interface ModelController {

    @GetMapping("/list")
    @Operation(summary = "Get all models")
    ResponseWrapper<List<ModelDto>> findAllModels();

    @GetMapping("/list/{projectId}")
    @Operation(summary = "Get all models by project id")
    ResponseWrapper<List<ModelDto>> listModelByProjectId(@PathVariable Long projectId);

    @GetMapping("/list/{projectId}/{month}")
    @Operation(summary = "Get all models by project id and month")
    ResponseWrapper<List<ModelDto>> listModelByProjectIdAndMonth(@PathVariable Long projectId, @PathVariable String month);

    @PostMapping("/add/{projectId}")
    @Operation(summary = "Add a new model to a project by project id")
    ResponseWrapper<ModelDto> addModel(@Valid @RequestBody ModelDto newModel, @PathVariable Long projectId);


    @PutMapping("/update/{modelId}")
    @Operation(summary = "Update a model by model id")
    ResponseWrapper<ModelDto> updateModel(@Valid @RequestBody ModelDto newModel, @PathVariable Long modelId);

    @DeleteMapping("/delete/{modelId}")
    @Operation(summary = "Delete a model by model id")
    ResponseWrapper<ModelDto> deleteModel(@PathVariable Long modelId);
}
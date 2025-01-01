package com.uydev.controller;

import com.uydev.dto.PartDto;
import com.uydev.dto.ResponseWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/part")
@Tag(name = "Part API", description = "Operations related to parts")
public interface PartController {

    @GetMapping("/list")
    @Operation(summary = "Get all parts")
    ResponseWrapper<List<PartDto>> findAllParts();

    @GetMapping("/list/{modelId}")
    @Operation(summary = "Get all parts by model id")
    ResponseWrapper<List<PartDto>> findAllPartsByModelId(@PathVariable Long modelId);

    @PostMapping("/add/{modelId}")
    @Operation(summary = "Add a new part to a model by model id")
    ResponseWrapper<PartDto> addPart(@Valid @RequestBody PartDto newPart, @PathVariable Long modelId);

    @PutMapping("/update/{partId}")
    @Operation(summary = "Update a part by part id")
    ResponseWrapper<PartDto> updatePart(@Valid @RequestBody PartDto newPart, @PathVariable Long partId);

    @DeleteMapping("/delete/{partId}")
    @Operation(summary = "Delete a part by part id")
    ResponseWrapper<PartDto> deletePart(@PathVariable Long partId);
}

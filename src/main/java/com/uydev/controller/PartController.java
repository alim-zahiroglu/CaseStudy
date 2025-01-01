package com.uydev.controller;

import com.uydev.dto.PartDto;
import com.uydev.dto.ResponseWrapper;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/part")
public interface PartController {

    @GetMapping("/list")
    ResponseWrapper<List<PartDto>> findAllParts();

    @GetMapping("/list/{modelId}")
    ResponseWrapper<List<PartDto>> findAllPartsByModelId(@PathVariable Long modelId);

    @PostMapping("/add/{modelId}")
    ResponseWrapper<PartDto> addPart(@Valid @RequestBody PartDto newPart, @PathVariable Long modelId);

    @PutMapping("/update/{partId}")
    ResponseWrapper<PartDto> updatePart(@Valid @RequestBody PartDto newPart, @PathVariable Long partId);

    @DeleteMapping("/delete/{partId}")
    ResponseWrapper<PartDto> deletePart(@PathVariable Long partId);
}

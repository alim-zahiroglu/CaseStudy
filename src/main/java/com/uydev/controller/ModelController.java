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

    @PostMapping("/add")
    ResponseWrapper<ModelDto> addModel(@Valid @RequestBody ModelDto newModel, @RequestParam Long projectId);
}
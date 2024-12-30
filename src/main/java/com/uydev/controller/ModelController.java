package com.uydev.controller;

import com.uydev.dto.ModelDto;
import com.uydev.dto.ResponseWrapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/api/v1/model")
public interface ModelController {
    @GetMapping("/list")
    ResponseWrapper<List<ModelDto>> findAllModels();
}

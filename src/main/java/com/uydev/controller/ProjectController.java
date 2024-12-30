package com.uydev.controller;

import com.uydev.dto.ProjectDto;
import com.uydev.dto.ResponseWrapper;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/project")
public interface ProjectController {
    @GetMapping("/list")
    ResponseWrapper<List<ProjectDto>> getAllProjects();

    @PostMapping("/create")
    ResponseWrapper<ProjectDto> createProject(@Valid @RequestBody ProjectDto newProject);

    @PutMapping("/update/{projectId}")
    ResponseWrapper<ProjectDto> updateProject(@Valid @RequestBody ProjectDto newProject, @PathVariable Long projectId);

}

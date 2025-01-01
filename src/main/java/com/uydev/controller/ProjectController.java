package com.uydev.controller;

import com.uydev.dto.ProjectDto;
import com.uydev.dto.ResponseWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/project")
@Tag(name = "Project API", description = "Operations related to projects")
public interface ProjectController {
    @GetMapping("/list")
    @Operation(summary = "Get all projects")
    ResponseWrapper<List<ProjectDto>> getAllProjects();

    @PostMapping("/create")
    @Operation(summary = "Create a new project")
    ResponseWrapper<ProjectDto> createProject(@Valid @RequestBody ProjectDto newProject);

    @PutMapping("/update/{projectId}")
    @Operation(summary = "Update a project by project id")
    ResponseWrapper<ProjectDto> updateProject(@Valid @RequestBody ProjectDto newProject, @PathVariable Long projectId);

    @DeleteMapping("/delete/{projectId}")
    @Operation(summary = "Delete a project by project id")
    ResponseWrapper<ProjectDto> deleteProject(@PathVariable Long projectId);

}

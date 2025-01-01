package com.uydev.controller.impl;

import com.uydev.controller.BaseResponseOk;
import com.uydev.controller.ProjectController;
import com.uydev.dto.ProjectDto;
import com.uydev.dto.ResponseWrapper;
import com.uydev.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequiredArgsConstructor
public class ProjectControllerImpl extends BaseResponseOk implements ProjectController {
    private final ProjectService projectService;
    @Override
    public ResponseWrapper<List<ProjectDto>> getAllProjects() {
        return ok(projectService.findAllProjects(), "Projects are retrieved successfully");
    }

    @Override
    public ResponseWrapper<ProjectDto> createProject(ProjectDto newProject) {
        return ok(projectService.createProject(newProject),"Project is created successfully");
    }

    @Override
    public ResponseWrapper<ProjectDto> updateProject(ProjectDto newProject, Long projectId) {
        return ok(projectService.update(newProject, projectId));
    }

    @Override
    public ResponseWrapper<ProjectDto> deleteProject(Long projectId) {
        return ok(projectService.deleteProject(projectId), "Project is deleted successfully");
    }
}

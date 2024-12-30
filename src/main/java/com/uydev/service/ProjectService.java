package com.uydev.service;

import com.uydev.dto.ProjectDto;

import java.util.List;

public interface ProjectService {
    List<ProjectDto> findAllProjects();

    ProjectDto createProject(ProjectDto newProject);
}

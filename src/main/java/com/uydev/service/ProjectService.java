package com.uydev.service;

import com.uydev.dto.ProjectDto;
import com.uydev.entity.Project;

import java.util.List;

public interface ProjectService {
    List<ProjectDto> findAllProjects();

    ProjectDto createProject(ProjectDto newProject);

    ProjectDto update(ProjectDto newProject, Long projectId);

    ProjectDto deleteProject(Long projectId);
    Project findById(Long projectId);

    boolean isProjectExist(Long projectId);

}

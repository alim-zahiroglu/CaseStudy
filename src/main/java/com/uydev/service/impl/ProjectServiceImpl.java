package com.uydev.service.impl;

import com.uydev.dto.MonthlyTargetDto;
import com.uydev.dto.ProjectDto;
import com.uydev.entity.MonthlyTarget;
import com.uydev.entity.Project;
import com.uydev.enums.ConfigType;
import com.uydev.enums.Month;
import com.uydev.exception.DuplicateKeyException;
import com.uydev.exception.ProjectNotFoundException;
import com.uydev.mapper.MapperUtil;
import com.uydev.repository.ProjectRepository;
import com.uydev.service.MonthlyTargetService;
import com.uydev.service.ProjectService;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final MonthlyTargetService monthlyTargetService;
    private final ProjectRepository repository;
    private final MapperUtil mapper;
    @Override
    public List<ProjectDto> findAllProjects() {
        List<Project> projects = repository.findAllByIsDeleted(false);

        return projects.stream()
                .map(project -> {
                            ProjectDto projectDto = mapper.convert(project, new ProjectDto());
                           MonthlyTargetDto currentMonthTarget = monthlyTargetService.getCurrentMonthlyTargetByProjectId(project.getId());
                           if (currentMonthTarget != null){
                               projectDto.setTarget(currentMonthTarget.getTarget());
                               return projectDto;
                           }
                           return projectDto;
                        }
                )
                .toList();
    }

    @Override
    public ProjectDto createProject(ProjectDto newProject) {
        if (repository.existsByNameAndIsDeleted(newProject.getName(),false)){
            throw new DuplicateKeyException("Project already exist with name: '" + newProject.getName() + "'");
        }
        Project project = mapper.convert(newProject, new Project());
        project.setConfigType(ConfigType.valueOf(newProject.getConfigType().toUpperCase()));
        Project savedProject = repository.save(project);

        // create current month target
        createCurrentMothTarget(savedProject, newProject.getTarget());

        ProjectDto response =  mapper.convert(savedProject, new ProjectDto());
        response.setTarget(newProject.getTarget());
        return response;
    }

    private void createCurrentMothTarget(Project savedProject, int target){
        MonthlyTarget monthlyTarget = new MonthlyTarget();
        monthlyTarget.setProject(savedProject);
        monthlyTarget.setMonth(Month.valueOf(LocalDate.now().getMonth().name()));
        monthlyTarget.setTarget(target);
        monthlyTargetService.save(monthlyTarget);
    }

    @Override
    public ProjectDto update(ProjectDto newProject, Long projectId) {
        Project oldProject = findById(projectId);
        Project updatedProject = mapper.convert(newProject, new Project());
        // update config type
        updatedProject.setConfigType(ConfigType.valueOf(newProject.getConfigType().toUpperCase()));
        updatedProject.setId(oldProject.getId());
        Project savedProject = repository.save(updatedProject);

        // update current month target
        updateCurrentMothTarget(savedProject, newProject.getTarget());

        ProjectDto response =  mapper.convert(savedProject, new ProjectDto());
        response.setTarget(newProject.getTarget());
        return response;
    }

    private void updateCurrentMothTarget(Project savedProject, int newTarget) {
    MonthlyTargetDto currentTarget = monthlyTargetService.getCurrentMonthlyTargetByProjectId(savedProject.getId());
    currentTarget.setTarget(newTarget);
    monthlyTargetService.save(mapper.convert(currentTarget,new MonthlyTarget()));

    }


    private Project findById(Long projectId){
        Project project =  repository.findByIdAndIsDeleted(projectId,false);
        if (project == null){
            throw new ProjectNotFoundException(projectId);
        }
        return project;
    }

    private ProjectDto getProjectById(Long projectId){
        return mapper.convert(findById(projectId), new ProjectDto());
    }

}

package com.uydev.service.impl;

import com.uydev.dto.MonthlyTargetDto;
import com.uydev.dto.ProjectDto;
import com.uydev.entity.MonthlyTarget;
import com.uydev.entity.Project;
import com.uydev.enums.ConfigType;
import com.uydev.enums.Month;
import com.uydev.exception.DuplicateKeyException;
import com.uydev.exception.UserNotFoundException;
import com.uydev.mapper.MapperUtil;
import com.uydev.repository.ProjectRepository;
import com.uydev.service.MonthlyTargetService;
import com.uydev.service.ProjectService;
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
        MonthlyTarget monthlyTarget = new MonthlyTarget();
        monthlyTarget.setProject(savedProject);
        monthlyTarget.setMonth(Month.valueOf(LocalDate.now().getMonth().name()));
        monthlyTarget.setTarget(newProject.getTarget());
        monthlyTargetService.save(monthlyTarget);

        return mapper.convert(savedProject, new ProjectDto());
    }

}

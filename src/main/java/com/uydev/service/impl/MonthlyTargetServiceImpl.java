package com.uydev.service.impl;

import com.uydev.dto.MonthlyTargetDto;
import com.uydev.entity.MonthlyTarget;
import com.uydev.enums.Month;
import com.uydev.exception.MonthlyTargetNotFoundException;
import com.uydev.mapper.MapperUtil;
import com.uydev.repository.MonthlyTargetRepository;
import com.uydev.service.MonthlyTargetService;
import com.uydev.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class MonthlyTargetServiceImpl implements MonthlyTargetService {
    private final MonthlyTargetRepository repository;
    private final ProjectService projectService;
    private final MapperUtil mapper;

    public MonthlyTargetServiceImpl(MonthlyTargetRepository repository, @Lazy ProjectService projectService, MapperUtil mapper) {
        this.repository = repository;
        this.projectService = projectService;
        this.mapper = mapper;
    }

    @Override
    public MonthlyTargetDto getCurrentMonthlyTargetByProjectId(Long projectId) {
        Month currentMonth = Month.valueOf(LocalDate.now().getMonth().name());
        MonthlyTarget currentMonthTarget = repository.getMonthlyTargetByProjectIdAndMonth(projectId,currentMonth);
        if (currentMonthTarget == null){
          MonthlyTarget target = new MonthlyTarget();
          target.setTarget(0);
          target.setMonth(currentMonth);
          target.setProject(projectService.findById(projectId));
          repository.save(target);
          return mapper.convert(target, new MonthlyTargetDto());
        }
        return mapper.convert(currentMonthTarget, new MonthlyTargetDto());

    }

    @Override
    public void save(MonthlyTarget monthlyTarget) {
        repository.save(monthlyTarget);
    }
}

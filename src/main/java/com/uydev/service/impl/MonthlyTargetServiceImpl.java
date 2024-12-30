package com.uydev.service.impl;

import com.uydev.dto.MonthlyTargetDto;
import com.uydev.entity.MonthlyTarget;
import com.uydev.enums.Month;
import com.uydev.exception.MonthlyTargetNotFoundException;
import com.uydev.mapper.MapperUtil;
import com.uydev.repository.MonthlyTargetRepository;
import com.uydev.service.MonthlyTargetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class MonthlyTargetServiceImpl implements MonthlyTargetService {
    private final MonthlyTargetRepository repository;
    private final MapperUtil mapper;

    @Override
    public MonthlyTargetDto getCurrentMonthlyTargetByProjectId(Long projectId) {
        Month currentMonth = Month.valueOf(LocalDate.now().getMonth().name());
        MonthlyTarget currentMonthTarget = repository.getMonthlyTargetByProjectIdAndMonth(projectId,currentMonth);
        if (currentMonthTarget ==null){
          return null;
        }
        return mapper.convert(currentMonthTarget, new MonthlyTargetDto());

    }

    @Override
    public void save(MonthlyTarget monthlyTarget) {
        repository.save(monthlyTarget);
    }
}

package com.uydev.service;

import com.uydev.dto.MonthlyTargetDto;
import com.uydev.entity.MonthlyTarget;

public interface MonthlyTargetService {
    MonthlyTargetDto getCurrentMonthlyTargetByProjectId(Long projectId);

    void save(MonthlyTarget monthlyTarget);
}

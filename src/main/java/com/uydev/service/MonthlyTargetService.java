package com.uydev.service;

import ch.qos.logback.core.ConsoleAppender;
import com.uydev.dto.MonthlyTargetDto;
import com.uydev.entity.MonthlyTarget;
import com.uydev.enums.Month;

public interface MonthlyTargetService {
    MonthlyTargetDto getCurrentMonthlyTargetByProjectId(Long projectId);

    void save(MonthlyTarget monthlyTarget);

    MonthlyTargetDto findMonthlyTargetByProjectIdAndMonth(Long projectId, Month monthEnum);
}

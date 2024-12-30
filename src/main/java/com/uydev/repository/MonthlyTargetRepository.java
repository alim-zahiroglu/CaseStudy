package com.uydev.repository;

import com.uydev.entity.MonthlyTarget;
import com.uydev.enums.Month;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonthlyTargetRepository extends JpaRepository<MonthlyTarget, Long> {

    MonthlyTarget getMonthlyTargetByProjectIdAndMonth(Long projectId, Month month);
}

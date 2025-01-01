// src/main/java/com/uydev/repository/LogEntryRepository.java
package com.uydev.repository;

import com.uydev.entity.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogEntryRepository extends JpaRepository<LogEntity, Long> {
}
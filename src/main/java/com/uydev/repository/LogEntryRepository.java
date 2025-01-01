
package com.uydev.repository;

import com.uydev.entity.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LogEntryRepository extends JpaRepository<LogEntity, Long> {
    List<LogEntity> findAllByEntity(String entity);
}
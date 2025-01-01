package com.uydev.service.impl;

import com.uydev.entity.LogEntity;
import com.uydev.repository.LogEntryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LogService {
    private final LogEntryRepository Repository;

    public LogService(LogEntryRepository logEntryRepository) {
        this.Repository = logEntryRepository;
    }

    public void log(String action, String entity, Long entityId, boolean operationStatus) {
        LogEntity logEntry = new LogEntity();
        logEntry.setAction(action);
        logEntry.setEntity(entity);
        logEntry.setEntityId(entityId);
        logEntry.setTimestamp(LocalDateTime.now());
        logEntry.setOperationStatus(operationStatus);
        Repository.save(logEntry);
    }
    public void log(String action, String entity, Long entityId) {
        LogEntity logEntry = new LogEntity();
        logEntry.setAction(action);
        logEntry.setEntity(entity);
        logEntry.setEntityId(entityId);
        logEntry.setTimestamp(LocalDateTime.now());
        Repository.save(logEntry);
    }

    public List<LogEntity> findAllLogsInPartService() {
        return Repository.findAllByEntity("Part");
    }

    public List<LogEntity> findAllLogsInModelService() {
        return Repository.findAllByEntity("Model");
    }

    public List<LogEntity> findAllSuccessLogsInModelService() {
        return Repository.findAllByEntityAndOperationStatus("Model", true);
    }

    public List<LogEntity> findAllSuccessLogsInPartService() {
        return Repository.findAllByEntityAndOperationStatus("Part", true);
    }
}
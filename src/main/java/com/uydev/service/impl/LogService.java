package com.uydev.service.impl;

import com.uydev.entity.LogEntity;
import com.uydev.repository.LogEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LogService {
    private final LogEntryRepository logEntryRepository;

    public LogService(LogEntryRepository logEntryRepository) {
        this.logEntryRepository = logEntryRepository;
    }

    public void log(String action, String entity, Long entityId, boolean operationStatus) {
        LogEntity logEntry = new LogEntity();
        logEntry.setAction(action);
        logEntry.setEntity(entity);
        logEntry.setEntityId(entityId);
        logEntry.setTimestamp(LocalDateTime.now());
        logEntry.setOperationStatus(operationStatus);
        logEntryRepository.save(logEntry);
    }
    public void log(String action, String entity, Long entityId) {
        LogEntity logEntry = new LogEntity();
        logEntry.setAction(action);
        logEntry.setEntity(entity);
        logEntry.setEntityId(entityId);
        logEntry.setTimestamp(LocalDateTime.now());
        logEntryRepository.save(logEntry);
    }

    public List<LogEntity> findAllLogsInPartService() {
        return logEntryRepository.findAllByEntity("Part");
    }

    public List<LogEntity> findAllLogsInModelService() {
        return logEntryRepository.findAllByEntity("Model");
    }
}
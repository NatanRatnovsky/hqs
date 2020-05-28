package com.nratnovsky.hqs.services;

import com.nratnovsky.hqs.models.SystemLogger;
import com.nratnovsky.hqs.repository.SystemLoggerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class SystemLoggerService {


    private final SystemLoggerRepo loggerRepo;

    public SystemLoggerService(SystemLoggerRepo loggerRepo) {
        this.loggerRepo = loggerRepo;
    }

    public List<SystemLogger> getAllLogs( ) {
        return loggerRepo.getAllLogDesc();
    }
}


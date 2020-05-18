package com.nratnovsky.hqs.services;

import com.nratnovsky.hqs.repository.SystemLoggerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class SystemLoggerService {

    @Autowired
    SystemLoggerRepo loggerRepo;

    public void getAllLogs( Model model) {
        model.addAttribute("logs", loggerRepo.getAllLogDesc());
    }
}


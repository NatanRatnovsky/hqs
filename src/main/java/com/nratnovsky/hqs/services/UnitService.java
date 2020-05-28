package com.nratnovsky.hqs.services;

import com.nratnovsky.hqs.models.SystemLogger;
import com.nratnovsky.hqs.models.Unit;
import com.nratnovsky.hqs.models.User;
import com.nratnovsky.hqs.models.enums.LogType;
import com.nratnovsky.hqs.repository.SystemLoggerRepo;
import com.nratnovsky.hqs.repository.UnitRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Date;

@Service
public class UnitService {
    @Autowired
    private UnitRepo unitRepo;

    @Autowired
    private SystemLoggerRepo loggerRepo;

    public Model getAllUnits(Model model) {
        if (unitRepo.findAll().isEmpty()) {
            model.addAttribute("message", "אין יחידות במערכת");
            return model;
        }
        model.addAttribute("units", unitRepo.findAll());
        return model;
    }

}

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

    public Model addUnit(User user, String unitName, Model model) {
        if (unitRepo.findByName(unitName).isPresent()) {
            model.addAttribute("message", "יחידה קיימת במערכת");
            model.addAttribute("units", unitRepo.findAll());
            loggerRepo.save(new SystemLogger(new Date(), user.getUsername(), LogType.HR,
                    "משתמש ניסה להוסיף יחידה חדשה שקיימת במערכת " + unitName));
            return model;
        }
        Unit unit = new Unit(unitName);
        unitRepo.save(unit);
        model.addAttribute("messageSuccess", "יחידה הוספה בהצלחה");
        model.addAttribute("units", unitRepo.findAll());
        loggerRepo.save(new SystemLogger(new Date(), user.getUsername(), LogType.HR,
                "משתמש הוסיף יחידה חדשה " + unitName));
        return model;
    }

    public Model editUnit (User user, String unitName, long unitId, Model model) {
        if (unitRepo.findByName(unitName).isPresent()) {
            model.addAttribute("message", "יחידה קיימת במערכת");
            model.addAttribute("units", unitRepo.findAll());
            loggerRepo.save(new SystemLogger(new Date(), user.getUsername(), LogType.HR,
                    "משתמש ניסה להחליף שם יחידה לשם " + unitName +" שקיים במערכת" ));
            return model;
        }
        Unit unit = unitRepo.findById(unitId).get();
        unit.setName(unitName);
        unitRepo.save(unit);
        model.addAttribute("messageSuccess", "שם של יחידה הושתנה בהצלחה");
        model.addAttribute("units", unitRepo.findAll());
        loggerRepo.save(new SystemLogger(new Date(), user.getUsername(), LogType.HR,
                "משתמש היחליף שם יחידה לשם חדש " + unitName));
        return model;

    }

    public Model deleteUnit(User user, long unitId, Model model) {
        if (unitRepo.findById(unitId).isPresent()) {
            unitRepo.deleteById(unitId);
            model.addAttribute("messageSuccess", "יחידה הוסרה בהצלחה");
            model.addAttribute("units", unitRepo.findAll());

            loggerRepo.save(new SystemLogger(new Date(), user.getUsername(), LogType.HR,
                    "משתמש מחק יחידה"));
            return model;
        }
        model.addAttribute("message", "היחידה לא קיימת במערכת");
        model.addAttribute("units", unitRepo.findAll());
        loggerRepo.save(new SystemLogger(new Date(), user.getUsername(), LogType.HR,
                "משתמש ניסה למחוק יחידה שלא קיימת במערכת "));
        return model;
    }
}

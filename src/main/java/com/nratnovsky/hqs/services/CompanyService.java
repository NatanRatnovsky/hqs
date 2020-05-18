package com.nratnovsky.hqs.services;

import com.nratnovsky.hqs.models.Company;
import com.nratnovsky.hqs.models.SystemLogger;
import com.nratnovsky.hqs.models.User;
import com.nratnovsky.hqs.models.enums.LogType;
import com.nratnovsky.hqs.repository.CompanyRepo;
import com.nratnovsky.hqs.repository.JobRepo;
import com.nratnovsky.hqs.repository.SystemLoggerRepo;
import com.nratnovsky.hqs.repository.UnitRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Date;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepo companyRepo;

    @Autowired
    private JobRepo jobRepo;

    @Autowired
    private UnitRepo unitRepo;

    @Autowired
    private SystemLoggerRepo loggerRepo;


    public void returnModelJobCompanyUnit(Model model) {
        model.addAttribute("jobs", jobRepo.findAll());
        model.addAttribute("companies", companyRepo.findAll());
        model.addAttribute("units", unitRepo.findAll());
    }

    public void returnModelCompaniesUnit(Model model) {
        model.addAttribute("companies", companyRepo.findAll());
        model.addAttribute("units", unitRepo.findAll());
    }

    public Model getAllCompanies(Model model) {
        returnModelJobCompanyUnit(model);
        return model;
    }

    public Model addCompany(User user, long unitId, String name, Model model) {
        if (companyRepo.findCompanyByNameAndUnitId(name, unitId).isPresent()) {
            model.addAttribute("message", "הפלוגה קיימת ביחידה");
            returnModelJobCompanyUnit(model);
            loggerRepo.save(new SystemLogger(new Date(), user.getUsername(), LogType.HR,
                    "משתמש ניסה להוסיף פלוגה " + name +" שקיים ביחידה" ));
            return model;
        }
        companyRepo.save(new Company(name, unitRepo.findById(unitId).get()));
        model.addAttribute("messageSuccess", "גדוד הוסף בהצלחה");
        returnModelJobCompanyUnit(model);
        loggerRepo.save(new SystemLogger(new Date(), user.getUsername(), LogType.HR,
                 "משתמש הוסיף פלוגה חדשה " + name));
        return model;
    }

    public void editCompany(User user, String name, long id, Model model) {
        if (companyRepo.findById(id).isPresent()) {
            Company company = companyRepo.findById(id).get();
            company.setName(name);
            companyRepo.save(company);
            returnModelJobCompanyUnit(model);
            model.addAttribute("messageSuccess", "שם הפלוגה הוחף בהצלחה");
            loggerRepo.save(new SystemLogger(new Date(), user.getUsername(), LogType.HR,
                    "משתמש היחלף שם של פלוגה"));
            return;
        }
        returnModelJobCompanyUnit(model);
        model.addAttribute("message", "הפלוגה לא נימצת במערכת");
        loggerRepo.save(new SystemLogger(new Date(), user.getUsername(), LogType.HR,
                "ניסה להחלף שם של פלוגה שלא קיימת"));
    }
}

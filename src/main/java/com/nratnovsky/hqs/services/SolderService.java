package com.nratnovsky.hqs.services;

import com.nratnovsky.hqs.models.Company;
import com.nratnovsky.hqs.models.Solder;
import com.nratnovsky.hqs.models.User;
import com.nratnovsky.hqs.repository.CompanyRepo;
import com.nratnovsky.hqs.repository.SolderRepo;
import com.nratnovsky.hqs.repository.SystemLoggerRepo;
import com.nratnovsky.hqs.repository.UnitRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class SolderService {

    @Autowired
    private SolderRepo solderRepo;

    @Autowired
    private SystemLoggerRepo loggerRepo;

    @Autowired
    private UnitRepo unitRepo;

    @Autowired
    private CompanyRepo companyRepo;

    private void returnSolderUnitCompany(Model model) {
        model.addAttribute("solders", solderRepo.findAll());
        model.addAttribute("units", unitRepo.findAll());
        model.addAttribute("companies", companyRepo.findAll());

    }

    public void findAll(Model model) {
        if (solderRepo.findAll().isEmpty()) {
            model.addAttribute("message", "אין חיילים במערכת");
        } else {
            returnSolderUnitCompany(model);
        }
    }

    public void addSolder(User user, String solderId, String firstName, String lastName,
                          String phone, Company company, Model model) {
        if (solderRepo.findBySolderId(solderId).isPresent()) {
            returnSolderUnitCompany(model);
            model.addAttribute("message", "חייל עם המספר האישי קיים במערכת");
            return;
        }
        if (solderRepo.findByPhone(phone).isPresent()) {
            returnSolderUnitCompany(model);
            model.addAttribute("message", "חייל עם המספר הטלפון קיים במערכת");
            return;
        }
        Solder solder = new Solder(solderId, firstName, lastName, phone, company);
        solderRepo.save(solder);
    }
}

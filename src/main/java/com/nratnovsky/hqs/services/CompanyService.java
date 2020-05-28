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



}

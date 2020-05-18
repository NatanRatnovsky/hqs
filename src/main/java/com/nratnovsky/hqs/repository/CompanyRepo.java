package com.nratnovsky.hqs.repository;

import com.nratnovsky.hqs.models.Job;
import com.nratnovsky.hqs.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface CompanyRepo extends JpaRepository<Company, Long> {
    Optional<Company> findByName(String name);

    Set<Company> findByJobNames(Job jobNames);

    Optional<Company> findCompanyByNameAndUnitId(String name, Long id);
}

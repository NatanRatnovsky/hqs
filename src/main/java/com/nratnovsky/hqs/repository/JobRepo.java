package com.nratnovsky.hqs.repository;

import com.nratnovsky.hqs.models.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JobRepo extends JpaRepository<Job, Long> {
    Optional<Job> findByJobName(String jobName);

    List<Job> findByCompanyId(long companyId);

    Optional<Job> findByCompanyIdAndJobName(Long companyId, String jobName);
}

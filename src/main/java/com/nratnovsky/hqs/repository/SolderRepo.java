package com.nratnovsky.hqs.repository;

import com.nratnovsky.hqs.models.Solder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SolderRepo extends JpaRepository<Solder, Long> {
    Solder findByLastName(String username);

    Optional<Solder> findByPhone(String phone);

    Optional<Solder> findBySolderId(String solderId);
}

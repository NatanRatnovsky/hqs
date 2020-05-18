package com.nratnovsky.hqs.repository;

import com.nratnovsky.hqs.models.Unit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UnitRepo extends JpaRepository<Unit, Long> {
    Optional<Unit> findByName(String name);
}

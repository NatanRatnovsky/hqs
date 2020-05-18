package com.nratnovsky.hqs.repository;

import com.nratnovsky.hqs.models.SystemLogger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SystemLoggerRepo extends JpaRepository<SystemLogger, Long> {
    @Query("SELECT l FROM SystemLogger l ORDER BY l.id DESC")
    List<SystemLogger> getAllLogDesc();
}

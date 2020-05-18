package com.nratnovsky.hqs.repository;

import com.nratnovsky.hqs.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

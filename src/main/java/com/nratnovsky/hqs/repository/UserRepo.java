package com.nratnovsky.hqs.repository;

import com.nratnovsky.hqs.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);

    List<User> findByUsernameStartsWith(String username);
}

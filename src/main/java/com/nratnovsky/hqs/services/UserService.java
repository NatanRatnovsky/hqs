package com.nratnovsky.hqs.services;

import com.nratnovsky.hqs.models.User;
import com.nratnovsky.hqs.models.enums.Role;
import com.nratnovsky.hqs.repository.SystemLoggerRepo;
import com.nratnovsky.hqs.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private SystemLoggerRepo loggerRepo;

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    public boolean addUser(User user) {
        User userFromDb = userRepo.findByUsername(user.getUsername());

        if (userFromDb != null) {
            return false;
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        if (userRepo.findAll().isEmpty()) {
            user.setRoles(Collections.singleton(Role.ADMIN));
        }
        System.out.println(user);
        userRepo.save(user);

        return true;
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public void saveUser(User user) {
        userRepo.save(user);
    }

    public Map<String, Object> changePassword(String newPassword, String confirmedPassword, Map<String, Object> model, User user) {
        if (newPassword.equals(confirmedPassword)) {
            if (newPassword.length() < 6) {
                model.put("message", "Password to short! Password need to be 6 character or more!");
                model.put("user", user);
                return model;
            }
        } else {
            model.put("message", "Confirmed password not match!");
            model.put("user", user);
            return model;
        }


        user.setPassword(newPassword);
        userRepo.save(user);
        model.put("message", "Password changed!");
        model.put("user", user);
        return model;

    }

    public void deleteUser(User user) {
        userRepo.delete(user);
    }

    public List<User> findByUsername(String username) {
        return userRepo.findByUsernameStartsWith(username);
    }

    public Optional<User> findById(Long id) {
        return userRepo.findById(id);
    }
}

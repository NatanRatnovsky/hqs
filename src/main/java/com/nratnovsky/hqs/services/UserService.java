package com.nratnovsky.hqs.services;

import com.nratnovsky.hqs.models.SystemLogger;
import com.nratnovsky.hqs.models.User;
import com.nratnovsky.hqs.models.enums.LogType;
import com.nratnovsky.hqs.models.enums.Role;
import com.nratnovsky.hqs.repository.SystemLoggerRepo;
import com.nratnovsky.hqs.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    private final UserRepo userRepo;

    @Autowired
    private SystemLoggerRepo loggerRepo;



    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    private String authUser () {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return  authentication.getName();
    }

    public boolean addUser(User user) {
        User userFromDb = userRepo.findByUsername(user.getUsername());

        if (userFromDb != null) {
            loggerRepo.save(new SystemLogger(LocalDateTime.now(),
                    authUser(), LogType.HR, "ניסה להסיף משתמש שקיים במערכת"));
            return false;
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        if (userRepo.findAll().isEmpty()) {
            loggerRepo.save(new SystemLogger(LocalDateTime.now(),
                    authUser(), LogType.HR, "הוסף מנהל של מערכת"));
            user.setRoles(Collections.singleton(Role.ADMIN));
        }
        loggerRepo.save(new SystemLogger(LocalDateTime.now(),
                authUser(), LogType.HR, "הוסף משתמש חדש"));
        userRepo.save(user);

        return true;
    }

    public List<User> findAll() {
        loggerRepo.save(new SystemLogger(LocalDateTime.now(),
                authUser(), LogType.HR, "קיבל רשימת משתמשים"));
        return userRepo.findAll();
    }

    public void saveUser(User user) {
        User userFromDb = userRepo.findByUsername(user.getUsername());
        if (user.getId() == null) {
            if (userFromDb == null) {
                userRepo.save(user);
                loggerRepo.save(new SystemLogger(LocalDateTime.now(),
                        authUser(), LogType.HR, "שינתה משתמש " + user.getUsername()));
            } else {
                loggerRepo.save(new SystemLogger(LocalDateTime.now(),
                        authUser(), LogType.HR, "ניסה להוסיף משתמש קיים " + user.getUsername()));
                return;
            }
            addUser(user);
        }

    }



    public void deleteUser(User user) {
        userRepo.delete(user);
        loggerRepo.save(new SystemLogger(LocalDateTime.now(),
                authUser(), LogType.HR, "נמחק משתמש " + user.getUsername()));
    }

    public List<User> findByUsername(String username) {
        loggerRepo.save(new SystemLogger(LocalDateTime.now(),
                authUser(), LogType.HR, "היה חיפוס לפי מספר אישי " + username));
        return userRepo.findByUsernameStartsWith(username);
    }

    public Optional<User> findById(Long id) {
        return userRepo.findById(id);
    }
}

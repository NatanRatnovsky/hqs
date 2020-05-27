package com.nratnovsky.hqs;

import com.nratnovsky.hqs.models.User;
import com.nratnovsky.hqs.models.enums.Role;
import com.nratnovsky.hqs.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

@SpringBootApplication(exclude = ErrorMvcAutoConfiguration.class)
public class HeadQuartersSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(HeadQuartersSystemApplication.class, args);

    }

}

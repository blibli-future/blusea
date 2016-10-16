package com.blibli.future.configuration;

import com.blibli.future.model.User;
import com.blibli.future.model.UserRole;
import com.blibli.future.repository.UserRepository;
import com.blibli.future.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by dhika on 08/10/2016.
 */
@Component
public class DatabaseSeeder {
    @Autowired
    UserRepository userRepo;
    @Autowired
    UserRoleRepository userRoleRepo;

    @PostConstruct
    private void initTestData() {
        User u = new User();
        u.setFullName("Adhika Setya Pramudita");
        u.setNickName("Dhika");
        u.setEmail("hello@adhikasetyap.me");
        u.setPassword("1234");
        userRepo.save(u);
        UserRole r = new UserRole();
        r.setEmail("hello@adhikasetyap.me");
        r.setRole("ROLE_USER");
        userRoleRepo.save(r);
        r = new UserRole();
        r.setEmail("hello@adhikasetyap.me");
        r.setRole("ROLE_ADMIN");
        userRoleRepo.save(r);
    }
}

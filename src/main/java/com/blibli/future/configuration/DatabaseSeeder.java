package com.blibli.future.configuration;

import com.blibli.future.model.Catering;
import com.blibli.future.model.User;
import com.blibli.future.model.UserRole;
import com.blibli.future.repository.CateringRepository;
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
    @Autowired
    CateringRepository cateringRepository;

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

        u = new User();
        r = new UserRole();
        u.setFullName("Ahmad Widardi");
        u.setNickName("Ardi");
        u.setEmail("awidardi@gmail.com");
        u.setPassword("12345");
        userRepo.save(u);
        r.setEmail("awidardi@gmail.com");
        r.setRole("ROLE_ADMIN");
        r.setRole("ROLE_CATERING");
        userRoleRepo.save(r);

        Catering c = new Catering();
        c.setUsername("x");
        c.setCateringName("x");
        c.setEmail("x");
        c.setPassword("x");
        c.setAddress("x");
        c.setDescription("x");
        c.setPhoneNumber("x");
        c.setDp("50");
        cateringRepository.save(c);
    }
}

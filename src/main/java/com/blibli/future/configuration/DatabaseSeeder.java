package com.blibli.future.configuration;

import com.blibli.future.model.Catering;
import com.blibli.future.model.Costumer;
import com.blibli.future.model.UserRole;
import com.blibli.future.repository.CateringRepository;
import com.blibli.future.repository.CostumerRepository;
import com.blibli.future.repository.UserRoleRepository;
import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.company.Company;
import io.codearte.jfairy.producer.person.Person;
import io.codearte.jfairy.producer.text.TextProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import static io.codearte.jfairy.producer.person.PersonProperties.withCompany;

/**
 * Created by dhika on 08/10/2016.
 */
@Component
public class DatabaseSeeder {
    @Autowired
    CostumerRepository costumerRepository;
    @Autowired
    UserRoleRepository userRoleRepo;
    @Autowired
    CateringRepository cateringRepository;

    @PostConstruct
    private void initTestData() {
        Costumer u = new Costumer();
        u.setFullName("Adhika Setya Pramudita");
        u.setNickName("Dhika");
        u.setEmail("hello@adhikasetyap.me");
        u.setPassword("1234");
        costumerRepository.save(u);
        UserRole r = new UserRole();
        r.setEmail("hello@adhikasetyap.me");
        r.setRole("ROLE_USER");
        userRoleRepo.save(r);
        r = new UserRole();
        r.setEmail("hello@adhikasetyap.me");
        r.setRole("ROLE_ADMIN");
        userRoleRepo.save(r);

        u = new Costumer();
        r = new UserRole();
        u.setFullName("Ahmad Widardi");
        u.setNickName("Ardi");
        u.setEmail("awidardi@gmail.com");
        u.setPassword("12345");
        costumerRepository.save(u);
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

        // automatic data seeder
        Fairy fairy = Fairy.create();
        TextProducer texter = fairy.textProducer();
        for (int i=0; i<30; i++) {
            Company company = fairy.company();
            Person person = fairy.person(withCompany(company));

            c = new Catering();
            c.setUsername(person.username());
            c.setCateringName(company.name());
            c.setEmail(person.companyEmail());
            c.setPassword("secret");
            c.setAddress(person.getAddress().toString());
            c.setDescription(texter.paragraph(5));
            c.setPhoneNumber(person.telephoneNumber());
            c.setDp("50");
            cateringRepository.save(c);
        }

    }
}

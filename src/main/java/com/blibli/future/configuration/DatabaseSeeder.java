package com.blibli.future.configuration;

import com.blibli.future.model.Catering;
import com.blibli.future.model.Customer;
import com.blibli.future.model.Product;
import com.blibli.future.model.UserRole;
import com.blibli.future.repository.CateringRepository;
import com.blibli.future.repository.CustomerRepository;
import com.blibli.future.repository.ProductRepository;
import com.blibli.future.repository.UserRoleRepository;
import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.company.Company;
import io.codearte.jfairy.producer.person.Person;
import io.codearte.jfairy.producer.text.TextProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import java.util.concurrent.ThreadLocalRandom;

import static io.codearte.jfairy.producer.person.PersonProperties.withCompany;

/**
 * Created by dhika on 08/10/2016.
 */
@Component
public class DatabaseSeeder {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    UserRoleRepository userRoleRepo;
    @Autowired
    CateringRepository cateringRepository;
    @Autowired
    ProductRepository productRepository;

    @PostConstruct
    private void initTestData() {
        Customer u = new Customer();
        u.setFullName("Adhika Setya Pramudita");
        u.setUsername("Dhika");
        u.setEmail("hello@adhikasetyap.me");
        u.setPassword("1234");
        customerRepository.save(u);
        UserRole r = new UserRole();
        r.setUsername("Dhika");
        r.setRole("ROLE_USER");
        userRoleRepo.save(r);
        r = new UserRole();
        r.setUsername("Dhika");
        r.setRole("ROLE_ADMIN");
        userRoleRepo.save(r);

        u = new Customer();
        r = new UserRole();
        u.setFullName("Ahmad Widardi");
        u.setUsername("Ardi");
        u.setEmail("awidardi@gmail.com");
        u.setPassword("12345");
        customerRepository.save(u);
        r.setUsername("Ardi");
        r.setRole("ROLE_ADMIN");
        r.setRole("ROLE_CATERING");
        userRoleRepo.save(r);

        u = new Customer();
        r = new UserRole();
        u.setFullName("user");
        u.setUsername("user");
        u.setPassword("12345");
        customerRepository.save(u);
        r.setUsername("user");
        r.setRole("ROLE_ADMIN");
        r.setRole("ROLE_USER");
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

            for (int j=0; j<10; j++) {
                Product p = new Product();
                p.setName("Produk " + j);
                p.setDescription("Deskripsi dari produk " + j);
                p.setPhoto("https://dummyimage.com/200x200/000/fff");
                p.setCatering(c);
                int randomPrice = ThreadLocalRandom.current().nextInt(10, 101) * 100;
                p.setPrice(randomPrice);
                productRepository.save(p);
            }
        }

    }
}

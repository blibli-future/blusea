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
        u.setNickName("Dhika");
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
        r.setRole("ROLE_CUSTOMER");
        userRoleRepo.save(r);

        Catering c = new Catering();
        r = new UserRole();
        c.setCateringName("Imperia Catering Jakarta");
        c.setUsername("ArdiCatering");
        c.setEmail("cateringa@gmail.com");
        c.setPassword("12345");
        c.setDescription("Building trust and relationship with you for every special events you have.</br>" +
                "Our mission is to provide you with fresh food from good sourced ingredients and quality service you need with the best value.");
        c.setAddress("Jl. Pasar Inpres No. 1, Radio Dalam, Kebayoran Baru, RT.2/RW.14, Gandaria Utara, Kby. Baru, Kota Jakarta Selatan, Daerah Khusus Ibukota Jakarta 12510");
        c.setDp("70");
        c.setPhoto("/assets/image/dummy/file-4.jpg");
        c.setPhoneNumber("085642196188");
        cateringRepository.save(c);
        r.setUsername("ArdiCatering");
        r.setRole("ROLE_ADMIN");
        r.setRole("ROLE_CATERING");
        userRoleRepo.save(r);

        u = new Customer();
        r = new UserRole();
        u.setFullName("Ahmad Widardi");
        u.setNickName("Ardi");
        u.setUsername("ArdiCustomer");
        u.setEmail("customera@gmail.com");
        u.setPassword("12345");
        customerRepository.save(u);
        r.setUsername("ArdiCustomer");
        r.setRole("ROLE_ADMIN");
        r.setRole("ROLE_CUSTOMER");
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


        c = new Catering();
        c.setUsername("prima.catering");
        c.setCateringName("Prima Catering");
        c.setEmail("debby@prima-catering.com");
        c.setPassword("1234");
        c.setAddress("Jl. Adi Sucipto");
        c.setDescription("Prima Catering, melalui pengalaman selama 15 tahun telah menjadi “partner of choice” bagi berbagai perusahaan terkemuka di Jakarta. \n" +
                "\n" +
                "<br>T: +622145841573 <br> HP: +628111333800 (Marketing) <br> E: debby@prima-catering.com");
        c.setPhoneNumber("+622145841573");
        c.setPhoto("http://static1.squarespace.com/static/52414d6fe4b00ee0badffc5d/t/52fa2817e4b0a0539a45c733/1477546613639/?format=1500w");
        c.setDp("50");
        cateringRepository.save(c);
        r = new UserRole();
        r.setUsername("prima.catering");
        r.setRole("ROLE_CATERING");
        userRoleRepo.save(r);

        // Prima Catering products
        Product p = new Product();
        p.setName("Roti tawar");
        p.setPhoto("https://upload.wikimedia.org/wikipedia/commons/b/b3/Various_grains.jpg");
        p.setDescription("Roti dengan gandum utuh dan organik");
        p.setCatering(c);
        p.setPrice("1-3000|10-2800|100-2500|1000-2000");
        productRepository.save(p);

        p = new Product();
        p.setName("Kacang gurih");
        p.setPhoto("https://upload.wikimedia.org/wikipedia/commons/f/f1/Snack_kacang_kedelai.JPG");
        p.setDescription("Kacang tanah yang digoreng dengan bumbu garam spesial.");
        p.setCatering(c);
        p.setPrice(2000);
        productRepository.save(p);

        p = new Product();
        p.setName("Tempura telur");
        p.setPhoto("https://upload.wikimedia.org/wikipedia/commons/4/46/Preety_much_sums_up_Osaka_food_culture_(4127987298).jpg");
        p.setDescription("Tempura yang digoreng deepfried dengan topping telur dadar dari ayam kampung.");
        p.setCatering(c);
        p.setPrice(5000);
        productRepository.save(p);


        // automatic data seeder
        Fairy fairy = Fairy.create();
        TextProducer texter = fairy.textProducer();
        int randomNumber;
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
            randomNumber = ThreadLocalRandom.current().nextInt(1, 101);
            c.setPhoto("/assets/image/dummy/file-" + randomNumber + ".jpg");
            cateringRepository.save(c);

            for (int j=0; j<10; j++) {
                p = new Product();
                p.setName("Produk " + j);
                p.setDescription("Deskripsi dari produk " + j);
                randomNumber = ThreadLocalRandom.current().nextInt(1, 101);
                p.setPhoto("/assets/image/dummy/file-" + randomNumber + ".jpg");
                p.setCatering(c);
                int randomPrice = ThreadLocalRandom.current().nextInt(10, 101) * 100;
                p.setPrice(randomPrice);
                productRepository.save(p);
            }
        }

    }
}

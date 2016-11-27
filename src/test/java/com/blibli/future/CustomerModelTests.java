package com.blibli.future;

import com.blibli.future.model.Customer;
import com.blibli.future.repository.CustomerRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

/**
 * Created by dhika on 14/11/2016.
 */
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@EnableAutoConfiguration
@ContextConfiguration
public class CustomerModelTests extends AbstractTests {
    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testCreate() {
        Customer c = new Customer();
        c.setUsername("mi6_agent");
        c.setFullName("Johny English");
        c.setNickName("John");
        c.setEmail("johny_mi6@gmail.com");
        customerRepository.save(c);

        Customer testCustomer = customerRepository.findByUsername("mi6_agent");
        Assert.assertNotNull(testCustomer);
        Assert.assertEquals("Johny English", testCustomer.getFullName());
        Assert.assertEquals("John", testCustomer.getNickName());
        Assert.assertEquals("johny_mi6@gmail.com", testCustomer.getEmail());
    }
}

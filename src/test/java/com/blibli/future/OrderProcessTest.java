package com.blibli.future;

import com.blibli.future.model.*;
import com.blibli.future.repository.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

/**
 * Created by dhika on 26/11/2016.
 */
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@EnableAutoConfiguration
@ContextConfiguration
public class OrderProcessTest extends AbstractTests {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CateringRepository cateringRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private ProductRepository productRepository;

    private Customer customer;
    private Catering catering;
    private Product product1;
    private Product product2;
    private Order order;

    @Before
    public void setup()
    {
        customer = new Customer();
        customer.setEmail("john.doe@gmail.com");
        customer.setNickName("John");
        customer.setUsername("JohnD");
        customerRepository.save(customer);

        // TODO write test for catering
        catering = new Catering();
        catering.setEmail("order@foodbank.com");
        catering.setCateringName("Foodbank Catering Company");
        catering.setDp("50");
        catering.setPhoneNumber("081234567890");
        cateringRepository.save(catering);

        // TODO write test for product
        Product product = new Product();
        product.setName("Ayam Bakar");
        product.setCatering(catering);
        productRepository.save(product);
        product1 = product;

        product = new Product();
        product.setName("Bakso Kuah");
        product.setCatering(catering);
        productRepository.save(product);
        product2 = product;

        order = new Order();
        order.setCustomer(customer);
        orderRepository.save(order);

        OrderDetail orderDetail = new OrderDetail(order, product1, 10000, 40);
        orderDetailRepository.save(orderDetail);
    }

    @Test
    public void shouldCreateOrder()
    {
        order = orderRepository.findByCustomerEmail("john.doe@gmail.com");
        Assert.assertNotNull(order);
    }

    @Test
    public void canAddDetailOrder()
    {
        OrderDetail orderDetail = new OrderDetail(order, product1, 10000, 40);
        orderDetailRepository.save(orderDetail);

        orderDetail = new OrderDetail(order, product2, 42000, 40);
        orderDetailRepository.save(orderDetail);

        order = orderRepository.findByCustomerEmail("john.doe@gmail.com");
        Assert.assertEquals(3, order.getOrderDetails().size());
    }

    @Test
    public void canFindByEmailAndStatus() {
        order = orderRepository.findByCustomerEmailAndStatus("john.doe@gmail.com", Order.ORDER_STATUS_CART);
        Assert.assertNotNull(order);
    }
}

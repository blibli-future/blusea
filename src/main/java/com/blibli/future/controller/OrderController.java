package com.blibli.future.controller;

import com.blibli.future.model.*;
import com.blibli.future.repository.CateringRepository;
import com.blibli.future.repository.OrderDetailRepository;
import com.blibli.future.repository.OrderRepository;
import com.blibli.future.repository.ProductRepository;
import com.blibli.future.utility.Helper;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;

/**
 * Created by ARDI on 11/2/2016.
 */
@Controller
public class OrderController{
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    CateringRepository cateringRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrderDetailRepository orderDetailRepository;
    @Autowired
    Helper helper;

    @RequestMapping(value = "/my-user/order/new", method = RequestMethod.POST)
    public String createNewOrder(HttpServletRequest request)
    {
        Order order = new Order();
        int orderQuantity = Integer.parseInt(request.getParameter("quantity"));
        Catering catering = cateringRepository.findOne(Long.parseLong(request.getParameter("catering-id")));
        order.setCustomer(helper.getCurrentCustomer());
        order.setCatering(catering);
        order.setQuantities(orderQuantity);
        order.setCreateDate(new Date());
        order.setNote(request.getParameter("note"));
        orderRepository.save(order);

        int totalPrice = 0;
        for(String productId: request.getParameterValues("choosen-product")) {
            Product orderedProduct = productRepository.findOne(Long.parseLong(productId));
            totalPrice += orderedProduct.getPrice() * orderQuantity;
            OrderDetail od = new OrderDetail(order, orderedProduct);
            orderDetailRepository.save(od);
        }
        order.setTotalPrices(totalPrice);
        orderRepository.save(order);

        return "redirect:/my-user/order/cart";
    }

    @RequestMapping(value = "/my-user/order/cart", method = RequestMethod.GET)
    public String orderCart(
            Model model,
            HttpServletRequest request)
    {
        String email = helper.getCurrentCustomer().getEmail();
        String _csrf = ((CsrfToken) request.getAttribute("_csrf")).getToken();
        model.addAttribute("_csrf", _csrf);

        model.addAttribute("order", orderRepository.findByCustomerEmailAndStatus(email, Order.ORDER_STATUS_CART).get(0));

        return "/user/cart";
    }

    @RequestMapping(value = "/my-user/order/checkout", method = RequestMethod.GET)
    public String checkoutOrder(HttpServletRequest request)
    {
        return "/user/checkout";
    }

    @RequestMapping(value="/order/add",method=RequestMethod.GET)
    public String orderAdd(
            Model model,
            HttpServletRequest request)
    {
        String _csrf = ((CsrfToken) request.getAttribute("_csrf")).getToken();
        model.addAttribute("_csrf", _csrf);

        model.addAttribute("catering", cateringRepository.findAll());

        return "order/add";
    }

}

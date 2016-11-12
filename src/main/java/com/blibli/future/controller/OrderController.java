package com.blibli.future.controller;

import com.blibli.future.model.Catering;
import com.blibli.future.model.Customer;
import com.blibli.future.model.Order;
import com.blibli.future.model.User;
import com.blibli.future.repository.CateringRepository;
import com.blibli.future.repository.OrderRepository;
import com.blibli.future.repository.ProductRepository;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/order/cart",method = RequestMethod.GET)
    public String orderCart(
            Model model,
            HttpServletRequest request)
    {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String _csrf = ((CsrfToken) request.getAttribute("_csrf")).getToken();
        model.addAttribute("_csrf", _csrf);

        model.addAttribute("order", orderRepository.findByCustomerEmail(email));

        return "order/cart";
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

package com.blibli.future.controller;

import com.blibli.future.model.Customer;
import com.blibli.future.model.User;
import com.blibli.future.repository.CateringRepository;
import com.blibli.future.repository.CustomerRepository;
import com.blibli.future.repository.UserRepository;
import com.blibli.future.utility.Helper;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * Created by dhika on 29/08/2016.
 */
@Controller
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Helper helper;

    @RequestMapping(value="/user/profile" , method = RequestMethod.GET)
    public String showMyProfile(ModelMap model)
    {
        Customer customer = (Customer) helper.getCurrentUser();
        model.addAttribute("customer", customer);
        return "user/dashboard";
    }

    @RequestMapping("/customer/{username}")
    public String showPublicUserProfile(
            @PathVariable String username,
            Model model)
    {
        model.addAttribute("customer", customerRepository.findByUsername(username));
        return "/customer/dashboard";
    }

    @RequestMapping(value="/register", method= RequestMethod.GET)
    public String addUserForm(
            HttpServletRequest request,
            Model model)
    {
        String _csrf = ((CsrfToken) request.getAttribute("_csrf")).getToken();
        model.addAttribute("_csrf", _csrf);
        return "customer/register";
    }

    @RequestMapping(value="/register", method= RequestMethod.POST)
    public String addUser(
            @ModelAttribute Customer newCustomer,
            Model model)
    {
        customerRepository.save(newCustomer);
        model.addAttribute("user", newCustomer);
        return "redirect:/customer/" + newCustomer.getUsername();
    }

    @RequestMapping(value="/customer/{username}/order",method = RequestMethod.GET)
    public String showOrder(
            @PathVariable String username,
            Model model,
            HttpServletRequest request)
    {
        String _csrf = ((CsrfToken) request.getAttribute("_csrf")).getToken();
        model.addAttribute("_csrf", _csrf);

        model.addAttribute("customer", customerRepository.findByUsername(username));

        return "customer/order";
    }


}


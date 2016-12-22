package com.blibli.future.controller;

import com.blibli.future.model.Customer;
import com.blibli.future.model.User;
import com.blibli.future.model.UserRole;
import com.blibli.future.model.UserRole;
import com.blibli.future.repository.CateringRepository;
import com.blibli.future.repository.CustomerRepository;
import com.blibli.future.repository.UserRepository;
import com.blibli.future.repository.UserRoleRepository;
import com.blibli.future.security.SecurityService;
import com.blibli.future.security.SecurityService;
import com.blibli.future.repository.UserRoleRepository;
import com.blibli.future.utility.Helper;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;


import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletResponse;

@Controller
public class CustomerController{
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private Helper helper;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private SecurityService securityService;

    @RequestMapping(value="/my-customer/profile" , method = RequestMethod.GET)
    public String showMyCustomerProfile(ModelMap model)
    {
        Customer customer = (Customer) helper.getCurrentUser();
        model.addAttribute("customer", customer);
        return "customer/profile";
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
        model.addAttribute("customer", newCustomer);
        UserRole r = new UserRole();
        r.setUsername(newCustomer.getUsername());
        r.setRole("ROLE_CUSTOMER");
        userRoleRepository.save(r);
        securityService.autologin(newCustomer.getUsername(), newCustomer.getPassword());
        System.out.println("Success");
        return "redirect:/my-customer/profile";
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


    @RequestMapping(value="/my-customer/edit", method= RequestMethod.GET)
    public String editCustomerForm(
            HttpServletRequest request,
            Model model)
    {
        Customer customer = (Customer) helper.getCurrentUser();
        model.addAttribute("customer", customer);
        String _csrf = ((CsrfToken) request.getAttribute("_csrf")).getToken();
        model.addAttribute("_csrf", _csrf);
        return "customer/edit";
    }

    @RequestMapping(value="/my-customer/edit", method= RequestMethod.POST)
    public String editCustomer(
            HttpServletRequest request)
    {
        Customer customer = (Customer) helper.getCurrentUser();
        customer.setUsername(request.getParameter("username"));
        customer.setPassword(request.getParameter("password"));
        customer.setEmail(request.getParameter("email"));
        customer.setFullName(request.getParameter("fullName"));
        customer.setNickName(request.getParameter("nickName"));
        customerRepository.save(customer);
        return "redirect:/my-customer/profile";
    }
}


package com.blibli.future.controller;

import com.blibli.future.model.Customer;
import com.blibli.future.model.User;
import com.blibli.future.repository.CustomerRepository;
import com.blibli.future.repository.UserRepository;
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
    private CustomerRepository repo;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value="/user/profile" , method = RequestMethod.GET)
    public String showMyProfile(ModelMap model)
    {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(username);
        User user = userRepository.findByUsername(username);
        model.addAttribute("user", user);
        return "/user/dashboard";
    }

    @RequestMapping("/customer/{username}")
    public String showPublicUserProfile(
            @PathVariable String username,
            Model model)
    {
        model.addAttribute("user", userRepository.findByUsername(username));
        return "/customer/dashboard";
    }

    @RequestMapping(value="register", method= RequestMethod.POST)
    public String addUser(
            @ModelAttribute Customer newCustomer,
            Model model)
    {
        repo.save(newCustomer);
        model.addAttribute("user", newCustomer);
        return "redirect:/user/" + newCustomer.getId();
    }

    @RequestMapping(value="/user/login", method= RequestMethod.GET)
    public String authenticateUser(
            @ModelAttribute User newUser,
            HttpServletRequest request,
            Model model)
    {
        String _csrf = ((CsrfToken) request.getAttribute("_csrf")).getToken();
        model.addAttribute("_csrf", _csrf);
        return "login";
    }

}

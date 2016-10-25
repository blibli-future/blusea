package com.blibli.future.controller;

import com.blibli.future.model.Costumer;
import com.blibli.future.model.User;
import com.blibli.future.repository.CostumerRepository;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by dhika on 29/08/2016.
 */
@Controller
public class CostumerController {
    @Autowired
    private CostumerRepository repo;

    @RequestMapping("/user/profile")
    public String showMyProfile(Model model)
    {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        return "/user/dashboard";
    }

    @RequestMapping("/user/{userId}")
    public String showPublicUserProfile(
            @PathVariable Long userId,
            Model model)
    {
        model.addAttribute("user", repo.findOne(userId));
        return "/user/dashboard";
    }

    @RequestMapping(value="register", method= RequestMethod.POST)
    public String addUser(
            @ModelAttribute Costumer newCostumer,
            Model model)
    {
        repo.save(newCostumer);
        model.addAttribute("user", newCostumer);
        return "redirect:/user/" + newCostumer.getId();
    }

    @RequestMapping(value="/user/login", method= RequestMethod.GET)
    public String authenticateUser(
            @ModelAttribute User newUser,
            HttpServletRequest request,
            Model model)
    {
        String _csrf = ((CsrfToken) request.getAttribute("_csrf")).getToken();
        model.addAttribute("_csrf", _csrf);
        return "user/login";
    }

}

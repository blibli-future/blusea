package com.blibli.future.controller;

import com.blibli.future.model.User;
import com.blibli.future.repository.UserRepository;
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
public class UserController {
    @Autowired
    private UserRepository repo;

    @RequestMapping("/user/profile")
    public String profile(Model model)
    {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        return "/user/dashboard";
    }

    @RequestMapping("/user/{userId}")
    public String show(
            @PathVariable Long userId,
            Model model)
    {
        model.addAttribute("user", (User) repo.findOne(userId));
        return "/user/dashboard";
    }

    @RequestMapping(value="register", method= RequestMethod.POST)
    public String register(
            @ModelAttribute User newUser,
            Model model)
    {
        repo.save(newUser);
        model.addAttribute("user", newUser);
        return "redirect:/user/" + newUser.getId();
    }

    @RequestMapping(value="/user/login", method= RequestMethod.GET)
    public String login(
            @ModelAttribute User newUser,
            HttpServletRequest request,
            Model model)
    {
        String _csrf = ((CsrfToken) request.getAttribute("_csrf")).getToken();
        model.addAttribute("_csrf", _csrf);
        return "user/login";
    }

}

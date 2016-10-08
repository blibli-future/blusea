package com.blibli.future.controller;

import com.blibli.future.model.User;
import com.blibli.future.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping("/user/{userId}")
    public String show(
            @PathVariable Long userId,
            Model model)
    {
        model.addAttribute("user", (User) repo.findOne(userId));
        return "/user/dashboard";
    }

    @RequestMapping(value="register", method= RequestMethod.POST)
    public String landing(
            @ModelAttribute User newUser,
            Model model)
    {
        repo.save(newUser);
        model.addAttribute("user", newUser);
        return "user/dashboard";
    }

}

package com.blibli.future.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.blibli.future.model.User;
import com.blibli.future.repository.UserRepository;

@Controller
public class RegisterController {
	@Autowired
	private UserRepository repo;

	@RequestMapping(value="register", method=RequestMethod.POST)
	public String landing(
			@ModelAttribute User newUser,
			Model model)
	{
		repo.save(newUser);
		model.addAttribute("user", newUser);
		return "user/dashboard";
	}
}

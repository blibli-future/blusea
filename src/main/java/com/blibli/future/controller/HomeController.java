package com.blibli.future.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.blibli.future.model.User;
import com.blibli.future.repository.UserRepository;

@Controller
public class HomeController {	
	@Autowired
	private UserRepository repo;
	
	@RequestMapping("/")
	public String landing(
			@RequestParam(
				value="name", 
				required=false,
				defaultValue="World")
			String name, 
			Model model)
	{
		repo.save(new User("Adhika Setya Pramudita", "Dhika", "hello@adhikasetyap.me"));
		repo.save(new User("Elisabeth Diana Kartika", "Diana", "elisabeth.dianaks@gmail.com"));
		List<String> users = new ArrayList<>();
		for (User u: repo.findAll()) {
			users.add(u.toString());
		}
		
		model.addAttribute("name", name);
		model.addAttribute("users", users);
		model.addAttribute("newUser", new User());
		return "landing";
	}
}

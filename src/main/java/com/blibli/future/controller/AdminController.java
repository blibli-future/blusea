package com.blibli.future.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blibli.future.model.User;
import com.blibli.future.repository.UserRepository;

@Controller
public class AdminController {	
	@Autowired
	private UserRepository repo;
	
	@RequestMapping("/admin")
	public String showAdminDashboard(
			Model model)
	{
		List<User> users = new ArrayList<>();
		for (User u: repo.findAll()) {
			users.add(u);
		}
		
		model.addAttribute("users", users);
		return "admin/dashboard";
	}
}

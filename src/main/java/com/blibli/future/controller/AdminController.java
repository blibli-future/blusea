package com.blibli.future.controller;

import com.blibli.future.model.User;
import com.blibli.future.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

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

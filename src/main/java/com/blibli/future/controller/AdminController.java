package com.blibli.future.controller;

import com.blibli.future.model.Consumer;
import com.blibli.future.model.User;
import com.blibli.future.repository.ConsumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class AdminController {
	@Autowired
	private ConsumerRepository repo;

	@RequestMapping("/admin")
	public String showAdminDashboard(
			Model model)
	{
		List<Consumer> consumers = (List<Consumer>) repo.findAll();

		model.addAttribute("users", consumers);
		return "admin/dashboard";
	}
}

package com.blibli.future.controller;

import com.blibli.future.model.Customer;
import com.blibli.future.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class AdminController {
	@Autowired
	private CustomerRepository repo;

	@RequestMapping("/admin")
	public String showAdminDashboard(
			Model model)
	{
		List<Customer> customers = repo.findAll();

		model.addAttribute("users", customers);
		return "admin/dashboard";
	}
}

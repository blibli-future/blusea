package com.blibli.future.controller;

import com.blibli.future.model.Costumer;
import com.blibli.future.repository.CostumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class AdminController {
	@Autowired
	private CostumerRepository repo;

	@RequestMapping("/admin")
	public String showAdminDashboard(
			Model model)
	{
		List<Costumer> costumers = (List<Costumer>) repo.findAll();

		model.addAttribute("users", costumers);
		return "admin/dashboard";
	}
}

package com.blibli.future.controller;

import com.blibli.future.model.User;
import com.blibli.future.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PublicController {
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
		// create User stub for registration purpose
		model.addAttribute("newUser", new User());
		return "landing";
	}
}

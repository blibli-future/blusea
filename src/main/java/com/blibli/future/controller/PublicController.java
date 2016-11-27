package com.blibli.future.controller;

import com.blibli.future.model.Catering;
import com.blibli.future.repository.CateringRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class PublicController {

	@Autowired
	CateringRepository cateringRepository;

	@RequestMapping("/")
	public String landing(
			Model model)
	{
		List<Catering> firstSixCatering = cateringRepository.findAll().subList(0, 6);
		model.addAttribute("caterings", firstSixCatering);
		return "landing";
	}
}

package com.blibli.future.controller;

import com.blibli.future.model.Catering;
import com.blibli.future.repository.CateringRepository;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
		return "public/landing";
	}

	@RequestMapping("/catering/{username}")
	public String cateringPublicProfile(
			@PathVariable String username,
			HttpServletRequest request,
			Model model){
		String _csrf = ((CsrfToken) request.getAttribute("_csrf")).getToken();
		model.addAttribute("_csrf", _csrf);

		Catering catering = cateringRepository.findByUsername(username);
		model.addAttribute("catering", catering);
		model.addAttribute("products", catering.getProducts());

		return "public/catering-profile";
	}
}

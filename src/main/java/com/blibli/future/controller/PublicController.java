package com.blibli.future.controller;

import com.blibli.future.model.Catering;
import com.blibli.future.model.Customer;
import com.blibli.future.model.User;
import com.blibli.future.repository.CateringRepository;
import com.blibli.future.repository.CustomerRepository;
import com.blibli.future.utility.Helper;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class PublicController {

	@Autowired
	CateringRepository cateringRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	Helper helper;

	@RequestMapping("/")
	public String landing(
			Model model) {
		List<Catering> firstSixCatering = cateringRepository.findAll().subList(0, 6);
		model.addAttribute("caterings", firstSixCatering);
        User user = helper.getCurrentUser();
        if(user instanceof Customer){
            Customer customer = (Customer) user;
            model.addAttribute("customer", customer);
            return "/customer/home";
        }else if(user instanceof Catering){
            Catering catering = (Catering) user;
            model.addAttribute("catering", catering);
            return "/catering/home";
        }
        return "public/landing";
	}

	@RequestMapping(value="/login", method= RequestMethod.GET)
	public String authenticateUser(
			@ModelAttribute User newUser,
			HttpServletRequest request,
			Model model)
	{
		String _csrf = ((CsrfToken) request.getAttribute("_csrf")).getToken();
		model.addAttribute("_csrf", _csrf);
		return "public/login";
	}

	@RequestMapping(value="/login-error")
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		return "public/login-error";
	}

	@RequestMapping(value="/login/process")
	public String decideUser(Model model) {
		User user = helper.getCurrentUser();
		if(user instanceof Customer){
			return "redirect:/my-customer/profile";
		}else if(user instanceof Catering){
			return "redirect:/my-catering/profile";
		}
		return "redirect:/";
	}

	//catering

	@RequestMapping(value="/catering",method=RequestMethod.GET)
	public String showAllCateringList(Model model){
		model.addAttribute("caterings", cateringRepository.findAll());
		return "catering/list";
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

	//Customer
	@RequestMapping("/customer/{username}")
	public String showPublicUserProfile(
			@PathVariable String username,
			Model model)
	{
		model.addAttribute("customer", customerRepository.findByUsername(username));
		return "/customer/dashboard";
	}


}

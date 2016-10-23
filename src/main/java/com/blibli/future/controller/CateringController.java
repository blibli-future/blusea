package com.blibli.future.controller;

/**
 * Created by ARDI on 10/6/2016.
 */
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.blibli.future.model.Product;
import com.blibli.future.model.Catering;
import com.blibli.future.repository.CateringRepository;
import com.blibli.future.repository.ProductRepository;

@Controller
public class CateringController {

    @Autowired
    CateringRepository cateringRepository;

    @Autowired
    ProductRepository productRepository;

    @RequestMapping("/catering/{username}")
    public String cateringProfile(
            @PathVariable String username,
            Model model){
        Catering catering = cateringRepository.findByUsername(username);
        model.addAttribute("catering", catering);
        model.addAttribute("products", catering.getProducts());

        System.out.println("Product : " + catering.toString());
        System.out.println("ISI :" + catering.getProducts().size());
        return "catering/detail";
    }

    @RequestMapping(value="/catering",method=RequestMethod.GET)
    public String showAllCateringList(Model model){
        model.addAttribute("catering", cateringRepository.findAll());
        return "catering/list";
    }

    @RequestMapping(value="/catering/register",method=RequestMethod.GET)
    public String cateringRegisterForm(
            Model model,
            HttpServletRequest request)
    {
        String _csrf = ((CsrfToken) request.getAttribute("_csrf")).getToken();
        model.addAttribute("_csrf", _csrf);

        model.addAttribute("catering", cateringRepository.findAll());
        return "catering/register";
    }

    @RequestMapping(value="/catering/register",method=RequestMethod.POST)
    public String cateringAdd(
            @ModelAttribute Catering newCatering,
            Model model){

        cateringRepository.save(newCatering);

        model.addAttribute("catering", newCatering);
        return "redirect:/catering/" + newCatering.getUsername();
    }

    @RequestMapping(value="/catering/{username}/addproducts",method=RequestMethod.GET)
    public String cateringGetAddProduct(
            @PathVariable String username,
            Model model,
            HttpServletRequest request)
    {
        String _csrf = ((CsrfToken) request.getAttribute("_csrf")).getToken();
        model.addAttribute("_csrf", _csrf);

        model.addAttribute("catering", cateringRepository.findByUsername(username));

        return "catering/addproducts";
    }

    @RequestMapping(value="/catering/{username}/addproducts", method=RequestMethod.POST)
    public String cateringPostAddProduct(
            @PathVariable String username,
            @ModelAttribute Product newProduct,
            Model model){
        Catering catering = cateringRepository.findByUsername(username);

        if(catering != null){
            if(!catering.hasProduct(newProduct)){
                newProduct.setCatering(catering);
            }
            productRepository.save(newProduct);
            cateringRepository.save(catering);
            return "redirect:/catering/" + catering.getUsername();
        }
        return "redirect:/catering/" + catering.getUsername();
    }
}

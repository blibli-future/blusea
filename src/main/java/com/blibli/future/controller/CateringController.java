package com.blibli.future.controller;

/**
 * Created by ARDI on 10/6/2016.
 */
import org.springframework.beans.factory.annotation.Autowired;
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
    public String catering(
            @PathVariable String username,
            Model model){
        model.addAttribute("catering", cateringRepository.findByUsername(username));
        model.addAttribute("products", productRepository.findAll());
        return "catering/detail";
    }

    @RequestMapping(value="/catering",method=RequestMethod.GET)
    public String cateringList(Model model){
        model.addAttribute("catering", cateringRepository.findAll());
        return "catering/list";
    }

    @RequestMapping(value="/catering/register",method=RequestMethod.GET)
    public String cateringRegister(Model model){
        model.addAttribute("catering", cateringRepository.findAll());
        return "catering/register";
    }

    @RequestMapping(value="/catering/register",method=RequestMethod.POST)
    public String cateringAdd(
            @ModelAttribute Catering newCatering,
            Model model){

        cateringRepository.save(newCatering);

        model.addAttribute("catering", newCatering);
        model.addAttribute("products", productRepository.findAll());
        return "redirect:/catering/" + newCatering.getUsername();
    }

    @RequestMapping(value="/catering/{cateringId}/products", method=RequestMethod.POST)
    public String cateringAddProduct(
            @PathVariable Long cateringId,
            @ModelAttribute Product newProduct,
            Model model){
        productRepository.save(newProduct);
        Catering catering = cateringRepository.findOne(cateringId);

        if(catering != null){
            if(!catering.hasProduct(newProduct)){
                catering.getProducts().add(newProduct);
            }
            cateringRepository.save(catering);
            model.addAttribute("catering", cateringRepository.findOne(cateringId));
            model.addAttribute("products", productRepository.findAll());
            return "redirect:/catering/" + catering.getId();
        }
        model.addAttribute("catering", cateringRepository.findAll());
        return "redirect:/catering";
    }
}

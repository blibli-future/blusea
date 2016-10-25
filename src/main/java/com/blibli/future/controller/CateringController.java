package com.blibli.future.controller;

/**
 * Created by ARDI on 10/6/2016.
 */
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.blibli.future.model.Product;
import com.blibli.future.model.Catering;
import com.blibli.future.repository.CateringRepository;
import com.blibli.future.repository.ProductRepository;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

@Controller
public class CateringController {

    @Autowired
    public Environment env;

    @Autowired
    CateringRepository cateringRepository;

    @Autowired
    ProductRepository productRepository;

    private static final Logger logger = LoggerFactory
            .getLogger(CateringController.class);

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
            @RequestParam("file") MultipartFile file,
            Model model){
        Catering catering = cateringRepository.findByUsername(username);

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        String formatted = format1.format(cal.getTime());

        if(catering != null){
            if(!catering.hasProduct(newProduct)){
                newProduct.setCatering(catering);
            }
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();

                    String fileName = UUID.randomUUID().toString().replaceAll("-","");

                    // Creating the directory to store file
                    //String rootPath = System.getProperty("catalina.home");
                    File dir = new File(env.getProperty("blusea.productPhotoDir.path") + formatted);
                    if (!dir.exists())
                        dir.mkdirs();

                    // Create the file on server
                    File serverFile = new File(dir.getAbsolutePath()
                            + File.separator + fileName + ".jpg");
                    newProduct.setPhoto("gambar"
                            + File.separator + formatted + File.separator + fileName + ".jpg");
                    BufferedOutputStream stream = new BufferedOutputStream(
                            new FileOutputStream(serverFile));
                    stream.write(bytes);
                    stream.close();

                    logger.info("Server File Location="
                            + serverFile.getAbsolutePath());

//                    return "You successfully uploaded file=" + newProduct.getName();
                } catch (Exception e) {
                    return "You failed to upload " + newProduct.getName() + " => " + e.getMessage();
                }
            } else {
                return "You failed to upload " + newProduct.getName()
                        + " because the file was empty.";
            }
            productRepository.save(newProduct);
            cateringRepository.save(catering);
            return "redirect:/catering/" + catering.getUsername();
        }
        return "redirect:/catering/" + catering.getUsername();
    }
}

package com.blibli.future.controller;

import com.blibli.future.model.User;
import com.blibli.future.model.UserRole;
import com.blibli.future.repository.UserRoleRepository;
import com.blibli.future.security.SecurityService;
import com.blibli.future.utility.Helper;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    Helper helper;

    @Autowired
    private SecurityService securityService;

    private static final Logger logger = LoggerFactory
            .getLogger(CateringController.class);

    @RequestMapping(value="/my-catering/register",method=RequestMethod.GET)
    public String cateringRegisterForm(
            Model model,
            HttpServletRequest request)
    {
        String _csrf = ((CsrfToken) request.getAttribute("_csrf")).getToken();
        model.addAttribute("_csrf", _csrf);

        model.addAttribute("catering", cateringRepository.findAll());
        return "catering/register";
    }

    @RequestMapping(value="/my-catering/register",method=RequestMethod.POST)
    public String cateringAdd(
            @ModelAttribute Catering newCatering,
            Model model){
        newCatering.setPhoto("https://dummyimage.com/200x200/000/fff");
        cateringRepository.save(newCatering);
        UserRole r = new UserRole();
        r.setUsername(newCatering.getUsername());
        r.setRole("ROLE_CATERING");
        userRoleRepository.save(r);
        securityService.autologin(newCatering.getUsername(), newCatering.getPassword());

        model.addAttribute("catering", newCatering);
        return "redirect:/my-catering/profile";
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
                    File dir = new File(env.getProperty("blusea.PhotoDir.path") + "/Product/" + formatted);
                    if (!dir.exists())
                        dir.mkdirs();

                    // Create the file on server
                    File serverFile = new File(dir.getAbsolutePath()
                            + File.separator + fileName + ".jpg");
                    newProduct.setPhoto("http://localhost/gambar/Product"
                            + File.separator + formatted + File.separator + fileName + ".jpg");
                    BufferedOutputStream stream = new BufferedOutputStream(
                            new FileOutputStream(serverFile));
                    stream.write(bytes);
                    stream.close();

                    logger.info("Server File Location="
                            + serverFile.getAbsolutePath());

                } catch (Exception e) {
                    return "You failed to upload " + newProduct.getName() + " => " + e.getMessage();
                }
            } else {
                return "You failed to upload " + newProduct.getName()
                        + " because the file was empty.";
            }
            productRepository.save(newProduct);
            cateringRepository.save(catering);
            return "redirect:/my-catering/profile";
        }
        return "redirect:/my-catering/profile";
    }

    @RequestMapping(value="/my-catering/profile" , method = RequestMethod.GET)
    public String showMyCateringProfile(
            Model model,
            HttpServletRequest request)
    {
        String _csrf = ((CsrfToken) request.getAttribute("_csrf")).getToken();
        model.addAttribute("_csrf", _csrf);

        Catering catering = (Catering) helper.getCurrentUser();
        model.addAttribute("catering", catering);
        return "catering/profile";
    }

    //editing
    @RequestMapping(value="/my-catering/edit", method= RequestMethod.GET)
    public String editCateringForm(
            HttpServletRequest request,
            Model model)
    {
        Catering catering = (Catering) helper.getCurrentUser();
        model.addAttribute("catering", catering);
        String _csrf = ((CsrfToken) request.getAttribute("_csrf")).getToken();
        model.addAttribute("_csrf", _csrf);
        return "catering/edit";
    }

    @RequestMapping(value="/my-catering/edit", method= RequestMethod.POST)
    public String editCatering(
            @RequestParam("file") MultipartFile file,
            HttpServletRequest request)
    {
        Catering catering = (Catering) helper.getCurrentUser();

        //photo
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        String formatted = format1.format(cal.getTime());

        if(catering != null){
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();

                    String fileName = UUID.randomUUID().toString().replaceAll("-","");

                    // Creating the directory to store file
                    File dir = new File(env.getProperty("blusea.PhotoDir.path") + "/Catering/" + formatted);
                    if (!dir.exists())
                        dir.mkdirs();

                    // Create the file on server
                    File serverFile = new File(dir.getAbsolutePath()
                            + File.separator + fileName + ".jpg");
                    catering.setPhoto("http://localhost/gambar/Catering"
                            + File.separator + formatted + File.separator + fileName + ".jpg");
                    BufferedOutputStream stream = new BufferedOutputStream(
                            new FileOutputStream(serverFile));
                    stream.write(bytes);
                    stream.close();

                    logger.info("Server File Location="
                            + serverFile.getAbsolutePath());

                } catch (Exception e) {
                    return "You failed to upload " + catering.getCateringName() + " => " + e.getMessage();
                }
            } else {
                return "You failed to upload " + catering.getCateringName()
                        + " because the file was empty.";
            }
        }

        catering.setUsername(request.getParameter("username"));
        catering.setPassword(request.getParameter("password"));
        catering.setEmail(request.getParameter("email"));
        catering.setCateringName(request.getParameter("cateringName"));
        catering.setAddress(request.getParameter("address"));
        catering.setDescription(request.getParameter("description"));
        catering.setPhoneNumber(request.getParameter("phoneNumber"));
        catering.setDp(request.getParameter("dp"));
        cateringRepository.save(catering);
        return "redirect:/my-catering/profile";
    }

    @RequestMapping(value="/my-catering/{id}/delete" , method = RequestMethod.POST)
    public String deleteProduct(
            @PathVariable long id,
            Model model)
    {
        Catering catering = (Catering) helper.getCurrentUser();
        //model.addAttribute("catering", catering);

        Product product = productRepository.findOne(id);
        System.out.println(product.getId());
        productRepository.delete(product);

        return "redirect:/my-catering/profile";
    }
}

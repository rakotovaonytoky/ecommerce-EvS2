/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javainuse.controllers;

import com.javainuse.model.Produit;
import com.javainuse.service.OtherService;
import com.javainuse.service.ProduitService;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author U
 */
@Controller
@RequestMapping(path = "admin")
public class AdminController {

    @Autowired
    private ProduitService produitService;
    @Autowired
    private OtherService otherservice;

    @GetMapping("index")
    public String showIndex() {
        return "indexAdmin";
    }

    @GetMapping("shop")
    public String showChartPage() {
        return "chartsAdmin";
    }

    @GetMapping("add")
    public ModelAndView loadAddprodut() {
        ModelAndView m = new ModelAndView("addproduct");
        m.addObject("categorie", produitService.findAllCategorie());
        return m;
    }

    @PostMapping("adding")
    public ModelAndView processAddprodut(
            @RequestParam MultipartFile file1,
            @RequestParam MultipartFile file2,
            @RequestParam MultipartFile file3,
            @RequestParam MultipartFile file4,
            @RequestParam String nom,
            @RequestParam String description,
            @RequestParam String prix,
            @RequestParam String idcategorie,
            HttpServletRequest req) throws IOException {

//        System.out.println(nom + "\n" + description + "\n" + prix + "\n" + idcategorie);
        Produit p = new Produit();
        p.setNom(nom);
        p.setPrix(new BigDecimal(prix));
        p.setDescription(description);
        p.setIdcategorie(produitService.findCategorie(Integer.valueOf(idcategorie)));
        p.setImage1(file1.getOriginalFilename());
        p.setImage2(file2.getOriginalFilename());
        p.setImage3(file3.getOriginalFilename());
        p.setImage4(file4.getOriginalFilename());
        try {
            produitService.addProductImage(file1, "D:\\NetBeans12\\Ecommerce-S2\\src\\main\\webapp\\resources\\images\\");
            produitService.addProductImage(file2, "D:\\NetBeans12\\Ecommerce-S2\\src\\main\\webapp\\resources\\images\\");
            produitService.addProductImage(file3, "D:\\NetBeans12\\Ecommerce-S2\\src\\main\\webapp\\resources\\images\\");
            produitService.addProductImage(file4, "D:\\NetBeans12\\Ecommerce-S2\\src\\main\\webapp\\resources\\images\\");            
            produitService.insert(p);
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        ModelAndView m = new ModelAndView("addproduct");
        return m;
    }
    
    @GetMapping("modify")
    public ModelAndView loadModifyProduct(){
        return new ModelAndView("modify");
    }
    
    @GetMapping("searchingProduct")
    public ModelAndView SearchProductModifyProduct( @RequestParam String nom){
        ModelAndView m= new ModelAndView("modify");
        m.addObject("listproduit", produitService.findProduitByName(nom));
        return m;
    }
    
   
    @GetMapping("validPortfolio")
    public ModelAndView showAllPortfolio(){
        ModelAndView m= new ModelAndView("adminPortfolio");
        m.addObject("listproduit", otherservice.findPortefeuilleByEtat("attente"));
        return m;
    }
}

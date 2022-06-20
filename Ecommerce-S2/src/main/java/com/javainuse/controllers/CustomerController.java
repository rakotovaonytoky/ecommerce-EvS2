/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javainuse.controllers;

import com.javainuse.model.Produit;
import com.javainuse.service.ProduitService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author U
 */
@Controller
@RequestMapping(path = "customer")
public class CustomerController {

    @Autowired
    private ProduitService produitService;

    @GetMapping("shop")
    public String loadShopPage() {
        return "shop";
    }

    @GetMapping("details")
    public String productDetails() {
        return "product-details";
    }

    @GetMapping("searchingProduct")
    public ModelAndView SearchProductModifyProduct(@RequestParam String nom, @RequestParam(required = false) String pageNo) {
        ModelAndView m = new ModelAndView("productSearch");
        List<Produit> lisp = new ArrayList<Produit>();
//        Page<Produit> page=produitService.findByProductByNameWithPagination(nom, Integer.valueOf(pageNo), 2);
//        Page<Produit> page=null;
        Page<Produit> page = null;
        Pageable pageable = null;
        if (pageNo == null) {
            pageable = new PageRequest(0, 2);
            page = produitService.produitsPagination(nom, pageable);
        } else {
           pageable = new PageRequest(Integer.parseInt(pageNo), 2);
            page = produitService.produitsPagination(nom, pageable);
        }
        m.addObject("listproduit", page.getContent());
        m.addObject("pageList",page.getTotalPages());
        m.addObject("totalElements",page.getTotalElements());
        m.addObject("pagesize",page.getSize());
        return m;
    }
}

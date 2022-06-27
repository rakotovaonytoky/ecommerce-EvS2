/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javainuse.controllers;

import com.javainuse.config.util.ProductQuantity;
import com.javainuse.model.Customer;
import com.javainuse.model.Portefeuille;
import com.javainuse.model.Produit;
import com.javainuse.service.OtherService;
import com.javainuse.service.ProduitService;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
    @Autowired
    private OtherService otherservice;

    @GetMapping("shop")
    public ModelAndView loadShopPage(@RequestParam(required = false) String id) {
        ModelAndView m = new ModelAndView("shop");
        m.addObject("categories", produitService.findAllCategorie());
        m.addObject("recettes", otherservice.findAllRecette());
        if (id == null) {
            m.addObject("listproduit", produitService.findProductByCategorie(1));

        } else {
            m.addObject("listproduit", produitService.findProductByCategorie(Integer.parseInt(id)));
        }
        return m;
    }

    @GetMapping("detailsPage")
    public String productDetails() {
        return "product-details";
    }

    @GetMapping("searchingProduct")
    public ModelAndView SearchProductModifyProduct(@RequestParam String nom, @RequestParam(required = false) String pageNo) {
        ModelAndView m = new ModelAndView("productSearch");
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
        m.addObject("pageList", page.getTotalPages());
        m.addObject("totalElements", page.getTotalElements());
        m.addObject("pagesize", page.getSize());
        return m;
    }

    @GetMapping("details")
    public ModelAndView detailsProduct(@RequestParam String id) {
        ModelAndView m = new ModelAndView("product-details");
        m.addObject("produit", produitService.findProduitById(Integer.parseInt(id)));
        return m;
    }

    @GetMapping("panier")
    public ModelAndView getPanier(Principal principal, HttpServletRequest req) throws Exception {
        ModelAndView m=new ModelAndView("redirect:/customer/shop");
        if (principal == null) {
            String redirect = "redirect:/";
            new ModelAndView(redirect);
        }
        String qteProduit = req.getParameter("qteProduit");
        Customer c=produitService.findCustomerByName(principal.getName());
        List<Produit> listp = new ArrayList<Produit>();
        List<ProductQuantity> listpQuantity = new ArrayList<ProductQuantity>();
        List<Integer> listIdProduit = new ArrayList<Integer>();
        for (int i = 0; i < Integer.parseInt(qteProduit); i++) {
            listIdProduit = produitService.getProductListFromQuantity(listIdProduit,
                    Integer.parseInt(req.getParameter("id" + i)),
                    Integer.parseInt(req.getParameter("quantite" + i)));
            
            listpQuantity = produitService.getProductQuantityList(listpQuantity,
                    Integer.parseInt(req.getParameter("id" + i)),
                    Integer.parseInt(req.getParameter("quantite" + i)));
        }
        listp = produitService.findProductInCart(listIdProduit);
        Integer totalPice=produitService.getTotalProductPrice(listp);
        try{
            produitService. estValidePaiement( listp,c);
            produitService.checkQteProduit(listpQuantity);
//            Manala ao amin'stock
 //           produitService.updateMvtStockWhenInsertCmd(listpQuantity);
//            Mampiditra dans achat
//            produitService.insertAchatWhenValidCart(listp, c);
//            manena volan'utilisateur
//            produitService.substractCustomerPortfolio(listp, c);
     for(ProductQuantity p : listpQuantity)  {
         System.out.println(p);
     }     


//            Mbola mila asina mouvement de stock
            m.addObject("PaniermessageSuccess", "ajout avec succces");
        }catch(Exception e){
            m.addObject("Paniermessage", e.getMessage());

//            e.printStackTrace();
            throw e;
//            System.out.print(e.getMessage());
        }
        return m;
    }

    @GetMapping("autocomplete")
    @ResponseBody
    public String inputAutoComplete(@RequestParam String nom) {

        String result = "";
        List<Produit> listproduit = produitService.findProduitByName(nom);
        if (listproduit.size() > 0) {
            for (int i = 0; i < listproduit.size(); i++) {
                result += "<option value='" + listproduit.get(i).getNom() + "'>";
            }
        }
        return result;
    }
   

    @GetMapping("loadMoneyPage")
    public String loaddMoney() {
        return "portfolio";
    }

    @PostMapping("ProcessMoney")
    public ModelAndView addMoneyProcess(HttpServletRequest req, Principal principle) throws Exception {
        String money = req.getParameter("money");
        ModelAndView m = new ModelAndView();
        Portefeuille p = new Portefeuille();
        if (money != null) {
            try {
                p.setIdcustomer(produitService.findCustomerByName(principle.getName()));
                p.setMontant(Integer.parseInt(money));
                p.setEtat("attente");
                produitService.insertMoney(p);
                m.setViewName("portfolio");
                m.addObject("attribute", "<div class='alert alert-success' role='alert'>\n"
                        + "  Ajout avec success!Attendez la confirmation des admin\n"
                        + "</div>");
            } catch (Exception ex) {
                m.addObject("attribute", "<div class='alert alert-danger' role='alert'>\n"
                        + "  Erreur!!" + ex.getMessage() + "\n"
                        + "</div>");

            }
            m.setViewName("portfolio");
        }
        return m;
    }
    @GetMapping("recetteDetails")
    public ModelAndView recetteDetails(@RequestParam String id){
        ModelAndView m=new ModelAndView("recetteDetails");
        m.addObject("recetteDetails", otherservice.findRecetteById(Integer.parseInt(id)));
        return m;
    }
}

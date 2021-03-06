/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javainuse.controllers;

import com.google.gson.Gson;
import com.javainuse.dao.MvtStockRepository;
import com.javainuse.model.Ingredient;
import com.javainuse.model.Mvstock;
import com.javainuse.model.Produit;
import com.javainuse.model.Recette;
import com.javainuse.model.Vstatachat;
import com.javainuse.service.OtherService;
import com.javainuse.service.ProduitService;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
            @RequestParam String qte,
            @RequestParam String qteenvente,
            HttpServletRequest req) throws Exception {

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
        p.setQte(new BigDecimal(qte));
        p.setQteenvente(qteenvente);
        Mvstock mvt=new Mvstock();
        mvt.setDatemvtstock(new Date());
        mvt.setEtat(new BigDecimal(qte));
        try {
            produitService.addProductImage(file1, "D:\\NetBeans12\\Ecommerce-S2\\src\\main\\webapp\\resources\\images\\");
            produitService.addProductImage(file2, "D:\\NetBeans12\\Ecommerce-S2\\src\\main\\webapp\\resources\\images\\");
            produitService.addProductImage(file3, "D:\\NetBeans12\\Ecommerce-S2\\src\\main\\webapp\\resources\\images\\");
            produitService.addProductImage(file4, "D:\\NetBeans12\\Ecommerce-S2\\src\\main\\webapp\\resources\\images\\");
           Produit pp= produitService.insert(p);
           mvt.setIdproduit(pp);
           otherservice.insertMvtStock(mvt);
        } catch (Exception e) {
            System.out.print(e.getMessage());
            throw e;
        }
        ModelAndView m = new ModelAndView("addproduct");
        return m;
    }

    @GetMapping("modify")
    public ModelAndView loadModifyProduct() {
        return new ModelAndView("modify");
    }

    @GetMapping("searchingProduct")
    public ModelAndView SearchProductModifyProduct(@RequestParam String nom) {
        ModelAndView m = new ModelAndView("modify");
        m.addObject("listproduit", produitService.findProduitByName(nom));
        return m;
    }

    @GetMapping("validPortfolio")
    public ModelAndView showAllPortfolio() {
        ModelAndView m = new ModelAndView("adminPortfolio");
        m.addObject("listproduit", otherservice.findPortefeuilleByEtat("attente"));
        return m;
    }

    @GetMapping("/adminRecette")
    public String loadAddRecettePage() {
        return "adminaddRecette";
    }

    @GetMapping("/ProcessaddRecette")
    public ModelAndView processloadAddRecettePage(@RequestParam String nom, @RequestParam String description) {
        ModelAndView m = new ModelAndView("adminaddRecette");
        Recette c = new Recette();
        try {
            c.setNom(nom);
            c.setDescription(description);
            c = otherservice.insertRecette(c);
            m.addObject("recette", c);
            m.addObject("Recettesuccess", "recette ajout??s avec success,ajoutez les ingredients maintenant");
        } catch (Exception e) {
            m.addObject("RecetteError", "echec d'ajout recette");

            throw e;
        }

        return m;
    }

    @GetMapping("ProcessaddIngredients")
    public ModelAndView processAddIngredients(@RequestParam String Idrecette, @RequestParam String nomProduit, @RequestParam String qte) {
        ModelAndView m = new ModelAndView("adminaddRecette");
        Recette r =  otherservice.findRecetteById(Integer.parseInt(Idrecette));
    List<Produit> listp=produitService.findProduitByName(nomProduit);
        try {
            Ingredient i = new Ingredient();
            i.setIdproduit(listp.get(0));
            i.setIdrecette(r);
            i.setQteIngredients(qte);
            i.setPourcentage(otherservice.calculPourcentage(i.getQteIngredients(), listp.get(0).getQteenvente()));
            String[] nombreProduitWithReste=otherservice.calculQuantiteProduitNecessaire(i.getQteIngredients(), listp.get(0).getQteenvente());
            i.setNombreProduit(nombreProduitWithReste[0]);
            i.setReste(nombreProduitWithReste[1]);
            otherservice.insertIngredient(i);
            m.addObject("recette", r);
        } catch (Exception e) {
            throw e;
        }
        return m;
    }

    @GetMapping("autocompleteIngredients")
    @ResponseBody
    public String inputAutoCompleteIngredients(@RequestParam String nom) {

        String result = "";
        List<Produit> listproduit = produitService.findProduitByName(nom);
        if (listproduit.size() > 0) {
            for (int i = 0; i < listproduit.size(); i++) {
                result += "<option value='" + listproduit.get(i).getNom() + "'>";
            }
        }
        return result;
    }
    
    @GetMapping("updatePortefeuille")
    public ModelAndView updatePortefeuille(@RequestParam String idcustomer){
//        idcustomer="1";
      ModelAndView m=new ModelAndView("indexAdmin") ;
      try{
          produitService.findPortefeuilleByIdCustomerAndSet(produitService.findCustomerById(Integer.parseInt(idcustomer)));
      }catch(Exception ex){
          throw ex;
      }
      
      return m;
    }
    @GetMapping("ToPagePortefeuille")
    public ModelAndView  toPagePortefeuille(){
        ModelAndView m=new ModelAndView("adminPortfolio");
        m.addObject("key", produitService.findPortefeuilleByEtat("attente"));
        return m;
    }
    @GetMapping("statistique")
    public ModelAndView loadStatistique(){
        ModelAndView m=new ModelAndView("chartAdmin");
        List<Vstatachat> vstat=otherservice.showStatistiqueProduit();
        List<String> listProduit=new ArrayList<String>();
        List<Integer> nbproduit=new ArrayList<Integer>();
        for(Vstatachat v : vstat){
            listProduit.add(v.getNom());
            nbproduit.add(v.getSum().intValue());
        }
              m.addObject("nomProduit",  new Gson().toJson( listProduit));
              m.addObject("nbproduit",new Gson().toJson( nbproduit));

        return m;
    }
}

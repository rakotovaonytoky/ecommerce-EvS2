/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javainuse.service;

import com.javainuse.dao.IngredientRepository;
import com.javainuse.dao.PortefeuilleRepository;
import com.javainuse.dao.RecetteRepository;
import com.javainuse.dao.StatachatRepository;
import com.javainuse.model.Ingredient;
import com.javainuse.model.Portefeuille;
import com.javainuse.model.Recette;
import com.javainuse.model.Statachat;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author U
 */
@Service
public class OtherService {

    @Autowired
    private PortefeuilleRepository portefeuilleRepository;
    @Autowired
    private RecetteRepository recetteRepository;
    @Autowired
    private IngredientRepository ingredientRepository;
    @Autowired
    private StatachatRepository statachatRepository;

    public List<Portefeuille> findPortefeuilleByEtat(String etat) {
        List<Portefeuille> list = new ArrayList<>();
        list = portefeuilleRepository.findByEtat(etat);
        return list;
    }

    public Recette insertRecette(Recette c) {
        c = recetteRepository.save(c);
        return c;
    }

    public Ingredient insertIngredient(Ingredient i) {
        i = ingredientRepository.save(i);
        return i;
    }

    public Recette findRecetteById(Integer id) {
        return recetteRepository.findOne(id);
    }

    public List<Recette> findAllRecette() {
        return recetteRepository.findAll();
    }

    public List<Statachat> statProduit() {
        return statachatRepository.findAll();
    }

//    qte en kilos na en litres no ampidirina ato
    public String calculPourcentage(String qteIngredients, String qteProduitsEnVente) {
        double result = 0;
        double qteIngredients1 = Double.parseDouble(qteIngredients);
        double qteProduits1 = Double.parseDouble(qteProduitsEnVente);
        result = (qteIngredients1 * 100) / qteProduits1;
        result= (Math.round(result * 1000.0) / 1000.0);
        return Double.toString(result);
    }
     public String[] calculQuantiteProduitNecessaire(String qteIngredients, String qteProduitsEnVente) {
           final DecimalFormat df = new DecimalFormat("0.00");
         String testconversion=calculPourcentage( qteIngredients,  qteIngredients);
        double calculPourcentage=Double.parseDouble(testconversion);
        
        double modulo=calculPourcentage/100;
        double qteIngredients1 = Float.parseFloat(qteIngredients);
        double qteProduits1 = Float.parseFloat(qteProduitsEnVente);
        double resultatOp=qteIngredients1-qteProduits1;
        String quantite="";
        String reste="";
        if((modulo%100) ==0){
            System.out.print("xzcxvsdvsdvsdvsdvsdbvsdsdfdfb");
            quantite= String.valueOf(Math.round(calculPourcentage/100));
            reste="0";
        }else{
            if(resultatOp >0){
                quantite= String.valueOf(Math.round(calculPourcentage/100) + 1);
               double temp= modulo -(int)modulo;
               System.out.print(temp);
                reste= Double.toString(temp);
            }else{
                 quantite= String.valueOf(Math.round(calculPourcentage/100) + 1);
               double temp= modulo -(int)modulo;
               System.out.print(temp);
                reste= Double.toString((1-temp));
            }
        }
        String[] quantityWithReste=new String[2];
        quantityWithReste[0]=quantite;
        quantityWithReste[1]=reste;
        return quantityWithReste;
     }
}

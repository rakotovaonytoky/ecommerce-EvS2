/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javainuse.service;

import com.javainuse.dao.IngredientRepository;
import com.javainuse.dao.MvtStockRepository;
import com.javainuse.dao.PortefeuilleRepository;
import com.javainuse.dao.RecetteRepository;
import com.javainuse.dao.StatachatRepository;
import com.javainuse.dao.VstatachatRepository;
import com.javainuse.model.Ingredient;
import com.javainuse.model.Mvstock;
import com.javainuse.model.Portefeuille;
import com.javainuse.model.Recette;
import com.javainuse.model.Statachat;
import com.javainuse.model.Vstatachat;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
    @Autowired
     private MvtStockRepository mvtStockRepository;
    @Autowired
    private VstatachatRepository vstatachatRepository;
    
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
        return Double.toString((int) result);
    }
     public String[] calculQuantiteProduitNecessaire(String qteIngredients, String qteProduitsEnVente) {
           String testconversion = calculPourcentage(qteIngredients, qteProduitsEnVente);
        double calculPourcentage = Double.parseDouble(testconversion);
        // System.out.println("pourcentage :" + calculPourcentage);
        double modulo = calculPourcentage / 100;
        double qteIngredients1 = Float.parseFloat(qteIngredients);
        double qteProduits1 = Float.parseFloat(qteProduitsEnVente);
        double resultatOp = qteIngredients1 - qteProduits1;
        String quantite = "";
        String reste = "";
        if ((modulo % 1) == 0) {
            // System.out.print("xzcxvsdvsdvsdvsdvsdbvsdsdfdfb");
            quantite = String.valueOf(Math.round(calculPourcentage / 100));
            reste = "0";
        } else {
            if (resultatOp > 0) {
                quantite = String.valueOf(Math.round(calculPourcentage / 100));
                double temp = modulo - (int) modulo;
                reste = Double.toString(temp);
            } else {
                quantite = String.valueOf(Math.round(calculPourcentage / 100));
                double temp = modulo - (int) modulo;
                BigDecimal bd = new BigDecimal(1 - temp).setScale(2, RoundingMode.HALF_UP);
                double newInput = bd.doubleValue();
                reste = Double.toString(newInput);
            }
        }
        String[] quantityWithReste = new String[2];
        quantityWithReste[0] = quantite;
        quantityWithReste[1] = reste;
        return quantityWithReste;
     }
     public void insertMvtStock(Mvstock m){
         mvtStockRepository.save(m);
     }
     
     public List<Vstatachat> showStatistiqueProduit(){
         return vstatachatRepository.findAll();
     }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javainuse.service;

import com.javainuse.dao.IngredientRepository;
import com.javainuse.dao.PortefeuilleRepository;
import com.javainuse.dao.RecetteRepository;
import com.javainuse.model.Ingredient;
import com.javainuse.model.Portefeuille;
import com.javainuse.model.Recette;
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
    
    public List<Portefeuille> findPortefeuilleByEtat(String etat){
        List<Portefeuille> list=new ArrayList<>();
        list=portefeuilleRepository.findByEtat(etat);
        return list;
    }
    
    public Recette insertRecette(Recette c){
       c= recetteRepository.save(c);
        return c;
    }
    public Ingredient insertIngredient(Ingredient i){
        i=ingredientRepository.save(i);
        return i;
    }
    public Recette findRecetteById(Integer id){
        return recetteRepository.findOne(id);
    }
    public List<Recette> findAllRecette(){
        return recetteRepository.findAll();
    }
}

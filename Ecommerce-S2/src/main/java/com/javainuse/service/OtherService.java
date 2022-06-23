/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javainuse.service;

import com.javainuse.dao.PortefeuilleRepository;
import com.javainuse.model.Portefeuille;
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
    
    public List<Portefeuille> findPortefeuilleByEtat(String etat){
        List<Portefeuille> list=new ArrayList<>();
        list=portefeuilleRepository.findByEtat(etat);
        return list;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javainuse.dao;

import com.javainuse.model.Achat;
import com.javainuse.model.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author U
 */
@Repository
public interface AchatRepository  extends JpaRepository<Achat,Integer>{
    
    Achat findTopByOrderByIdDesc();
}

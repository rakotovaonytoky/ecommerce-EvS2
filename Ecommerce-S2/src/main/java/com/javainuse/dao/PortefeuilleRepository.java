/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javainuse.dao;

import com.javainuse.model.Portefeuille;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author U
 */
public interface PortefeuilleRepository  extends JpaRepository<Portefeuille,Integer>{
    
}

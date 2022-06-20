/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javainuse.dao;

import com.javainuse.model.Produit;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author U
 */
@Repository
public interface ProduitRepository extends JpaRepository<Produit,Integer> {
    
    List<Produit> findByNomContainingIgnoreCase(String nom);
    
    Page<Produit> findByNomContainingIgnoreCase(String nom,Pageable p);
}

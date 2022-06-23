/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javainuse.config.util;

import java.io.Serializable;

/**
 *
 * @author U
 */
public class ProductQuantity implements Serializable{
    public Integer id;
    public Integer qte;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQte() {
        return qte;
    }

    public void setQte(Integer qte) {
        this.qte = qte;
    }

    public ProductQuantity(Integer id, Integer qte) {
        this.id = id;
        this.qte = qte;
    }
    
    
}

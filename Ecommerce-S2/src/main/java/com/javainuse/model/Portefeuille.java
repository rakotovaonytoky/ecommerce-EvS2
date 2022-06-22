/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javainuse.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author U
 */
@Entity
@Table(name = "portefeuille", catalog = "ecommerce", schema = "postgres")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Portefeuille.findAll", query = "SELECT p FROM Portefeuille p"),
    @NamedQuery(name = "Portefeuille.findById", query = "SELECT p FROM Portefeuille p WHERE p.id = :id"),
    @NamedQuery(name = "Portefeuille.findByMontant", query = "SELECT p FROM Portefeuille p WHERE p.montant = :montant"),
    @NamedQuery(name = "Portefeuille.findByEtat", query = "SELECT p FROM Portefeuille p WHERE p.etat = :etat")})
public class Portefeuille implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "montant")
    private int montant;
    @Size(max = 20)
    @Column(name = "etat")
    private String etat;
    @JoinColumn(name = "idcustomer", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Customer idcustomer;

    public Portefeuille() {
    }

    public Portefeuille(Integer id) {
        this.id = id;
    }

    public Portefeuille(Integer id, int montant) {
        this.id = id;
        this.montant = montant;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        if(montant <0){
            throw new RuntimeException("montant ne peut etre <0");
        }
        this.montant = montant;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Customer getIdcustomer() {
        return idcustomer;
    }

    public void setIdcustomer(Customer idcustomer) {
        this.idcustomer = idcustomer;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Portefeuille)) {
            return false;
        }
        Portefeuille other = (Portefeuille) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.javainuse.model.Portefeuille[ id=" + id + " ]";
    }
    
}

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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author U
 */
@Entity
@Table(name = "detailspanier", catalog = "ecommerce", schema = "POSTGRES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detailspanier.findAll", query = "SELECT d FROM Detailspanier d"),
    @NamedQuery(name = "Detailspanier.findById", query = "SELECT d FROM Detailspanier d WHERE d.id = :id"),
    @NamedQuery(name = "Detailspanier.findByIdpanier", query = "SELECT d FROM Detailspanier d WHERE d.idpanier = :idpanier")})
public class Detailspanier implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idpanier")
    private int idpanier;
    @JoinColumn(name = "idproduit", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Produit idproduit;

    public Detailspanier() {
    }

    public Detailspanier(Integer id) {
        this.id = id;
    }

    public Detailspanier(Integer id, int idpanier) {
        this.id = id;
        this.idpanier = idpanier;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdpanier() {
        return idpanier;
    }

    public void setIdpanier(int idpanier) {
        this.idpanier = idpanier;
    }

    public Produit getIdproduit() {
        return idproduit;
    }

    public void setIdproduit(Produit idproduit) {
        this.idproduit = idproduit;
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
        if (!(object instanceof Detailspanier)) {
            return false;
        }
        Detailspanier other = (Detailspanier) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.javainuse.model.Detailspanier[ id=" + id + " ]";
    }
    
}

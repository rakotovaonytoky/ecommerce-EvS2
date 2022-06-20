/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javainuse.model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author U
 */
@Entity
@Table(name = "panier", catalog = "ecommerce", schema = "POSTGRES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Panier.findAll", query = "SELECT p FROM Panier p"),
    @NamedQuery(name = "Panier.findById", query = "SELECT p FROM Panier p WHERE p.id = :id"),
    @NamedQuery(name = "Panier.findByDatepanier", query = "SELECT p FROM Panier p WHERE p.datepanier = :datepanier"),
    @NamedQuery(name = "Panier.findByIdproduit", query = "SELECT p FROM Panier p WHERE p.idproduit = :idproduit"),
    @NamedQuery(name = "Panier.findByEstvalide", query = "SELECT p FROM Panier p WHERE p.estvalide = :estvalide")})
public class Panier implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "datepanier")
    @Temporal(TemporalType.DATE)
    private Date datepanier;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idproduit")
    private int idproduit;
    @Column(name = "estvalide")
    private Boolean estvalide;
    @JoinColumn(name = "idcustomer", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Customer idcustomer;

    public Panier() {
    }

    public Panier(Integer id) {
        this.id = id;
    }

    public Panier(Integer id, Date datepanier, int idproduit) {
        this.id = id;
        this.datepanier = datepanier;
        this.idproduit = idproduit;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDatepanier() {
        return datepanier;
    }

    public void setDatepanier(Date datepanier) {
        this.datepanier = datepanier;
    }

    public int getIdproduit() {
        return idproduit;
    }

    public void setIdproduit(int idproduit) {
        this.idproduit = idproduit;
    }

    public Boolean getEstvalide() {
        return estvalide;
    }

    public void setEstvalide(Boolean estvalide) {
        this.estvalide = estvalide;
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
        if (!(object instanceof Panier)) {
            return false;
        }
        Panier other = (Panier) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.javainuse.model.Panier[ id=" + id + " ]";
    }
    
}

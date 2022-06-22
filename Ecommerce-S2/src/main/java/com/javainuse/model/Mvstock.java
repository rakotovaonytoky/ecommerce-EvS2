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
@Table(name = "mvstock", catalog = "ecommerce", schema = "postgres")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mvstock.findAll", query = "SELECT m FROM Mvstock m"),
    @NamedQuery(name = "Mvstock.findById", query = "SELECT m FROM Mvstock m WHERE m.id = :id"),
    @NamedQuery(name = "Mvstock.findByDatemvtstock", query = "SELECT m FROM Mvstock m WHERE m.datemvtstock = :datemvtstock"),
    @NamedQuery(name = "Mvstock.findByEtat", query = "SELECT m FROM Mvstock m WHERE m.etat = :etat")})
public class Mvstock implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "datemvtstock")
    @Temporal(TemporalType.DATE)
    private Date datemvtstock;
    @Basic(optional = false)
    @NotNull
    @Column(name = "etat")
    private int etat;
    @JoinColumn(name = "idproduit", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Produit idproduit;

    public Mvstock() {
    }

    public Mvstock(Integer id) {
        this.id = id;
    }

    public Mvstock(Integer id, Date datemvtstock, int etat) {
        this.id = id;
        this.datemvtstock = datemvtstock;
        this.etat = etat;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDatemvtstock() {
        return datemvtstock;
    }

    public void setDatemvtstock(Date datemvtstock) {
        this.datemvtstock = datemvtstock;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
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
        if (!(object instanceof Mvstock)) {
            return false;
        }
        Mvstock other = (Mvstock) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.javainuse.model.Mvstock[ id=" + id + " ]";
    }
    
}

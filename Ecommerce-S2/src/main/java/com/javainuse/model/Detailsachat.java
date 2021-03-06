/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javainuse.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "detailsachat", catalog = "ecommerce", schema = "postgres")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detailsachat.findAll", query = "SELECT d FROM Detailsachat d"),
    @NamedQuery(name = "Detailsachat.findById", query = "SELECT d FROM Detailsachat d WHERE d.id = :id"),
    @NamedQuery(name = "Detailsachat.findByIdachat", query = "SELECT d FROM Detailsachat d WHERE d.idachat = :idachat"),
    @NamedQuery(name = "Detailsachat.findByQteachete", query = "SELECT d FROM Detailsachat d WHERE d.qteachete = :qteachete")})
public class Detailsachat implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idachat")
    private int idachat;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "qteachete")
    private BigDecimal qteachete;
    @JoinColumn(name = "idproduit", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Produit idproduit;

    public Detailsachat() {
    }

    public Detailsachat(Integer id) {
        this.id = id;
    }

    public Detailsachat(Integer id, int idachat) {
        this.id = id;
        this.idachat = idachat;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdachat() {
        return idachat;
    }

    public void setIdachat(int idachat) {
        this.idachat = idachat;
    }

    public BigDecimal getQteachete() {
        return qteachete;
    }

    public void setQteachete(BigDecimal qteachete) {
        this.qteachete = qteachete;
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
        if (!(object instanceof Detailsachat)) {
            return false;
        }
        Detailsachat other = (Detailsachat) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.javainuse.model.Detailsachat[ id=" + id + " ]";
    }
    
}

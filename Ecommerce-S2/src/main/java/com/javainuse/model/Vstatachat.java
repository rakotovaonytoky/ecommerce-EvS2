/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javainuse.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.Immutable;

/**
 *
 * @author U
 */
@Entity
@Immutable
@Table(name = "vstatachat", catalog = "ecommerce", schema = "postgres")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vstatachat.findAll", query = "SELECT v FROM Vstatachat v"),
    @NamedQuery(name = "Vstatachat.findByIdproduit", query = "SELECT v FROM Vstatachat v WHERE v.idproduit = :idproduit"),
    @NamedQuery(name = "Vstatachat.findByNom", query = "SELECT v FROM Vstatachat v WHERE v.nom = :nom"),
    @NamedQuery(name = "Vstatachat.findBySum", query = "SELECT v FROM Vstatachat v WHERE v.sum = :sum"),
    @NamedQuery(name = "Vstatachat.findByDateachat", query = "SELECT v FROM Vstatachat v WHERE v.dateachat = :dateachat")})
public class Vstatachat implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "idproduit")
    @Id
    private Integer idproduit;
    @Size(max = 50)
    @Column(name = "nom")
    private String nom;
    @Column(name = "sum")
    private BigInteger sum;
    @Column(name = "dateachat")
    @Temporal(TemporalType.DATE)
    private Date dateachat;

    public Vstatachat() {
    }

    public Integer getIdproduit() {
        return idproduit;
    }

    public void setIdproduit(Integer idproduit) {
        this.idproduit = idproduit;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public BigInteger getSum() {
        return sum;
    }

    public void setSum(BigInteger sum) {
        this.sum = sum;
    }

    public Date getDateachat() {
        return dateachat;
    }

    public void setDateachat(Date dateachat) {
        this.dateachat = dateachat;
    }
    
}

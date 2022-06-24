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
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.Immutable;

/**
 *
 * @author U
 */
@Entity
@Immutable
@Table(name = "statachat", catalog = "ecommerce", schema = "postgres")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Statachat.findAll", query = "SELECT s FROM Statachat s"),
    @NamedQuery(name = "Statachat.findByIdproduit", query = "SELECT s FROM Statachat s WHERE s.idproduit = :idproduit"),
    @NamedQuery(name = "Statachat.findBySum", query = "SELECT s FROM Statachat s WHERE s.sum = :sum"),
    @NamedQuery(name = "Statachat.findByDateachat", query = "SELECT s FROM Statachat s WHERE s.dateachat = :dateachat")})
public class Statachat implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "idproduit")
    @Id
    private Integer idproduit;
    @Column(name = "sum")
    private BigInteger sum;
    @Column(name = "dateachat")
    @Temporal(TemporalType.DATE)
    private Date dateachat;

    public Statachat() {
    }

    public Integer getIdproduit() {
        return idproduit;
    }

    public void setIdproduit(Integer idproduit) {
        this.idproduit = idproduit;
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

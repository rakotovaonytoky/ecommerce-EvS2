/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javainuse.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author U
 */
@Entity
@Table(name = "produit", catalog = "ecommerce", schema = "postgres")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Produit.findAll", query = "SELECT p FROM Produit p"),
    @NamedQuery(name = "Produit.findById", query = "SELECT p FROM Produit p WHERE p.id = :id"),
    @NamedQuery(name = "Produit.findByDescription", query = "SELECT p FROM Produit p WHERE p.description = :description"),
    @NamedQuery(name = "Produit.findByImage1", query = "SELECT p FROM Produit p WHERE p.image1 = :image1"),
    @NamedQuery(name = "Produit.findByImage2", query = "SELECT p FROM Produit p WHERE p.image2 = :image2"),
    @NamedQuery(name = "Produit.findByImage3", query = "SELECT p FROM Produit p WHERE p.image3 = :image3"),
    @NamedQuery(name = "Produit.findByImage4", query = "SELECT p FROM Produit p WHERE p.image4 = :image4"),
    @NamedQuery(name = "Produit.findByNom", query = "SELECT p FROM Produit p WHERE p.nom = :nom"),
    @NamedQuery(name = "Produit.findByPrix", query = "SELECT p FROM Produit p WHERE p.prix = :prix"),
    @NamedQuery(name = "Produit.findByQte", query = "SELECT p FROM Produit p WHERE p.qte = :qte"),
    @NamedQuery(name = "Produit.findByQteenvente", query = "SELECT p FROM Produit p WHERE p.qteenvente = :qteenvente")})
public class Produit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "description")
    private String description;
    @Size(max = 50)
    @Column(name = "image1")
    private String image1;
    @Size(max = 50)
    @Column(name = "image2")
    private String image2;
    @Size(max = 50)
    @Column(name = "image3")
    private String image3;
    @Size(max = 50)
    @Column(name = "image4")
    private String image4;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nom")
    private String nom;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "prix")
    private BigDecimal prix;
    @Column(name = "qte")
    private BigDecimal qte;
    @Size(max = 60)
    @Column(name = "qteenvente")
    private String qteenvente;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idproduit", fetch = FetchType.LAZY)
    private List<Ingredient> ingredientList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idproduit", fetch = FetchType.LAZY)
    private List<Mvstock> mvstockList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idproduit", fetch = FetchType.LAZY)
    private List<Detailsachat> detailsachatList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idproduit", fetch = FetchType.LAZY)
    private List<Detailspanier> detailspanierList;
    @JoinColumn(name = "idcategorie", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Categorie idcategorie;

    public Produit() {
    }

    public Produit(Integer id) {
        this.id = id;
    }

    public Produit(Integer id, String nom, BigDecimal prix) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    public String getImage4() {
        return image4;
    }

    public void setImage4(String image4) {
        this.image4 = image4;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    public BigDecimal getQte() {
        return qte;
    }

    public void setQte(BigDecimal qte) {
        this.qte = qte;
    }

    public String getQteenvente() {
        return qteenvente;
    }

    public void setQteenvente(String qteenvente) {
        this.qteenvente = qteenvente;
    }

    @XmlTransient
    public List<Ingredient> getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(List<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
    }

    @XmlTransient
    public List<Mvstock> getMvstockList() {
        return mvstockList;
    }

    public void setMvstockList(List<Mvstock> mvstockList) {
        this.mvstockList = mvstockList;
    }

    @XmlTransient
    public List<Detailsachat> getDetailsachatList() {
        return detailsachatList;
    }

    public void setDetailsachatList(List<Detailsachat> detailsachatList) {
        this.detailsachatList = detailsachatList;
    }

    @XmlTransient
    public List<Detailspanier> getDetailspanierList() {
        return detailspanierList;
    }

    public void setDetailspanierList(List<Detailspanier> detailspanierList) {
        this.detailspanierList = detailspanierList;
    }

    public Categorie getIdcategorie() {
        return idcategorie;
    }

    public void setIdcategorie(Categorie idcategorie) {
        this.idcategorie = idcategorie;
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
        if (!(object instanceof Produit)) {
            return false;
        }
        Produit other = (Produit) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.javainuse.model.Produit[ id=" + id + " ]";
    }
    
}

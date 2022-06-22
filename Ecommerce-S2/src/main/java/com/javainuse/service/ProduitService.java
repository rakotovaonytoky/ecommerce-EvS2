/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javainuse.service;

import com.javainuse.dao.CategorieRepository;
import com.javainuse.dao.CustomerRepository;
import com.javainuse.dao.PortefeuilleRepository;
import com.javainuse.dao.ProduitRepository;
import com.javainuse.model.Categorie;
import com.javainuse.model.Customer;
import com.javainuse.model.Portefeuille;
import com.javainuse.model.Produit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author U
 */
@Service
public class ProduitService {
    
    private CategorieRepository categorieRepository;
    private ProduitRepository produitRepository; 
    private PortefeuilleRepository portefeuilleRepository;
    private CustomerRepository customerRepository; 

    @Autowired
    public ProduitService(CategorieRepository categorieRepository, ProduitRepository produitRepository, PortefeuilleRepository portefeuilleRepository, CustomerRepository customerRepository) {
        this.categorieRepository = categorieRepository;
        this.produitRepository = produitRepository;
        this.portefeuilleRepository = portefeuilleRepository;
        this.customerRepository = customerRepository;
    }

   
    
    public List<Categorie> findAllCategorie(){
        List<Categorie> listCategorie=new ArrayList<Categorie>();
        listCategorie=categorieRepository.findAll();
        
        if(listCategorie.isEmpty()) throw new RuntimeException("liste produit vide");
        return listCategorie;
    }

    
    
    public Categorie findCategorie(Integer id){
        return categorieRepository.findOne(id);
    }
    
    public void insert(Produit p){
        try{
            produitRepository.save(p);
        }catch(Exception e){
            throw e;
        }
    }
    public void addProductImage(MultipartFile file,String pathFolder) throws FileNotFoundException, IOException{
//           File path = new File("D:\\testupload\\" + file.getOriginalFilename());
           File path = new File(pathFolder + file.getOriginalFilename());
            path.createNewFile();
            FileOutputStream output = new FileOutputStream(path);
            output.write(file.getBytes());
            output.close();
    }
    
    public List<Produit> findProduitByName(String name){
        List<Produit> listp=new ArrayList<Produit>();
        listp=produitRepository.findByNomContainingIgnoreCase(name);
        return listp;
    }
    
    public Page<Produit> findByProductByNameWithPagination(String name,int pageNo,int pageSize){
//        rehefa tsisy sort
         Pageable pageable =new PageRequest(pageNo, pageSize);
        Page<Produit> page = produitRepository.findAll(pageable);
        System.out.println("****************************************");
        System.out.println( page.getTotalPages());
        return page;
    }
    
    public Page<Produit> produitsPagination(String name, Pageable p){
        Page<Produit> page=produitRepository.findByNomContainingIgnoreCase(name, p);
        return page;
    }
    
    public Produit findProduitById(Integer id){
        return produitRepository.findOne(id);
    }
    public Page<Produit> findProductByCategorie(Integer id ,Pageable p){
        Categorie c=findCategorie( id);
        return produitRepository.findByIdcategorie(c,p);
        
    }
    public List<Produit> findProductByCategorie(Integer id ){
        Categorie c=findCategorie( id);
        return produitRepository.findByIdcategorie(c);    
    }
    public void insertMoney(Portefeuille p) throws Exception{
        try{
             portefeuilleRepository.save(p);
        }catch(Exception e){
            throw e;
        }
       
    }
    public Customer findCustomerByName(String name){
        return customerRepository.findByName(name);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javainuse.service;

import com.javainuse.config.util.ProductQuantity;
import com.javainuse.dao.AchatRepository;
import com.javainuse.dao.CategorieRepository;
import com.javainuse.dao.CustomerRepository;
import com.javainuse.dao.DetailsachatRepository;
import com.javainuse.dao.MvtStockRepository;
import com.javainuse.dao.PortefeuilleRepository;
import com.javainuse.dao.ProduitRepository;
import com.javainuse.model.Achat;
import com.javainuse.model.Categorie;
import com.javainuse.model.Customer;
import com.javainuse.model.Detailsachat;
import com.javainuse.model.Mvstock;
import com.javainuse.model.Portefeuille;
import com.javainuse.model.Produit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    private MvtStockRepository mvtStockRepository;
    private AchatRepository achatRepository;
    private DetailsachatRepository detailsachatRepository;

    @Autowired
    public ProduitService(CategorieRepository categorieRepository, ProduitRepository produitRepository, PortefeuilleRepository portefeuilleRepository, CustomerRepository customerRepository, MvtStockRepository mvtStockRepository, AchatRepository achatRepository, DetailsachatRepository detailsachatRepository) {
        this.categorieRepository = categorieRepository;
        this.produitRepository = produitRepository;
        this.portefeuilleRepository = portefeuilleRepository;
        this.customerRepository = customerRepository;
        this.mvtStockRepository = mvtStockRepository;
        this.achatRepository = achatRepository;
        this.detailsachatRepository = detailsachatRepository;
    }

    public List<Categorie> findAllCategorie() {
        List<Categorie> listCategorie = new ArrayList<Categorie>();
        listCategorie = categorieRepository.findAll();

        if (listCategorie.isEmpty()) {
            throw new RuntimeException("liste produit vide");
        }
        return listCategorie;
    }

    public Categorie findCategorie(Integer id) {
        return categorieRepository.findOne(id);
    }

    public void insert(Produit p) {
        try {
            produitRepository.save(p);
        } catch (Exception e) {
            throw e;
        }
    }

    public void addProductImage(MultipartFile file, String pathFolder) throws FileNotFoundException, IOException {
//           File path = new File("D:\\testupload\\" + file.getOriginalFilename());
        File path = new File(pathFolder + file.getOriginalFilename());
        path.createNewFile();
        FileOutputStream output = new FileOutputStream(path);
        output.write(file.getBytes());
        output.close();
    }

    public List<Produit> findProduitByName(String name) {
        List<Produit> listp = new ArrayList<Produit>();
        listp = produitRepository.findByNomContainingIgnoreCase(name);
        return listp;
    }

    public Page<Produit> findByProductByNameWithPagination(String name, int pageNo, int pageSize) {
//        rehefa tsisy sort
        Pageable pageable = new PageRequest(pageNo, pageSize);
        Page<Produit> page = produitRepository.findAll(pageable);
        System.out.println("****************************************");
        System.out.println(page.getTotalPages());
        return page;
    }

    public Page<Produit> produitsPagination(String name, Pageable p) {
        Page<Produit> page = produitRepository.findByNomContainingIgnoreCase(name, p);
        return page;
    }

    public Produit findProduitById(Integer id) {
        return produitRepository.findOne(id);
    }

    public Page<Produit> findProductByCategorie(Integer id, Pageable p) {
        Categorie c = findCategorie(id);
        return produitRepository.findByIdcategorie(c, p);

    }

    public List<Produit> findProductByCategorie(Integer id) {
        Categorie c = findCategorie(id);
        return produitRepository.findByIdcategorie(c);
    }

    public void insertMoney(Portefeuille p) throws Exception {
        try {
            portefeuilleRepository.save(p);
        } catch (Exception e) {
            throw e;
        }

    }

    public Customer findCustomerByName(String name) {
        return customerRepository.findByName(name);
    }

    public List<Integer> getProductListFromQuantity(List<Integer> list, Integer id, Integer qte) {
        for (int i = 0; i < qte; i++) {
            list.add(id);
        }
        return list;
    }

    public List<ProductQuantity> getProductQuantityList(List<ProductQuantity> list, Integer id, Integer qte) {
        list.add(new ProductQuantity(id, qte));
        return list;
    }

    public List<Produit> findProductInCart(List<Integer> listp) {
        List<Produit> listproduit = new ArrayList<Produit>();
        Produit p = findProduitById(listp.get(0));
        listproduit.add(p);
        for (int i = 1; i < listp.size(); i++) {
            if (!Objects.equals(listp.get(i), p.getId())) {
                p = findProduitById(listp.get(i));
            }
            listproduit.add(p);
        }
        return listproduit;
    }

    public Integer getTotalProductPrice(List<Produit> listproduit) {
        Integer sum = 0;
        for (Produit p : listproduit) {
            sum += p.getPrix().intValue();
        }
        return sum;
    }

    public void estValidePaiement(List<Produit> listproduit, Customer c) {
        Integer sum = getTotalProductPrice(listproduit);
        if (sum > c.getSolde()) {
            throw new RuntimeException("portefeuille insufisant");
        }

    }

    public void checkQteProduit(List<ProductQuantity> listp) {
        for (ProductQuantity p : listp) {
            if (p.getQte() > findProduitById(p.getId()).getQte().intValue()) {
                throw new RuntimeException("qte insuffisant dans le stock");

            }
        }
    }

    @Transactional
    public void updateMvtStockWhenInsertCmd(List<ProductQuantity> listp) {
        for (ProductQuantity p : listp) {
            Produit pd = findProduitById(p.getId());
            Mvstock mvt = new Mvstock();
            mvt.setEtat(new BigDecimal(p.getQte() * -1));
            mvt.setIdproduit(pd);
            mvt.setDatemvtstock(new Date());
            mvtStockRepository.save(mvt);
            pd.setQte(new BigDecimal((pd.getQte().intValue() - p.getQte())));
            produitRepository.save(pd);
        }

    }

    @Transactional
    public void insertAchatWhenValidCart(List<Produit> listp, Customer c) {
        Achat a = new Achat();
        a.setIdcustomer(c);
        a.setDateachat(new Date());
        a.setTotal(new BigDecimal(getTotalProductPrice(listp)));
        a = achatRepository.save(a);

        for (Produit p : listp) {
            Detailsachat d = new Detailsachat();
            d.setIdachat(a.getId());
            d.setIdproduit(p);
            detailsachatRepository.save(d);
        }
    }

    @Transactional
    public void substractCustomerPortfolio(List<Produit> listproduit, Customer c) {
        Integer sum = getTotalProductPrice(listproduit);
        c.setSolde(c.getSolde() - sum);
        customerRepository.save(c);
    }

    @Transactional
    public void validerMoneyClient(List<Produit> listproduit, Customer c) {
        Integer sum = getTotalProductPrice(listproduit);
        c.setSolde(c.getSolde() - sum);
        customerRepository.save(c);

    }
//    ----------

    public Portefeuille findPortefeuilleByIdCustomerAndSet(Customer c) {
        Portefeuille p = new Portefeuille();
        try {
            p = portefeuilleRepository.findByIdcustomer(c);
//            p.setId(null);
            p.setEtat("valide");
            portefeuilleRepository.save(p);
            Customer customer=customerRepository.findOne(c.getId());
            c.setSolde(c.getSolde() + p.getMontant());
        } catch (Exception e) {
            throw e;
        }

        return p;
    }
    
    public List<Portefeuille> findPortefeuilleByEtat(String etat){
        return portefeuilleRepository.findByEtat(etat);
    }
    public Customer findCustomerById(Integer id){
        return customerRepository.findOne(id);
    }

}

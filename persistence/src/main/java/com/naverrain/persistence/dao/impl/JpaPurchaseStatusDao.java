package com.naverrain.persistence.dao.impl;

import com.naverrain.persistence.dao.PurchaseStatusDao;
import com.naverrain.persistence.dto.PurchaseStatusDto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

@Repository
public class JpaPurchaseStatusDao implements PurchaseStatusDao {

    @Override
    public PurchaseStatusDto getPurchaseStatusById(Integer id) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        try {
            emf = Persistence.createEntityManagerFactory("persistence-unit");
            em = emf.createEntityManager();
            em.getTransaction().begin();

            PurchaseStatusDto purchaseStatus = em.find(PurchaseStatusDto.class, id);

            em.getTransaction().commit();

            return purchaseStatus;
        }
        finally {
            if (emf != null){
                emf.close();
            }
            if (em != null){
                em.close();
            }
        }
    }
}

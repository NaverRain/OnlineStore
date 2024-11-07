package com.naverrain.persistence.dao.impl;

import com.naverrain.persistence.dao.PurchaseStatusDao;
import com.naverrain.persistence.dto.PurchaseStatusDto;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class JpaPurchaseStatusDao implements PurchaseStatusDao {

    @Override
    public PurchaseStatusDto getPurchaseStatusById(Integer id) {
        try (var emf = Persistence.createEntityManagerFactory("persistence-unit");
                var em = emf.createEntityManager()){
            em.getTransaction().begin();

            PurchaseStatusDto purchaseStatus = em.find(PurchaseStatusDto.class, id);

            em.getTransaction().commit();

            return purchaseStatus;
        }
    }
}

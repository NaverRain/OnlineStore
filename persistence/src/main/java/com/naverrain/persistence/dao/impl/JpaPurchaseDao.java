package com.naverrain.persistence.dao.impl;

import com.naverrain.persistence.dao.PurchaseDao;
import com.naverrain.persistence.dto.ProductDto;
import com.naverrain.persistence.dto.PurchaseDto;
import com.naverrain.persistence.dto.PurchaseStatusDto;
import com.naverrain.persistence.dto.UserDto;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

@Repository
public class JpaPurchaseDao implements PurchaseDao {

    @Override
    public void savePurchase(PurchaseDto purchase) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        try {
            emf = Persistence.createEntityManagerFactory("persistence-unit");
            em = emf.createEntityManager();
            em.getTransaction().begin();

            em.persist(purchase);

            em.getTransaction().commit();
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

    @Override
    public List<PurchaseDto> getPurchaseByUserId(int userId) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        try {
            emf = Persistence.createEntityManagerFactory("persistence-unit");
            em = emf.createEntityManager();
            em.getTransaction().begin();


            TypedQuery<PurchaseDto> query = em.createQuery(
                    "SELECT p FROM purchase p WHERE p.userDto.id = :id", PurchaseDto.class);
            query.setParameter("id", userId);

            List<PurchaseDto> purchases = query.getResultList();
            em.getTransaction().commit();
            return purchases;
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

    @Override
    public List<PurchaseDto> getPurchases() {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        try {
            emf = Persistence.createEntityManagerFactory("persistence-unit");
            em = emf.createEntityManager();
            em.getTransaction().begin();

            TypedQuery<PurchaseDto> query = em.createQuery("SELECT p FROM purchase p", PurchaseDto.class);
            List<PurchaseDto> purchases = query.getResultList();
            em.getTransaction().commit();

            return purchases;
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

    @Override
    public List<PurchaseDto> getNotCompletedPurchases(Integer lastFulfilmentStageId) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        try {
            emf = Persistence.createEntityManagerFactory("persistence-unit");
            em = emf.createEntityManager();
            em.getTransaction().begin();

            Query query = em.createQuery(
                    "SELECT p.id, p.userDto, p.purchaseStatusDto FROM purchase p WHERE p.purchaseStatusDto.id != :statusId");
            query.setParameter("statusId", lastFulfilmentStageId);

            List<Object[]> result = query.getResultList();
            List<PurchaseDto> purchases = new ArrayList<>();
            for (Object[] resultTuple : result) {
                purchases.add(new PurchaseDto(
                        (Integer)resultTuple[0],
                        (UserDto)resultTuple[1],
                        (PurchaseStatusDto)resultTuple[2]));
            }

            em.getTransaction().commit();
            return purchases;
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

    @Override
    public PurchaseDto getPurchaseById(Integer id) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        try {
            emf = Persistence.createEntityManagerFactory("persistence-unit");
            em = emf.createEntityManager();
            em.getTransaction().begin();

            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<PurchaseDto> criteriaQuery = criteriaBuilder.createQuery(PurchaseDto.class);
            Root<PurchaseDto> purchaseRoot = criteriaQuery.from(PurchaseDto.class);
            purchaseRoot.fetch("userDto");
            purchaseRoot.fetch("productDtos");
            Query query = em.createQuery(criteriaQuery.select(purchaseRoot)
                    .where(criteriaBuilder
                            .equal(purchaseRoot.get("id"), id)));

            PurchaseDto purchase = (PurchaseDto)query.getSingleResult();
            em.getTransaction().commit();

            return purchase;
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

    @Override
    public void updatePurchase(PurchaseDto purchaseDto) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        try {
            emf = Persistence.createEntityManagerFactory("persistence-unit");
            em = emf.createEntityManager();
            em.getTransaction().begin();

            em.merge(purchaseDto);
            em.getTransaction().commit();
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

package com.naverrain.persistence.dao.impl;

import com.naverrain.persistence.dao.PrivilegeDao;
import com.naverrain.persistence.dto.PrivilegeDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

@Repository
public class JpaPrivilegeDao implements PrivilegeDao {

    @Override
    public void save(PrivilegeDto privilege) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        try {
            emf = Persistence.createEntityManagerFactory("persistence-unit");
            em = emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(privilege);
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
    public PrivilegeDto getPrivilegeByName(String name) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        try {
            emf = Persistence.createEntityManagerFactory("persistence-unit");
            em = emf.createEntityManager();
            em.getTransaction().begin();

            TypedQuery<PrivilegeDto> query = em.createQuery("SELECT p FROM privilege p WHERE p.name = :privilegeName", PrivilegeDto.class);
            query.setParameter("privilegeName", name);

            PrivilegeDto privilegeDto = query.getResultList().stream().findFirst().orElse(null);

            em.getTransaction().commit();
            return privilegeDto;
        } finally {
            if (emf != null) {
                emf.close();
            }
            if (em != null) {
                em.close();
            }
        }
    }

}

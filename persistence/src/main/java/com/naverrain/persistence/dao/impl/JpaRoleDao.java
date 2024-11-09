package com.naverrain.persistence.dao.impl;

import com.naverrain.persistence.dao.RoleDao;
import com.naverrain.persistence.dto.RoleDto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

@Repository
public class JpaRoleDao implements RoleDao {

    @Override
    public RoleDto getRoleById(int id) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        try {
            emf = Persistence.createEntityManagerFactory("persistence-unit");
            em = emf.createEntityManager();
            em.getTransaction().begin();

            RoleDto role = em.find(RoleDto.class, id);
            em.getTransaction().commit();
            return role;
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
    public RoleDto getRoleByRoleName(String roleName) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        try {
            emf = Persistence.createEntityManagerFactory("persistence-unit");
            em = emf.createEntityManager();
            em.getTransaction().begin();

            TypedQuery<RoleDto> query = em.createQuery("SELECT r FROM role r WHERE r.roleName = :role", RoleDto.class);
            query.setParameter("role", roleName);

            RoleDto role = query.getSingleResult();
            em.getTransaction().commit();
            return role;
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

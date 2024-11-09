package com.naverrain.persistence.dao.impl;

import com.naverrain.persistence.dao.UserDao;
import com.naverrain.persistence.dto.UserDto;
import com.naverrain.persistence.entities.User;

import javax.persistence.*;

import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public class JpaUserDao implements UserDao {

    @Override
    public boolean saveUser(UserDto user) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        try {
            emf = Persistence.createEntityManagerFactory("persistence-unit");
            em = emf.createEntityManager();
            em.getTransaction().begin();

            em.persist(user);
            em.getTransaction().commit();
            return true;
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
    public List<UserDto> getUsers() {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        try {
            emf = Persistence.createEntityManagerFactory("persistence-unit");
            em = emf.createEntityManager();
            em.getTransaction().begin();

            List<UserDto> users = em.createQuery("SELECT u FROM user u", UserDto.class)
                    .getResultList();

            em.getTransaction().commit();
            return users;
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
    public UserDto getUserById(int id) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        try {
            emf = Persistence.createEntityManagerFactory("persistence-unit");
            em = emf.createEntityManager();
            em.getTransaction().begin();

            UserDto user = em.find(UserDto.class, id);
            em.getTransaction().commit();
            return user;
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
    public UserDto getUserByEmail(String email) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        try {
            emf = Persistence.createEntityManagerFactory("persistence-unit");
            em = emf.createEntityManager();
            em.getTransaction().begin();

            UserDto user = null;
            try {
                 user = em.createQuery("SELECT u FROM user u WHERE u.email = :email", UserDto.class)
                        .setParameter("email", email)
                        .getSingleResult();
            }
            catch (NoResultException e){
                user = null;
            }
            em.getTransaction().commit();

            return user;
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
    public UserDto getUserByPartnerCode(String partnerCode) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        try {
            emf = Persistence.createEntityManagerFactory("persistence-unit");
            em = emf.createEntityManager();
            em.getTransaction().begin();

            TypedQuery<UserDto> query = em.createQuery("SELECT u FROM user u WHERE u.partnerCode = :code", UserDto.class);
            query.setParameter("code", partnerCode);

            try {
                UserDto user = query.getSingleResult();
                em.getTransaction().commit();
                return user;
            } catch (NoResultException e) {
                em.getTransaction().rollback();
                return null;
            }
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
    public void updateUser(UserDto user) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        try {
            emf = Persistence.createEntityManagerFactory("persistence-unit");
            em = emf.createEntityManager();
            em.getTransaction().begin();

            em.merge(user);
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
    public List<UserDto> getReferralsByUserId(int id) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        try {
            emf = Persistence.createEntityManagerFactory("persistence-unit");
            em = emf.createEntityManager();
            em.getTransaction().begin();

            TypedQuery<UserDto> query = em.createQuery("SELECT u FROM user u WHERE u.referrerUser.id = :id", UserDto.class);
            query.setParameter("id", id);

            List<UserDto> users = query.getResultList();
            em.getTransaction().commit();

            return users;
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

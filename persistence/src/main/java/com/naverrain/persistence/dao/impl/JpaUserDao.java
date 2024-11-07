package com.naverrain.persistence.dao.impl;

import com.naverrain.persistence.dao.UserDao;
import com.naverrain.persistence.dto.UserDto;
import com.naverrain.persistence.entities.User;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.sql.SQLException;
import java.util.List;

public class JpaUserDao implements UserDao {

    @Override
    public boolean saveUser(UserDto user) {
        try (var emf = Persistence.createEntityManagerFactory("persistence-unit");
                var em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
            return true;
        }
    }

    @Override
    public List<UserDto> getUsers() {
        try (var emf = Persistence.createEntityManagerFactory("persistence-unit");
             var em = emf.createEntityManager()) {
            em.getTransaction().begin();

            List<UserDto> users = em.createQuery("SELECT u FROM user u", UserDto.class) // Fixed entity name
                    .getResultList();

            em.getTransaction().commit();
            return users;
        }
    }
    @Override
    public UserDto getUserById(int id) {
        try (var emf = Persistence.createEntityManagerFactory("persistence-unit");
             var em = emf.createEntityManager()){
            em.getTransaction().begin();
            UserDto user = em.find(UserDto.class, id);
            em.getTransaction().commit();
            return user;
        }
    }

    @Override
    public UserDto getUserByEmail(String email) {
        try (var emf = Persistence.createEntityManagerFactory("persistence-unit");
             var em = emf.createEntityManager()) {
            em.getTransaction().begin();

            UserDto user = null;
            try {
                 user = em.createQuery("SELECT u FROM user u WHERE u.email = :email", UserDto.class) // Fixed entity name
                        .setParameter("email", email)
                        .getSingleResult();
            }
            catch (NoResultException e){
                user = null;
            }
            em.getTransaction().commit();

            return user;
        }
    }

    @Override
    public UserDto getUserByPartnerCode(String partnerCode) {
        try (var emf = Persistence.createEntityManagerFactory("persistence-unit");
             var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            TypedQuery<UserDto> query = em.createQuery("SELECT u FROM user u WHERE u.partnerCode = :code", UserDto.class); // Fixed entity name
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
    }

    @Override
    public void updateUser(UserDto user) {
        try (var emf = Persistence.createEntityManagerFactory("persistence-unit");
             var em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();
        }
    }

    @Override
    public List<UserDto> getReferralsByUserId(int id) {
        try (var emf = Persistence.createEntityManagerFactory("persistence-unit");
             var em = emf.createEntityManager()) {
            em.getTransaction().begin();

            TypedQuery<UserDto> query = em.createQuery("SELECT u FROM user u WHERE u.referrerUser.id = :id", UserDto.class); // Fixed entity name and alias
            query.setParameter("id", id);

            List<UserDto> users = query.getResultList();
            em.getTransaction().commit();

            return users;
        }
    }
}

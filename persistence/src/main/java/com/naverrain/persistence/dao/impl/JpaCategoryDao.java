package com.naverrain.persistence.dao.impl;

import com.naverrain.persistence.dao.CategoryDao;
import com.naverrain.persistence.dto.CategoryDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.List;

@Repository
public class JpaCategoryDao implements CategoryDao {

    @Override
    public CategoryDto getCategoryByCategoryId(int id) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        try{
            emf = Persistence.createEntityManagerFactory("persistence-unit");
            em = emf.createEntityManager();
            em.getTransaction().begin();

            CategoryDto category = em.find(CategoryDto.class, id);
            em.getTransaction().commit();
            return category;
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
    public List<CategoryDto> getCategories() {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        try {
            emf = Persistence.createEntityManagerFactory("persistence-unit");
            em = emf.createEntityManager();
            em.getTransaction().begin();

            List<CategoryDto> categories = em.createQuery("SELECT c FROM category c", CategoryDto.class)
                    .getResultList();
            em.getTransaction().commit();
            return categories;
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

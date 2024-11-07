package com.naverrain.persistence.dao.impl;

import com.naverrain.persistence.dao.ProductDao;
import com.naverrain.persistence.dto.ProductDto;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class JpaProductDao implements ProductDao {

    @Override
    public List<ProductDto> getProducts() {
        try (var emf = Persistence.createEntityManagerFactory("persistence-unit");
               var em = emf.createEntityManager() ){
            em.getTransaction().begin();

            List<ProductDto> products = em.createQuery("SELECT p FROM product p", ProductDto.class).getResultList();
            em.getTransaction().commit();
            return products;
        }
    }

    @Override
    public ProductDto getProductByProductId(int id) {
        try (var emf = Persistence.createEntityManagerFactory("persistence-unit");
                var em = emf.createEntityManager()){
            em.getTransaction().begin();

            ProductDto product = em.find(ProductDto.class, id);
            em.getTransaction().commit();
            return product;
        }
    }

    @Override
    public List<ProductDto> getProductsLikeName(String searchQuery) {
        try (var emf = Persistence.createEntityManagerFactory("persistence-unit");
                var em = emf.createEntityManager()){
            em.getTransaction().begin();

           Query query = em.createNativeQuery(
                    "SELECT * FROM product WHERE UPPER(product_name) LIKE UPPER (CONCAT('%',?1,'%'))",
                    ProductDto.class);
           query.setParameter(1, searchQuery);

            List<ProductDto> products = query.getResultList();
            em.getTransaction().commit();
            return products;
        }
    }

    @Override
    public List<ProductDto> getProductsByCategoryId(Integer id) {
        try (var emf = Persistence.createEntityManagerFactory("persistence-unit");
                var em = emf.createEntityManager()){
            em.getTransaction().begin();

            TypedQuery<ProductDto> query = em.createQuery("SELECT p FROM product p WHERE p.categoryDto.id = :id", ProductDto.class);
            query.setParameter("id", id);

            List<ProductDto> products = query.getResultList();
            em.getTransaction().commit();
            return products;
        }
    }

    @Override
    public List<ProductDto> getProductsByCategoryIdPaginationLimit(Integer categoryId, Integer page, Integer limit) {
        try (var emf = Persistence.createEntityManagerFactory("persistence-unit");
             var em = emf.createEntityManager()){
            em.getTransaction().begin();

            TypedQuery<ProductDto> query = em.createQuery("SELECT p FROM product p WHERE p.categoryDto.id = :id", ProductDto.class);
            query.setParameter("id", categoryId);

            query.setFirstResult((page - 1) * limit);
            query.setMaxResults(limit);

            List<ProductDto> products = query.getResultList();
            em.getTransaction().commit();
            return products;
        }
    }

    @Override
    public Integer getProductCountForCategory(Integer categoryId) {
        try (var emf = Persistence.createEntityManagerFactory("persistence-unit");
             var em = emf.createEntityManager()){
            em.getTransaction().begin();
            TypedQuery<Long> query = em.createQuery("SELECT COUNT(p) FROM product p WHERE p.categoryDto.id = :id", Long.class);
            query.setParameter("id", categoryId);

            Long count = query.getSingleResult();
            em.getTransaction().commit();
            return count.intValue();
        }
    }

    @Override
    public Integer getProductCountForSearch(String searchQuery) {
        try (var emf = Persistence.createEntityManagerFactory("persistence-unit");
             var em = emf.createEntityManager()){
            em.getTransaction().begin();

            Query query = em.createQuery("SELECT COUNT(*) FROM product WHERE UPPER(product_name) LIKE UPPER(CONCAT('%',:searchQuery,'%'))", Integer.class);
            query.setParameter("searchQuery", searchQuery);
            Integer count = (Integer)query.getSingleResult();
            em.getTransaction().commit();

            return count;
        }
    }

    @Override
    public List<ProductDto> getProductsLikeNameForPageWithLimit(String searchQuery, Integer page, Integer limit) {
        try (var emf = Persistence.createEntityManagerFactory("persistence-unit");
             var em = emf.createEntityManager()){
            em.getTransaction().begin();

            Query query = em.createQuery(
                    "SELECT p.id, p.guid, p.product_name, p.description, p.price, p.category_id, p.img_name, " +
                            "c.id as cat_id, c.category_name, c.img_name as cat_img " +
                            "FROM product p JOIN category c ON p.category_id = c.id " +
                            "WHERE UPPER(product_name) LIKE UPPER(CONCAT('%',:searchQuery,'%')) LIMIT :offset, :limit", ProductDto.class);
            query.setParameter("searchQuery", searchQuery);
            query.setParameter("offset", (page - 1) * limit);
            query.setParameter("limit", limit);

            List<ProductDto> products = query.getResultList();
            em.getTransaction().commit();

            return products;
        }
    }

    @Override
    public ProductDto getProductByProductGuid(String guid) {
        try (var emf = Persistence.createEntityManagerFactory("persistence-unit");
             var em = emf.createEntityManager()){
            em.getTransaction().begin();

            TypedQuery<ProductDto> query = em.createQuery("SELECT p FROM product p WHERE p.guid=:guid", ProductDto.class);
            query.setParameter("guid", guid);
            ProductDto product = query.getSingleResult();

            em.getTransaction().commit();
            return product;
        }
    }
}
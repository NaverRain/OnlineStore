package com.naverrain.persistence;

import com.naverrain.persistence.dao.impl.JpaCategoryDao;
import com.naverrain.persistence.dto.CategoryDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ConnectionTest {
    public static void main(String[] args) {
        JpaCategoryDao categoryDao = new JpaCategoryDao();

        // Test getCategoryByCategoryId method
        int testId = 1; // Replace with a valid category ID from your database
        CategoryDto category = categoryDao.getCategoryByCategoryId(testId);
        System.out.println("Category with ID " + testId + ": " + category);

        // Test getCategories method
        var categories = categoryDao.getCategories();
        System.out.println("All categories:");
        for (CategoryDto cat : categories) {
            System.out.println(cat);
        }
    }
}

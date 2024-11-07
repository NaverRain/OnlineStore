package com.naverrain.core.facades.impl;

import com.naverrain.core.facades.CategoryFacade;
import com.naverrain.persistence.dao.CategoryDao;
import com.naverrain.persistence.dao.impl.JpaCategoryDao;
import com.naverrain.persistence.dto.converter.CategoryDtoToCategoryConverter;
import com.naverrain.persistence.entities.Category;
import java.util.List;

public class DefaultCategoryFacade implements CategoryFacade {

    private static DefaultCategoryFacade instance;

    private static CategoryDao categoryDao = new JpaCategoryDao();
    private static CategoryDtoToCategoryConverter converter = new CategoryDtoToCategoryConverter();

    public static synchronized DefaultCategoryFacade getInstance(){
        if (instance == null){
            instance = new DefaultCategoryFacade();
        }
        return instance;
    }

    @Override
    public List<Category> getCategories() {
        return converter.convertCategoryDtosToCategories(categoryDao.getCategories());
    }
}

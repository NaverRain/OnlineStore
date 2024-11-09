package com.naverrain.core.facades.impl;

import com.naverrain.core.facades.CategoryFacade;
import com.naverrain.persistence.dao.CategoryDao;
import com.naverrain.persistence.dto.converter.CategoryDtoToCategoryConverter;
import com.naverrain.persistence.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultCategoryFacade implements CategoryFacade {

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private CategoryDtoToCategoryConverter converter;


    @Override
    public List<Category> getCategories() {
        return converter.convertCategoryDtosToCategories(categoryDao.getCategories());
    }
}

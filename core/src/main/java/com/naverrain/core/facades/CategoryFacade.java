package com.naverrain.core.facades;

import com.naverrain.persistence.entities.Category;

import java.util.List;

public interface CategoryFacade {
    List<Category> getCategories();
}

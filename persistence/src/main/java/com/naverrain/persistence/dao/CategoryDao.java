package com.naverrain.persistence.dao;

import com.naverrain.persistence.dto.CategoryDto;

import java.util.List;

public interface CategoryDao {

    CategoryDto getCategoryByCategoryId(int id);

    List<CategoryDto> getCategories();
}

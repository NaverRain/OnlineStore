package com.naverrain.persistence.dto.converter;

import com.naverrain.persistence.dto.CategoryDto;
import com.naverrain.persistence.entities.Category;
import com.naverrain.persistence.entities.impl.DefaultCategory;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryDtoToCategoryConverter {

    public CategoryDto convertCategoryNameToCategoryDtoWithOnlyName(String categoryName){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryName(categoryName);
        return categoryDto;
    }

    public List<Category> convertCategoryDtosToCategories(List<CategoryDto> categoryDtos){
        List<Category> categories = new ArrayList<>();

        for (CategoryDto categoryDto : categoryDtos){
            categories.add(convertCategoryDtoToCategory(categoryDto));
        }
        return categories;
    }

    private Category convertCategoryDtoToCategory(CategoryDto categoryDto){
        DefaultCategory category = new DefaultCategory();
        category.setId(categoryDto.getId());
        category.setCategoryName(categoryDto.getCategoryName());
        category.setImgName(categoryDto.getImgName());
        return category;
    }
}

package com.naverrain.persistence.dao.impl;

import com.naverrain.persistence.dao.CategoryDao;
import com.naverrain.persistence.dto.CategoryDto;
import com.naverrain.persistence.utils.db.DBUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlJdbcCategoryDao implements CategoryDao {

    @Override
    public CategoryDto getCategoryByCategoryId(int id) {
        try (var connection = DBUtils.getConnection();
                var ps = connection.prepareStatement("SELECT * FROM category WHERE id = ?")){
                ps.setInt(1, id);

                try (var res = ps.executeQuery()){
                    if (res.next()){
                        CategoryDto category = new CategoryDto();
                        category.setId(res.getInt("id"));
                        category.setCategoryName(res.getString("category_name"));
                        category.setImgName(res.getString("img_name"));
                        return category;
                    }
                }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<CategoryDto> getCategories() {
        try (var connection = DBUtils.getConnection();
                var ps = connection.prepareStatement("SELECT * FROM category")){
                List<CategoryDto> categories = new ArrayList<>();
                try (var res = ps.executeQuery()){
                    while (res.next()){
                        CategoryDto categoryDto = new CategoryDto();
                        categoryDto.setId(res.getInt("id"));
                        categoryDto.setCategoryName(res.getString("category_name"));
                        categoryDto.setImgName(res.getString("img_name"));
                        categories.add(categoryDto);
                    }
                    return categories;
                }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}

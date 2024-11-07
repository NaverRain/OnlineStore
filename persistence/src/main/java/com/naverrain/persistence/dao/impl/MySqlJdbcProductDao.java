package com.naverrain.persistence.dao.impl;

import com.naverrain.persistence.dao.CategoryDao;
import com.naverrain.persistence.dao.ProductDao;
import com.naverrain.persistence.dto.ProductDto;
import com.naverrain.persistence.utils.db.DBUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlJdbcProductDao implements ProductDao {

    private CategoryDao categoryDao = new MySqlJdbcCategoryDao();

    @Override
    public List<ProductDto> getProducts() {
        try (var connection = DBUtils.getConnection();
                var ps = connection.prepareStatement("SELECT * FROM product");
                var res = ps.executeQuery();){
            List<ProductDto> products = new ArrayList<>();

            while (res.next()){
                ProductDto product = parseProductDto(res);
                products.add(product);
            }
            return products;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ProductDto getProductByProductId(int id) {
        try (var connection = DBUtils.getConnection();
                var ps = connection.prepareStatement("SELECT * FROM product WHERE id = ?")) {
            ps.setInt(1, id);
            try (var res = ps.executeQuery()){
                if (res.next()){
                    ProductDto product = parseProductDto(res);
                    return product;
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ProductDto> getProductsLikeName(String searchQuery) {
        try (var connection = DBUtils.getConnection();
                var ps = connection.prepareStatement("SELECT * FROM product WHERE UPPER(product_name) LIKE UPPER(CONCAT('%',?,'%'))")){
            ps.setString(1, searchQuery);
            try (var rs = ps.executeQuery()){
                List<ProductDto> products = new ArrayList<>();

                while (rs.next()){
                    ProductDto productDto = parseProductDto(rs);
                    products.add(productDto);
                }
                return products;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ProductDto> getProductsByCategoryId(Integer id) {
        try (var connection = DBUtils.getConnection();
                var ps = connection.prepareStatement("SELECT * FROM product WHERE id = ?")){
                ps.setInt(1, id);
                try (var rs = ps.executeQuery()){
                    List<ProductDto> products = new ArrayList<>();
                    while (rs.next()){
                        ProductDto productDto = parseProductDto(rs);
                        products.add(productDto);
                    }
                    return products;
                }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ProductDto> getProductsByCategoryIdPaginationLimit(Integer categoryId, Integer page, Integer limit) {
        try (var connection = DBUtils.getConnection();
                var ps = connection.prepareStatement("SELECT * FROM product WHERE category_id = ? LIMIT ?, ?")){
                ps.setInt(1, categoryId);
                ps.setInt(2, ((limit * page) - limit));
                ps.setInt(3, limit);
                try (var rs = ps.executeQuery()){
                    List<ProductDto> products = new ArrayList<>();
                    while (rs.next()){
                        ProductDto productDto = parseProductDto(rs);
                        products.add(productDto);
                    }
                    return products;
                }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer getProductCountForCategory(Integer categoryId) {
        try (var connection = DBUtils.getConnection();
                var ps = connection.prepareStatement("SELECT COUNT(id) as amount FROM product WHERE category_id = ?")){
            ps.setInt(1, categoryId);
            try (var rs = ps.executeQuery()){
                if (rs.next()){
                    return rs.getInt("amount");
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer getProductCountForSearch(String searchQuery) {
        try (var connection = DBUtils.getConnection();
             var ps = connection.prepareStatement("SELECT COUNT(id) as amount FROM product WHERE UPPER(product_name) LIKE UPPER(CONCAT('%',?'%'))")){
            ps.setString(1, searchQuery);
                try (var rs = ps.executeQuery()){
                    if (rs.next()){
                        return rs.getInt("amount");
                    }
                }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ProductDto> getProductsLikeNameForPageWithLimit(String searchQuery, Integer page, Integer limit) {
        try (var connection = DBUtils.getConnection();
             var ps = connection.prepareStatement("SELECT * FROM product WHERE UPPER(product_name) LIKE UPPER(CONCAT('%',?,'%')) LIMIT ?, ?")){
            ps.setString(1, searchQuery);
            ps.setInt(2, ((limit * page) - limit));
            ps.setInt(3, limit);
                try (var rs = ps.executeQuery()){
                    List<ProductDto> products = new ArrayList<>();
                    while (rs.next()){
                        ProductDto productDto = parseProductDto(rs);
                        products.add(productDto);
                    }
                    return products;
                }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ProductDto getProductByProductGuid(String guid) {
        try (var connection = DBUtils.getConnection();
             var ps = connection.prepareStatement("SELECT * FROM product WHERE guid = ?")) {
            ps.setString(1, guid);
            try (var res = ps.executeQuery()){
                if (res.next()){
                    ProductDto product = parseProductDto(res);
                    return product;
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    private ProductDto parseProductDto(ResultSet rs) throws SQLException{
        ProductDto productDto = new ProductDto();
        productDto.setId(rs.getInt("id"));
        productDto.setGuid(rs.getString("guid"));
        productDto.setProductName(rs.getString("product_name"));
        productDto.setDescription(rs.getString("description"));
        productDto.setPrice(rs.getBigDecimal("price"));
        productDto.setCategoryDto(categoryDao.getCategoryByCategoryId(rs.getInt("category_id")));
        productDto.setImgName(rs.getString("img_name"));

        return productDto;
    }
}

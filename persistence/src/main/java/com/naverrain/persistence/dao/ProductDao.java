package com.naverrain.persistence.dao;

import com.naverrain.persistence.dto.ProductDto;

import java.util.List;

public interface ProductDao {

    List<ProductDto> getProducts();

    ProductDto getProductByProductId(int id);

    List<ProductDto> getProductsLikeName(String searchQuery);

    List<ProductDto> getProductsByCategoryId(Integer id);

    List<ProductDto> getProductsByCategoryIdPaginationLimit(Integer categoryId, Integer page, Integer limit);

    Integer getProductCountForCategory(Integer categoryId);

    Integer getProductCountForSearch(String searchQuery);

    List<ProductDto> getProductsLikeNameForPageWithLimit(String searchQuery, Integer page, Integer limit);

    ProductDto getProductByProductGuid(String guid);
}

package com.naverrain.core.facades.impl;

import com.naverrain.core.facades.ProductFacade;
import com.naverrain.persistence.dao.ProductDao;
import com.naverrain.persistence.dto.converter.ProductDtoToProductConverter;
import com.naverrain.persistence.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultProductFacade implements ProductFacade {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ProductDtoToProductConverter converter;

    @Override
    public List<Product> getProductsLikeName(String searchQuery) {
        return converter.convertProductDtosToProducts(productDao.getProductsLikeName(searchQuery));
    }

    @Override
    public List<Product> getProductsByCategoryId(Integer id) {
        return converter.convertProductDtosToProducts(productDao.getProductsByCategoryId(id));
    }

    @Override
    public List<Product> getProductsByCategoryIdForPageWithLimit(Integer categoryId, Integer page, Integer paginationLimit) {
        return converter.convertProductDtosToProducts(productDao.getProductsByCategoryIdPaginationLimit(categoryId, page, paginationLimit));
    }

    @Override
    public Integer getNumberOfPagesForCategory(Integer categoryId, Integer paginationLimit) {
        Integer totalProducts = productDao.getProductCountForCategory(categoryId);
        int pages = totalProducts / paginationLimit;
        if ( (totalProducts % paginationLimit) != 0){
            pages++;
        }
        return pages;
    }

    @Override
    public Integer getNumberOfPagesForSearch(String searchQuery, Integer paginationLimit) {
        Integer totalProducts = productDao.getProductCountForSearch(searchQuery);
        int pages = totalProducts / paginationLimit;
        if ( (totalProducts % paginationLimit) != 0){
            pages++;
        }
        return pages;
    }

    @Override
    public List<Product> getProductsLikeNameForPageWithLimit(String searchQuery, Integer page, Integer paginationLimit) {
        return converter.convertProductDtosToProducts(productDao.getProductsLikeNameForPageWithLimit(searchQuery, page, paginationLimit));
    }

    @Override
    public Product getProductById(Integer id) {
        return converter.convertProductDtoToProduct(productDao.getProductByProductId(id));
    }

    @Override
    public Product getProductByGuid(String guid) {
        return converter.convertProductDtoToProduct(productDao.getProductByProductGuid(guid));
    }
}

package com.naverrain.core.facades.impl;

import com.naverrain.core.facades.ProductFacade;
import com.naverrain.persistence.dao.ProductDao;
import com.naverrain.persistence.dao.impl.JpaProductDao;
import com.naverrain.persistence.dto.converter.ProductDtoToProductConverter;
import com.naverrain.persistence.entities.Product;

import java.util.List;

public class DefaultProductFacade implements ProductFacade {

    private static DefaultProductFacade instance;

    private static ProductDao productDao = new JpaProductDao();
    private static ProductDtoToProductConverter converter = new ProductDtoToProductConverter();

    public static synchronized DefaultProductFacade getInstance(){
        if (instance == null){
            instance = new DefaultProductFacade();
        }
        return instance;
    }


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

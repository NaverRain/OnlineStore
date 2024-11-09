package com.naverrain.core.services.impl;

import com.naverrain.persistence.dao.ProductDao;
import com.naverrain.persistence.dto.ProductDto;
import com.naverrain.persistence.dto.converter.ProductDtoToProductConverter;
import com.naverrain.persistence.entities.Product;
import com.naverrain.core.services.ProductManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MySqlProductManagementService implements ProductManagementService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ProductDtoToProductConverter productConverter;


    @Override
    public List<Product> getProducts() {
        List<ProductDto> productDtos = productDao.getProducts();
        return productConverter.convertProductDtosToProducts(productDtos);
    }

    @Override
    public Product getProductById(int productId) {
        ProductDto productDto = productDao.getProductByProductId(productId);
        return productConverter.convertProductDtoToProduct(productDto);
    }
}

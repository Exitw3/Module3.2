package com.example.bt_product_management.service.impl;

import com.example.bt_product_management.model.Product;
import com.example.bt_product_management.repository.IProductRepository;
import com.example.bt_product_management.repository.impl.ProductRepository;
import com.example.bt_product_management.service.IProductService;

import java.util.List;

public class ProductService implements IProductService {
    IProductRepository productRepository = new ProductRepository();
    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(int id) {
        return null;
    }

    @Override
    public boolean save(Product product) {
        return productRepository.save(product);
    }

}
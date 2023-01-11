package com.example.bt_product_management.service;

import com.example.bt_product_management.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll();

    Product findById(int id);

    boolean save(Product product);
}
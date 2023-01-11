package com.example.bt_product_management.repository;

import com.example.bt_product_management.model.Product;

import java.util.List;

public interface IProductRepository {
    List<Product> findAll();

    Product findById(int id);

    Product findById(Double id);

    boolean save(Product product);

}
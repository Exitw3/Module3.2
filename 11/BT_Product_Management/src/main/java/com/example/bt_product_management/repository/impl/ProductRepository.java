package com.example.bt_product_management.repository.impl;

import com.example.bt_product_management.model.Product;
import com.example.bt_product_management.repository.IProductRepository;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository implements IProductRepository {
    private static List<Product> productList = new ArrayList<>();
    static {
        productList.add(new Product(1, "samsung", 5000));
        productList.add(new Product(2, "nokia", 3000));
        productList.add(new Product(3, "iphone", 7000));
    }

    @Override
    public List<Product> findAll() {
        return productList;
    }

    @Override
    public Product findById(int id) {
        return null;
    }

    @Override
    public Product findById(Double id) {
        return null;
    }

    @Override
    public boolean save(Product product) {
        return productList.add(product);
    }

}
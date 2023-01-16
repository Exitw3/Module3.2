package com.example.customermanager.service;

import com.example.customermanager.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> getAllCustomers();

     List<Product> getAllCustomersByKw(String kw);
    void addCustomer(Product customer);
    Product findCustomerById(long id);
    void updateCustomer(Product customer);
    void deleteCustomer(long id);

    public long findCustomerByIdMax();

    List<Product> getAllCustomerByIdCountry(long idCountry);

    int getNoOfRecords();
}

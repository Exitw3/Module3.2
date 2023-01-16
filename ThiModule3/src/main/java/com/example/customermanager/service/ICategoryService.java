package com.example.customermanager.service;

import com.example.customermanager.model.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> getAllCategory();
    Category findCountryById(long id);

    boolean insertCountryBySP(String nameCountry);


    boolean testProcedure();
}

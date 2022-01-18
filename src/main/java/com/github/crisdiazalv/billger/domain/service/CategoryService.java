package com.github.crisdiazalv.billger.domain.service;

import com.github.crisdiazalv.billger.domain.model.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findAll();

    Category findById(long id);

    void save(Category category);

    void deleteById(long id);

}

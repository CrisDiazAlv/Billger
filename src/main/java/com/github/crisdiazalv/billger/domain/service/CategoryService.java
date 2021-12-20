package com.github.crisdiazalv.billger.domain.service;

import com.github.crisdiazalv.billger.domain.model.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findAll();

    void save(Category category);

}

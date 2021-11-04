package com.github.crisdiazalv.billger.infrastructure.service;

import com.github.crisdiazalv.billger.domain.model.Category;
import com.github.crisdiazalv.billger.domain.service.CategoryService;
import com.github.crisdiazalv.billger.infrastructure.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Category> findAll() {
        return repository.findAll();
    }

    @Override
    public void save(Category category) {
        repository.save(category);
    }


}

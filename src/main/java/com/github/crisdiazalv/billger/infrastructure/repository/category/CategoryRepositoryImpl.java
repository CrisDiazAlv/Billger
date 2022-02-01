package com.github.crisdiazalv.billger.infrastructure.repository.category;

import com.github.crisdiazalv.billger.domain.model.Category;
import com.github.crisdiazalv.billger.domain.repository.CategoryRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

    private final CategoryJpaRepository repository;

    public CategoryRepositoryImpl(CategoryJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Category save(Category category) {
        return repository.save(category);
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }

}

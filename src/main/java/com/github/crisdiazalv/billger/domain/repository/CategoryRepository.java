package com.github.crisdiazalv.billger.domain.repository;

import com.github.crisdiazalv.billger.domain.model.Category;

public interface CategoryRepository {

    Category save(Category category);

    void deleteById(long id);

}

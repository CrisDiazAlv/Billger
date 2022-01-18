package com.github.crisdiazalv.billger.infrastructure.service;

import com.github.crisdiazalv.billger.domain.exception.NotFoundException;
import com.github.crisdiazalv.billger.domain.model.Category;
import com.github.crisdiazalv.billger.domain.model.User;
import com.github.crisdiazalv.billger.domain.model.UserPrincipal;
import com.github.crisdiazalv.billger.domain.service.CategoryService;
import com.github.crisdiazalv.billger.infrastructure.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
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

    @Transactional(readOnly = true)
    @Override
    public Category findById(long id) {
        User user = getUser();
        return user.getCategories()
                .stream()
                .filter(c -> c.getId() == id)
                .findAny()
                .orElseThrow(() -> new NotFoundException("La categoria no existe"));
    }

    @Transactional
    @Override
    public void save(Category category) {
        User user = getUser();
        category.setUser(user);
        log.info("Saving new category {}", category);
        repository.save(category);
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        Category category = findById(id);
        log.info("Deleting account '{}'", category.getName());
        repository.delete(category);
    }

    private User getUser() {
        return ((UserPrincipal) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal())
                .getUser();
    }

}

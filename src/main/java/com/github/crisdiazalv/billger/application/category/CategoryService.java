package com.github.crisdiazalv.billger.application.category;

import com.github.crisdiazalv.billger.domain.exception.NotFoundException;
import com.github.crisdiazalv.billger.domain.model.Category;
import com.github.crisdiazalv.billger.domain.model.User;
import com.github.crisdiazalv.billger.domain.model.UserPrincipal;
import com.github.crisdiazalv.billger.domain.repository.CategoryRepository;
import com.github.crisdiazalv.billger.domain.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class CategoryService {

    private final CategoryRepository repository;
    private final UserRepository userRepository;

    @Autowired
    public CategoryService(CategoryRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<Category> findAll() {
        User user = userRepository.findByUsername(getUser().getUsername())
                .orElseThrow(() -> new NotFoundException("El usuario no existe"));
        log.info("Found {} categories for user '{}'", user.getCategories().size(), user.getName());
        return user.getCategories();
    }

    @Transactional(readOnly = true)
    public Category findById(long id) {
        User user = userRepository.findByUsername(getUser().getUsername())
                .orElseThrow(() -> new NotFoundException("El usuario no existe"));
        return user.getCategories()
                .stream()
                .filter(c -> c.getId() == id)
                .findAny()
                .orElseThrow(() -> new NotFoundException("La categoria no existe"));
    }

    @Transactional
    public void save(Category category) {
        User user = getUser();
        category.setUser(user);
        log.info("Saving new category: {}", category);
        repository.save(category);
    }

    @Transactional
    public void deleteById(long id) {
        Category category = findById(id);
        log.info("Deleting category '{}'", category.getName());
        // first, remove it from the user
        category.getUser().getCategories().remove(category);
        // delete it
        repository.deleteById(id);
    }

    private User getUser() {
        return ((UserPrincipal) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal())
                .getUser();
    }

}

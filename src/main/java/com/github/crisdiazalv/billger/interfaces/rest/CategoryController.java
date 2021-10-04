package com.github.crisdiazalv.billger.interfaces.rest;


import com.github.crisdiazalv.billger.domain.model.Category;
import com.github.crisdiazalv.billger.domain.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @PostMapping
    public void save(@RequestBody Category category) {
        service.save(category);
    }

    @GetMapping
    public List<Category> findAll() {
        return service.findAll();
    }


}

package com.github.crisdiazalv.billger.interfaces.rest;


import com.github.crisdiazalv.billger.domain.model.Bill;
import com.github.crisdiazalv.billger.domain.model.Category;
import com.github.crisdiazalv.billger.domain.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService service;

    @Autowired
    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody Category category) {
        service.save(category);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<Category>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }



}

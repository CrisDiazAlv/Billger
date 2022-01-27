package com.github.crisdiazalv.billger.interfaces.rest.controller;

import com.github.crisdiazalv.billger.application.service.CategoryService;
import com.github.crisdiazalv.billger.interfaces.rest.dto.category.CategoryDTO;
import com.github.crisdiazalv.billger.interfaces.rest.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService service;
    private final CategoryMapper mapper;

    @Autowired
    public CategoryController(CategoryService service, CategoryMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll() {
        List<CategoryDTO> categories = mapper.toDTOList(service.findAll());
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> findById(@PathVariable long id) {
        CategoryDTO category = mapper.toCategoryDTO(service.findById(id));
        return ResponseEntity.ok(category);
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody CategoryDTO category) {
        service.save(mapper.toCategory(category));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        service.deleteById(id);
        return ResponseEntity.accepted().build();
    }

}

package com.github.crisdiazalv.billger.interfaces.rest;


import com.github.crisdiazalv.billger.domain.service.CategoryService;
import com.github.crisdiazalv.billger.interfaces.rest.dto.CategoryDTO;
import com.github.crisdiazalv.billger.interfaces.rest.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody CategoryDTO category) {
        service.save(mapper.toCategory(category));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll() {
        List<CategoryDTO> categories = mapper.toDTOList(service.findAll());
        return ResponseEntity.status(HttpStatus.OK).body(categories);
    }


}

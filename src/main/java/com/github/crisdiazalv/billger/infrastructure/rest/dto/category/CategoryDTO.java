package com.github.crisdiazalv.billger.infrastructure.rest.dto.category;

import lombok.Data;

@Data
public class CategoryDTO {

    private long id;
    private String name;
    private String color;
    private String description;

}

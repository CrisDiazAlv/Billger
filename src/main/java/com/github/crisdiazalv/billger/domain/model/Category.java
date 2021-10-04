package com.github.crisdiazalv.billger.domain.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Category {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String color;
    private String description;


}

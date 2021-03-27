package com.itsubedibesh.wallmart.controllers.web.Categories;

import javax.validation.constraints.NotBlank;

public class CategoriesDto {
    private long id;
    @NotBlank(message = "Category Name is Required")
    private String name;
    private String description;

    public CategoriesDto() {
    }

    public CategoriesDto(long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public CategoriesDto(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

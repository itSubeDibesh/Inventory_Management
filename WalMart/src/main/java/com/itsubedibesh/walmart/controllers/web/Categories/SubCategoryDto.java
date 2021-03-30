package com.itsubedibesh.walmart.controllers.web.Categories;

public class SubCategoryDto {

    private Long parentId;
    private Long categoryId;

    public SubCategoryDto(Long parentId, Long categoryId) {
        this.parentId = parentId;
        this.categoryId = categoryId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public Long getParentId() {
        return parentId;
    }
}

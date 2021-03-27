package com.itsubedibesh.wallmart.controllers.api.Categories;

public class SubCategory {

    private Long parentId;
    private Long categoryId;

    public SubCategory(Long parentId, Long categoryId) {
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

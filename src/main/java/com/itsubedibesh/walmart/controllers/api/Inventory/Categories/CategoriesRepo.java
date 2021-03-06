package com.itsubedibesh.walmart.controllers.api.Inventory.Categories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriesRepo extends JpaRepository<Categories,Long> {
    List<Categories> findCategoriesByParentIdIsNotNull();
}

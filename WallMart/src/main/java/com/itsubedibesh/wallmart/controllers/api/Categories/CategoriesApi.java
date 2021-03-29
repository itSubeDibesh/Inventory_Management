package com.itsubedibesh.wallmart.controllers.api.Categories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CategoriesApi {

    @Autowired
    CategoriesRepo categoriesRepo;

    @GetMapping("/categories")
    public ResponseEntity<List<Categories>> getAllCategories() {
        try {
            List<Categories> categories = new ArrayList<Categories>();
            categoriesRepo.findAll().forEach(categories::add);
            if (categories.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(categories, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/categories/subcategories")
    public ResponseEntity<List<Categories>> getAllSubCategories() {
        try {
            List<Categories> categories = new ArrayList<Categories>();
            categoriesRepo.findCategoriesByParentIdIsNotNull().forEach(categories::add);
            if (categories.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(categories, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

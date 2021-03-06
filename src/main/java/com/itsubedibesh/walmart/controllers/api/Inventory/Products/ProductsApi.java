package com.itsubedibesh.walmart.controllers.api.Inventory.Products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductsApi {
    @Autowired
    ProductsRepo productsRepo;

    @GetMapping("/products")
    public ResponseEntity<List<Products>> getAllProducts() {
        try {
            List<Products> products = new ArrayList<Products>();
            productsRepo.findAll().forEach(products::add);
            if (products.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

   @GetMapping("/productsOfVendor/{vendorName}")
    public ResponseEntity<List<Products>> getAllProductsOfVendor(@PathVariable() String vendorName) {
        try {
            List<Products> products = new ArrayList<Products>();
            productsRepo.findAllByVendorName(vendorName).forEach(products::add);
            if (products.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/vendors")
    public ResponseEntity<List<Products>> getAllVendors(){
        try{
            List<Products> vendors = new ArrayList<Products>();
            productsRepo.findAllGroupByVendorName().forEach(vendors::add);
            if (vendors.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(vendors, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

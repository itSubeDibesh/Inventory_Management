package com.itsubedibesh.walmart.controllers.api.Inventory.Products;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductsRepo extends JpaRepository<Products,Long> {
    @Query(value = "SELECT * FROM Products GROUP BY Vendor_Name", nativeQuery = true)
    List<Products> findAllGroupByVendorName();
    List<Products> findAllByVendorName(String vendorName);
}


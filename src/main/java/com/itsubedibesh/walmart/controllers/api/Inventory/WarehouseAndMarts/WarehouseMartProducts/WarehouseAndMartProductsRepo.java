package com.itsubedibesh.walmart.controllers.api.Inventory.WarehouseAndMarts.WarehouseMartProducts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WarehouseAndMartProductsRepo extends JpaRepository<WarehouseAndMartProducts, Long> {
    @Query(value = "SELECT * FROM warehouse_and_mart_products WHERE product_id=?1 AND wmid=?2", nativeQuery = true)
    WarehouseAndMartProducts findWarehouseAndMartProductsByProductIdAndWMId(long productId, Integer WMId);
}

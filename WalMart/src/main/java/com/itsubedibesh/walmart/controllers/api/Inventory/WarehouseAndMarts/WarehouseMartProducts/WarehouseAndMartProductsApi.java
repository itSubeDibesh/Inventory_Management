package com.itsubedibesh.walmart.controllers.api.Inventory.WarehouseAndMarts.WarehouseMartProducts;

import com.itsubedibesh.walmart.controllers.api.Inventory.Products.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class WarehouseAndMartProductsApi {
    @Autowired
    WarehouseAndMartProductsRepo wAPRepo;

    @GetMapping("/WarehouseAndMartProducts")
    public ResponseEntity<List<WarehouseAndMartProducts>> getAllWarehouseAndMartProducts(){
        try {
            List<WarehouseAndMartProducts> products = new ArrayList<WarehouseAndMartProducts>();
            wAPRepo.findAll().forEach(products::add);
            if (products.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/products/paginate")
    public List<WarehouseAndMartProducts> getPaginatedProducts(@RequestParam(value="pageNo", defaultValue="0")Integer pageNo, @RequestParam(value="pageSize", defaultValue="5")Integer pageSize){
        Pageable page = PageRequest.of(pageNo, pageSize);
        Page<WarehouseAndMartProducts> pagedResult = wAPRepo.findAll(page);
        return pagedResult.getContent();
    }
}

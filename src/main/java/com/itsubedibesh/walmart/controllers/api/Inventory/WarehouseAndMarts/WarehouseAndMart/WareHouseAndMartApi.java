package com.itsubedibesh.walmart.controllers.api.Inventory.WarehouseAndMarts.WarehouseAndMart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class WareHouseAndMartApi {

    @Autowired
    WarehouseAndMartRepo warehouseAndMartRepo;

    @GetMapping("/warehouseAndMart")
    public ResponseEntity<List<WareHouseAndMart>> getAllWareHouseAndMart(){
        try {
            List<WareHouseAndMart> inventories = new ArrayList<WareHouseAndMart>();
            warehouseAndMartRepo.findAll().forEach(inventories::add);
            if (inventories.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(inventories, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

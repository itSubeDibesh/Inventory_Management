package com.itsubedibesh.walmart.controllers.api.Inventory.DamageAndExpiry;

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
public class DamageAndExpiryApi {
    @Autowired
    DamageAndExpiryRepo damageAndExpiryRepo;

    @GetMapping("/damageAndExpires")
    public ResponseEntity<List<DamageAndExpiry>> getAllDamageAndExpiry(){
        try {
            List<DamageAndExpiry> items = new ArrayList<DamageAndExpiry>();
            damageAndExpiryRepo.findAll().forEach(items::add);
            if (items.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(items, HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

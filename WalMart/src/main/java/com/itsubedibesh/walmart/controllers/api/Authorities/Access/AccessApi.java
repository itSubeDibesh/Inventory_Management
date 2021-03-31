package com.itsubedibesh.walmart.controllers.api.Authorities.Access;

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
public class AccessApi {

    @Autowired
    AccessRepo accessRepo;

    @GetMapping("/access")
    public ResponseEntity<List<Access>> getAllAccess(){
        try {
            List<Access> accesses = new ArrayList<Access>();
            accessRepo.findAll().forEach(accesses::add);
            if (accesses.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(accesses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

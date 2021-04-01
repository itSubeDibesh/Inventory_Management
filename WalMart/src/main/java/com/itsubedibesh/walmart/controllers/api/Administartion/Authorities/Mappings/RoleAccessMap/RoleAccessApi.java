package com.itsubedibesh.walmart.controllers.api.Administartion.Authorities.Mappings.RoleAccessMap;

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
public class RoleAccessApi {
    @Autowired
    RoleAccessMappingRepo roleAccessMappingRepo;

    @GetMapping("/roleAccess")
    public ResponseEntity<List<RoleAccessMapping>> getRoleAccessMappings(){
        try {
            List<RoleAccessMapping> roleAccess = new ArrayList<RoleAccessMapping>();
            roleAccessMappingRepo.findAll().forEach(roleAccess::add);
            if (roleAccess.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(roleAccess, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

package com.itsubedibesh.walmart.controllers.api.Administartion.Authorities.Roles;

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
public class RolesApi {

    @Autowired
    RoleRepo roleRepo;

    @GetMapping("/roles")
    public ResponseEntity<List<Roles>> getAllRoles() {
        try {
            List<Roles> roles = new ArrayList<Roles>();
            roleRepo.findAll().forEach(roles::add);
            if (roles.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(roles, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

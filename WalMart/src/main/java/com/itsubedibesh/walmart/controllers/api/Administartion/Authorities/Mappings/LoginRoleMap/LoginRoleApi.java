package com.itsubedibesh.walmart.controllers.api.Administartion.Authorities.Mappings.LoginRoleMap;

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
public class LoginRoleApi {
    @Autowired
    LoginRoleMappingRepo loginRoleMappingRepo;

    @GetMapping("/loginRole")
    public ResponseEntity<List<LoginRoleMapping>> getLoginRolesMappings(){
        try {
            List<LoginRoleMapping> loginRoles = new ArrayList<LoginRoleMapping>();
            loginRoleMappingRepo.findAll().forEach(loginRoles::add);
            if (loginRoles.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(loginRoles, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

package com.itsubedibesh.walmart.controllers.api.Administartion.Users.Logins;

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
public class LoginApi {

    @Autowired
    LoginsRepo loginsRepo;

    @GetMapping("/logins")
    public ResponseEntity<List<Logins>> getAllLogins() {
        try {
            List<Logins> logins = new ArrayList<Logins>();
            loginsRepo.findAll().forEach(logins::add);
            if (logins.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(logins, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

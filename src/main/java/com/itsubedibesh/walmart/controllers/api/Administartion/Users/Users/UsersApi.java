package com.itsubedibesh.walmart.controllers.api.Administartion.Users.Users;

import com.itsubedibesh.walmart.controllers.api.Administartion.Users.Logins.Logins;
import com.itsubedibesh.walmart.controllers.api.Administartion.Users.Logins.LoginsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UsersApi {

    @Autowired
    UsersRepo usersRepo;

    @Autowired
    LoginsRepo loginsRepo;

    @GetMapping("/users")
    public ResponseEntity<List<Users>> getAllUsers() {
        try {
            List<Users> users = new ArrayList<Users>();
            usersRepo.findAll().forEach(users::add);
            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<Users> getUserById(@PathVariable long userId) {
        try {
            Optional<Users> userData = usersRepo.findById(userId);
            if (!userData.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(userData.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users/byLogin/{loginId}")
    public ResponseEntity<Users> getUserByLoginId(@PathVariable long loginId) {
        try {
            Optional<Logins> loginsData = loginsRepo.findById(loginId);
            Optional<Users> userLoggedInData = usersRepo.findByLoginId(loginsData.get());
            if (!userLoggedInData.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(userLoggedInData.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

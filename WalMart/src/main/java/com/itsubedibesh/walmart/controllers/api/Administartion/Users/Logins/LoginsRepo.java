package com.itsubedibesh.walmart.controllers.api.Administartion.Users.Logins;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginsRepo extends JpaRepository<Logins,Long> {
    Optional<Logins> findLoginsByUserNameAndPassword(String userName, String password);
}

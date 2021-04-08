package com.itsubedibesh.walmart.controllers.api.Administartion.Users.Users;

import com.itsubedibesh.walmart.controllers.api.Administartion.Users.Logins.Logins;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepo extends JpaRepository<Users,Long> {
    Optional<Users> findByLoginId(Logins loginId);
}

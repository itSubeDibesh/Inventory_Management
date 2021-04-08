package com.itsubedibesh.walmart.controllers.seeder;

import com.itsubedibesh.walmart.controllers.api.Administartion.Users.Logins.Logins;
import com.itsubedibesh.walmart.controllers.api.Administartion.Users.Logins.LoginsRepo;
import com.itsubedibesh.walmart.controllers.api.Administartion.Users.Users.GendersEnum;
import com.itsubedibesh.walmart.controllers.api.Administartion.Users.Users.Users;
import com.itsubedibesh.walmart.controllers.api.Administartion.Users.Users.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.Date;
import java.util.Optional;

@Component
public class LoginsSeeder implements CommandLineRunner {
    @Autowired
    LoginsRepo loginsRepo;

    @Autowired
    UsersRepo usersRepo;

    @Override
    public void run(String... args) throws Exception {
        SeedData();
    }

    private void SeedData() {
        if (loginsRepo.count() == 0)
        {
            Logins firstUser = new Logins("1234567890", "admin@admin.com", null, "admin", "password");
            loginsRepo.save(firstUser);
            Optional<Logins> savedUser = loginsRepo.findLoginsByUserNameAndPassword("admin","password");
            if (savedUser.isPresent()){
                if (usersRepo.count()==0) {
                    Date date = new Date();
                    Users firstLoggedUser = new Users(savedUser.get(), "Administrator", "986000000", "Server",date , GendersEnum.None, "0001");
                    usersRepo.save(firstLoggedUser);
                }
            }
        }
    }

}

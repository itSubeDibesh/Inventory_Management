package com.itsubedibesh.walmart.controllers.seeder;

import com.itsubedibesh.walmart.controllers.api.Administartion.Users.Logins.Logins;
import com.itsubedibesh.walmart.controllers.api.Administartion.Users.Logins.LoginsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class LoginsSeeder implements CommandLineRunner {
    @Autowired
    LoginsRepo loginsRepo;

    @Override
    public void run(String... args) throws Exception {
        SeedData();
    }

    private void SeedData() {
        if (loginsRepo.count() == 0)
            loginsRepo.save(new Logins("1234567890", "admin@admin.com", null, "admin", "password"));
    }

}

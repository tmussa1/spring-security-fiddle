package com.mc.imageanswer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner{
    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    AppRoleRepository appRoleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        AppRole aRole = new AppRole();
        aRole.setRole("ADMIN");
        appRoleRepository.save(aRole);

        aRole = new AppRole();
        aRole.setRole("USER");
        appRoleRepository.save(aRole);

        AppUser user = new AppUser();
        user.setPassword(passwordEncoder.encode("password"));
        user.setUsername("newuser");
        user.addRole(appRoleRepository.findByRole("ADMIN"));
        user.addRole(appRoleRepository.findByRole("USER"));
        appUserRepository.save(user);

        user = new AppUser();
        user.setPassword(passwordEncoder.encode("password"));
        user.setUsername("adminuser");
        user.addRole(appRoleRepository.findByRole("ADMIN"));
        appUserRepository.save(user);

        user = new AppUser();
        user.setPassword(passwordEncoder.encode("password"));
        user.setUsername("ordinaryuser");
        user.addRole(appRoleRepository.findByRole("USER"));
        appUserRepository.save(user);
    }
}

package ru.kata.spring.boot_security.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
public class DbInit {
    private final UserService userService;


    public DbInit(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void initDbUsers() {
        Role roleAdmin = new Role("ROLE_ADMIN");
        Role roleUser = new Role("ROLE_USER");

        User admin = new User();
        Set<Role> adminRoles = new HashSet<>();
        Collections.addAll(adminRoles, roleAdmin, roleUser);
        admin.setId(1L);
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setFirstName("Евгений");
        admin.setLastName("Казьмин");
        admin.setAge((byte) 30);
        admin.setRoles(adminRoles);
        userService.saveUser(admin);

        User user = new User();
        Set<Role> userRoles = new HashSet<>();
        Collections.addAll(userRoles, roleUser);
        user.setId(2L);
        user.setUsername("user");
        user.setPassword("user");
        user.setFirstName("Василий");
        user.setLastName("Обухов");
        user.setAge((byte) 20);
        user.setRoles(userRoles);
        userService.saveUser(user);
    }
}
package com.eventstoday.api.util;


import com.eventstoday.api.security.entity.Role;
import com.eventstoday.api.security.enums.RoleName;
import com.eventstoday.api.security.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CreateRoles implements CommandLineRunner {

    @Autowired
    RoleService roleService;

    @Override
    public void run(String... args) throws Exception{
        //SOLO DEBEMOS EJECUTARLO UNA SOLA VEZ
        /*Role roleAdmin = new Role(RoleName.ROLE_ADMIN);
        Role roleUser = new Role(RoleName.ROLE_USER);
        roleService.save(roleAdmin);
        roleService.save(roleUser);*/
    }
}

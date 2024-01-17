package com.security.basicauth;

import com.security.basicauth.dtos.CreateUserRequest;
import com.security.basicauth.models.Role;
import com.security.basicauth.services.UserManager;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Set;

@SpringBootApplication
@AllArgsConstructor
public class BasicAuthApplication implements CommandLineRunner {

    private final UserManager userManager;

    public static void main(String[] args) {
        SpringApplication.run(BasicAuthApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        createDummyData();
    }

    private void createDummyData() {
        CreateUserRequest createUserRequest = CreateUserRequest.builder()
                .name("Bedir")
                .username("bedir")
                .password("pass")
                .authorities(Set.of(Role.ROLE_USER))
                .build();
        userManager.createUser(createUserRequest);

        CreateUserRequest createUserRequest2 = CreateUserRequest.builder()
                .name("Bdr")
                .username("bdr")
                .password("pass")
                .authorities(Set.of(Role.ROLE_BDR))
                .build();
        userManager.createUser(createUserRequest2);


        CreateUserRequest createUserRequest3 = CreateUserRequest.builder()
                .name("No Name")
                .username("noname")
                .password("pass")
                .authorities(Set.of(Role.ROLE_ADMIN))
                .build();

        userManager.createUser(createUserRequest3);
    }

}

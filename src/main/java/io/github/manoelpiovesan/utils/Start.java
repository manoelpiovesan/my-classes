package io.github.manoelpiovesan.utils;

import io.github.manoelpiovesan.entities.User;
import io.github.manoelpiovesan.repositories.UserRepository;
import io.quarkus.runtime.Startup;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;

/**
 * @author Manoel Rodrigues
 */
@Startup
public class Start {
    @Inject
    UserRepository userRepository;

    @PostConstruct
    void init() {
        System.out.println("Application started =)");

        // Updating all users password to "string"
        for (User user : userRepository.listAll()) {
            user.password = "string";
            userRepository.update(user);
        }

    }
}

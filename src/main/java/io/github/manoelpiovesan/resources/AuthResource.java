package io.github.manoelpiovesan.resources;

import io.github.manoelpiovesan.utils.LoginCredentials;
import io.github.manoelpiovesan.entities.User;
import io.github.manoelpiovesan.repositories.TokenRepository;
import io.github.manoelpiovesan.repositories.UserRepository;
import io.github.manoelpiovesan.utils.MyException;
import io.github.manoelpiovesan.utils.Role;
import jakarta.annotation.security.PermitAll;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Map;

/**
 * @author Manoel Rodrigues
 */
@Path("/auth")
@RequestScoped
public class AuthResource {

    @Inject
    TokenRepository tokenRepository;

    @Inject
    UserRepository userRepository;

    /**
     * Login the user
     *
     * @param loginCredentials LoginCredentials
     * @return Response
     */
    @POST
    @Path("/login")
    @PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    public Map<String, Object> login(LoginCredentials loginCredentials) {
        return tokenRepository.generateAccessToken(loginCredentials);
    }

    /**
     * Create a new user
     *
     * @param user User
     * @return Response
     */
    @POST
    @Path("/register")
    @PermitAll
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User create(User user) {
        return userRepository.create(user, Role.TEACHER);
    }
}

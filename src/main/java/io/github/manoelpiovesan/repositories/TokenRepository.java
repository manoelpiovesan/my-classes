package io.github.manoelpiovesan.repositories;

import io.github.manoelpiovesan.utils.LoginCredentials;
import io.github.manoelpiovesan.entities.User;
import io.github.manoelpiovesan.utils.MyException;
import io.github.manoelpiovesan.utils.TokenUtils;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import net.bytebuddy.utility.RandomString;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.jwt.Claims;
import org.jose4j.jwt.JwtClaims;

import java.util.List;
import java.util.Map;

/**
 * @author Manoel Rodrigues
 */
@RequestScoped
public class TokenRepository {
    @Inject
    UserRepository userRepository;

    @ConfigProperty(name = "mp.jwt.verify.issuer")
    String issuer;


    /**
     * Generate an access token for the user
     *
     * @param loginCredentials LoginCredentials
     * @return String
     */
    public Map<String, Object> generateAccessToken(LoginCredentials loginCredentials) {

        User user = userRepository.checkPassword(loginCredentials);

        if (user == null) {
            throw MyException.unauthorized("Invalid credentials");
        }

        try {
            String token = TokenUtils.generateTokenString(getUserClaims(user));

            System.out.println("[JWT] Generating token for user: " + user.username);

            return Map.of("token", token, "user", user);
        } catch (Exception e) {

            System.out.println("[JWT] Error generating token: " + e.getMessage());
            throw MyException.internalServerError("Error generating token");
        }

    }

    /**
     * Get all the user claims
     *
     * @param user User
     * @return JwtClaims
     */
    private JwtClaims getUserClaims(User user) {
        JwtClaims jwtClaims = new JwtClaims();
        jwtClaims.setIssuer(issuer);
        jwtClaims.setJwtId(RandomString.make(8));
        jwtClaims.setSubject(user.id.toString());
        jwtClaims.setAudience("quarkus-jwt");
        jwtClaims.setExpirationTimeMinutesInTheFuture(30);

        /*
          Set the user claims
         */
        jwtClaims.setClaim("firstName", user.firstName);
        jwtClaims.setClaim("lastName", user.lastName);
        jwtClaims.setClaim("username", user.username);
        jwtClaims.setClaim("email", user.email);
        jwtClaims.setClaim("user_id", user.id);
        jwtClaims.setClaim(Claims.groups.name(), List.of(user.role));


        return jwtClaims;
    }


}
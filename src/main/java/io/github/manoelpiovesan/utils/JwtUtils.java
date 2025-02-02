package io.github.manoelpiovesan.utils;

import org.eclipse.microprofile.jwt.JsonWebToken;

/**
 * @author Manoel Rodrigues
 */
public class JwtUtils {

    /**
     * Get the user id from the token
     *
     * @param jwt JsonWebToken
     * @return Long
     */
    public static Long userId(JsonWebToken jwt) {
        return Long.parseLong(jwt.getClaim("user_id").toString());
    }

    /**
     * Get the user first name from the token
     *
     * @param jwt JsonWebToken
     * @return String
     */
    public static String firstName(JsonWebToken jwt) {
        return jwt.getClaim("firstName");
    }

    /**
     * Get the user last name from the token
     *
     * @param jwt JsonWebToken
     * @return String
     */
    public static String lastName(JsonWebToken jwt) {
        return jwt.getClaim("lastName");
    }

    /**
     * Get the user email from the token
     *
     * @param jwt JsonWebToken
     * @return String
     */
    public static String email(JsonWebToken jwt) {
        return jwt.getClaim("email");
    }

}

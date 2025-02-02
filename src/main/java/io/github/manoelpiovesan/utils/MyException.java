package io.github.manoelpiovesan.utils;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

/**
 * @author Manoel Rodrigues
 */
public class MyException extends WebApplicationException {
    // 400 Bad Request
    public static MyException badRequest(String message) {
        return new MyException(message, 400);
    }

    // 401 Unauthorized
    public static MyException unauthorized(String message) {
        return new MyException(message, 401);
    }

    // 403 Forbidden
    public static MyException forbidden(String message) {
        return new MyException(message, 403);
    }

    // 404 Not Found
    public static MyException notFound(String message) {
        return new MyException(message, 404);
    }

    // 409 Conflict
    public static MyException conflict(String message) {
        return new MyException(message, 409);
    }

    // 500 Internal Server Error
    public static MyException internalServerError(String message) {
        return new MyException(message, 500);
    }

    public MyException(String message, int status) {
        super(message, Response.status(status).entity(message).build());
    }

    public MyException(String message, int status, Throwable cause) {
        super(message, cause, Response.status(status).entity(message).build());
    }
}

package io.github.manoelpiovesan.resources;

import io.github.manoelpiovesan.entities.Course;
import io.github.manoelpiovesan.repositories.CourseRepository;
import io.github.manoelpiovesan.utils.Role;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.jwt.JsonWebToken;

import java.util.List;

/**
 * @author Manoel Rodrigues
 */
@Path("/c")
@RequestScoped
public class CourseResource {

    @Inject
    JsonWebToken jwt;

    @Inject
    CourseRepository repository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed(Role.TEACHER)
    public List<Course> getCourses(

            @QueryParam("term") String term,
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("15") int size
    ) {
        return repository.list(term, page, size, userId());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed(Role.TEACHER)
    public Course createCourse(Course course) {
        return repository.create(course, userId());
    }


    /**
     * Get the user id from the token
     *
     * @return Long
     */
    private Long userId() {
        return Long.parseLong(jwt.getClaim("user_id").toString());
    }
}

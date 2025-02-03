package io.github.manoelpiovesan.resources;

import io.github.manoelpiovesan.entities.Classroom;
import io.github.manoelpiovesan.repositories.ClassroomRepository;
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
@RequestScoped
@Path("/courses/{courseId}/classrooms")
public class ClassroomResource {
    @Inject
    JsonWebToken jwt;

    @Inject
    ClassroomRepository repository;

    /**
     * Get the classrooms from a course
     *
     * @param courseId Long
     * @param term     String
     * @param page     int
     * @param size     int
     * @return List<Classroom>
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed(Role.TEACHER)
    public List<Classroom> getClassrooms(
            @PathParam("courseId") Long courseId,
            @QueryParam("t") String term,
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("15") int size
    ) {
        return repository.getAll(term, courseId, page, size);
    }

    /**
     * Create a new classroom
     *
     * @param courseId  Long
     * @param classroom Classroom
     * @return Classroom
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed(Role.TEACHER)
    public Classroom createClassroom(@PathParam("courseId") Long courseId, Classroom classroom) {
        return repository.create(classroom, courseId, List.of());
    }


}
